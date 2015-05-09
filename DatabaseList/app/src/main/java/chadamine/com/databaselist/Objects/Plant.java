package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema.Plants;
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

    }

    public int getListItemLayoutId() {
        return mContext.getResources()
                .getIdentifier("list_item_plant", "layout", mContext.getPackageName());
    }

    public int getViewItemLayoutId() {
        return  R.layout.fragment_plant_new;
                //mContext.getResources().getIdentifier("fragment_plant_view_info", "layout", mContext.getPackageName());
    }

    public int getNewItemLayoutId() {
        return mContext.getResources().getIdentifier("fragment_plant_new", "layout", mContext.getPackageName());
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

    public Cursor getNewCursor(@Nullable String[] idArray,
                               @Nullable String selection,
                               @Nullable String[] selectionArgs,
                               @Nullable String sortOrder) {
        String[] idA;

        if(idArray == null)
            idA = KEY_ID_ARRAY;
        else
            idA = idArray;

        Cursor cursor = mContext.getContentResolver().query(CONTENT_URI, idA, selection, selectionArgs, sortOrder);
        cursor.moveToFirst();

        return cursor;
    }


    // TODO: CHANGE TO setLayoutContent(View view)
    public void setListItemContent(View view, Cursor c) {

        String name = c.getString(c.getColumnIndexOrThrow(Plants.KEY_NAME));
        ((TextView) view.findViewById(R.id.textview_plants_item_name))
                .setText(name.isEmpty() ? "" : name);

        String species = c.getString(c.getColumnIndexOrThrow(Plants.KEY_SPECIES));
        ((TextView) view.findViewById(R.id.textview_plants_item_species))
                .setText(species.isEmpty() ? "" : species);

        String cultivar = c.getString(c.getColumnIndexOrThrow(Plants.KEY_CULTIVAR));
        ((TextView) view.findViewById(R.id.textview_plants_item_cultivar))
                .setText(cultivar.isEmpty() ? "" : cultivar);

        String stage = c.getString(c.getColumnIndexOrThrow(Plants.KEY_STAGE));
        ((TextView) view.findViewById(R.id.textview_plants_item_stage))
                .setText(stage.isEmpty() ? "" : stage);

        String age = c.getString(c.getColumnIndexOrThrow(Plants.KEY_AGE));
        ((TextView) view.findViewById(R.id.textview_plants_item_age))
                .setText(age.isEmpty() ? "" : age);


    }

    @Override
    public void setContent(View view) {

    // TODO: FIND A BETTER WAY TO CHECK VIEWS THAN BY TRYING TO CREATE ONE
/*        if((view.findViewById(R.id.textview_plant_view_info_name)) != null) {
            setViewItemContent(view);
        }

        if((view.findViewById(R.id.textview_plant_new_name_title)) != null) {
            setNewItemContent(view);
        }*/
    }

    public void setViewItemContent(View view, int position, String sortOrder) {

        mCursor = getNewCursor(getKeyIdArray(), null, null, sortOrder);
        mCursor.moveToPosition(position);

        String name = mCursor.getString(mCursor.getColumnIndexOrThrow(Plants.KEY_NAME));
        ((TextView) view.findViewById(R.id.textview_plant_view_info_name))
                .setText(name.isEmpty() ? "" : name);

        String species = mCursor.getString(mCursor.getColumnIndexOrThrow(Plants.KEY_SPECIES));
        ((TextView) view.findViewById(R.id.textview_plant_view_info_species))
                .setText(species.isEmpty() ? "" : species);

        String cultivar = mCursor.getString(mCursor.getColumnIndexOrThrow(Plants.KEY_CULTIVAR));
        ((TextView) view.findViewById(R.id.textview_plant_view_info_cultivar))
                .setText(cultivar.isEmpty() ? "" : cultivar);

        String stage = mCursor.getString(mCursor.getColumnIndexOrThrow(Plants.KEY_STAGE));
        ((TextView) view.findViewById(R.id.textview_plant_view_info_stage))
                .setText(stage.isEmpty() ? "" : stage);

        String age = mCursor.getString(mCursor.getColumnIndexOrThrow(Plants.KEY_AGE));
        ((TextView) view.findViewById(R.id.textview_plant_view_info_age))
                .setText(age.isEmpty() ? "" : age);

        mCursor.moveToNext();
    }

    public void setNewItemContent(View view) {

    }
/*
    public void setContent(List<View> views, int position) {
        mCursor.moveToPosition(position);

        int s = 0;
        while(s < views.size() - 1){
            //for(int i = 0; i < mCursor.getCount(); ++i) {
                for (int j = 0; j < mCursor.getColumnCount() - 2; j++) {
                    //if (views.get(i).getClass().toString().equals(TextView.class.toString())) {
                    ((TextView) views.get(j)).setText(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_ARRAY[j])));
                    //}

                }
            s++;

        }

    }*/

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
