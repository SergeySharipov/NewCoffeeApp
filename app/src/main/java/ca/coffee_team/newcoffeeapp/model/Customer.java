package ca.coffee_team.newcoffeeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
customerSchema: new mongoose.Schema({
    businessName: {type: String, default: " "},
    address: {type: String, default: " "},
    telephone: {type: Number, default: " "},
    email: {type: String, default: " "},
    contactPerson: {type: String, default: " "},
    dateCreated: {type: Date, default: Date.now}
}),
{"_id":"5a3989b87840630021e02504",
"__v":0,
"dateCreated":"2017-12-19T21:50:48.391Z",
"contactPerson":"John Smith",
"email":"costa@test.com",
"telephone":6478760976,
"address":"375 Bloor Street",
"businessName":"Costa"}
*/
public class Customer extends ModelObject {

    @SerializedName("businessName")
    @Expose
    private String mBusinessName;
    @SerializedName("address")
    @Expose
    private String mAddress;
    @SerializedName("telephone")
    @Expose
    private String mTelephone;
    @SerializedName("email")
    @Expose
    private String mEmail;
    @SerializedName("contactPerson")
    @Expose
    private String mContactPerson;

    public Customer() {
    }

    public Customer(String id, int version, String dateCreated, String businessName, String address,
                    String telephone, String email, String contactPerson) {
        super(id, version, dateCreated);
        mBusinessName = businessName;
        mAddress = address;
        mTelephone = telephone;
        mEmail = email;
        mContactPerson = contactPerson;
    }

    public Customer(String businessName, String address, String telephone, String email, String contactPerson) {
        mBusinessName = businessName;
        mAddress = address;
        mTelephone = telephone;
        mEmail = email;
        mContactPerson = contactPerson;
    }

    public String getBusinessName() {
        return mBusinessName;
    }

    public void setBusinessName(String businessName) {
        mBusinessName = businessName;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getTelephone() {
        return mTelephone;
    }

    public void setTelephone(String telephone) {
        mTelephone = telephone;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getContactPerson() {
        return mContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        mContactPerson = contactPerson;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "mBusinessName='" + mBusinessName + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mTelephone='" + mTelephone + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mContactPerson='" + mContactPerson + '\'' +
                '}';
    }
}
