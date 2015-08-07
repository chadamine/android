package chadamine.com.databaselist.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Cultivation.Plants.PlantViewFragment;
import chadamine.com.databaselist.Business.Product;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/30/2015.
 */
public class SwipePagerAdapter extends FragmentStatePagerAdapter {

    private Context mActivity;
    //private String[] mPageData;
    private LayoutInflater mInflater;

    private Uri mUri;
    //private int mID;
    //private String mKeyID;
    private String[] mKeyArray;
    private Cursor mCursor;
    private List<DatabaseAdapter> mObjects;

    private HashMap<Integer, Long> mIds;

    public SwipePagerAdapter(FragmentManager fm) {
        super(fm);

    }

    public SwipePagerAdapter(FragmentManager fm, Context context, List<DatabaseAdapter> objects, Uri uri) {
        super(fm);
        mActivity = context;
        mUri = uri;
        mCursor = getCursor();
        mIds = new HashMap<>();
        mObjects = objects;

        setDatabase();

    }

    @Override
    public int getCount() {

        //return mPageData.length;
        return mObjects.size();
    }

   /* @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        object = null;
    }*/

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        Bundle args = new Bundle();

        final String plantClass = mObjects.getClass().toString();
        final String objectClass = plantClass;

        for(DatabaseAdapter dbObject : mObjects) {
            switch(DatabaseSchema.URI_MATCHER.match(dbObject.getUri())) {
                case DatabaseSchema.PLANTS:
                    fragment = new PlantViewFragment();
                    break;
            }

        }
        return fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int mPosition = position;

        mInflater = (LayoutInflater) mActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // TODO: INFLATE PAGE BASED ON APPROPRIATE FRAGMENT
        View view = null;

        // TODO: SET ALL FIELDS IN FRAGMENT BASED ON LAYOUT

        switch(DatabaseSchema.URI_MATCHER.match(mUri)) {

            case DatabaseSchema.PRODUCTS:
                view = mInflater.inflate(R.layout.fragment_plant_new, null);
                setProducts(view);
                break;

            case DatabaseSchema.JOURNALS:
                setJournals(view);
                break;

            case DatabaseSchema.PLANTS:
                setPlants(view);
                break;

            default:
        }

        container.addView(view, 0);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private void setPlants(View view) {

    }

    private void setProducts(View view) {

        //Product product = new Product();
        //final String product = Product.class.toString();

        for(Object object : mObjects){
            String type = object.getClass().toString();

            if(type == Product.class.toString())
                ((Product) object).setProductFields(view, mCursor, mObjects.indexOf(object));
            /*if(type == Journal.class.toString())
                ((Journal) object).setProductFields(view, mCursor, mObjects.indexOf(object));*/
        }
    }

    private void setJournals(View view) {
        ((TextView) view.findViewById(R.id.textview_journallist_name)).setText(
                mCursor.getString(mCursor.getColumnIndex(DatabaseSchema.Journals.KEY_NAME)));
                /*((TextView) view.findViewById(android.R.id.text1)).setText(
                        mCursor.getString(mCursor.getColumnIndex(DatabaseContract.Journals.KEY_NAME)));*/
    }

    private void setDatabase() {

        mCursor = mActivity.getContentResolver().query(mUri, mKeyArray, null, null, null);

        switch(DatabaseSchema.URI_MATCHER.match(mUri)) {

            case DatabaseSchema.PRODUCTS:
                //mKeyID = DatabaseContract.Products.KEY_ID;
                mKeyArray = DatabaseSchema.Products.KEY_ID_ARRAY;
                mUri = DatabaseSchema.Products.CONTENT_URI;
                mObjects = new ArrayList<>();
                break;

            case DatabaseSchema.JOURNALS:
                //mKeyID = DatabaseContract.Journals.KEY_ID;
                mUri = DatabaseSchema.Journals.CONTENT_URI;
                mKeyArray = DatabaseSchema.Journals.KEY_ID_ARRAY;
                break;

            case DatabaseSchema.PLANTS:
                mUri = DatabaseSchema.Plants.CONTENT_URI;
                mKeyArray = DatabaseSchema.Plants.KEY_ID_ARRAY;
                break;

            default:
                //mKeyID = "";
                mUri = null;
                mKeyArray = null;
        }
    }

    public Cursor getCursor() {

        return mActivity.getContentResolver().query(mUri, mKeyArray, mKeyArray[0], null, null);
    }
}
