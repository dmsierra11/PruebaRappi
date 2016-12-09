package example.danielsierraf.pruebarappi.api;

import example.danielsierraf.pruebarappi.api.classes.Response_;
import retrofit2.Call;

/**
 * Created by VCHI on 10/5/16.
 */
public class PublicService {
    private APIService apiService;

    public PublicService(APIService apiService) {
        this.apiService = apiService;
    }

    public Call<Response_> getResponse(){
        return apiService.getResponse();
    }

}
