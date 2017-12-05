package net.samael.villette.myapplication.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import net.samael.villette.myapplication.models.MenuItem;

/**
 * Created by Samaël Villette on 04/12/2017.
 */

public class OnOtherItemsClickMenuListener implements AdapterView.OnItemClickListener
{
    private Activity activity;
    private MenuItem[] menuItems;

    public OnOtherItemsClickMenuListener(Activity activity, MenuItem[] menuItems)
    {
        this.activity = activity;
        this.menuItems = menuItems;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Intent toOthersItemsActivity = menuItems[i].getIntent();

        this.activity.startActivity(toOthersItemsActivity);
    }
}
