package net.samael.villette.myapplication.classes;

import android.content.Context;
import android.content.Intent;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.activities.contacts.ContactActivity;
import net.samael.villette.myapplication.activities.games.BattleShipActivity;
import net.samael.villette.myapplication.activities.games.CheckersActivity;
import net.samael.villette.myapplication.activities.games.ChessActivity;
import net.samael.villette.myapplication.activities.games.HorsesActivity;
import net.samael.villette.myapplication.activities.games.Power4Activity;
import net.samael.villette.myapplication.activities.games.SudokuActivity;
import net.samael.villette.myapplication.activities.photos.PhotoActivity;
import net.samael.villette.myapplication.models.Game;
import net.samael.villette.myapplication.models.MenuItem;
import net.samael.villette.myapplication.models.Power4.Power4;


public class MyConstants
{
    public final static String MY_LOGIN = "";
    public final static String MY_PASSWORD = "";

    public final static String KEY_PREFERENCES = "Game_Preferences";
    public final static String KEY_LOGIN = "Login";
    public final static String KEY_PASSWORD = "Password";

    public final static int BATTLESHIP_CODE = 999;

    public final static int STATUS_OK = 1;
    public final static String BS_END_STRING = "bs_end_string";

    public final static int P4_NB_PLAYERS = 2;

    public final static int P4_NB_COLUMNS = 7;
    public final static int P4_NB_LINES = 6;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 789;
    public static final int STORAGE_PERMISSIONS_CODE = 788;
    public static final int CAMERA_INTENT_CODE = 998;

    public static Game[] initGameArray(Context context)
    {
        Game checkers = new Game(context.getResources().getString(R.string.checkers), R.drawable.checkers, new Intent(context, CheckersActivity.class));
        Game sudoku = new Game(context.getResources().getString(R.string.sudoku), R.drawable.sudoku, new Intent(context, SudokuActivity.class));
        Game power4 = new Power4(context.getResources().getString(R.string.power4), R.drawable.power4, new Intent(context, Power4Activity.class));
        Game horses = new Game(context.getResources().getString(R.string.horses), R.drawable.horses, new Intent(context, HorsesActivity.class));
        Game battleship = new Game(context.getResources().getString(R.string.naval_war), R.drawable.battleship, new Intent(context, BattleShipActivity.class));
        Game chess = new Game(context.getResources().getString(R.string.chess), R.drawable.chess, new Intent(context, ChessActivity.class));

        return new Game[]{checkers, sudoku, power4, horses, battleship, chess};
    }

    public static MenuItem[] initOthersItemArray(Context context)
    {
        MenuItem contacts = new MenuItem(context.getResources().getString(R.string.contact), R.drawable.contacts, new Intent(context, ContactActivity.class));
        MenuItem photo = new MenuItem(context.getResources().getString(R.string.photo), R.drawable.camera, new Intent(context, PhotoActivity.class));
        return new MenuItem[]{contacts, photo};
    }
}
