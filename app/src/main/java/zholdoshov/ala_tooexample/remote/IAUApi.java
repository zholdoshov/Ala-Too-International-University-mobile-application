package zholdoshov.ala_tooexample.remote;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zholdoshov.ala_tooexample.model.Mark;
import zholdoshov.ala_tooexample.model.StudentInfo;
import zholdoshov.ala_tooexample.model.Transcript;
import zholdoshov.ala_tooexample.utils.Constants;
import zholdoshov.ala_tooexample.utils.PreferenceUtils;

public class IAUApi {

    public interface OnStudentInfo {
        public void onStudentInfo(StudentInfo studentInfo);

        public void onStudentInfoError(String message);
    }

    public interface OnAllMark {
        public void onAllMark(Mark mark);

        public void onAllMarkError(String message);

    }

    public interface OnTranscript {
        public void onTranscript(Transcript studentInfo);

        public void onTranscriptError(String message);
    }

    private static IAUApi instance;
    private UserService userService;
    private Context context;

    public IAUApi(Context context) {
        userService = ApiUtils.getUserService();
        this.context = context;
    }

    public static IAUApi getInstance(Context context) {
        if (instance == null)
            instance = new IAUApi(context);

        return instance;
    }

    public void getStudentInfo(final OnStudentInfo callback) {
        String token = PreferenceUtils.getValue(context, Constants.KEY_TOKEN);
        Call<StudentInfo> call = userService.getInfo(token);
        call.enqueue(new Callback<StudentInfo>() {
            @Override
            public void onResponse(Call<StudentInfo> call, Response<StudentInfo> response) {
                if (response.isSuccessful()) callback.onStudentInfo(response.body());
                else callback.onStudentInfoError(response.message());
            }

            @Override
            public void onFailure(Call<StudentInfo> call, Throwable t) {
                callback.onStudentInfoError(t.getMessage());
            }
        });
    }

    public void getSubject(final OnAllMark callback) {
        String token = PreferenceUtils.getValue(context, Constants.KEY_TOKEN);
        Call<Mark> call = userService.getSubject(token);
        call.enqueue(new Callback<Mark>() {
            @Override
            public void onResponse(Call<Mark> call, Response<Mark> response) {
                if (response.isSuccessful()) callback.onAllMark(response.body());
                else callback.onAllMarkError(response.message());
            }

            @Override
            public void onFailure(Call<Mark> call, Throwable t) {
                callback.onAllMarkError(t.getMessage());
            }
        });
    }

    public void getTranscript(final OnTranscript callback) {
        String token = PreferenceUtils.getValue(context, Constants.KEY_TOKEN);
        Call<Transcript> call = userService.getTranscript(token);
        call.enqueue(new Callback<Transcript>() {
            @Override
            public void onResponse(Call<Transcript> call, Response<Transcript> response) {
                if (response.isSuccessful()) callback.onTranscript(response.body());
                else callback.onTranscriptError(response.message());
            }

            @Override
            public void onFailure(Call<Transcript> call, Throwable t) {
                callback.onTranscriptError(t.getMessage());
            }
        });
    }
}
