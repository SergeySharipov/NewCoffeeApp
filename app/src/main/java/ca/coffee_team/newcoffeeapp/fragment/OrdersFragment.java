package ca.coffee_team.newcoffeeapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.adapter.OrdersRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.model.TempData;

public class OrdersFragment extends StandardListFragment {
    private static final String TITLE = "Orders";
    private static final String CUSTOMER_ID = "CUSTOMER_ID";
    private int mCustomerId;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCustomerId = getArguments().getInt(CUSTOMER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customers_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new OrdersRecyclerViewAdapter(TempData.getOrders(), mOnListItemClickListener));
        }
        return view;
    }


    @Override
    public String getTitle() {
        return TITLE;
    }
}
