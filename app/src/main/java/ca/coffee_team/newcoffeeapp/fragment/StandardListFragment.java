package ca.coffee_team.newcoffeeapp.fragment;

import android.content.Context;

public abstract class StandardListFragment extends StandardFragment {

    public OnListItemClickListener mOnListItemClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListItemClickListener) {
            mOnListItemClickListener = (OnListItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnListItemClickListener = null;
    }
}
