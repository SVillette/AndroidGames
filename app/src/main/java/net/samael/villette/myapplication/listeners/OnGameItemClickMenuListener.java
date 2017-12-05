package net.samael.villette.myapplication.listeners;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import net.samael.villette.myapplication.activities.games.BattleShipActivity;
import net.samael.villette.myapplication.classes.MyConstants;
import net.samael.villette.myapplication.models.Game;

public class OnGameItemClickMenuListener implements AdapterView.OnItemClickListener
{
    private Activity activity;
    private Game[] games;

    public OnGameItemClickMenuListener(Activity activity, Game[] games)
    {
        this.activity = activity;
        this.games = games;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
//        Game game = ((Game) adapterView.getAdapter().getItem(i));
        Intent toGameActivity = games[i].getIntent();

        this.activity.startActivity(toGameActivity);
    }
}
