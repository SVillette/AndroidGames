package net.samael.villette.myapplication.models.Power4;

import android.content.Intent;

import net.samael.villette.myapplication.models.Game;

public class Power4 extends Game
{
    P4Case[][] board;

    public Power4(String name, int image, Intent intent)
    {
        super(name, image, intent);
    }
}
