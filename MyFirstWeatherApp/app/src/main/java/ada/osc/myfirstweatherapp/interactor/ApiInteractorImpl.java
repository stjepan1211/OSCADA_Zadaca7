package ada.osc.myfirstweatherapp.interactor;

import ada.osc.myfirstweatherapp.Constants;
import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.networking.ApiService;
import retrofit2.Callback;

public class ApiInteractorImpl implements ApiInteractor {

    private final ApiService apiService;

    public ApiInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getWeather(Callback<WeatherResponse> callback, String appId, String cityToDisplay) {
        apiService.getWeather(appId, cityToDisplay).enqueue(callback);
    }
}
