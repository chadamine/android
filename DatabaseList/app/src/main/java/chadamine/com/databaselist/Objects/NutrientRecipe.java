package chadamine.com.databaselist.Objects;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chadamine on 4/22/2015.
 */
public class NutrientRecipe {

    private int mID;

    private String mName;
    private String mStage;
    private String mWeek;

    private int mVolume;
    private int mDateCreated;

    private List<Date> mDatesUsed;
    private HashMap<NutrientSolution, Double> mNutrientSolutionRatios;
    private List<NutrientSolution> mNutrientSolutions;
    private HashMap<Nutrient, Double> mNutrientRatios;

    public NutrientRecipe(HashMap<NutrientSolution, Double> solutionRatios) {
        mNutrientSolutionRatios = solutionRatios;

        for(NutrientSolution selected : mNutrientSolutionRatios.keySet())
            mNutrientSolutions.add(selected);
    }

    public NutrientSolution getNutrientSolution(String name) {

        NutrientSolution solution = null;

        for(NutrientSolution selected: mNutrientSolutions)
            if( selected.getName() == name)
                solution = selected;

        return solution;
    }

    public Nutrient getNutrient(String name) {

        Nutrient nutrient = null;

        for(Nutrient selected : mNutrientRatios.keySet())
            if(selected.getName() == name)
                nutrient = selected;

        return nutrient;

    }

    public void setNutrientRatios(HashMap<Nutrient, Double> ratios) {
        mNutrientRatios = ratios;
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



    public String getString() {
        return mName;
    }


}
