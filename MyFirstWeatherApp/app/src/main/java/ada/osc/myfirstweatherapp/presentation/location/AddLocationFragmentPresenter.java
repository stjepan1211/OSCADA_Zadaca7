package ada.osc.myfirstweatherapp.presentation.location;

import ada.osc.myfirstweatherapp.ui.addLocation.fragments.AddLocationFragmentContract;

public class AddLocationFragmentPresenter implements AddLocationFragmentContract.Presenter{

    private AddLocationFragmentContract.View mLocationView;


    @Override
    public void setView(AddLocationFragmentContract.View addLocationView) {
        this.mLocationView = addLocationView;
    }

    @Override
    public void postLocation(String appId, String location) {
        //TODO call post method on api service i suppose
    }
}
