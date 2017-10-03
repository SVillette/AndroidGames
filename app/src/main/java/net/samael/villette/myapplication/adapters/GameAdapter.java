package net.samael.villette.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.models.Game;

public class GameAdapter extends ArrayAdapter
{

    public class ViewHolderGameAdapter
    {
        public TextView title;
        public ImageView image;

        public ViewHolderGameAdapter(TextView title, ImageView image)
        {
            this.title = title;
            this.image = image;
        }
    }

    private Context context;
    private Game[] games;
    private int layout;

    public GameAdapter(Context context, int resource, Game[] games)
    {
        super(context, resource, games);
        this.context = context;
        this.games = games;
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent)
    {
        ViewHolderGameAdapter viewHolderGameAdapter;

        if (view == null)
        {
            view = (LinearLayout) LayoutInflater.from(context).inflate(this.layout, null);

            ImageView image = (ImageView) view.findViewById(R.id.grid_image);
            TextView title = (TextView) view.findViewById(R.id.grid_title);

            viewHolderGameAdapter = new ViewHolderGameAdapter(title, image);
            view.setTag(viewHolderGameAdapter);
        } else {
            viewHolderGameAdapter = (ViewHolderGameAdapter) view.getTag();
        }

        Game game = games[position];

        viewHolderGameAdapter.title.setText(game.getName());
        viewHolderGameAdapter.image.setImageResource(game.getImage());

        return view;
    }
}
