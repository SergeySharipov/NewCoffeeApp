package ca.coffee_team.newcoffeeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
orderSchema: new mongoose.Schema({
    customerID: {type: mongoose.Schema.Types.ObjectId, ref: 'Customer'},
    product: {type: mongoose.Schema.Types.ObjectId, ref: 'Product'},
    amount: {type: Number, default: 1},
    isPaid: {type: Boolean, default: false},
    dateCreated: {type: Date, default: Date.now}
}),
"_id": "5a398a7f7840630021e02507",
        "customerID": "5a3989b87840630021e02504",
        "__v": 0,
        "product": "5a398d0f7840630021e0250d",
        "dateCreated": "2017-12-19T21:54:07.651Z",
        "isPaid": false,
        "amount": 25
*/
public class Order extends ModelObject {
    @SerializedName("customerID")
    @Expose
    private String mCustomerId;
    @SerializedName("product")
    @Expose
    private String mProduct;
    @SerializedName("amount")
    @Expose
    private double mAmount;
    @SerializedName("isPaid")
    @Expose
    private boolean mIsPaid;

    public Order(String customerId, String product, double amount, boolean isPaid) {
        mCustomerId = customerId;
        mProduct = product;
        mAmount = amount;
        mIsPaid = isPaid;
    }

    public Order(String id, int version, String dateCreated, String customerId, String product,
                 double amount, boolean isPaid) {
        super(id, version, dateCreated);
        mCustomerId = customerId;
        mProduct = product;
        mAmount = amount;
        mIsPaid = isPaid;
    }

    public String getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(String customerId) {
        mCustomerId = customerId;
    }

    public String getProductId() {
        return mProduct;
    }

    public void setProduct(String product) {
        mProduct = product;
    }

    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
    }

    public boolean isPaid() {
        return mIsPaid;
    }

    public void setPaid(boolean paid) {
        mIsPaid = paid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "mCustomerId='" + mCustomerId + '\'' +
                ", mProduct='" + mProduct + '\'' +
                ", mAmount='" + mAmount + '\'' +
                ", mIsPaid='" + mIsPaid + '\'' +
                '}';
    }
}
