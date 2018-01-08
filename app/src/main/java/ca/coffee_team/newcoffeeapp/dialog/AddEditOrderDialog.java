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

import java.util.List;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.api.ItemResponseCallback;
import ca.coffee_team.newcoffeeapp.api.ItemsResponseCallback;
import ca.coffee_team.newcoffeeapp.api.ServerAPIHelper;
import ca.coffee_team.newcoffeeapp.model.Customer;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Order;

public class AddEditOrderDialog extends DialogFragment implements View.OnClickListener,
        ItemResponseCallback, ItemsResponseCallback {
    public static final String TITLE = "Add order";
    public static final String CUSTOMER_ID = "ca.coffee_team.newcoffeeapp.CUSTOMER_ID";
    public static final String ORDER_ID = "ca.coffee_team.newcoffeeapp.ORDER_ID";
    private EditText mAmount;
    private EditText mIsPaid;
    private Order mItem;
    private String mCustomerId;
    private String mOrderId;
    private ServerAPIHelper mServerAPIHelper;
    private List<Customer> mListCustomers;

    public static AddEditOrderDialog newInstance(String orderId) {
        AddEditOrderDialog addEditOrderDialog = new AddEditOrderDialog();
        Bundle args = new Bundle();
        args.putString(ORDER_ID, orderId);
        addEditOrderDialog.setArguments(args);
        return addEditOrderDialog;
    }

    public static AddEditOrderDialog newInstanceFor(String customerId) {
        AddEditOrderDialog addEditOrderDialog = new AddEditOrderDialog();
        Bundle args = new Bundle();
        args.putString(CUSTOMER_ID, customerId);
        addEditOrderDialog.setArguments(args);
        return addEditOrderDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCustomerId = bundle.getString(CUSTOMER_ID);
            mOrderId = bundle.getString(ORDER_ID);
        }
        mServerAPIHelper = new ServerAPIHelper();
        mServerAPIHelper.setItemResponseCallback(this);

        mItem = new Order();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_edit_order, null);

        ((TextView) view.findViewById(R.id.title)).setText(TITLE);

        view.findViewById(R.id.cancel_but).setOnClickListener(this);
        view.findViewById(R.id.ok_but).setOnClickListener(this);

        mAmount = view.findViewById(R.id.amount_et);
        mIsPaid = view.findViewById(R.id.is_paid_et);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mServerAPIHelper.getCustomers();

        if (mOrderId != null)
            mServerAPIHelper.getOrder(mOrderId);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_but:
                try {
                    mItem.setAmount(Double.parseDouble(mAmount.getText().toString()));
                    mItem.setPaid(Boolean.parseBoolean(mIsPaid.getText().toString()));

                    if(mItem.getId()!=null) {
                        mServerAPIHelper.updateOrder(mItem.getId(), mItem);
                        Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        mServerAPIHelper.addOrder(mItem);
                        Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
                    }
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
        if (item instanceof Order) {
            mItem = (Order) item;
            mAmount.setText(mItem.getAmount() + "");
            mIsPaid.setText(mItem.isPaid() + "");

            //todo   if(mCustomerId!=null)

        }
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public void updateListItems(List<? extends ModelObject> list) {
        if (list != null && list.get(0) instanceof Customer) {
            mListCustomers = (List<Customer>) list;
        }
    }
}
