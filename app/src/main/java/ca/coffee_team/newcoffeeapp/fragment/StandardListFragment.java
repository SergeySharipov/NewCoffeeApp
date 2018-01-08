package ca.coffee_team.newcoffeeapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.adapter.RecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.api.ItemsResponseCallback;
import ca.coffee_team.newcoffeeapp.api.ServerAPIHelper;
import ca.coffee_team.newcoffeeapp.model.ModelObject;

public abstract class StandardListFragment extends StandardFragment implements ItemsResponseCallback {

    protected OnListItemClickListener mOnListItemClickListener;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mServerAPIHelper.setItemsResponseCallback(this);

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateListItems();
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    public void setAdapter(RecyclerViewAdapter adapter) {
        mAdapter = adapter;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter((RecyclerView.Adapter) mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateListItems();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListItemClickListener) {
            mOnListItemClickListener = (OnListItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnListItemClickListener = null;
    }

    abstract void updateListItems();

    abstract List<? extends ModelObject> getListItems();

    abstract void swapListItems(List<? extends ModelObject> list);

    @Override
    public void updateListItems(List<? extends ModelObject> list) {
        swapListItems(list);
        mAdapter.swap(getListItems());
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
