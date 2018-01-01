package ca.coffee_team.newcoffeeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
productSchema: new mongoose.Schema({
    mProductName: {type: String, default: " "},
    mPrice: {type: Number, default: 0},
    dateCreated: {type: Date, default: Date.now}
})
"_id": "5a398ba17840630021e0250c",
        "__v": 0,
        "dateCreated": "2017-12-19T21:58:57.764Z",
        "mPrice": 23,
        "mProductName": "Christmas Blend"
*/
public class Product extends ModelObject {

    @SerializedName("mProductName")
    @Expose
    private String mProductName;
    @SerializedName("mPrice")
    @Expose
    private double mPrice;

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        this.mProductName = productName;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        this.mPrice = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "mProductName='" + mProductName + '\'' +
                ", mPrice='" + mPrice + '\'' +
                '}';
    }
}
