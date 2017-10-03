package net.samael.villette.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.adapters.GameAdapter;
import net.samael.villette.myapplication.classes.MyConstants;
import net.samael.villette.myapplication.listeners.OnItemClickMenuListener;
import net.samael.villette.myapplication.models.Game;

public class MenuActivity extends AppCompatActivity
{
    public Context context = MenuActivity.this;

    private GridView gridView;

    Game[] myGames;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        myGames = MyConstants.initGameArray(context);

        initGridView();
    }

    protected void initGridView()
    {
        gridView = (GridView) findViewById(R.id.menu_grid_view);
        gridView.setAdapter(new GameAdapter(context, R.layout.menu_grid, myGames));

        gridView.setOnItemClickListener(new OnItemClickMenuListener(this, myGames));
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
