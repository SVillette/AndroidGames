package net.samael.villette.myapplication.models;

import android.content.Intent;

/**
 * Created by Samaël Villette on 04/12/2017.
 */

public class MenuItem
{
    private String name;
    private int image;
    private Intent intent;

    public MenuItem (String name, int image, Intent intent)
    {
        this.name = name;
        this.image = image;
        this.intent = intent;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getImage()
    {
        return image;
    }

    public void setImage(int image)
    {
        this.image = image;
    }

    public Intent getIntent()
    {
        return intent;
    }
}
