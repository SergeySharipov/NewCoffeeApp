package ca.coffee_team.newcoffeeapp.api;

import java.util.List;

import ca.coffee_team.newcoffeeapp.model.ModelObject;

public interface ResponseListener {
    void updateListItems(List<? extends ModelObject> list);
}
