package ca.coffee_team.newcoffeeapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Product;

public class ProductFragment extends StandardItemFragment {
    public static final String TITLE = "Product";
    private TextView mProductName;
    private TextView mPrice;
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

        view.findViewById(R.id.delete_product_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mItem.getId()!=null) {
                    mServerAPIHelper.deleteProduct(mItem.getId());
                    mOnItemClickListener.onDeleteClick();
                    Toast.makeText(getContext(),"Product deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mProductName = view.findViewById(R.id.product_name);
        mPrice = view.findViewById(R.id.price);
        return view;
    }

    @Override
    void updateItem() {
        mServerAPIHelper.getProduct(getObjectId());
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
        mProductName.setText(mItem.getProductName());
        mPrice.setText(mItem.getPrice()+"$");

        itemUpdated();
    }
}
