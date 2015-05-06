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

        switch(DatabaseContract.URI_MATCHER.match(uri)) {

            case DatabaseContract.PRODUCT_ID:
                queryBuilder.appendWhere(DatabaseContract.Products.KEY_ID + "=" + uri.getLastPathSegment());
                //keyId = DatabaseContract.Products.KEY_ID;
                //isItem = true;

            case DatabaseContract.PRODUCTS:
                queryBuilder.setTables(DatabaseContract.Products.TABLE_NAME);
                //tableName = DatabaseContract.Products.TABLE_NAME;

                break;

            case DatabaseContract.PHOTO_ID:
                queryBuilder.appendWhere(DatabaseContract.Photos.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseContract.PHOTOS:
                queryBuilder.setTables(DatabaseContract.Photos.TABLE_NAME);
                break;

            case DatabaseContract.PLANT_ID:
                queryBuilder.appendWhere(DatabaseContract.Plants.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseContract.PLANTS:
                queryBuilder.setTables(DatabaseContract.Plants.TABLE_NAME);
                break;

            case DatabaseContract.JOURNAL_ID:
                queryBuilder.appendWhere(DatabaseContract.Journals.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseContract.JOURNALS:
                queryBuilder.setTables(DatabaseContract.Journals.TABLE_NAME);
                break;

            case DatabaseContract.SUBSTRATE_ID:
                queryBuilder.appendWhere(DatabaseContract.Substrates.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseContract.SUBSTRATES:
                queryBuilder.setTables(DatabaseContract.Substrates.TABLE_NAME);
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

        switch(DatabaseContract.URI_MATCHER.match(uri)) {

            case DatabaseContract.PRODUCTS:
                id = mDatabase.insert(DatabaseContract.Products.TABLE_NAME, null, values);
                tableName = DatabaseContract.Products.TABLE_NAME;
                break;

            case DatabaseContract.JOURNALS:
                id = mDatabase.insert(DatabaseContract.Journals.TABLE_NAME, null, values);
                tableName = DatabaseContract.Journals.TABLE_NAME;
                break;

            case DatabaseContract.PLANTS:
                id = mDatabase.insert(DatabaseContract.Plants.TABLE_NAME, null, values);
                tableName = DatabaseContract.Plants.TABLE_NAME;
                break;

            case DatabaseContract.PHOTOS:
                id = mDatabase.insert(DatabaseContract.Photos.TABLE_NAME, null, values);
                tableName = DatabaseContract.Photos.TABLE_NAME;
                break;

            case DatabaseContract.SUBSTRATES:
                id = mDatabase.insert(DatabaseContract.Substrates.TABLE_NAME, null, values);
                tableName = DatabaseContract.Substrates.TABLE_NAME;
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

        switch(DatabaseContract.URI_MATCHER.match(uri)) {

            case DatabaseContract.PRODUCTS:
                rowsDeleted = mDatabase.delete(DatabaseContract.Products.TABLE_NAME,
                        selection, selectionArgs);
                break;
            case DatabaseContract.PRODUCT_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = mDatabase.
                            delete(DatabaseContract.Products.TABLE_NAME,
                                    DatabaseContract.Products.KEY_ID
                                            + "=" + id, null);
                } else {
                    rowsDeleted = mDatabase.delete(DatabaseContract.Products.TABLE_NAME,
                            DatabaseContract.Products.KEY_ID + "=" + id + " and " + selection, selectionArgs);
                }

                break;

            case DatabaseContract.JOURNALS:
                rowsDeleted = mDatabase.delete(DatabaseContract.Journals.TABLE_NAME,
                        selection, selectionArgs);
                break;

            case DatabaseContract.JOURNAL_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = mDatabase.
                            delete(DatabaseContract.Journals.TABLE_NAME,
                                    DatabaseContract.Journals.KEY_ID
                                            + "=" + id, null);
                } else {
                    rowsDeleted = mDatabase.delete(DatabaseContract.Journals.TABLE_NAME,
                            DatabaseContract.Journals.KEY_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;

            case DatabaseContract.PLANTS:
                rowsDeleted = mDatabase.delete(DatabaseContract.Plants.TABLE_NAME,
                        selection, selectionArgs);
                break;
            case DatabaseContract.PLANT_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = mDatabase.
                            delete(DatabaseContract.Plants.TABLE_NAME,
                                    DatabaseContract.Plants.KEY_ID
                                            + "=" + id, null);
                } else {
                    rowsDeleted = mDatabase.delete(DatabaseContract.Plants.TABLE_NAME,
                            DatabaseContract.Plants.KEY_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;

            case DatabaseContract.SUBSTRATES:
                rowsDeleted = mDatabase.delete(DatabaseContract.Substrates.TABLE_NAME,
                        selection, selectionArgs);
                break;
            case DatabaseContract.SUBSTRATE_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = mDatabase.
                            delete(DatabaseContract.Substrates.TABLE_NAME,
                                    DatabaseContract.Substrates.KEY_ID
                                            + "=" + id, null);
                } else {
                    rowsDeleted = mDatabase.delete(DatabaseContract.Substrates.TABLE_NAME,
                            DatabaseContract.Substrates.KEY_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            //TODO: Delete case for pictures

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
