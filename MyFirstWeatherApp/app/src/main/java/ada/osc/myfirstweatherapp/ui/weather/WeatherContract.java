package ada.osc.myfirstweatherapp.ui.weather;

import ada.osc.myfirstweatherapp.model.WeatherResponse;

public class WeatherContract {

    public interface View {

        void showWeather(WeatherResponse data);

        void setCurrentTemperatureValues(double temperatureValues);

        void setMinTemperatureValues(double minTemperatureValues);

        void setMaxTemperatureValues(double maxTemperatureValues);

        void setPressureValues(double pressureValues);

        void setWindValues(double windValues);

        void setWeatherIcon(String iconPath);

        void setDescriptionValues(String descriptionValues);

        void refreshCurrentData();

        void toastMessage(String message);

        void checkIsConnected();
    }

    public interface Presenter {

        void setView(View weatherView);

        void getWeather(String appId, String cityToDisplay);

    }
}
