package ca.coffee_team.newcoffeeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.coffee_team.newcoffeeapp.R;

public class ProductFragment extends StandardFragment {
    private static final String TITLE = "Product";
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public ProductFragment() {} // Required empty public constructor

    public static ProductFragment newInstance(boolean backButtonStatus) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putBoolean(BACK_BUTTON_STATUS, backButtonStatus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
}
