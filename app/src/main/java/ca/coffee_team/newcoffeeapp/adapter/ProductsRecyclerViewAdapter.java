package ca.coffee_team.newcoffeeapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.coffee_team.newcoffeeapp.ProductsFragment;
import ca.coffee_team.newcoffeeapp.R;
import ca.coffee_team.newcoffeeapp.dummy.DummyContent.DummyItem;

public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final ProductsFragment.OnListFragmentInteractionListener mListener;

    public ProductsRecyclerViewAdapter(List<DummyItem> items, ProductsFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_customers, parent, false);
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
        private DummyItem mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        void bind(DummyItem dummyItem){
            mItem = dummyItem;
            mIdView.setText(dummyItem.id);
            mContentView.setText("Products");

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onListFragmentInteraction(mItem);
                    }
                }
            });
        }
    }
}
