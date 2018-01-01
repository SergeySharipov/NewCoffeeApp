package ca.coffee_team.newcoffeeapp.adapter;

import java.util.List;

import ca.coffee_team.newcoffeeapp.model.Customer;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Order;

public interface RecyclerViewAdapter {

    void swap(List<? extends ModelObject> items);
}
