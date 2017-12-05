package net.samael.villette.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.models.Contact;

/**
 * Created by Samaël Villette on 04/12/2017.
 */

public class ContactListViewAdapter extends ArrayAdapter
{
    public class ViewHolderContactAdapter
    {
        public TextView name;
        public TextView address;
        public TextView phone;

        public ViewHolderContactAdapter(TextView name, TextView address, TextView phone)
        {
            this.name = name;
            this.address = address;
            this.phone = phone;
        }

        public ViewHolderContactAdapter(TextView name, TextView phone)
        {
            this.name = name;
            this.phone = phone;
        }
    }

    private Context context;
    private int layout;
    private Contact[] contacts;

    public ContactListViewAdapter(Context context, int layout, Contact[] contacts)
    {
        super(context, layout, contacts);
        this.context = context;
        this.layout = layout;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolderContactAdapter viewHolderContactAdapter;

        if (convertView == null)
        {
            convertView = (LinearLayout) LayoutInflater.from(this.context).inflate(this.layout, null);

            TextView name = (TextView) convertView.findViewById(R.id.contact_name);
            TextView address = (TextView) convertView.findViewById(R.id.contact_address);
            TextView phone = (TextView) convertView.findViewById(R.id.contact_phone);

            viewHolderContactAdapter = new ViewHolderContactAdapter(name, address, phone);
            convertView.setTag(viewHolderContactAdapter);
        } else {
            viewHolderContactAdapter = (ViewHolderContactAdapter) convertView.getTag();
        }

        Contact contact = contacts[position];

        viewHolderContactAdapter.name.setText(contact.getName());
        viewHolderContactAdapter.address.setText(contact.getAddress());
        viewHolderContactAdapter.phone.setText(contact.getPhone());

        return convertView;
    }
}
