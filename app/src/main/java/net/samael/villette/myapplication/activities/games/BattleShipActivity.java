package net.samael.villette.myapplication.activities.games;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.activities.MenuActivity;
import net.samael.villette.myapplication.classes.MyConstants;

public class BattleShipActivity extends AppCompatActivity
{
    protected Context context = BattleShipActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_ship_activity);
    }

    @Override
    public void onBackPressed()
    {
        Intent backToMenu = new Intent();
        backToMenu.putExtra(MyConstants.BS_END_STRING, "La guerre est terminée");
        setResult(MyConstants.STATUS_OK, backToMenu);
        finish();
    }
}
