package chadamine.com.databaselist.Objects;

import java.util.HashMap;
import java.util.List;

/**
 * Created by calipinski on 4/22/2015.
 */
public class ChemicalCompound {

    private int mID;

    private String mName;

    private List<ChemicalElement> mChemicalElements;
    private HashMap<ChemicalElement, Double> mChemicalPercents;

    public ChemicalCompound() {

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


}
