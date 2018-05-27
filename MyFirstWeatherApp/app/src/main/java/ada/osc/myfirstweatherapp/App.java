package ada.osc.myfirstweatherapp;

import android.app.Application;

import ada.osc.myfirstweatherapp.interactor.ApiInteractor;
import ada.osc.myfirstweatherapp.interactor.ApiInteractorImpl;
import ada.osc.myfirstweatherapp.networking.ApiService;
import ada.osc.myfirstweatherapp.networking.RetrofitUtil;
import retrofit2.Retrofit;

public class App extends Application {

    private static ApiInteractor apiInteractor;

    @Override
    public void onCreate() {
        super.onCreate();


        final Retrofit retrofit = RetrofitUtil.createRetrofit();
        final ApiService apiService = retrofit.create(ApiService.class);

        apiInteractor = new ApiInteractorImpl(apiService);

    }

    public static ApiInteractor getApiInteractor() {
        return apiInteractor;
    }

}
