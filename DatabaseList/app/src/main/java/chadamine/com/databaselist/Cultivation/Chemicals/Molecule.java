package chadamine.com.databaselist.Cultivation.Chemicals;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema;

/**
 * Created by chadamine on 4/22/2015.
 */
public class Molecule implements DatabaseAdapter {

    private int mID;

    private String mName;

    private HashMap<ChemicalElement, Double> mElementalContent;

    public Molecule() {}

    public Molecule(String name, HashMap<ChemicalElement, Double> content) {
        mName = name;
        mElementalContent = content;
    }

    public int getID() {
        return mID;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public Uri getUri() {
        return null;
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
        return null;
    }

    @Override
    public String[] getKeyIdArray() {
        return new String[0];
    }

    @Override
    public ContentValues getValues() {
        return null;
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
        return null;
    }

    @Override
    public void setListItemContent(View view, Cursor cursor) {

    }

    @Override
    public void setContent(View view) {

    }

    @Override
    public int getListItemLayoutId() {
        return 0;
    }

    public void setElementalContent(HashMap<ChemicalElement, Double> content) {
        mElementalContent = content;
    }

    public void setElementalContent(ChemicalElement element, Double content) {
        mElementalContent.put(element, content);
    }

    public HashMap<ChemicalElement, Double> getmElementalContent() {
        return mElementalContent;
    }

    public Double getElementalContent(ChemicalElement element) {
        return mElementalContent.get(element);
    }


}
