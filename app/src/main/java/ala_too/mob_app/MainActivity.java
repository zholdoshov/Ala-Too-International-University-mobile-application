package ala_too.mob_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import ala_too.mob_app.Activities.LoginActivity;
import ala_too.mob_app.fragments.AboutStudentFragment;
import ala_too.mob_app.fragments.ContactsFragment;
import ala_too.mob_app.fragments.FeedbackFragment;
import ala_too.mob_app.fragments.HomeFragment;
import ala_too.mob_app.fragments.SocialMediaFragment;
import ala_too.mob_app.fragments.TranscriptFragment;
import ala_too.mob_app.model.StudentInfo;
import ala_too.mob_app.remote.IAUApi;
import ala_too.mob_app.utils.PreferenceUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView name;
    TextView department;

    private StudentInfo studentInfo;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {
            setContentView(R.layout.activity_main);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        name = navigationView.getHeaderView(0).findViewById(R.id.name);
        department = navigationView.getHeaderView(0).findViewById(R.id.department);

        showHome();
        doGetInfo();
    }

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

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c, R.style.Theme_AppCompat_DayNight_Dialog);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }

    //Shows news fragment when you open the application
    private void showHome(){
        fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainLayout, fragment, fragment.getTag()).commit();
    }

    private void doGetInfo() {
        IAUApi.getInstance(this).getStudentInfo(new IAUApi.OnStudentInfo() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onStudentInfo(StudentInfo studentInfo) {
                MainActivity.this.studentInfo = studentInfo;
                name.setText(studentInfo.getName() + " " + studentInfo.getSurname());
                department.setText(studentInfo.getDepartment());
            }

            @Override
            public void onStudentInfoError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        /*}if (fragment instanceof HomeFragment){
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);*/
        }else {
          showHome();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_log_out) {
            PreferenceUtils.deleteIdPassword(this);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    Fragment fragment;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
                showHome();
                break;
            case R.id.navigation_about_student:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, new AboutStudentFragment()).commit();
                break;
            case R.id.navigation_transcript:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, new TranscriptFragment()).commit();
                break;
            case R.id.navigation_feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, new FeedbackFragment()).commit();
                break;
            case R.id.navigation_social_media:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, new SocialMediaFragment()).commit();
                break;
            case R.id.navigation_contacts:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, new ContactsFragment()).commit();
                break;
            case R.id.navigation_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog);

                builder.setTitle("Thank You!");
                builder.setMessage("Thank You For Using Our Application Please Give Us Your Suggestions and Feedback")
                        .setCancelable(true)
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.super.onBackPressed();
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
