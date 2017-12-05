package net.samael.villette.myapplication.listeners;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import net.samael.villette.myapplication.managers.Power4GameManager;

/**
 * Created by Samaël Villette on 07/11/2017.
 */

public class OnClickLinearListener implements View.OnClickListener
{
    private Context context;
    private Power4GameManager gameManager;
    private int position;

    public OnClickLinearListener(Context context, Power4GameManager gameManager, int position)
    {
        this.context = context;
        this.gameManager = gameManager;
        this.position = position;
    }

    @Override
    public void onClick(View view)
    {
        gameManager.play(this.position);
        gameManager.updateGrid();
    }
}
