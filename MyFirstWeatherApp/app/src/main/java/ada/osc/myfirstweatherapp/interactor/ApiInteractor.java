package ada.osc.myfirstweatherapp.interactor;

import ada.osc.myfirstweatherapp.model.WeatherResponse;
import retrofit2.Callback;

public interface ApiInteractor {

    void getWeather(Callback<WeatherResponse> callback, String appId, String cityToDisplay);
}