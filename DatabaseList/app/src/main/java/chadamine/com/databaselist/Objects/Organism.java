package chadamine.com.databaselist.Objects;

import java.util.HashMap;

/**
 * Created by chadamine on 4/30/2015.
 */
public class Organism {

    private int mID;
    private String mName;
    private Taxonomy mTaxonomy;

    public Organism() { }

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setTaxonomy(HashMap<String, String> taxonomy) {

    }

    public Taxonomy getTaxonomy() {
        return mTaxonomy;
    }

    public void setTaxonomy(Taxonomy mTaxonomy) {
        this.mTaxonomy = mTaxonomy;
    }
}
