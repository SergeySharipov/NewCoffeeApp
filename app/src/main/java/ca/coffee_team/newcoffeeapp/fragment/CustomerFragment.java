package ca.coffee_team.newcoffeeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.coffee_team.newcoffeeapp.R;

public class CustomerFragment extends StandardFragment {
    private static final String TITLE = "Customer";
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public CustomerFragment() {
    } // Required empty public constructor

//    public static CustomerFragment newInstance(boolean backButtonStatus) {
//        CustomerFragment fragment = new CustomerFragment();
//        Bundle args = new Bundle();
//        args.putBoolean(BACK_BUTTON_STATUS, backButtonStatus);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_customer, container, false);
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

}
