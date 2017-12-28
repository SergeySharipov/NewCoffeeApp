package ca.coffee_team.newcoffeeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.coffee_team.newcoffeeapp.adapter.CustomersRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.adapter.OrdersRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.dummy.DummyContent;
import ca.coffee_team.newcoffeeapp.dummy.DummyContent.DummyItem;

public class OrdersFragment extends Fragment {

    public static final String TAG = "Orders";
    private static final String ARG_CUSTOMER_ID = "ca.coffee_team.newcoffeeapp.ARG_CUSTOMER_ID";
    private OnListFragmentInteractionListener mListener;
    // TODO: Customize parameters
    private String mCustomerId;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OrdersFragment() {
    }

    public static OrdersFragment newInstance(int columnCount) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CUSTOMER_ID, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCustomerId = getArguments().getString(ARG_CUSTOMER_ID);
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
            recyclerView.setAdapter(new OrdersRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onResumeFragment(TAG);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
        void onResumeFragment(String tag);
    }
}
