package ca.coffee_team.newcoffeeapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.coffee_team.newcoffeeapp.R;

public class FragmentContainer extends Fragment {
    public static final String BACK_BUTTON_STATUS = "ca.coffee_team.newcoffeeapp.fragment.BACK_BUTTON_STATUS";
    public static final String PAGE = "ca.coffee_team.newcoffeeapp.fragment.PAGE";
    public static final String TITLE = "ca.coffee_team.newcoffeeapp.fragment.TITLE";
    public static final String OBJECT_ID = "FragmentContainer.OBJECT_ID";
    private boolean mBackButtonStatus = false;
    private int mPage = 0;
    private OnFragmentContainerManageListener mListener;
    private String mTitle = "null";
    private String mObjectId = "";

    public static FragmentContainer newInstance(int page) {
        FragmentContainer fragment = new FragmentContainer();
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    public boolean isEditStatus() {
        return !mTitle.endsWith("s");
    }

    public boolean isBackButtonStatus() {
        return mBackButtonStatus;
    }

    public void setBackButtonStatus(boolean backButtonStatus) {
        mBackButtonStatus = backButtonStatus;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getObjectId() {
        return mObjectId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mPage = getArguments().getInt(PAGE, 0);
        }
        if (savedInstanceState != null) {
            mPage = savedInstanceState.getInt(PAGE, 0);
            mTitle = savedInstanceState.getString(TITLE, "");
            mObjectId = savedInstanceState.getString(OBJECT_ID, "");
            mBackButtonStatus = savedInstanceState.getBoolean(BACK_BUTTON_STATUS, false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PAGE, mPage);
        outState.putString(TITLE, mTitle);
        outState.putString(OBJECT_ID, mObjectId);
        outState.putBoolean(BACK_BUTTON_STATUS, mBackButtonStatus);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            initFirstFragment();
        }
        mListener.updateActionBar();
    }

    public void initFirstFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        StandardFragment standardFragment = mListener.getFirstFragment(mPage);
        mTitle = standardFragment.getTitle();
        fragmentTransaction.addToBackStack(mTitle);
        fragmentTransaction.add(R.id.fragment_container, standardFragment, mTitle);
        fragmentTransaction.commit();

        mListener.updateActionBar();
    }

    public int getBackStackCount() {
        return getChildFragmentManager().getBackStackEntryCount();
    }

    public boolean isBackStackEmpty() {
        return getBackStackCount() <= 1;
    }

    String getLastBackStackFragmentTitle() {
        if (getBackStackCount() > 1)
            return getChildFragmentManager().getBackStackEntryAt(getBackStackCount() - 2).getName();
        else return mTitle;
    }

    String getLastBackStackFragmentObjectId() {
        if (getBackStackCount() > 1) {
            CharSequence objectId = getChildFragmentManager().getBackStackEntryAt(getBackStackCount() - 2)
                    .getBreadCrumbTitle();
            if (objectId != null)
                return objectId.toString();
        }
        return null;
    }

    public void showPreviousFragment() {
        if (getBackStackCount() > 1) {
            if (getBackStackCount() == 2)
                setBackButtonStatus(false);

            mTitle = getLastBackStackFragmentTitle();
            mObjectId = getLastBackStackFragmentObjectId();
            getChildFragmentManager().popBackStack();
        } else {
            setBackButtonStatus(false);
        }

        mListener.updateActionBar();
    }

    public void showFirstFragment() {
        setBackButtonStatus(false);

        getChildFragmentManager().popBackStack(mListener.getFirstFragmentTitle(mPage),
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
        initFirstFragment();

        mListener.updateActionBar();
    }

    public void showNextFragment(StandardFragment standardFragment,String objectId) {
        setBackButtonStatus(true);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        mTitle = standardFragment.getTitle();
        mObjectId = objectId;
        fragmentTransaction.addToBackStack(mTitle);
        fragmentTransaction.setBreadCrumbTitle(mObjectId);
        fragmentTransaction.replace(R.id.fragment_container, standardFragment, mTitle);
        fragmentTransaction.commit();

        mListener.updateActionBar();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentContainerManageListener) {
            mListener = (OnFragmentContainerManageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentResumeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentContainerManageListener {
        StandardFragment getFirstFragment(int position);

        String getFirstFragmentTitle(int position);

        void updateActionBar();
    }
}
