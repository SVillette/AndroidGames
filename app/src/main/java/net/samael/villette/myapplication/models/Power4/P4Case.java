package net.samael.villette.myapplication.models.Power4;

/**
 * Created by Samaël Villette on 03/10/2017.
 */

public class P4Case
{
    private int x;
    private int y;
    private P4Pawn pawn;

    public P4Case(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.pawn = new P4Pawn();
    }

    public P4Case(int x, int y, P4Pawn pawn)
    {

        this.x = x;
        this.y = y;
        this.pawn = pawn;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public P4Pawn getPawn() { return this.pawn; }

    public void setPawn(P4Pawn pawn) { this.pawn = pawn; }
}
