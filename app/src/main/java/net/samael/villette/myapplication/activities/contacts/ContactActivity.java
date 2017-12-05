package net.samael.villette.myapplication.activities.contacts;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.adapters.ContactListViewAdapter;
import net.samael.villette.myapplication.database.ContactDbHandler;
import net.samael.villette.myapplication.listeners.OnClickLinearListener;
import net.samael.villette.myapplication.models.Contact;

import org.apache.commons.lang3.StringUtils;

public class ContactActivity extends AppCompatActivity
{
    public Context context = ContactActivity.this;
    FloatingActionButton addContactButton;
    AlertDialog addContactDialog;

    CoordinatorLayout coordinatorLayout;

    ListView contactListView;

    ContactListViewAdapter contactAdapter;

    TextInputLayout contactNameLayout;
    TextInputEditText editContactName;
    TextInputLayout contactAddressLayout;
    TextInputEditText editContactAddress;
    TextInputLayout contactPhoneLayout;
    TextInputEditText editContactPhone;

    public static ContactDbHandler dbHandler;

    Cursor cContacts;

    Contact[] contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.contact_coordinator_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.contact_toolbar);
        setSupportActionBar(toolbar);

        addContactButton = (FloatingActionButton) findViewById(R.id.add_contact);
        addContactButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addContact();
            }
        });

        dbHandler = new ContactDbHandler(context);
        dbHandler.open();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contactListView = (ListView) findViewById(R.id.contact_list_view);

        contacts = initContacts();
        contactAdapter = new ContactListViewAdapter(context, R.layout.contact_list_view_item, contacts);
        contactListView.setAdapter(contactAdapter);
    }

    public Contact[] initContacts()
    {
        Cursor cursor = dbHandler.selectAllContacts();

        this.contacts = new Contact[cursor.getCount()];

        if (cursor.moveToFirst())
        {
            for (int i = 0; i < cursor.getCount(); i++)
            {
                this.contacts[i] = StringUtils.isNotBlank(cursor.getString(2))
                        ? new Contact(cursor.getString(1), cursor.getString(2), cursor.getString(3))
                        : new Contact(cursor.getString(1), cursor.getString(3));
                cursor.moveToNext();
            }
        }

        return this.contacts;
    }

    public void addContact()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.add_contact);

        LayoutInflater inflater = this.getLayoutInflater();

        View view = inflater.inflate(R.layout.add_contact_dialog, null);
        builder.setView(view);

        contactNameLayout = (TextInputLayout) view.findViewById(R.id.contact_name_layout);
        contactAddressLayout = (TextInputLayout) view.findViewById(R.id.contact_address_layout);
        contactPhoneLayout = (TextInputLayout) view.findViewById(R.id.contact_phone_layout);

        editContactName = (TextInputEditText) view.findViewById(R.id.edit_contact_name);
        editContactAddress = (TextInputEditText) view.findViewById(R.id.edit_contact_address);
        editContactPhone = (TextInputEditText) view.findViewById(R.id.edit_contact_phone);

        builder.setPositiveButton(R.string.validate, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });

        addContactDialog = builder.show();

        addContactDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name, address, phone;
                name = editContactName.getText().toString();
                address = editContactAddress.getText().toString();
                phone = editContactPhone.getText().toString();

                if (StringUtils.isNotBlank(name)) {
                    contactNameLayout.setErrorEnabled(false);

                    if (StringUtils.isNotBlank(phone)) {
                        contactPhoneLayout.setErrorEnabled(false);

                        Contact contact = StringUtils.isNotBlank(address)
                                ? new Contact(name, address, phone)
                                : new Contact(name, phone);

                        dbHandler.insertContact(contact);
                        contactAdapter.notifyDataSetChanged();
                        addContactDialog.hide();
                        Snackbar snackbar =
                                Snackbar.make(coordinatorLayout, R.string.contact_added, Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    } else {
                        contactPhoneLayout.setErrorEnabled(true);
                        contactPhoneLayout.setError(getResources().getString(R.string.phone_empty_error));
                    }
                } else {
                    contactNameLayout.setErrorEnabled(true);
                    contactNameLayout.setError(getResources().getString(R.string.name_empty_error));
                }
            }
        });
    }
}
