package chadamine.com.databaselist.Objects;

import java.util.HashMap;

/**
 * Created by chadamine on 4/22/2015.
 */
public class ProductManufacturer {

    private int mID;
    private String mName;
    private String mPrimaryPhone;
    private String mPrimaryAddress;
    private String mPickupAddress;
    private HashMap<Product, Double> mProductPrices;
    private HashMap<Shipper, HashMap<Double, Double>> mDeliveryPrices;

    public ProductManufacturer() {
        mProductPrices = new HashMap<>();
        mDeliveryPrices = new HashMap<>();

    }

    public void setID(int id) {
        mID = id;
    }

    public int getID() {
        return mID;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void addProductPrice(Product product, Double price, String currency) {
        mProductPrices.put(product, price);
    }

    public Double getProductPrice(Product product) {
        return mProductPrices.get(product);
    }

    public String getPrimaryPhone() {
        return mPrimaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        mPrimaryPhone = primaryPhone;
    }

    public String getPrimaryAddress() {
        return mPrimaryAddress;
    }

    public void setPrimaryAddress(String primaryAddress) {
        mPrimaryAddress = primaryAddress;
    }

    public String getPickupAddress() {
        return mPickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        mPickupAddress = pickupAddress;
    }

    public HashMap<Shipper, HashMap<Double, Double>> getDeliveryPrices() {
        return mDeliveryPrices;
    }

    public void setDeliveryPrices(HashMap<Shipper, HashMap<Double, Double>> deliveryPrices) {
        mDeliveryPrices = deliveryPrices;
    }
}
