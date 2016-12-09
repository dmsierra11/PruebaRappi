package example.danielsierraf.pruebarappi.api;

import example.danielsierraf.pruebarappi.utils.AppConstant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VCHI on 10/5/16.
 */
public class RestClientPublic {

    private String BASE_URL;
    private PublicService publicService;
    private APIService apiService;

    public RestClientPublic(){
        this.BASE_URL = AppConstant.apps_link;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiService = retrofit.create(APIService.class);
        this.publicService = new PublicService(getApiService());
    }

    private APIService getApiService() {
        return this.apiService;
    }

    public PublicService getPublicService() {
        return this.publicService;
    }
}

