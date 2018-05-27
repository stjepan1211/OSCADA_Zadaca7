package ada.osc.myfirstweatherapp.ui.addLocation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.presentation.location.AddLocationFragmentPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Filip on 10/02/2016.
 */
public class AddLocationFragment extends Fragment implements AddLocationFragmentContract.View{

    @BindView(R.id.fragment_add_location_enter_city_edit_text) EditText mEnterLocationNameEditText;
    @BindView(R.id.fragment_add_location_button) Button mAddLocationButton;

    private AddLocationFragmentContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter = new AddLocationFragmentPresenter();
        mPresenter.setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.location_added_success_toast_message), Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void onLocationAlreadyExistsError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.location_already_exists_error_message));
    }

    @Override
    public void onEmptyStringRequestError() {
        mEnterLocationNameEditText.setError(getActivity().getString(R.string.empty_location_string_error_message));
    }

    @Override
    @OnClick({R.id.fragment_add_location_button})
    public void onAddLocationClick() {

    }
}
