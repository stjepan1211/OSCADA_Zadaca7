package ada.osc.myfirstweatherapp.presentation.location;

import ada.osc.myfirstweatherapp.ui.addLocation.AddLocationContract;

public class AddLocationActivityPresenter implements AddLocationContract.Presenter{

    private AddLocationContract.View mLocationView;

    @Override
    public void setView(AddLocationContract.View addLocationView) {
        this.mLocationView = addLocationView;
    }
}
