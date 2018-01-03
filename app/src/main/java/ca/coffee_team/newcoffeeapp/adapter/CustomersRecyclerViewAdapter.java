package ca.coffee_team.newcoffeeapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.fragment.OnListItemClickListener;
import ca.coffee_team.newcoffeeapp.model.Customer;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Order;

public class CustomersRecyclerViewAdapter extends
        RecyclerView.Adapter<CustomersRecyclerViewAdapter.ViewHolder>
        implements RecyclerViewAdapter {

    private OnListItemClickListener mListener;
    private List<Customer> mValues;

    public CustomersRecyclerViewAdapter(@NonNull List<Customer> items,OnListItemClickListener listener) {
        mValues=items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_customers_list, parent, false);
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
    @SuppressWarnings (value="unchecked")
    public void swap(List<? extends ModelObject> items) {
        if(!items.isEmpty() && items.get(0) instanceof Customer) {
            mValues = (List<Customer>) items;
            Log.e("omg", "swap");
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mIdView;
        private final TextView mContentView;
        private Customer mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
            mContentView = view.findViewById(R.id.content);
        }

        void bind(Customer item) {
            mItem = item;
            mIdView.setText("Business name: "+mItem.getBusinessName());
            mContentView.setText("Address: "+mItem.getAddress());

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
