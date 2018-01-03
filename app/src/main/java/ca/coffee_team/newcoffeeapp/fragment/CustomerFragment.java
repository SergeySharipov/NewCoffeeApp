package ca.coffee_team.newcoffeeapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.model.Customer;
import ca.coffee_team.newcoffeeapp.model.ModelObject;

public class CustomerFragment extends StandardItemFragment {
    public static final String TITLE = "Customer";
    private TextView mIdView;
    private TextView mContentView;
    private Customer mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CustomerFragment() {
    }

    public static CustomerFragment newInstance(String id) {
        CustomerFragment fragment = new CustomerFragment();
        Bundle args = new Bundle();
        args.putString(OBJECT_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        mIdView = view.findViewById(R.id.id);
        mContentView = view.findViewById(R.id.content);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mServerAPIHelper.getCustomer(getObjectId());
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void updateItem(ModelObject item) {
        mItem= (Customer) item;
        mIdView.setText("Business name: "+mItem.getBusinessName());
        mContentView.setText("Address: "+mItem.getAddress());
    }
}
