package ca.coffee_team.newcoffeeapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.fragment.OnListItemClickListener;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Product;

public class ProductsRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductsRecyclerViewAdapter.ViewHolder>
        implements RecyclerViewAdapter {

    private List<Product> mValues;
    private OnListItemClickListener mListener;

    public ProductsRecyclerViewAdapter(@NonNull List<Product> items, OnListItemClickListener listener) {
        mValues=items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_products_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public void swap(List<? extends ModelObject> items) {
        if (!items.isEmpty() && items.get(0) instanceof Product) {
            mValues = (List<Product>) items;
            notifyDataSetChanged();
        }else {
            mValues.clear();
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mProductName;
        private final TextView mPrice;
        private Product mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;

            mProductName = view.findViewById(R.id.product_name);
            mPrice = view.findViewById(R.id.price);
        }

        void bind(Product item) {
            mItem = item;

            mProductName.setText(mItem.getProductName());
            mPrice.setText(mItem.getPrice()+"$");

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onListItemClick(mItem);
                    }
                }
            });
        }
    }
}
