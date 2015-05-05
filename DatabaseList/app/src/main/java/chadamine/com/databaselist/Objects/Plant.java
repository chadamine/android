package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Database.DatabaseContract.Plants;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/30/2015.
 */
public class Plant extends Organism implements DatabaseAdapter {

    private int mID;

    private String mName;
    private String mCultivar;
    private String mHeight;
    private String mSpecies;

    private Substrate mSubstrate;
    private CultivationSystem mSystem;
    private Schedule mIrrigationSchedule;
    private Progress mProgress;
    private Lineage mLineage;
    private String mStage;
    private String mAge;
    private String mPotSize;

    private Context mContext;
    private static final Uri CONTENT_URI = Plants.CONTENT_URI;
    private Cursor mCursor;
    private static final String KEY_ID = Plants.KEY_ID;
    private static final String[] KEY_ID_ARRAY = Plants.KEY_ID_ARRAY;
    private static final String[] KEY_ARRAY = Plants.KEY_ARRAY;
    private static final String KEY_NAME = Plants.KEY_NAME;

    public Plant() {}

    public Plant (Context context) {
        mContext = context;
        mCursor = mContext.getContentResolver()
                .query(getUri(), getKeyIdArray(), null, null, null);
    }

    public int getListItemLayoutId() {
        return mContext.getResources()
                .getIdentifier("list_item_plant", "layout", mContext.getPackageName());
    }

    public int getViewPlantLayoutId() {
        return mContext.getResources()
                .getIdentifier("fragment_new_plant", "layout", mContext.getPackageName());
    }

    public int getNewPlantLayoutId() {
        return mContext.getResources()
                .getIdentifier("fragment_view_plant", "layout", mContext.getPackageName());
    }

    // TODO: REMOVE
    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public Uri getUri() {
        return CONTENT_URI;
    }

    @Override
    public String getKeyID() {
        return KEY_ID;
    }

    @Override
    public Cursor getCursor() {
        return mCursor;
    }


    // TODO: CHANGE TO setLayoutContent(View view)
    public void setListItemContent(View view, Cursor c) {
        //mCursor = cursor;

        // this check is pointless but need something to compare Views
        if(view.getResources().getIdentifier("list_item_plant", "layout", mContext.getPackageName())
                == getListItemLayoutId()) {

            String name = c.getString(c.getColumnIndexOrThrow(Plants.KEY_NAME));

            if (!name.isEmpty())
                ((TextView) view.findViewById(R.id.textview_plants_item_name)).setText(name);

            String species = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Plants.KEY_SPECIES));
            if (!species.isEmpty())
                ((TextView) view.findViewById(R.id.textview_plants_item_species)).setText(species);


            String cultivar = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Plants.KEY_CULTIVAR));
            //if(cultivar == "")
            ((TextView) view.findViewById(R.id.textview_plants_item_cultivar)).setText(cultivar);

            String stage = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Plants.KEY_STAGE));
            //if(stage == "")
            ((TextView) view.findViewById(R.id.textview_plants_item_stage)).setText(stage);

            String age = c.getString(c.getColumnIndexOrThrow(DatabaseContract.Plants.KEY_AGE));
            //if(age == "")
            ((TextView) view.findViewById(R.id.textview_plants_item_age)).setText(age);
        }

        if(view.getId() == getViewPlantLayoutId())
            return;
        if(view.getId() == getNewPlantLayoutId())
            return;
    }

    // TODO: REMOVE
    @Override
    public void insertValues(Context context, Uri uri) {
        mContext.getContentResolver().insert(DatabaseContract.Plants.CONTENT_URI, getValues());
    }

    @Override
    public String[] getKeyIdArray() {
        return KEY_ID_ARRAY;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getPhotoDir() {
        return Plants.DIR_PHOTOS;
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

    public void setLineage(Lineage mLineage) {
        this.mLineage = mLineage;
    }

    @Override
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
