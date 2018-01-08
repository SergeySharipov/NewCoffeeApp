package ca.coffee_team.newcoffeeapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.coffee_team.newcoffeeapp.api.ServerAPIHelper;

public abstract class StandardFragment extends Fragment {
    protected ServerAPIHelper mServerAPIHelper;

    public static final String OBJECT_ID = "StandardFragment_ID";
    private String mObjectId ="";

    public String getObjectId() {
        return mObjectId;
    }

    public abstract String getTitle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mObjectId = getArguments().getString(OBJECT_ID);
        }
        mServerAPIHelper = new ServerAPIHelper();
    }

}
