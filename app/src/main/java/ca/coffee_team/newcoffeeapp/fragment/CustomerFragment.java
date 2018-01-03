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
    private TextView mBusinessName;
    private TextView mAddress;
    private TextView mTelephone;
    private TextView mEmail;
    private TextView mContactPerson;
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
        mBusinessName = view.findViewById(R.id.business_name);
        mAddress = view.findViewById(R.id.address);
        mTelephone = view.findViewById(R.id.telephone);
        mEmail = view.findViewById(R.id.email);
        mContactPerson = view.findViewById(R.id.contact_person);
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
        mBusinessName.setText(mItem.getBusinessName());
        mAddress.setText(mItem.getAddress());
        mTelephone.setText(mItem.getTelephone());
        mEmail.setText(mItem.getEmail());
        mContactPerson.setText(mItem.getContactPerson());
    }
}
