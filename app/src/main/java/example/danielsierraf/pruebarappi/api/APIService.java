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
//    @POST("weather")
//    Call<Rain> getRain(@Query("q") String q, @Query("APPID") String APPID);
//
//    @POST("weather")
//    Call<WeatherInfo> getWeatherInfo(@Query("q") String q, @Query("APPID") String APPID);
}
