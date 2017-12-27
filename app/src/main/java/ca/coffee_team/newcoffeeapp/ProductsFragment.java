package ca.coffee_team.newcoffeeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.coffee_team.newcoffeeapp.adapter.CustomersRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.adapter.ProductsRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.dummy.DummyContent;
import ca.coffee_team.newcoffeeapp.dummy.DummyContent.DummyItem;

public class ProductsFragment extends Fragment implements ProductsRecyclerViewAdapter.OnListFragmentInteractionListener{

    public static final String TAG = "ProductsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customers_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new ProductsRecyclerViewAdapter(DummyContent.ITEMS, this));
        }
        return view;
    }


    @Override
    public void onListFragmentInteraction(DummyItem item) {
        //startActivity(new Intent(this,);
    }
}
