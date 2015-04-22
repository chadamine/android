package chadamine.com.databaselist.Database;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by chadamine on 4/12/2015.
 */
public class DatabaseContract {

    public static final String AUTHORITY = "chadamine.com.databaselist.provider";
    public static final String DATABASE_NAME = "example.db";
    public static final int DATABASE_VERSION = 1;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String[] CREATE_TABLES = {
            Products.TABLE_CREATE,
            Journals.TABLE_CREATE
    };

    public static final class Products implements BaseColumns {

        public static final String TABLE_NAME = "products";

        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_MANUFACTURER = "manufacturer";
        public static final String KEY_LINE = "line";
        public static final String KEY_TYPE = "type";

        public static final String[] KEY_ID_ARRAY = { KEY_ID, KEY_NAME, KEY_MANUFACTURER, KEY_LINE, KEY_TYPE };
        public static final String[] KEY_ARRAY = { KEY_NAME };

        public static final String TABLE_CREATE =
                "CREATE TABLE "
                + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT, "
                + KEY_MANUFACTURER + " TEXT, "
                + KEY_LINE + " TEXT, "
                + KEY_TYPE + " TEXT"

                + ");";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);

        public static final String DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final class Journals implements BaseColumns {
        public static final String TABLE_NAME = "journals";
        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";

        public static final String[] KEY_ID_ARRAY = { KEY_ID, KEY_NAME };

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);

        public static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT"
                + ");";
    }

    public static final int PRODUCTS = 1;
    public static final int PRODUCT_ID = 2;

    public static final int JOURNALS = 3;
    public static final int JOURNAL_ID = 4;

    public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, Products.TABLE_NAME, PRODUCTS);
        URI_MATCHER.addURI(AUTHORITY, Products.TABLE_NAME + "/#", PRODUCT_ID);

        URI_MATCHER.addURI(AUTHORITY, Journals.TABLE_NAME, JOURNALS);
        URI_MATCHER.addURI(AUTHORITY, Journals.TABLE_NAME + "/#", JOURNAL_ID);
    }
}
