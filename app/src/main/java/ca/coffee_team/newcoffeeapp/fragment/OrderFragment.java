package ca.coffee_team.newcoffeeapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.model.Customer;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Order;

public class OrderFragment extends StandardItemFragment {
    public static final String TITLE = "Order";
    private TextView mIdView;
    private TextView mContentView;
    private Order mItem;

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
        mIdView = view.findViewById(R.id.id);
        mContentView = view.findViewById(R.id.content);
        return view;
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
        mItem= (Order) item;
        mIdView.setText("Date created: "+mItem.getDateCreated());
        mContentView.setText("Amount: "+mItem.getAmount() + "");
    }
}
