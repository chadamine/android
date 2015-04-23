package chadamine.com.databaselist.Objects;

/**
 * Created by calipinski on 4/22/2015.
 */
public class ChemicalElement {

    private int mChemicalElementID;
    private int mAtomicNumber;
    private String mName;

    private String mAbbr;
    private double mMass;

    public ChemicalElement(String name, String abbr, double mass) {
        mName = name;
        mAbbr = abbr;
        mMass = mass;
    }


    public int getID() {
        return mChemicalElementID;
    }

    public int getAtomicNumber() {
        return mAtomicNumber;
    }

    public String getName() {
        return mName;
    }

    public String getAbbr() {
        return mAbbr;
    }

    public double getMass() {
        return mMass;
    }

    @Override
    public String toString() {
        return mName;
    }
}
