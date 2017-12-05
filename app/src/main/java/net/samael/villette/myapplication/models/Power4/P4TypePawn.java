package net.samael.villette.myapplication.models.Power4;

import android.content.res.Resources;

import net.samael.villette.myapplication.R;

import java.util.ResourceBundle;

/**
 * Created by Samaël Villette on 04/10/2017.
 */

public class P4TypePawn
{
    private String color;
    private int image;

    public P4TypePawn(String color, int image)
    {
        this.color = color;
        this.image = image;
    }

    public P4TypePawn()
    {
        this.color = Resources.getSystem().getString(R.string.empty_color);
        this.image = R.drawable.p4_pawn_bg;
    }

    public String getColor()
    {
        return color;
    }

    public int getImage()
    {
        return image;
    }
}
