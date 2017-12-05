package net.samael.villette.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Samaël Villette on 04/12/2017.
 */

public class DAOBase
{
    protected final static int VERSION = 5;

    protected final static String NAME = "net.samael.villete.myapplication.database.db";

    public static SQLiteDatabase mDb = null;

    public static DatabaseHandler mHandler = null;

    public DAOBase(Context context)
    {
        mHandler = new DatabaseHandler(context, NAME, null, VERSION);
    }

    public SQLiteDatabase getDb()
    {
        return mDb;
    }

    public SQLiteDatabase open()
    {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close()
    {
        mDb.close();
    }

    public void commit()
    {
        mDb.beginTransactionNonExclusive();
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }
}
