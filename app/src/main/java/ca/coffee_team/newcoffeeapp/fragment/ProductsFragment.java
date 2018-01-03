package ca.coffee_team.newcoffeeapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.coffee_team.newcoffeeapp.adapter.ProductsRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Product;

public class ProductsFragment extends StandardListFragment {
    public static final String TITLE = "Products";
    private List<Product> mListItems;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListItems = new ArrayList<>();
        setAdapter(new ProductsRecyclerViewAdapter(mListItems, mOnListItemClickListener));
    }

    @Override
    void updateListItems() {
        mServerAPIHelper.getProducts();
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
        if(!list.isEmpty()&&list.get(0) instanceof Product) {
            mListItems.clear();
            mListItems.addAll((List<? extends Product>) list);
        }
    }
}
