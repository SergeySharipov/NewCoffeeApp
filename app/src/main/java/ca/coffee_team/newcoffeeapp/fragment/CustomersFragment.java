package ca.coffee_team.newcoffeeapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.adapter.CustomersRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.model.TempData;

public class CustomersFragment extends StandardListFragment {
    private static final String TITLE = "Customers";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CustomersFragment() {
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
            recyclerView.setAdapter(new CustomersRecyclerViewAdapter(TempData.getCustomers(), mOnListItemClickListener));
        }
        return view;
    }


    @Override
    public String getTitle() {
        return TITLE;
    }
}
