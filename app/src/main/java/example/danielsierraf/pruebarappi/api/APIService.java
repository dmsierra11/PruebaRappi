package example.danielsierraf.pruebarappi.api;

import example.danielsierraf.pruebarappi.api.classes.Response_;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by VCHI on 10/5/16.
 */
public interface APIService {

    @POST("json")
    Call<Response_> getResponse();
}
