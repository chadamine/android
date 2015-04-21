package chadamine.com.databaselist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chadamine on 4/10/2015.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */

    public DatabaseOpenHelper(Context c) {
        super(c, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        for(String create:DatabaseContract.CREATE_TABLES)
            db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
