package chadamine.com.databaselist.Objects;

import java.util.HashMap;
import java.util.List;

/**
 * Created by calipinski on 4/22/2015.
 */
public class Nutrient {

    private int mID;

    private String mName;
    private String mManufacturer;
    private String mProductLine;
    private int mState;
    private double mPurity;
    private String mType;

    public static final int STATE_SOLID = 0;
    public static final int STATE_LIQUID = 1;
    public static final int STATE_GAS = 2;

    private List<ChemicalElement> mChemicalElements;
    private List<ChemicalCompound> mChemicalCompounds;
    private HashMap<ChemicalElement, Double> mElementPercents;
    private HashMap<ChemicalCompound, Double> mCompoundPercents;

    public Nutrient(int id, String name) {
        mID = id;
        mName = name;
        mElementPercents = new HashMap<>();
        mCompoundPercents = new HashMap<>();

    }

    public Nutrient(int id, String name, String manufacturer, String productLine, double purity, int state, String type) {
        mID = id;
        mName = name;
        mManufacturer = manufacturer;
        mProductLine = productLine;
        mPurity = purity;
        mState = state;
        mType = type;
    }

    public int getID() {
        return mID;
    }

    public String getManufacturer() {
        return mManufacturer;
    }
    public void setManufacturer(String m) {
        mManufacturer = m;
    }

    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }

    public void setProductLine(String pl) {
        mProductLine = pl;
    }
    public String getProductLine() {
        return mProductLine;
    }

    public double getPurity() {
        if(mPurity == 0)
            mPurity = 1;
        return mPurity;
    }
    public void setPurity(double p) {
        mPurity = p;
    }

    public int getState() {
        return mState;
    }
    public void setState(int state) {
        mState = state;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public void setElementPercent(ChemicalElement element, double percent) {
        mElementPercents.put(element, percent);
    }

    public double getElementPercent(ChemicalElement e) {

        double percent = 0;

        for(ChemicalElement element : mChemicalElements) {
            if(element.getAtomicNumber() == e.getAtomicNumber()) {
                percent = mElementPercents.get(element);
            }
        }

        return percent;
    }

    public double getCompoundPercent(ChemicalCompound c) {

        double percent = 0;

        for(ChemicalCompound compound : mChemicalCompounds) {
            if(compound.getName() == c.getName()) {
                percent = mCompoundPercents.get(compound);
            }
        }

        return percent;
    }

}
