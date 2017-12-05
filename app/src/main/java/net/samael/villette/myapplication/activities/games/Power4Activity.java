package net.samael.villette.myapplication.activities.games;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.adapters.ColorSpinnerAdapter;
import net.samael.villette.myapplication.classes.MyConstants;
import net.samael.villette.myapplication.listeners.OnClickLinearListener;
import net.samael.villette.myapplication.managers.Power4GameManager;
import net.samael.villette.myapplication.models.Power4.P4Player;
import net.samael.villette.myapplication.models.Power4.P4TypePawn;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class Power4Activity extends AppCompatActivity
{
    Context context = Power4Activity.this;
    Resources res;

    Intent fromMenuGame;

    LinearLayout rootLinearLayout;
    LinearLayout boardLinear;

    AlertDialog initPlayerDialog;
    AlertDialog endGameDialog;

    TextInputLayout playerNameLayout;
    TextInputEditText editPlayerName;
    Spinner colorSpinner;

    Power4GameManager gameManager;

    LinearLayout[] linears;

    ArrayList<P4Player> players;
    ArrayList<P4TypePawn> pawns;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.power4_activity);

        rootLinearLayout = (LinearLayout) findViewById(R.id.power4_linear_layout);

        gameManager = new Power4GameManager();

        linears = gameManager.drawGrid(rootLinearLayout, this);

        res = getResources();

        players = new ArrayList<>();

        initPawns();
        initPlayers();
    }

    protected void initPawns()
    {
        pawns = new ArrayList<>();
        pawns.add(new P4TypePawn(res.getString(R.string.red), R.drawable.red_pawns));
        pawns.add(new P4TypePawn(res.getString(R.string.yellow), R.drawable.yellow_pawns));
        pawns.add(new P4TypePawn(res.getString(R.string.blue), R.drawable.blue_pawns));
        pawns.add(new P4TypePawn(res.getString(R.string.green), R.drawable.green_pawns));
    }

    protected void initPlayers()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(res.getString(R.string.ask_player_name) + " " + String.valueOf(players.size() + 1));

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.add_players_dialog, null);
        builder.setView(view);

        playerNameLayout = (TextInputLayout) view.findViewById(R.id.player_name_layout);
        editPlayerName = (TextInputEditText) view.findViewById(R.id.edit_player_name);

        colorSpinner = (Spinner) view.findViewById(R.id.color_spinner);
        colorSpinner.setAdapter(new ColorSpinnerAdapter(context, R.layout.color_spinner, R.id.type_pawn_tv, pawns));

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

        builder.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            @Override
            public void onCancel(DialogInterface dialogInterface)
            {
                if (players.size() == 0)
                {
                    finish();
                } else {
                    pawns.add(players.get(players.size() - 1).getTypePawn());
                    players.remove(players.size() - 1);
                    initPlayers();
                }
            }
        });

        initPlayerDialog = builder.create();
        initPlayerDialog.show();

        initPlayerDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String playerName = editPlayerName.getText().toString();
                int colorSelected = colorSpinner.getSelectedItemPosition();

                if (StringUtils.isNotBlank(playerName))
                {
                    playerNameLayout.setErrorEnabled(false);

                    P4Player player = new P4Player(playerName, pawns.get(colorSelected));
                    pawns.remove(colorSelected);
                    players.add(player);

                    initPlayerDialog.dismiss();

                    if (players.size() < MyConstants.P4_NB_PLAYERS)
                    {
                        initPlayers();
                    } else {
                        manageGame();
                    }
                } else {
                    playerNameLayout.setErrorEnabled(true);
                    playerNameLayout.setError(res.getString(R.string.name_empty_error));
                }
            }
        });
    }

    protected void manageGame()
    {
        gameManager.initGame(players.toArray(new P4Player[players.size()]));

        for (int i = 0; i < linears.length; i++)
        {
            linears[i].setOnClickListener(new OnClickLinearListener(context, gameManager, i));
        }
    }
}
