package ada.osc.myfirstweatherapp.ui.addLocation;

public interface AddLocationContract {

    interface View {

        void initFragment();

        void initToolbar();
    }

    interface Presenter {

        void setView(View addLocationView);

    }

}
