package ca.coffee_team.newcoffeeapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.coffee_team.newcoffeeapp.fragment.OnListItemClickListener;
import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.model.Customer;

import java.util.List;

public class CustomersRecyclerViewAdapter extends RecyclerView.Adapter<CustomersRecyclerViewAdapter.ViewHolder> {

    private final List<Customer> mValues;
    private final OnListItemClickListener mListener;

    public CustomersRecyclerViewAdapter(List<Customer> items, OnListItemClickListener listener) {
        mValues = items;
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

        void bind(Customer item){
            mItem=item;
            mIdView.setText(mItem.getStringId());
            mContentView.setText(mItem.getName());

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
