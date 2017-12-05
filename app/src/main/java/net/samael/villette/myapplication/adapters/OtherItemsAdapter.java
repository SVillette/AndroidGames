package net.samael.villette.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.models.Game;
import net.samael.villette.myapplication.models.MenuItem;

/**
 * Created by Samaël Villette on 04/12/2017.
 */

public class OtherItemsAdapter extends ArrayAdapter
{

    public class ViewHolderGameAdapter
    {
        public TextView title;
        public ImageView image;

        public ViewHolderGameAdapter(TextView title, ImageView image)
        {
            this.title = title;
            this.image = image;
        }
    }

    private Context context;
    private MenuItem[] menuItems;
    private int layout;

    public OtherItemsAdapter(Context context, int resource, MenuItem[] menuItems)
    {
        super(context, resource, menuItems);
        this.context = context;
        this.menuItems = menuItems;
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent)
    {
        OtherItemsAdapter.ViewHolderGameAdapter viewHolderGameAdapter;

        if (view == null)
        {
            view = (LinearLayout) LayoutInflater.from(context).inflate(this.layout, null);

            ImageView image = (ImageView) view.findViewById(R.id.grid_image);
            TextView title = (TextView) view.findViewById(R.id.grid_title);

            viewHolderGameAdapter = new OtherItemsAdapter.ViewHolderGameAdapter(title, image);
            view.setTag(viewHolderGameAdapter);
        } else {
            viewHolderGameAdapter = (OtherItemsAdapter.ViewHolderGameAdapter) view.getTag();
        }

        MenuItem item = menuItems[position];

        viewHolderGameAdapter.title.setText(item.getName());
        viewHolderGameAdapter.image.setImageResource(item.getImage());

        return view;
    }
}
