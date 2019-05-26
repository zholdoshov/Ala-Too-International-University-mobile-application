package zholdoshov.ala_tooexample.remote;

public class ApiUtils {

    private static final String BASE_URL = "https://ams.iaau.edu.kg/api/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}
