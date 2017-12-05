package net.samael.villette.myapplication.managers;

import android.app.Activity;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.classes.MyConstants;
import net.samael.villette.myapplication.models.Power4.P4Case;
import net.samael.villette.myapplication.models.Power4.P4Pawn;
import net.samael.villette.myapplication.models.Power4.P4Player;

/**
 * Created by Samaël Villette on 03/10/2017.
 */

public class Power4GameManager
{
    Activity activity;

    LinearLayout rootLinear;
    LinearLayout[] linears;
    ImageView[][] imageViews;

    Snackbar playerTurnSnackbar;

    P4Case[][] board;
    P4Player[] players;


    public Power4GameManager()
    {
        initPower4Grid();
    }

    private void initPower4Grid()
    {
        this.board = new P4Case[MyConstants.P4_NB_COLUMNS][MyConstants.P4_NB_LINES];
        this.linears = new LinearLayout[MyConstants.P4_NB_COLUMNS];
        this.imageViews = new ImageView[MyConstants.P4_NB_COLUMNS][MyConstants.P4_NB_LINES];

        for (int i = 0; i < this.board.length; i++)
        {
            for (int j = 0; j < this.board[i].length; j++)
            {
                this.board[i][j] = new P4Case(i, j);
            }
        }
    }

    public LinearLayout[] drawGrid(LinearLayout linearLayout, Activity activity)
    {
        this.activity = activity;
        this.rootLinear = linearLayout;
        linearLayout.setPadding(0, 50, 0, 0);

        for (int i = 0; i < board.length; i++)
        {
            LinearLayout lineLinear = new LinearLayout(activity);
            lineLinear.setOrientation(LinearLayout.VERTICAL);
            int width = (int) activity.getResources().getDisplayMetrics().density * 60;
            LinearLayout.LayoutParams lp =
                    new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
            lineLinear.setLayoutParams(lp);
            linearLayout.addView(lineLinear);
            this.linears[i] = lineLinear;

            for (int j = 0; j < this.board[i].length; j++)
            {
                ImageView imageView = new ImageView(activity);
                LinearLayout.LayoutParams ilp = new LinearLayout.LayoutParams(170, 170);
                ilp.setMargins(15, 20, 15, 20);
                imageView.setLayoutParams(ilp);
                imageView.setImageResource(R.drawable.p4_pawn_bg);
                lineLinear.addView(imageView);
                this.imageViews[i][j] = imageView;
            }
        }

        return linears;
    }

    public void updateGrid()
    {
        for (int i = 0; i < this.board.length; i++)
        {
            for (int j = 0; j < this.board[i].length; j++)
            {
                int background = this.board[i][j].getPawn().getPlayer() == null
                        ? R.drawable.p4_pawn_bg
                        : this.board[i][j].getPawn().getPlayer().getTypePawn().getImage();
                imageViews[i][j].setImageResource(background);
            }
        }
    }

    public void initGame(P4Player[] players)
    {
        this.players = players;
        P4Player playerTurn = players[0];

        playerTurnSnackbar = Snackbar.make(rootLinear, String.format(
            activity.getResources().getString(R.string.player_turn), playerTurn.getName()
        ), BaseTransientBottomBar.LENGTH_INDEFINITE);

        playerTurnSnackbar.show();
    }

    public P4Player getPlayerTurn()
    {
        return this.players[0];
    }

    public void setPlayerTurn()
    {
        P4Player[] tmpPlayers = new P4Player[this.players.length];
        tmpPlayers[tmpPlayers.length - 1] = this.getPlayerTurn();

        for (int i = 0; i < this.players.length - 1; i++)
        {
            tmpPlayers[i] = this.players[i + 1];
        }

        this.players = tmpPlayers;

        playerTurnSnackbar.dismiss();
        playerTurnSnackbar = Snackbar.make(rootLinear, String.format(
                activity.getResources().getString(R.string.player_turn), this.getPlayerTurn().getName()
        ), BaseTransientBottomBar.LENGTH_INDEFINITE);
        playerTurnSnackbar.show();
    }

    public int getLinePlayable(int column)
    {
        for (int i = this.board[column].length - 1; i >= 0; i--)
        {
            if (this.board[column][i].getPawn().getPlayer() == null)
            {
                return i;
            }
        }

        return -1;
    }

    public ImageView[] play(int column)
    {
        P4Player player = this.getPlayerTurn();
        int line = this.getLinePlayable(column);
        if (line != -1)
        {
            P4Case playingCase = this.board[column][line];
            playingCase.setPawn(new P4Pawn(player));
            /*if (isFinished(playingCase))
            {
                this.end();
            }*/
            this.setPlayerTurn();
        }
        return new ImageView[] {imageViews[column][0], imageViews[column][line]};
    }

    public boolean isFinished(P4Case lastPawn)
    {
        boolean flag = false;

        for (int i = 0; i < this.board.length; i++)
        {
            if (board[0][i].getPawn().getPlayer() == null)
            {
                flag = true;
            }
        }

        if (flag) {
            return false;
        }

        int alignPawns = 0;

        do {
            flag = false;
            if (board[lastPawn.getX() + 1][lastPawn.getY()].getPawn().getPlayer() == lastPawn.getPawn().getPlayer())
            {
                alignPawns++;
            }
            if (board[lastPawn.getX() + 1][lastPawn.getY() + 1].getPawn().getPlayer() == lastPawn.getPawn().getPlayer())
            {
                alignPawns++;
            }
            if (board[lastPawn.getX() + 1][lastPawn.getY()].getPawn().getPlayer() == lastPawn.getPawn().getPlayer())
            {
                alignPawns++;
            }
            if (board[lastPawn.getX()][lastPawn.getY() + 1].getPawn().getPlayer() == lastPawn.getPawn().getPlayer())
            {
                alignPawns++;
            }
            if (board[lastPawn.getX() + 1][lastPawn.getY()].getPawn().getPlayer() == lastPawn.getPawn().getPlayer())
            {
                alignPawns++;
            }
        } while (!flag);
        return false;
    }

    public void end()
    {

    }
}
