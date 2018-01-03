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
import ca.coffee_team.newcoffeeapp.model.Product;

public class ProductFragment extends StandardItemFragment {
    public static final String TITLE = "Product";
    private TextView mIdView;
    private TextView mContentView;
    private Product mItem;
    
    public ProductFragment() {} // Required empty public constructor

    public static ProductFragment newInstance(String id) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(OBJECT_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        mIdView = view.findViewById(R.id.id);
        mContentView = view.findViewById(R.id.content);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mServerAPIHelper.getProduct(getObjectId());
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void updateItem(ModelObject item) {
        mItem= (Product) item;
        mIdView.setText("Product name: "+mItem.getProductName());
        mContentView.setText("Price: "+mItem.getPrice() + "$");
    }
}
