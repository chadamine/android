package chadamine.com.databaselist.Database;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by chadamine on 4/12/2015.
 */
public class DatabaseContract {

    public static final String AUTHORITY = "chadamine.com.databaselist.database.provider";
    public static final String DATABASE_NAME = "example.db";
    public static final int DATABASE_VERSION = 1;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String DTIE = "DROP TABLE IF EXISTS ";
    public static final String CT = "CREATE TABLE ";
    public static final String OB = "(";
    public static final String IPKA = " INTEGER PRIMARY KEY AUTOINCREMENT,";
    public static final String TXT = " TEXT, ";
    public static final String TXF = " TEXT";
    public static final String CLOSE = ");";

    public static final String[] CREATE_TABLES = {
            Products.TABLE_CREATE,
            Photos.TABLE_CREATE,
            Journals.TABLE_CREATE,
            Plants.TABLE_CREATE
            // TODO: add all tables
    };

    public static final int PRODUCTS = 1;
    public static final int PRODUCT_ID = 2;

    public static final int PRODUCT_PHOTOS = 3;
    public static final int PRODUCT_PHOTO_ID = 4;

    public static final int JOURNALS = 5;
    public static final int JOURNAL_ID = 6;

    public static final int PLANTS = 7;
    public static final int PLANT_ID = 8;

    // TODO: ADD REMAINING TABLES

    public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, Products.TABLE_NAME, PRODUCTS);
        URI_MATCHER.addURI(AUTHORITY, Products.TABLE_NAME + "/#", PRODUCT_ID);

        URI_MATCHER.addURI(AUTHORITY, Photos.TABLE_NAME, PRODUCT_PHOTOS);
        URI_MATCHER.addURI(AUTHORITY, Photos.TABLE_NAME + "/#", PRODUCT_PHOTO_ID);

        URI_MATCHER.addURI(AUTHORITY, Journals.TABLE_NAME, JOURNALS);
        URI_MATCHER.addURI(AUTHORITY, Journals.TABLE_NAME + "/#", JOURNAL_ID);

        URI_MATCHER.addURI(AUTHORITY, Plants.TABLE_NAME, PLANTS);
        URI_MATCHER.addURI(AUTHORITY, Plants.TABLE_NAME + "/#", PLANT_ID);
        // TODO: ADD REMAINING TABLES TO URI_MATCHER

    }

    public static final class ChemicalCompounds implements BaseColumns {
    }

    public static final class ChemicalElements implements BaseColumns {
    }

    public static final class CultivationSystems implements BaseColumns {
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

    public static final class Lineages implements BaseColumns {
    }

    public static final class Nutrients implements BaseColumns {
    }

    public static final class NutrientRecipes implements BaseColumns {
    }

    public static final class NutrientSolutions implements BaseColumns {

    }

    public static final class Organisms implements BaseColumns {
    }

    public static final class PeriodicTable implements BaseColumns {
    }

    public static final class ProductManufacturers implements BaseColumns {
    }

    public static final class Plants implements BaseColumns {

        public static final String TABLE_NAME = "plants";

        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_CULTIVAR = "cultivar";
        public static final String KEY_CULTIGEN = "cultigen";
        public static final String KEY_HEIGHT = "height";
        public static final String KEY_IS_CULTIGEN = "isCultigen";
        public static final String KEY_IS_CULTIVAT = "isCultivar";


        public static final String[] KEY_ID_ARRAY = { KEY_ID, KEY_NAME, KEY_CULTIVAR,
                KEY_CULTIGEN, KEY_HEIGHT };

        public static final String[] KEY_ARRAY = { KEY_NAME };

        public static final String TABLE_CREATE =
                     CT + TABLE_NAME + OB
                        + KEY_ID + IPKA
                        + KEY_NAME + TXT
                        + KEY_CULTIVAR + TXT
                        + KEY_CULTIGEN + TXT
                        + KEY_HEIGHT + TXF
                        + CLOSE;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);

        public static final String DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final class Products implements BaseColumns {

        public static final String TABLE_NAME = "products";

        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_MANUFACTURER = "manufacturer";
        public static final String KEY_LINE = "line";
        public static final String KEY_TYPE = "type";

        public static final String[] KEY_ID_ARRAY = {
                KEY_ID, KEY_NAME, KEY_MANUFACTURER, KEY_LINE, KEY_TYPE };

        //public static final String[] KEY_ARRAY = { KEY_NAME, KEY_MANUFACTURER, KEY_LINE, KEY_TYPE };

        public static final String TABLE_CREATE =
                CT + TABLE_NAME + OB
                        + KEY_ID + IPKA
                        + KEY_NAME + TXT
                        + KEY_MANUFACTURER + TXT
                        + KEY_LINE + TXT
                        + KEY_TYPE + TXF
                        + CLOSE;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);

        public static final String DELETE = DTIE + TABLE_NAME;
    }

    public static final class Photos implements BaseColumns {

        public static final String DATE_FORMAT = "yyyy_MM_Ddd_HH_mm_ss";
        public static final String DIR_PRODUCT_PHOTOS = "/DatabaseListPictures/Photos/";
        public static final String EXTENSION_PNG = ".png";

        public static final String TABLE_NAME = "photos";
        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_DIRECTORY = "directory";
        public static final String KEY_TIMESTAMP = "timestamp";
        public static final String KEY_TYPE = "type";

        public static final String[] KEY_ID_ARRAY = {
                KEY_ID, KEY_NAME, KEY_DIRECTORY, KEY_TIMESTAMP, KEY_TYPE };

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);

        public static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_NAME + " TEXT, "
                        + KEY_DIRECTORY + " TEXT, "
                        + KEY_TIMESTAMP + " TEXT"
                        + ");";

        public static final String DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final class ProductSources implements BaseColumns {
    }

    public static final class Progresses implements BaseColumns {
    }

    public static final class Schedules implements BaseColumns {
    }

    public static final class Shippers implements BaseColumns {
    }

    public static final class ShippingCompanies implements BaseColumns {
    }

    public static final class Substrates implements BaseColumns {

        public static final String TABLE_NAME = "substrates";

        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_MATERIAL = "material";
        public static final String KEY_FORM = "form";
        public static final String KEY_SIZE = "size";
        public static final String KEY_POROSITY = "porosity";
        public static final String KEY_DENSITY = "density";
        public static final String KEY_CEC = "cec";

        public static final String[] KEY_ID_ARRAY = {
                KEY_ID, KEY_NAME, KEY_MATERIAL, KEY_FORM, KEY_SIZE };
        public static final String[] KEY_ARRAY = { KEY_NAME };

        public static final String TABLE_CREATE =
                CT+ TABLE_NAME + OB
                        + KEY_ID + IPKA
                        + KEY_NAME + TXT
                        + KEY_MATERIAL + TXT
                        + KEY_FORM + TXT
                        + KEY_SIZE + TXT
                        + KEY_POROSITY + TXT
                        + KEY_DENSITY + TXT
                        + KEY_CEC + TXF
                        + CLOSE;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContract.CONTENT_URI, TABLE_NAME);

        public static final String DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final class Tasks implements BaseColumns {
    }

    public static final class Taxonomies implements BaseColumns {
    }



}
