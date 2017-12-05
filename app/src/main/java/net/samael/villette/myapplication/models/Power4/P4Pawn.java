package net.samael.villette.myapplication.models.Power4;

/**
 * Created by Samaël Villette on 03/10/2017.
 */

public class P4Pawn
{
    private P4Player player;

    public P4Pawn(P4Player player)
    {
        this.player = player;
    }

    public P4Pawn()
    {
        this.player = null;
    }

    public P4Player getPlayer()
    {
        return player;
    }

    public void setPlayer(P4Player player)
    {
        this.player = player;
    }
}
