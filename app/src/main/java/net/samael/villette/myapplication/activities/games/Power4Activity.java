package net.samael.villette.myapplication.activities.games;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.Toast;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.classes.MyConstants;
import net.samael.villette.myapplication.models.Power4;

public class Power4Activity extends AppCompatActivity
{
    Intent fromMenuGame;

    Context context = Power4Activity.this;

    LinearLayoutCompat rootLinearLayout;

    Power4 game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.power4_activity);

        rootLinearLayout = (LinearLayoutCompat) findViewById(R.id.power4_linear_layout);

        fromMenuGame = getIntent();
    }
}
