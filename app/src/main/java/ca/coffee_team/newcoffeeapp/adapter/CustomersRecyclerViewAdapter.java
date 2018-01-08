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
        }else {
            mValues.clear();
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mBusinessName;
        private final TextView mAddress;
        private final TextView mTelephone;
        private final TextView mEmail;
        private final TextView mContactPerson;
        private Customer mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;

            mBusinessName = view.findViewById(R.id.business_name);
            mAddress = view.findViewById(R.id.address);
            mTelephone = view.findViewById(R.id.telephone);
            mEmail = view.findViewById(R.id.email);
            mContactPerson = view.findViewById(R.id.contact_person);
        }

        void bind(Customer item) {
            mItem = item;

            mBusinessName.setText(mItem.getBusinessName());
            mAddress.setText(mItem.getAddress());
            mTelephone.setText(mItem.getTelephone());
            mEmail.setText(mItem.getEmail());
            mContactPerson.setText(mItem.getContactPerson());

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
