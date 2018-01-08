package ca.coffee_team.newcoffeeapp.fragment;

import ca.coffee_team.newcoffeeapp.model.ModelObject;

public interface OnItemClickListener {

    void onShowOrdersClick(ModelObject item);

    void onDeleteClick();
}
