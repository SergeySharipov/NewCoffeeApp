package ca.coffee_team.newcoffeeapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.coffee_team.newcoffeeapp.api.ItemResponseCallback;
import ca.coffee_team.newcoffeeapp.api.ServerAPIHelper;

public abstract class StandardItemFragment extends StandardFragment implements ItemResponseCallback {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mServerAPIHelper.setItemResponseCallback(this);
    }

}
