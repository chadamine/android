package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema.Nutrients;
import chadamine.com.databaselist.R;


/**
 * Created by calipinski on 4/22/2015.
 */
public class Nutrient implements DatabaseAdapter {

    private int mID;

    private static final Uri CONTENT_URI = Nutrients.CONTENT_URI;
    private static final String KEY_ID = Nutrients.KEY_ID;
    private static final String[] KEY_ID_ARRAY = Nutrients.KEY_ID_ARRAY;
    //private static final String[] KEY_ARRAY = Nutrients.KEY_ARRAY;
    private static final String KEY_NAME = Nutrients.KEY_NAME;

    private String mName;
    private String mManufacturer;
    private String mProductLine;
    private String mSource;
    private int mState;
    private int mForm;
    private double mPurity;
    private String mType;

    private Context mContext;

    public static final int STATE_SOLID = 1;
    public static final int STATE_LIQUID = 2;
    public static final int STATE_GAS = 3;

    public static final int FORM_FINE_POWDER = 1;
    public static final int FORM_POWDER = 2;
    public static final int FORM_GRANULAR = 3;

    HashMap<String, String> mFields;

    private List<ChemicalElement> mChemicalElements;
    private List<ChemicalCompound> mChemicalCompounds;
    private HashMap<ChemicalElement, Double> mElementRatios;
    private HashMap<ChemicalCompound, Double> mCompoundRatios;

    public Nutrient(Context context) {
        //TODO: Manufacturer, ProductLine classes

       /* mID = id;
        mName = name;
        mManufacturer = manufacturer;
        mProductLine = productLine;
        mPurity = purity;
        mState = state;
        mType = type;*/
        mContext = context;
        mElementRatios = new HashMap<>();
        mCompoundRatios = new HashMap<>();
        mFields = new HashMap<>();
    }

    public void setElementRatio(ChemicalElement element, double ratio) {
        mElementRatios.put(element, ratio);
    }

    public double getElementRatio(ChemicalElement e) {

        double percent = 0;

        for(ChemicalElement element : mChemicalElements) {
            if(element.getAtomicNumber() == e.getAtomicNumber()) {
                percent = mElementRatios.get(element);
            }
        }

        return percent;
    }

    public double getCompoundRatio(ChemicalCompound c) {

        double percent = 0;

        for(ChemicalCompound compound : mChemicalCompounds) {
            if(compound.getName() == c.getName()) {
                percent = mCompoundRatios.get(compound);
            }
        }

        return percent;
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

    @Override
    public Uri getUri() {
        return CONTENT_URI;
    }

    @Override
    public Uri getHistoryUri() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getKeyID() {
        return KEY_ID;
    }

    @Override
    public String[] getKeyIdArray() {
        return KEY_ID_ARRAY;
    }

    @Override
    public ContentValues getValues() {

        ContentValues values = new ContentValues();
        values.put(Nutrients.KEY_NAME, getName());
        values.put(Nutrients.KEY_TIMESTAMP, new Date().toString());
        values.put(Nutrients.KEY_PURITY, getPurity());
        values.put(Nutrients.KEY_STATE, getState());

        return values;
    }

    @Override
    public ContentValues getHistoryValues() {
        return null;
    }

    @Override
    public Cursor getCursor() {
        return null;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String getCurrentDate() {
        return null;
    }

    @Override
    public String getPhotoDir() {
        return Nutrients.DIR_PHOTOS;
    }

    @Override
    public void setListItemContent(View view, Cursor c) {

        String name = c.getString(c.getColumnIndexOrThrow(Nutrients.KEY_NAME));

        ((TextView) view.findViewById(R.id.textview_nutrient_item_name))
                .setText(name == null ? "" : name);

        String purity = c.getString(c.getColumnIndexOrThrow(Nutrients.KEY_PURITY));
        ((TextView) view.findViewById(R.id.textview_nutrient_item_purity))
                .setText(purity == null ? "" : purity);
    }

    @Override
    public void setContent(View view) {

    }

    public void saveFields(View view, boolean isListItem) {

        if(!isListItem) {

            setName(((EditText) view.findViewById(R.id.edittext_nutrient_new_name))
                    .getText().toString());

            try {
                setPurity(Double.valueOf(((EditText) view.findViewById(R.id.edittext_nutrient_new_name))
                        .getText().toString()));
            } catch (NumberFormatException e) {
                setPurity(1);
            }

            try {
                setState(Integer.valueOf(((EditText) view.findViewById(R.id.edittext_nutrient_new_state))
                        .getText().toString()));
            } catch (NumberFormatException e) {
                setState(0);
            }

            mContext.getContentResolver().insert(getUri(), getValues());
        }

    }

    public HashMap<String, String> getFieldContent(int position, @Nullable String sortOrder) {

        Cursor cursor = mContext.getContentResolver().query(getUri(), getKeyIdArray(), null, null, sortOrder);
        cursor.moveToPosition(position);

        mFields.put(KEY_NAME,  // TODO: ITERATE OVER KEY_ARRAY
                cursor.getString(cursor.getColumnIndexOrThrow(Nutrients.KEY_NAME)));

        mFields.put(Nutrients.KEY_PURITY,
                cursor.getString(cursor.getColumnIndexOrThrow(Nutrients.KEY_PURITY)));

        return mFields;
    }

    @Override
    public int getListItemLayoutId() {
        return mContext.getResources().getIdentifier("list_item_nutrient", "layout", mContext.getPackageName());
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



}
