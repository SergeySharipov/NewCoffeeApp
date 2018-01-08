package ca.coffee_team.newcoffeeapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ca.coffee_team.newcoffeeapp.adapter.CustomersRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.model.Customer;
import ca.coffee_team.newcoffeeapp.model.ModelObject;

public class CustomersFragment extends StandardListFragment {
    public static final String TITLE = "Customers";
    private List<Customer> mListItems;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CustomersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListItems = new ArrayList<>();
        setAdapter(new CustomersRecyclerViewAdapter(mListItems,mOnListItemClickListener));
    }

    @Override
    void updateListItems() {
        mServerAPIHelper.getCustomers();
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    List<? extends ModelObject> getListItems() {
        return mListItems;
    }

    @Override
    @SuppressWarnings (value="unchecked")
    void swapListItems(List<? extends ModelObject> list) {
        if(!list.isEmpty()&&list.get(0) instanceof Customer) {
            mListItems = (List<Customer>) list;
        }else
            mListItems.clear();
    }
}
