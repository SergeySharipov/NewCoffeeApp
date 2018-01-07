package ca.coffee_team.newcoffeeapp.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.api.ItemResponseCallback;
import ca.coffee_team.newcoffeeapp.api.ServerAPIHelper;
import ca.coffee_team.newcoffeeapp.model.Customer;
import ca.coffee_team.newcoffeeapp.model.ModelObject;

public class AddEditCustomerDialog extends DialogFragment implements View.OnClickListener,
        ItemResponseCallback {
    public static final String TITLE = "Add customer";
    public static final String CUSTOMER_ID = "ca.coffee_team.newcoffeeapp.CUSTOMER_ID";
    private EditText mBusinessName;
    private EditText mAddress;
    private EditText mTelephone;
    private EditText mEmail;
    private EditText mContactPerson;
    private Customer mItem;
    private String customerId;
    private ServerAPIHelper mServerAPIHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            customerId = bundle.getString(CUSTOMER_ID);
        }
        mServerAPIHelper = new ServerAPIHelper();
        mServerAPIHelper.setItemResponseCallback(this);

        mItem = new Customer();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_edit_customer, null);

        ((TextView) view.findViewById(R.id.title)).setText(TITLE);

        view.findViewById(R.id.cancel_but).setOnClickListener(this);
        view.findViewById(R.id.ok_but).setOnClickListener(this);

        mBusinessName = view.findViewById(R.id.business_name_et);
        mAddress = view.findViewById(R.id.address_et);
        mTelephone = view.findViewById(R.id.telephone_et);
        mEmail = view.findViewById(R.id.email_et);
        mContactPerson = view.findViewById(R.id.contact_person_et);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (customerId != null)
            mServerAPIHelper.getCustomer(customerId);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_but:
                try {
                    mItem.setBusinessName(mBusinessName.getText().toString());
                    mItem.setAddress(mAddress.getText().toString());
                    mItem.setTelephone(mTelephone.getText().toString());
                    mItem.setEmail(mEmail.getText().toString());
                    mItem.setContactPerson(mContactPerson.getText().toString());

                    mServerAPIHelper.addCustomer(mItem);
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            case R.id.cancel_but:
            default:
                dismiss();
        }

    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        if(window!=null) {
            ViewGroup.LayoutParams params = window.getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        }
        super.onResume();
    }

    @Override
    public void updateItem(ModelObject item) {
        mItem = (Customer) item;
        mBusinessName.setText(mItem.getBusinessName());
        mAddress.setText(mItem.getAddress());
        mTelephone.setText(mItem.getTelephone());
        mEmail.setText(mItem.getEmail());
        mContactPerson.setText(mItem.getContactPerson());
    }
}
