package net.samael.villette.myapplication.listeners;

import android.content.Context;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.samael.villette.myapplication.R;
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
        ImageView[] imageViewsAnimation = gameManager.play(this.position);
        TranslateAnimation animation = new TranslateAnimation(
                imageViewsAnimation[0].getX(), imageViewsAnimation[1].getX(),
                imageViewsAnimation[0].getY(), imageViewsAnimation[1].getY());
        animation.setDuration(300);
        animation.setFillAfter(false);
        animation.setAnimationListener(new P4AnimationListener(context, imageViewsAnimation));
        imageViewsAnimation[0].startAnimation(animation);
        gameManager.updateGrid();
    }
}
