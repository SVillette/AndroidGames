package net.samael.villette.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.adapters.GameAdapter;
import net.samael.villette.myapplication.adapters.OtherItemsAdapter;
import net.samael.villette.myapplication.classes.MyConstants;
import net.samael.villette.myapplication.listeners.OnGameItemClickMenuListener;
import net.samael.villette.myapplication.listeners.OnOtherItemsClickMenuListener;
import net.samael.villette.myapplication.models.Game;
import net.samael.villette.myapplication.models.MenuItem;

public class MenuActivity extends AppCompatActivity
{
    public Context context = MenuActivity.this;

    private GridView gamesGridView;
    private GridView otherItemsGridView;

    Game[] myGames;
    MenuItem[] othersItems;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        myGames = MyConstants.initGameArray(context);
        othersItems = MyConstants.initOthersItemArray(context);

        initGridViews();
    }

    protected void initGridViews()
    {
        gamesGridView = (GridView) findViewById(R.id.games_menu_grid_view);
        gamesGridView.setAdapter(new GameAdapter(context, R.layout.menu_grid, myGames));

        otherItemsGridView = (GridView) findViewById(R.id.others_menu_grid_view);
        otherItemsGridView.setAdapter(new OtherItemsAdapter(context, R.layout.menu_grid, othersItems));

        gamesGridView.setOnItemClickListener(new OnGameItemClickMenuListener(this, myGames));
        otherItemsGridView.setOnItemClickListener(new OnOtherItemsClickMenuListener(this, othersItems));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == MyConstants.BATTLESHIP_CODE)
        {
            if (resultCode == MyConstants.STATUS_OK)
            {
                Toast.makeText(context, data.getStringExtra(MyConstants.BS_END_STRING), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, resultCode, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "no request code", Toast.LENGTH_SHORT).show();
        }
    }
}
