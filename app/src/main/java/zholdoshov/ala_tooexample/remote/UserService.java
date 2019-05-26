package zholdoshov.ala_tooexample.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import zholdoshov.ala_tooexample.model.Mark;
import zholdoshov.ala_tooexample.model.ResObj;
import zholdoshov.ala_tooexample.model.StudentInfo;
import zholdoshov.ala_tooexample.model.Transcript;

public interface UserService {

    @POST("authentication/{Id}/{Password}")
    Call<ResObj> login(@Path("Id") String Id, @Path("Password") String password);

    @GET("v1/studentinfo")
    Call<StudentInfo> getInfo(@Header("Bearer") String Bearer);

    @GET("v1/marks/all")
    Call<Transcript> getTranscript(@Header("Bearer") String Bearer);

    @GET("v1/marks/all")
    Call<Mark> getSubject(@Header("Bearer") String Bearer);
}
