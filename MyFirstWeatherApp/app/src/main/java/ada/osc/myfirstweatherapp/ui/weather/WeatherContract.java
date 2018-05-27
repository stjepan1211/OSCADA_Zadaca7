package ada.osc.myfirstweatherapp.ui.weather;

public interface WeatherContract {

    interface View {

        void createWeatherIconValue(String description);

        void handleItemSelectedClick(int itemId);

        void setWeatherIcon(String iconPath);

        double toCelsiusFromKelvin(double temperature);

        void initToolbar();

        void initNavigationDrawer();

        void initViewPager();
    }

    interface Presenter {

        void setView(View weatherView);
    }
}
