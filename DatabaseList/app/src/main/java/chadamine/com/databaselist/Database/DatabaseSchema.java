package chadamine.com.databaselist.Database;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by chadamine on 4/12/2015.
 */
public class DatabaseSchema {

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
            Plants.TABLE_CREATE,
            Substrates.TABLE_CREATE,
            Nutrients.TABLE_CREATE,
            NutrientConcentrations.TABLE_CREATE
            // TODO: add all tables
    };

    public static final int JOURNALS = 1;
    public static final int JOURNAL_ID = 2;

    public static final int NUTRIENTS = 3;
    public static final int NUTRIENT_ID = 4;

    public static final int NUTRIENT_CONCENTRATIONS = 13;
    public static final int NUTRIENT_CONCENTRATION_ID = 14;

    public static final int PHOTOS = 5;
    public static final int PHOTO_ID = 6;

    public static final int PLANTS = 7;
    public static final int PLANT_ID = 8;

    public static final int PRODUCTS = 9;
    public static final int PRODUCT_ID = 10;

    public static final int SUBSTRATES = 11;
    public static final int SUBSTRATE_ID = 12;

    // TODO: ADD REMAINING TABLES

    public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, Products.TABLE_NAME, PRODUCTS);
        URI_MATCHER.addURI(AUTHORITY, Products.TABLE_NAME + "/#", PRODUCT_ID);

        URI_MATCHER.addURI(AUTHORITY, Photos.TABLE_NAME, PHOTOS);
        URI_MATCHER.addURI(AUTHORITY, Photos.TABLE_NAME + "/#", PHOTO_ID);

        URI_MATCHER.addURI(AUTHORITY, Journals.TABLE_NAME, JOURNALS);
        URI_MATCHER.addURI(AUTHORITY, Journals.TABLE_NAME + "/#", JOURNAL_ID);

        URI_MATCHER.addURI(AUTHORITY, Plants.TABLE_NAME, PLANTS);
        URI_MATCHER.addURI(AUTHORITY, Plants.TABLE_NAME + "/#", PLANT_ID);

        URI_MATCHER.addURI(AUTHORITY, Substrates.TABLE_NAME, SUBSTRATES);
        URI_MATCHER.addURI(AUTHORITY, Substrates.TABLE_NAME + "/#", SUBSTRATE_ID);

        URI_MATCHER.addURI(AUTHORITY, Nutrients.TABLE_NAME, NUTRIENTS);
        URI_MATCHER.addURI(AUTHORITY, Nutrients.TABLE_NAME + "/#", NUTRIENT_ID);

        URI_MATCHER.addURI(AUTHORITY, NutrientConcentrations.TABLE_NAME, NUTRIENT_CONCENTRATIONS);
        URI_MATCHER.addURI(AUTHORITY, NutrientConcentrations.TABLE_NAME + "/#", NUTRIENT_CONCENTRATION_ID);
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

        public static final String DIR_PHOTOS = "Journals/";


        public static final String[] KEY_ID_ARRAY = { KEY_ID, KEY_NAME };

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseSchema.CONTENT_URI, TABLE_NAME);

        public static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_NAME + " TEXT"
                        + ");";

        public static final String DELETE = DTIE + TABLE_NAME;
    }

    public static final class Lineages implements BaseColumns {
    }

    public static final class Nutrients implements BaseColumns {

        public static final String TABLE_NAME = "nutrients";
        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_TIMESTAMP = "timestamp";
        public static final String KEY_PURITY = "purity";
        public static final String KEY_STATE = "state";
        public static final String KEY_FORM = "form";
        public static final String KEY_SOURCE = "source";

        public static final String DIR_PHOTOS = "Nutrients/";

        public static final String[] KEY_ID_ARRAY = { KEY_ID, KEY_NAME , KEY_TIMESTAMP, KEY_PURITY,
                KEY_STATE, KEY_FORM, KEY_SOURCE};

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(DatabaseSchema.CONTENT_URI, TABLE_NAME);

        public static final String TABLE_CREATE =
                CT + TABLE_NAME + OB
                        + KEY_ID + IPKA
                        + KEY_NAME + TXT
                        + KEY_TIMESTAMP + TXT
                        + KEY_PURITY + TXT
                        + KEY_STATE + TXT
                        + KEY_FORM + TXT
                        + KEY_SOURCE + TXF
                        + CLOSE;
    }

    public static final class NutrientConcentrations implements BaseColumns {

        public static final String TABLE_NAME = "nutrient_concentration";

        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";


        // SPECIAL COMPOUNDS
        // TODO: GET FROM COMPOUNDS TABLE?
        public static final String KEY_NO3 = "no3";
        public static final String KEY_NH4 = "nh4";
        public static final String KEY_P2O5 = "p2o5";
        public static final String KEY_K2O = "k2o";

        public static final String KEY_H = "h";
        public static final String KEY_HE = "he";
        public static final String KEY_LI = "li";
        public static final String KEY_BE = "be";
        public static final String KEY_B = "b";

        public static final String KEY_C = "c";
        public static final String KEY_N = "n";
        public static final String KEY_O = "o";
        public static final String KEY_F = "f";
        public static final String KEY_NE = "ne";

        public static final String KEY_NA = "na";
        public static final String KEY_MG = "mg";
        public static final String KEY_AL = "al";
        public static final String KEY_SI = "si";
        public static final String KEY_P = "p";

        public static final String KEY_S = "s";
        public static final String KEY_CL = "cl";
        public static final String KEY_AR = "ar";
        public static final String KEY_K = "k";
        public static final String KEY_CA = "ca";

        public static final String KEY_SC = "sc";
        public static final String KEY_TI = "ti";
        public static final String KEY_V = "v";
        public static final String KEY_CR = "cr";
        public static final String KEY_MN = "mn";

        public static final String KEY_FE = "fe";
        public static final String KEY_CO = "co";
        public static final String KEY_NI = "ni";
        public static final String KEY_CU = "cu";
        public static final String KEY_ZN = "zn";

        public static final String KEY_GA = "ga";
        public static final String KEY_GE = "ge";
        public static final String KEY_AS = "as";
        public static final String KEY_SE = "se";
        public static final String KEY_BR = "br";

        public static final String KEY_KR = "kr";
        public static final String KEY_RB = "rb";
        public static final String KEY_SR = "sr";
        public static final String KEY_Y = "y";
        public static final String KEY_ZR = "zr";

        public static final String KEY_NB = "nb";
        public static final String KEY_MO = "mo";

        public static final String DIR_PHOTOS = "NutrientConcentrations/";

        public static final String[] KEY_ID_ARRAY = { KEY_ID, KEY_NAME,
                KEY_H, KEY_HE, KEY_LI, KEY_BE, KEY_B,
                KEY_C, KEY_N, KEY_O, KEY_F, KEY_NE,
                KEY_NA, KEY_MG, KEY_AL, KEY_SI, KEY_P,
                KEY_S, KEY_CL, KEY_AR, KEY_K, KEY_CA,
                KEY_SC, KEY_TI, KEY_V, KEY_CR, KEY_MN,
                KEY_FE, KEY_CO, KEY_NI, KEY_CU, KEY_ZN,
                KEY_GA, KEY_GE, KEY_AS, KEY_SE, KEY_BR,
                KEY_KR, KEY_RB, KEY_SR, KEY_Y, KEY_ZR,
                KEY_NB, KEY_MO };

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseSchema.CONTENT_URI, TABLE_NAME);

        public static final String TABLE_CREATE =
                CT + TABLE_NAME + OB
                        + KEY_ID + IPKA
                        + KEY_NAME + TXF
                        + CLOSE;

        public static final String DELETE = DTIE + TABLE_NAME;
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
        public static final String KEY_SPECIES = "species";
        public static final String KEY_CULTIVAR = "cultivar";
        public static final String KEY_STAGE = "stage";
        public static final String KEY_AGE = "age";
        public static final String KEY_HEIGHT = "height";
        public static final String KEY_SUBSTRATE = "substrate";
        public static final String KEY_POTSIZE = "potSize";

        public static final String DIR_PHOTOS = Photos.DIR_PHOTOS + "Plants/";

        public static final String[] KEY_ID_ARRAY = { KEY_ID, KEY_NAME, KEY_SPECIES, KEY_CULTIVAR,
                KEY_STAGE, KEY_AGE, KEY_HEIGHT };

        public static final String[] KEY_ARRAY = { KEY_NAME, KEY_SPECIES, KEY_CULTIVAR,
                KEY_STAGE, KEY_AGE, KEY_HEIGHT };

        public static final String TABLE_CREATE =
                     CT + TABLE_NAME + OB
                        + KEY_ID + IPKA + KEY_NAME + TXT + KEY_SPECIES + TXT + KEY_CULTIVAR + TXT
                        + KEY_STAGE + TXT + KEY_AGE + TXT + KEY_HEIGHT + TXT
                             + KEY_SUBSTRATE + TXT
                             + KEY_POTSIZE + TXF
                        + CLOSE;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseSchema.CONTENT_URI, TABLE_NAME);

        public static final String DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final class Products implements BaseColumns {

        public static final String TABLE_NAME = "products";

        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_MANUFACTURER = "manufacturer";
        public static final String KEY_LINE = "line";
        public static final String KEY_TYPE = "type";

        public static final String DIR_PHOTOS = "Products/";

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

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseSchema.CONTENT_URI, TABLE_NAME);

        public static final String DELETE = DTIE + TABLE_NAME;
    }

    public static final class Photos implements BaseColumns {

        public static final String DATE_FORMAT = "yyyy_MM_Ddd_HH_mm_ss";

        public static final String DIR_PHOTOS = "/DatabaseListPictures/Photos/";

        public static final String EXTENSION_PNG = ".png";

        public static final String TABLE_NAME = "photos";
        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_DIRECTORY = "directory";
        public static final String KEY_TIMESTAMP = "timestamp";
        public static final String KEY_TYPE = "type";

        public static final String[] KEY_ID_ARRAY = {
                KEY_ID, KEY_NAME, KEY_DIRECTORY, KEY_TIMESTAMP, KEY_TYPE };

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseSchema.CONTENT_URI, TABLE_NAME);

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

        public static final String[] KEY_ID_ARRAY =
                { KEY_ID, KEY_NAME, KEY_MATERIAL, KEY_FORM, KEY_SIZE,
                        KEY_POROSITY, KEY_DENSITY, KEY_CEC};

        public static final String[] KEY_ARRAY =
                { KEY_NAME, KEY_MATERIAL, KEY_FORM, KEY_SIZE, KEY_POROSITY, KEY_DENSITY, KEY_CEC };

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

        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseSchema.CONTENT_URI, TABLE_NAME);

        public static final String DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static final class Tasks implements BaseColumns {
    }

    public static final class Taxonomies implements BaseColumns {
    }



}
