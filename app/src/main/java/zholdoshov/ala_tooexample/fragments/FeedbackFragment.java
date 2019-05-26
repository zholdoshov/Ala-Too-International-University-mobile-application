package zholdoshov.ala_tooexample.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import zholdoshov.ala_tooexample.R;

public class FeedbackFragment extends Fragment implements View.OnClickListener {

    EditText subject;
    EditText message;

    Button send;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);

        subject = v.findViewById(R.id.subject);
        message = v.findViewById(R.id.your_message);
        send = v.findViewById(R.id.send);

        send.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        String toS = "nur.joldoshov@gmail.com";
        String subS = subject.getText().toString();
        String mesS = message.getText().toString();

        if (subS.isEmpty() || mesS.isEmpty()) {
            Toast toast = Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "Empty blanks!", Toast.LENGTH_SHORT);
            toast.show();
        } else {

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{toS});
            email.putExtra(Intent.EXTRA_SUBJECT, subS);
            email.putExtra(Intent.EXTRA_TEXT, mesS);

            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Choose app to send mail"));

        }
    }
}
