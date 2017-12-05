package net.samael.villette.myapplication.listeners;

import android.content.Context;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Samaël Villette on 05/12/2017.
 */

public class P4AnimationListener implements Animation.AnimationListener
{
    private Context context;
    private ImageView[] imageViews;

    public P4AnimationListener(Context context, ImageView[] imageViews)
    {
        this.context = context;
        this.imageViews = imageViews;
    }

    @Override
    public void onAnimationStart(Animation animation)
    {

    }

    @Override
    public void onAnimationEnd(Animation animation)
    {
        Toast.makeText(context, "animation ended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationRepeat(Animation animation)
    {

    }
}
