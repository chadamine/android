package chadamine.com.databaselist.Cultivation.Biologicals;

/**
 * Created by chadamine on 4/30/2015.
 */
public class Taxonomy {

    private int mID;

    private String mKingdom;
    private String mPhylum;
    private String mClass;
    private String mOrder;
    private String mFamily;
    private String mGenus;
    private String mSpecies;

    public Taxonomy() { }

    public String getKingdom() {
        return mKingdom;
    }

    public void setKingdom(String mKingdom) {
        this.mKingdom = mKingdom;
    }

    public String getBiologigicalClass() {
        return mClass;
    }

    public void setBiologicalClass(String mClass) {
        this.mClass = mClass;
    }

    public String getPhylum() {
        return mPhylum;
    }

    public void setPhylum(String mPhylum) {
        this.mPhylum = mPhylum;
    }

    public String getOrder() {
        return mOrder;
    }

    public void setOrder(String mOrder) {
        this.mOrder = mOrder;
    }

    public String getFamily() {
        return mFamily;
    }

    public void setFamily(String mFamily) {
        this.mFamily = mFamily;
    }

    public String getGenus() {
        return mGenus;
    }

    public void setGenus(String mGenus) {
        this.mGenus = mGenus;
    }

    public String getSpecies() {
        return mSpecies;
    }

    public void setSpecies(String mSpecies) {
        this.mSpecies = mSpecies;
    }

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }
}
