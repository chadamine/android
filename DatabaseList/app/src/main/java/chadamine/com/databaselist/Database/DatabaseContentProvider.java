package chadamine.com.databaselist.Database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by chadamine on 4/10/2015.
 */


public class DatabaseContentProvider extends ContentProvider {

    private SQLiteDatabase mDatabase;


    @Override
    public boolean onCreate() {
        DatabaseOpenHelper dbOpenHelper = new DatabaseOpenHelper(getContext());
        mDatabase = dbOpenHelper.getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//        String keyId = "";
//        String tableName = "";
//        boolean isItem = false;

        switch(DatabaseSchema.URI_MATCHER.match(uri)) {

            case DatabaseSchema.JOURNAL_ID:
                queryBuilder.appendWhere(DatabaseSchema.Journals.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseSchema.JOURNALS:
                queryBuilder.setTables(DatabaseSchema.Journals.TABLE_NAME);
                break;

            case DatabaseSchema.NUTRIENT_ID:
                queryBuilder.appendWhere(DatabaseSchema.Nutrients.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseSchema.NUTRIENTS:
                queryBuilder.setTables(DatabaseSchema.Nutrients.TABLE_NAME);
                break;

            case DatabaseSchema.PHOTO_ID:
                queryBuilder.appendWhere(DatabaseSchema.Photos.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseSchema.PHOTOS:
                queryBuilder.setTables(DatabaseSchema.Photos.TABLE_NAME);
                break;

            case DatabaseSchema.PLANT_ID:
                queryBuilder.appendWhere(DatabaseSchema.Plants.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseSchema.PLANTS:
                queryBuilder.setTables(DatabaseSchema.Plants.TABLE_NAME);
                break;

            case DatabaseSchema.PRODUCT_ID:
                queryBuilder.appendWhere(DatabaseSchema.Products.KEY_ID + "=" + uri.getLastPathSegment());
                //keyId = DatabaseContract.Products.KEY_ID;
                //isItem = true;

            case DatabaseSchema.PRODUCTS:
                queryBuilder.setTables(DatabaseSchema.Products.TABLE_NAME);
                //tableName = DatabaseContract.Products.TABLE_NAME;

                break;

            case DatabaseSchema.SUBSTRATE_ID:
                queryBuilder.appendWhere(DatabaseSchema.Substrates.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseSchema.SUBSTRATES:
                queryBuilder.setTables(DatabaseSchema.Substrates.TABLE_NAME);
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }

       /* if(isItem)
            queryBuilder.appendWhere(keyId + "=" + uri.getLastPathSegment());

        queryBuilder.setTables(tableName);*/

        Cursor cursor = queryBuilder.query(mDatabase, projection, selection ,selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long id;
        String tableName;

        switch(DatabaseSchema.URI_MATCHER.match(uri)) {

            case DatabaseSchema.JOURNALS:
                id = mDatabase.insert(DatabaseSchema.Journals.TABLE_NAME, null, values);
                tableName = DatabaseSchema.Journals.TABLE_NAME;
                break;

            case DatabaseSchema.NUTRIENTS:
                id = mDatabase.insert(DatabaseSchema.Nutrients.TABLE_NAME, null, values);
                tableName = DatabaseSchema.Nutrients.TABLE_NAME;
                break;

            case DatabaseSchema.PHOTOS:
                id = mDatabase.insert(DatabaseSchema.Photos.TABLE_NAME, null, values);
                tableName = DatabaseSchema.Photos.TABLE_NAME;
                break;

            case DatabaseSchema.PLANTS:
                id = mDatabase.insert(DatabaseSchema.Plants.TABLE_NAME, null, values);
                tableName = DatabaseSchema.Plants.TABLE_NAME;
                break;

            case DatabaseSchema.PRODUCTS:
                id = mDatabase.insert(DatabaseSchema.Products.TABLE_NAME, null, values);
                tableName = DatabaseSchema.Products.TABLE_NAME;
                break;

            case DatabaseSchema.SUBSTRATES:
                id = mDatabase.insert(DatabaseSchema.Substrates.TABLE_NAME, null, values);
                tableName = DatabaseSchema.Substrates.TABLE_NAME;
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: "+ uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return Uri.parse(tableName + "/" + id);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
       int rowsDeleted;
       String id;

        switch(DatabaseSchema.URI_MATCHER.match(uri)) {


            case DatabaseSchema.JOURNALS:
                rowsDeleted = mDatabase.delete(DatabaseSchema.Journals.TABLE_NAME,
                        selection, selectionArgs);
                break;

            case DatabaseSchema.JOURNAL_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = mDatabase.
                            delete(DatabaseSchema.Journals.TABLE_NAME,
                                    DatabaseSchema.Journals.KEY_ID
                                            + "=" + id, null);
                } else {
                    rowsDeleted = mDatabase.delete(DatabaseSchema.Journals.TABLE_NAME,
                            DatabaseSchema.Journals.KEY_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;

            case DatabaseSchema.NUTRIENTS:
                rowsDeleted = mDatabase.delete(DatabaseSchema.Nutrients.TABLE_NAME,
                        selection, selectionArgs);
                break;

            case DatabaseSchema.NUTRIENT_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = mDatabase.
                            delete(DatabaseSchema.Nutrients.TABLE_NAME,
                                    DatabaseSchema.Nutrients.KEY_ID
                                            + "=" + id, null);
                } else {
                    rowsDeleted = mDatabase.delete(DatabaseSchema.Nutrients.TABLE_NAME,
                            DatabaseSchema.Nutrients.KEY_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;

            //TODO: Delete case for photos

            case DatabaseSchema.PLANTS:
                rowsDeleted = mDatabase.delete(DatabaseSchema.Plants.TABLE_NAME,
                        selection, selectionArgs);
                break;
            case DatabaseSchema.PLANT_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = mDatabase.delete(DatabaseSchema.Plants.TABLE_NAME,
                            DatabaseSchema.Plants.KEY_ID + "=" + id, null);
                } else {
                    rowsDeleted = mDatabase.delete(DatabaseSchema.Plants.TABLE_NAME,
                            DatabaseSchema.Plants.KEY_ID + "=" + id
                                    + " and " + selection, selectionArgs);
                }
                break;

            case DatabaseSchema.PRODUCTS:
                rowsDeleted = mDatabase.delete(DatabaseSchema.Products.TABLE_NAME,
                        selection, selectionArgs);
                break;
            case DatabaseSchema.PRODUCT_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = mDatabase.
                            delete(DatabaseSchema.Products.TABLE_NAME,
                                    DatabaseSchema.Products.KEY_ID
                                            + "=" + id, null);
                } else {
                    rowsDeleted = mDatabase.delete(DatabaseSchema.Products.TABLE_NAME,
                            DatabaseSchema.Products.KEY_ID + "=" + id + " and " + selection, selectionArgs);
                }

                break;

            case DatabaseSchema.SUBSTRATES:
                rowsDeleted = mDatabase.delete(DatabaseSchema.Substrates.TABLE_NAME,
                        selection, selectionArgs);
                break;
            case DatabaseSchema.SUBSTRATE_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = mDatabase.
                            delete(DatabaseSchema.Substrates.TABLE_NAME,
                                    DatabaseSchema.Substrates.KEY_ID
                                            + "=" + id, null);
                } else {
                    rowsDeleted = mDatabase.delete(DatabaseSchema.Substrates.TABLE_NAME,
                            DatabaseSchema.Substrates.KEY_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;


            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    // TODO: SET UP UPDATE
    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        return 0;
    }
}
