package ca.coffee_team.newcoffeeapp.model;

public abstract class ModelObject {
    private int mId;
    private String mName;

    public ModelObject(int id, String name) {
        mId = id;
        mName = name;
    }

    public String getStringId() {
        return String.valueOf(mId);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
