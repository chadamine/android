package chadamine.com.databaselist.Objects;

/**
 * Created by chadamine on 4/30/2015.
 */
public class Plant extends Organism {

    private int mID;

    private String mName;
    private String mCultivar;
    private String mCultigen;
    private String mHeight;

    private boolean isCultigen;
    private boolean isCultivar;

    private Substrate mSubstrate;
    private CultivationSystem mSystem;
    private Schedule mIrrigationSchedule;
    private Progress mProgress;
    private Lineage mLineage;

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

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCultivar() {
        return mCultivar;
    }

    public void setmCultivar(String mCultivar) {
        this.mCultivar = mCultivar;
    }

    public String getmCultigen() {
        return mCultigen;
    }

    public void setmCultigen(String mCultigen) {
        this.mCultigen = mCultigen;
    }

    public String getmHeight() {
        return mHeight;
    }

    public void setmHeight(String mHeight) {
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

    public Substrate getmSubstrate() {
        return mSubstrate;
    }

    public void setmSubstrate(Substrate mSubstrate) {
        this.mSubstrate = mSubstrate;
    }

    public CultivationSystem getmSystem() {
        return mSystem;
    }

    public void setmSystem(CultivationSystem mSystem) {
        this.mSystem = mSystem;
    }

    public Schedule getmIrrigationSchedule() {
        return mIrrigationSchedule;
    }

    public void setmIrrigationSchedule(Schedule mIrrigationSchedule) {
        this.mIrrigationSchedule = mIrrigationSchedule;
    }

    public Progress getmProgress() {
        return mProgress;
    }

    public void setmProgress(Progress mProgress) {
        this.mProgress = mProgress;
    }

    public Lineage getmLineage() {
        return mLineage;
    }

    public void setmLineage(Lineage mLineage) {
        this.mLineage = mLineage;
    }
}
