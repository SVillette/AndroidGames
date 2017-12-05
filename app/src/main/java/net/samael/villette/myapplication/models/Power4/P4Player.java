package net.samael.villette.myapplication.models.Power4;

/**
 * Created by Samaël Villette on 04/10/2017.
 */

public class P4Player
{
    private String name;
    private P4TypePawn typePawn;

    public P4Player(String name, P4TypePawn typePawn)
    {
        this.name = name;
        this.typePawn = typePawn;
    }

    public P4Player(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public P4TypePawn getTypePawn()
    {
        return typePawn;
    }
}
