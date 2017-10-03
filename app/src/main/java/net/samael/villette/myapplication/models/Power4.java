package net.samael.villette.myapplication.models;

import android.content.Intent;

import net.samael.villette.myapplication.classes.MyConstants;

public class Power4 extends Game
{
    P4Case[] board;

    public Power4(String name, int image, Intent intent)
    {
        super(name, image, intent);

    }

    private void initPower4Grid()
    {
        for (int i = 0; i < MyConstants.P4_NB_LINES; i++)
        {
            for (int j = 0; j < MyConstants.P4_NB_COLUMNS; j++)
            {

            }
        }
    }
}
