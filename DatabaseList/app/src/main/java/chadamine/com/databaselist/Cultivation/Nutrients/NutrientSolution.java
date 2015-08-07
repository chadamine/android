package chadamine.com.databaselist.Cultivation.Nutrients;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by calipinski on 4/22/2015.
 */
public class NutrientSolution {

    private int mID;

    private String mName;
    private Date mDateCreated;

    private double mSolutionVolume;

    private HashMap<Nutrient, Double> mNutrientRatios;


    public NutrientSolution(HashMap<Nutrient, Double> ratios) {
        mNutrientRatios = ratios;
    }

    public int getID() {
        return mID;
    }

    public Date getDateCreated() {
        return mDateCreated;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setNutrientRatios(HashMap<Nutrient, Double> ratios) {
        mNutrientRatios = ratios;
    }

    public HashMap<Nutrient, Double> getNutrientRatios() {
        return mNutrientRatios;
    }


}
