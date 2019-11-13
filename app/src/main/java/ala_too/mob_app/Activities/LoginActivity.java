package ala_too.mob_app.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import ala_too.mob_app.MainActivity;
import ala_too.mob_app.R;
import ala_too.mob_app.model.ResObj;
import ala_too.mob_app.remote.ApiUtils;
import ala_too.mob_app.remote.UserService;
import ala_too.mob_app.utils.Constants;
import ala_too.mob_app.utils.PreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    Button guestLogin;

    Toast backtoast;

    Button btLogin;

    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!isConnected(LoginActivity.this)) buildDialog(LoginActivity.this).show();
        else {
            setContentView(R.layout.activity_login);
        }

        userService = ApiUtils.getUserService();

        String password = PreferenceUtils.getPassword(this);
        String id = PreferenceUtils.getId(this);
        Log.e("onCreate PASSWORD", password + "");
        Log.e("onCreate ID", id + "");
        if (password != null && id != null) {
            Toast.makeText(this, "Using Saved Values", Toast.LENGTH_SHORT).show();
            doLogin(id, password);
        } else {
            Toast.makeText(this, "Login please", Toast.LENGTH_SHORT).show();
            initViews();
            btLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = Objects.requireNonNull(textInputEditTextEmail.getText()).toString();
                    String originalStr = Objects.requireNonNull(textInputEditTextPassword.getText()).toString();
                    MessageDigest digest = null;
                    try {
                        digest = MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    byte[] encodedhashPassword = Objects.requireNonNull(digest).digest(
                            originalStr.getBytes(StandardCharsets.UTF_8));
                    String hash = bytesToHex(encodedhashPassword);
                    if (validateLogin(username, hash)) {
                        doLogin(username, hash);
                    }
                }
            });
        }
    }

    //Checks is there is internet connection or no!
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
        } else
            return false;
    }

    //Shows "No Internet Connection" Dialog
    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c,R.style.Theme_AppCompat_DayNight_Dialog);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");
        String s = "Ok";

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }

    @SuppressLint("SetTextI18n")
    private void initViews() {
        TextInputLayout textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutId);
        TextInputLayout textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        guestLogin = findViewById(R.id.loginGuest);
        SpannableString content = new SpannableString("Continue");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        guestLogin.setText(content);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextId);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        btLogin = findViewById(R.id.log_in_button);
    }


    //bytes to hex
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte hash1 : hash) {
            String hex = Integer.toHexString(0xff & hash1);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public boolean validateLogin(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Username is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String Id, final String password) {
        Call<ResObj> call = userService.login(Id, password);
        PreferenceUtils.saveId(Id, this);
        PreferenceUtils.savePassword(password, this);

        Log.e("doLogin PASSWORD", PreferenceUtils.getPassword(this) + "");
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(@NonNull Call<ResObj> call, @NonNull Response<ResObj> response) {
                if (response.isSuccessful()) {

                    if (response.code() == 200) {
                        assert response.body() != null;
                        PreferenceUtils.saveValue(LoginActivity.this, Constants.KEY_TOKEN, response.body().getAuthToken());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(LoginActivity.this, "The Username or Password incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Error! Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResObj> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backtoast != null && backtoast.getView().getWindowToken() != null) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        } else {
            backtoast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
            backtoast.show();
        }
    }

    public void guestClick(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(LoginActivity.this, "Logon Success", Toast.LENGTH_SHORT).show();
    }
}
