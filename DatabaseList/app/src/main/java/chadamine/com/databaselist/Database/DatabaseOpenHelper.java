package chadamine.com.databaselist.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chadamine on 4/10/2015.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public DatabaseOpenHelper(Context c) {
        super(c, DatabaseSchema.DATABASE_NAME, null, DatabaseSchema.DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        for(String create : DatabaseSchema.CREATE_TABLES)
            db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
