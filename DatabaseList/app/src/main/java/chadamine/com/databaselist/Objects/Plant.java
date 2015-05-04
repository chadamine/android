package chadamine.com.databaselist.Objects;

import android.content.ContentValues;

//import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Database.DatabaseContract.Plants;

/**
 * Created by chadamine on 4/30/2015.
 */
public class Plant extends Organism {

    private int mID;

    private String mName;
    private String mCultivar;
    private String mCultigen;
    private String mHeight;
    private String mSpecies;

    private boolean isCultigen;
    private boolean isCultivar;

    private Substrate mSubstrate;
    private CultivationSystem mSystem;
    private Schedule mIrrigationSchedule;
    private Progress mProgress;
    private Lineage mLineage;
    private String mStage;
    private String mAge;
    private String mPotSize;

    public Plant() {

    }

    public Plant(int mID,
                 String mName, String mCultivar, String mCultigen, String mHeight,
                 boolean isCultigen, boolean isCultivar,
                 Substrate mSubstrate, CultivationSystem mSystem, Schedule mIrrigationSchedule,
                 Progress mProgress, Lineage mLineage) {

        this.mID = mID;
        this.mName = mName;
        this.mCultivar = mCultivar;
        this.mCultigen = mCultigen;
        this.mHeight = mHeight;

        this.isCultigen = isCultigen;
        this.isCultivar = isCultivar;
        this.mSubstrate = mSubstrate;
        this.mSystem = mSystem;
        this.mIrrigationSchedule = mIrrigationSchedule;
        this.mProgress = mProgress;
        this.mLineage = mLineage;
    }

    public void dbInsert() {
        // TODO: insert into database
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String mName) {
        this.mName = mName;
    }

    public void setSpecies(String species) {
        mSpecies = species;
    }

    public String getCultivar() {
        return mCultivar;
    }

    public void setCultivar(String mCultivar) {
        this.mCultivar = mCultivar;
    }

    public String getCultigen() {
        return mCultigen;
    }

    public void setCultigen(String mCultigen) {
        this.mCultigen = mCultigen;
    }


    public String getSpecies() {
        return mSpecies;
    }


    public void setStage(String stage) {
        mStage = stage;
    }

    public String getStage() {
        return mStage;
    }

    public void setAge(String age) {
        mAge = age;
    }

    public String getAge() {
        return mAge;
    }

    public void setPotSize(String potSize) {
        mPotSize = potSize;
    }

    public String getPotSize() {
        return mPotSize;
    }

    public String getHeight() {
        return mHeight;
    }

    public void setHeight(String mHeight) {
        this.mHeight = mHeight;
    }

    public boolean isCultigen() {
        return isCultigen;
    }

    public void setIsCultigen(boolean isCultigen) {
        this.isCultigen = isCultigen;
    }

    public boolean isCultivar() {
        return isCultivar;
    }

    public void setIsCultivar(boolean isCultivar) {
        this.isCultivar = isCultivar;
    }

    public Substrate getSubstrate() {
        return mSubstrate;
    }

    public void setSubstrate(Substrate mSubstrate) {
        this.mSubstrate = mSubstrate;
    }

    public CultivationSystem getSystem() {
        return mSystem;
    }

    public void setSystem(CultivationSystem mSystem) {
        this.mSystem = mSystem;
    }

    public Schedule getIrrigationSchedule() {
        return mIrrigationSchedule;
    }

    public void setIrrigationSchedule(Schedule mIrrigationSchedule) {
        this.mIrrigationSchedule = mIrrigationSchedule;
    }

    public Progress getProgress() {
        return mProgress;
    }

    public void setProgress(Progress mProgress) {
        this.mProgress = mProgress;
    }

    public Lineage getLineage() {
        return mLineage;
    }

    public void setmLineage(Lineage mLineage) {
        this.mLineage = mLineage;
    }

    public ContentValues getValues() throws NullPointerException {

        ContentValues values = new ContentValues();
        values.put(Plants.KEY_NAME, getName());
        values.put(Plants.KEY_SPECIES, getSpecies());
        values.put(Plants.KEY_CULTIVAR, getCultivar());
        values.put(Plants.KEY_STAGE, getStage());
        values.put(Plants.KEY_AGE, getAge());
        values.put(Plants.KEY_HEIGHT, getHeight());
        //values.put(Plants.KEY_SUBSTRATE, getSubstrate());
        //values.put(Plants.KEY_POTSIZE, getPotSize());

        return values;
    }
}
