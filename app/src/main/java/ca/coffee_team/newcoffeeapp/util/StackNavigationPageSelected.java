package ca.coffee_team.newcoffeeapp.util;

import java.util.ArrayList;
import java.util.Stack;

public class StackNavigationPageSelected extends Stack<Integer> {

    private int mCountPages;

    public StackNavigationPageSelected(int countPages) {
        super();
        mCountPages = countPages;
    }

    public void pushPage(int position) {
        if (position < mCountPages && (size() == 0 || lastElement() != position)) {
            push(position);
        }
    }

    public int popPage() {
        if (size() > 1) {
            pop();
            return pop();
        }
        return 0;
    }

    public void fromIntArrayList(ArrayList<Integer> list) {
        addAll(list);
    }

    public ArrayList<Integer> toIntArrayList() {
        return new ArrayList<>(this);
    }
}
