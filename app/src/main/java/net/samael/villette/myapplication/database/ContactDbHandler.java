package net.samael.villette.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.samael.villette.myapplication.models.Contact;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Samaël Villette on 04/12/2017.
 */

public class ContactDbHandler extends DAOBase
{
    private SQLiteDatabase db;

    public ContactDbHandler(Context context)
    {
        super(context);
        this.db = super.open();
    }

    public long insertContact(Contact contact)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHandler.CONTACT_NAME, contact.getName());
        if (StringUtils.isNotBlank(contact.getAddress()))
        {
            contentValues.put(DatabaseHandler.CONTACT_ADDRESS, contact.getAddress());
        }
        contentValues.put(DatabaseHandler.CONTACT_PHONE, contact.getPhone());
        return db.insert(DatabaseHandler.CONTACTS_TABLE, null, contentValues);
    }

    public Cursor selectAllContacts()
    {
        return mDb.rawQuery("SELECT " + DatabaseHandler.CONTACT_ID + " as _id, " +
                DatabaseHandler.CONTACT_NAME + ", " +
                DatabaseHandler.CONTACT_ADDRESS + ", " +
                DatabaseHandler.CONTACT_PHONE +
                " FROM " + DatabaseHandler.CONTACTS_TABLE, null);
    }
}
