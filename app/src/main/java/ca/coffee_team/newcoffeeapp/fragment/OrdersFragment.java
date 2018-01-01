package ca.coffee_team.newcoffeeapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.coffee_team.newcoffeeapp.adapter.OrdersRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Order;

public class OrdersFragment extends StandardListFragment {
    private static final String TITLE = "Orders";
    private static final String CUSTOMER_ID = "CUSTOMER_ID";
    private int mCustomerId;
    private List<Order> mListItems;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OrdersFragment() {
    }

    @SuppressWarnings("unused")
    public static OrdersFragment newInstance(int columnCount) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putInt(CUSTOMER_ID, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListItems = new ArrayList<>();
        if (getArguments() != null) {
            mCustomerId = getArguments().getInt(CUSTOMER_ID);
        }
        setAdapter(new OrdersRecyclerViewAdapter(mListItems, mOnListItemClickListener));
    }

    @Override
    void updateListItems() {
        mServerAPIHelper.getOrders();
    }

    @Override
    List<? extends ModelObject> getListItems() {
        return mListItems;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    @SuppressWarnings (value="unchecked")
    void swapListItems(List<? extends ModelObject> list) {
        if(!list.isEmpty()&&list.get(0) instanceof Order) {
            mListItems.clear();
            mListItems.addAll((List<? extends Order>) list);
        }
    }
}
