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

    //private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase database;


    @Override
    public boolean onCreate() {
        //dbOpenHelper = new DatabaseOpenHelper(getContext());
        DatabaseOpenHelper dbOpenHelper = new DatabaseOpenHelper(getContext());
        database = dbOpenHelper.getWritableDatabase();        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {


        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        //queryBuilder.setTables(ProductTable.TABLE_PRODUCT);


        switch(DatabaseContract.URI_MATCHER.match(uri)) {

            case DatabaseContract.PRODUCT_ID:
                queryBuilder.appendWhere(DatabaseContract.Products.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseContract.PRODUCTS:
                queryBuilder.setTables(DatabaseContract.Products.TABLE_NAME);
                break;

            case DatabaseContract.PRODUCT_PHOTO_ID:
                queryBuilder.appendWhere(DatabaseContract.Photos.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseContract.PRODUCT_PHOTOS:
                queryBuilder.setTables(DatabaseContract.Photos.TABLE_NAME);
                break;


            case DatabaseContract.JOURNAL_ID:
                queryBuilder.appendWhere(DatabaseContract.Journals.KEY_ID + "=" + uri.getLastPathSegment());

            case DatabaseContract.JOURNALS:
                queryBuilder.setTables(DatabaseContract.Journals.TABLE_NAME);
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }

        Cursor cursor = queryBuilder.query(database, projection, selection ,selectionArgs, null, null, sortOrder);
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
                id = database.insert(DatabaseContract.Products.TABLE_NAME, null, values);
                tableName = DatabaseContract.Products.TABLE_NAME;
                break;

            case DatabaseContract.JOURNALS:
                id = database.insert(DatabaseContract.Journals.TABLE_NAME, null, values);
                tableName = DatabaseContract.Journals.TABLE_NAME;
                break;

            case DatabaseContract.PRODUCT_PHOTOS:
                id = database.insert(DatabaseContract.Photos.TABLE_NAME, null, values);
                tableName = DatabaseContract.Photos.TABLE_NAME;
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
                rowsDeleted = database.delete(DatabaseContract.Products.TABLE_NAME,
                        selection, selectionArgs);
                break;
            case DatabaseContract.PRODUCT_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = database.
                            delete(DatabaseContract.Products.TABLE_NAME,
                                    DatabaseContract.Products.KEY_ID
                                            + "=" + id, null);
                } else {
                    rowsDeleted = database.delete(DatabaseContract.Products.TABLE_NAME,
                            DatabaseContract.Products.KEY_ID + "=" + id + " and " + selection, selectionArgs);
                }

                break;

            case DatabaseContract.JOURNALS:
                rowsDeleted = database.delete(DatabaseContract.Journals.TABLE_NAME,
                        selection, selectionArgs);
                break;
            case DatabaseContract.JOURNAL_ID:
                id = uri.getLastPathSegment();

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = database.
                            delete(DatabaseContract.Journals.TABLE_NAME,
                                    DatabaseContract.Journals.KEY_ID
                                            + "=" + id, null);
                } else {
                    rowsDeleted = database.delete(DatabaseContract.Journals.TABLE_NAME,
                            DatabaseContract.Journals.KEY_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;

            //TODO: Delete case for pictures

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        return 0;
    }
}
