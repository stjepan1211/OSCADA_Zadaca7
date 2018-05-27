package ada.osc.myfirstweatherapp.ui.addLocation.fragments;

public interface AddLocationFragmentContract {

    interface View {

        void onSuccess();

        void onLocationAlreadyExistsError();

        void onEmptyStringRequestError();

        void onAddLocationClick();
    }

    interface Presenter {

        void setView(View addLocationView);

        void postLocation(String appId, String location);

    }
}
