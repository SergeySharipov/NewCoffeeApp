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
import ca.coffee_team.newcoffeeapp.model.Order;
import ca.coffee_team.newcoffeeapp.model.Product;

public class OrderFragment extends StandardItemFragment {
    public static final String TITLE = "Order";
    private TextView mAmount;
    private TextView mIsPaid;
    private TextView mDateCreated;
    private Order mItem;

    private TextView mProductName;
    private TextView mPrice;

    private TextView mBusinessName;
    private TextView mAddress;
    private TextView mTelephone;
    private TextView mEmail;
    private TextView mContactPerson;

    public OrderFragment() {} // Required empty public constructor

    public static OrderFragment newInstance(String id) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(OBJECT_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        view.findViewById(R.id.delete_order_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mItem.getId()!=null) {
                    mServerAPIHelper.deleteOrder(mItem.getId());
                    mOnItemClickListener.onDeleteClick();
                    Toast.makeText(getContext(),"Order deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAmount = view.findViewById(R.id.amount);
        mIsPaid = view.findViewById(R.id.isPaid);
        mDateCreated = view.findViewById(R.id.data_created);

        mProductName = view.findViewById(R.id.product_name);
        mPrice = view.findViewById(R.id.price);

        mBusinessName = view.findViewById(R.id.business_name);
        mAddress = view.findViewById(R.id.address);
        mTelephone = view.findViewById(R.id.telephone);
        mEmail = view.findViewById(R.id.email);
        mContactPerson = view.findViewById(R.id.contact_person);
        return view;
    }

    @Override
    void updateItem() {
        mServerAPIHelper.getOrder(getObjectId());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mServerAPIHelper.getOrder(getObjectId());
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void updateItem(ModelObject item) {
        if(item instanceof Order) {
            mItem = (Order) item;
            mAmount.setText(mItem.getAmount() + "");
            mIsPaid.setText(mItem.isPaid() + "");
            mDateCreated.setText(mItem.getDateCreated());

            mServerAPIHelper.getCustomer(mItem.getCustomerId());
        } else if(item instanceof Customer) {
            Customer customer = (Customer) item;
            mBusinessName.setText(customer.getBusinessName());
            mAddress.setText(customer.getAddress());
            mTelephone.setText(customer.getTelephone());
            mEmail.setText(customer.getEmail());
            mContactPerson.setText(customer.getContactPerson());

            mServerAPIHelper.getProduct(mItem.getProductId());
        } else if(item instanceof Product) {
            Product product = (Product) item;
            mProductName.setText(product.getProductName());
            mPrice.setText(product.getPrice()+"$");
        }

        itemUpdated();
    }
}
