package ca.coffee_team.newcoffeeapp.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.api.ItemResponseCallback;
import ca.coffee_team.newcoffeeapp.api.ServerAPIHelper;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Product;

public class AddEditProductDialog extends DialogFragment implements View.OnClickListener,
        ItemResponseCallback {
    public static final String TITLE = "Add product";
    public static final String PRODUCT_ID = "ca.coffee_team.newcoffeeapp.PRODUCT_ID";
    private EditText mProductName;
    private EditText mPrice;
    private Product mItem;
    private String productId;
    private ServerAPIHelper mServerAPIHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!=null){
            productId = bundle.getString(PRODUCT_ID);
        }
        mServerAPIHelper = new ServerAPIHelper();
        mServerAPIHelper.setItemResponseCallback(this);

        mItem = new Product();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_edit_product, null);

        ((TextView)view.findViewById(R.id.title)).setText(TITLE);

        view.findViewById(R.id.cancel_but).setOnClickListener(this);
        view.findViewById(R.id.ok_but).setOnClickListener(this);

        mProductName = view.findViewById(R.id.product_name_et);
        mPrice = view.findViewById(R.id.price_et);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(productId != null)
            mServerAPIHelper.getProduct(productId);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ok_but:
                try {
                    mItem.setProductName(mProductName.getText().toString());
                    mItem.setPrice(Double.parseDouble(mPrice.getText().toString()));

                    mServerAPIHelper.addProduct(mItem);
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            case R.id.cancel_but:
            default:
                    dismiss();
        }

    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        if(window!=null) {
            ViewGroup.LayoutParams params = window.getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        }
        super.onResume();
    }

    @Override
    public void updateItem(ModelObject item) {
        if(item instanceof Product) {
            mItem = (Product) item;
            mProductName.setText(mItem.getProductName());
            mPrice.setText(mItem.getPrice()+"");
        }
    }
}
