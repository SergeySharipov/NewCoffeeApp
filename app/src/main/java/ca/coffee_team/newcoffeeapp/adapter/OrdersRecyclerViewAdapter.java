package ca.coffee_team.newcoffeeapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.fragment.OnListItemClickListener;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Order;

public class OrdersRecyclerViewAdapter extends
        RecyclerView.Adapter<OrdersRecyclerViewAdapter.ViewHolder>
        implements RecyclerViewAdapter {

    private List<Order> mValues;
    private OnListItemClickListener mListener;

    public OrdersRecyclerViewAdapter(@NonNull List<Order> items,OnListItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_orders_list, parent, false);
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
        if (!items.isEmpty() && items.get(0) instanceof Order) {
            mValues = (List<Order>) items;
            notifyDataSetChanged();
        } else {
            mValues.clear();
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mAmount;
        private final TextView mIsPaid;
        private final TextView mDateCreated;
        private Order mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;

            mAmount = view.findViewById(R.id.amount);
            mIsPaid = view.findViewById(R.id.isPaid);
            mDateCreated = view.findViewById(R.id.data_created);
        }

        void bind(Order item) {
            mItem = item;

            mAmount.setText(mItem.getAmount()+"kg");
            mIsPaid.setText(String.valueOf(mItem.isPaid()));
            mDateCreated.setText(mItem.getDateCreated());

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
