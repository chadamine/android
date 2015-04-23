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
    private String mSource;
    private int mState;
    private int mForm;
    private double mPurity;
    private String mType;

    public static final int STATE_SOLID = 0;
    public static final int STATE_LIQUID = 1;
    public static final int STATE_GAS = 2;

    public static final int FORM_FINE_POWDER = 0;
    public static final int FORM_POWDER = 1;
    public static final int FORM_GRANULAR = 2;

    private List<ChemicalElement> mChemicalElements;
    private List<ChemicalCompound> mChemicalCompounds;
    private HashMap<ChemicalElement, Double> mElementRatios;
    private HashMap<ChemicalCompound, Double> mCompoundRatios;

    public Nutrient(int id, String name) {

        mID = id;

        mName = name;
        mElementRatios = new HashMap<>();
        mCompoundRatios = new HashMap<>();

    }

    //TODO: Manufacturer, ProductLine classes
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

    public void setSource(String source) {
        mSource = source;
    }

    public String getmSource() {
        return mSource;
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
        mElementRatios.put(element, percent);
    }

    public double getElementPercent(ChemicalElement e) {

        double percent = 0;

        for(ChemicalElement element : mChemicalElements) {
            if(element.getAtomicNumber() == e.getAtomicNumber()) {
                percent = mElementRatios.get(element);
            }
        }

        return percent;
    }

    public double getCompoundPercent(ChemicalCompound c) {

        double percent = 0;

        for(ChemicalCompound compound : mChemicalCompounds) {
            if(compound.getName() == c.getName()) {
                percent = mCompoundRatios.get(compound);
            }
        }

        return percent;
    }

}
