package net.samael.villette.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Samaël Villette on 04/12/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper
{
    public static final String CONTACTS_TABLE = "contacts";
    public static final String CONTACT_ID = "id";
    public static final String CONTACT_NAME = "name";
    public static final String CONTACT_ADDRESS = "address";
    public static final String CONTACT_PHONE = "phone";

    public static final String CONTACT_TABLE_CREATE = "CREATE TABLE " + CONTACTS_TABLE + " (" +
            CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CONTACT_NAME + " TEXT NOT NULL," +
            CONTACT_ADDRESS + " TEXT," +
            CONTACT_PHONE + " TEXT NOT NULL);";

    public static final String CONTACT_TABLE_DROP = "DROP TABLE " + CONTACTS_TABLE + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CONTACT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int odlVersion, int newVersion)
    {

    }
}
