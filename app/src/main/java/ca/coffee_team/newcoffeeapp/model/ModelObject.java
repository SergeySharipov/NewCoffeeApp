package ca.coffee_team.newcoffeeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergey-PC on 01.01.2018.
 */

public class ModelObject {
    @SerializedName("_id")
    @Expose
    private String mId;
    @SerializedName("__v")
    @Expose
    private int mVersion;
    @SerializedName("dateCreated")
    @Expose
    private String mDateCreated;

    public ModelObject() {
    }

    public ModelObject(String id, int version, String dateCreated) {
        mId = id;
        mVersion = version;
        mDateCreated = dateCreated;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public int getVersion() {
        return mVersion;
    }

    public void setVersion(int version) {
        mVersion = version;
    }

    public String getDateCreated() {
        return mDateCreated;
    }

    public void setDateCreated(String dateCreated) {
        mDateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "ModelObject{" +
                "mId='" + mId + '\'' +
                ", mVersion=" + mVersion +
                ", mDateCreated='" + mDateCreated + '\'' +
                '}';
    }
}
