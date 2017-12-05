package net.samael.villette.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.models.Game;
import net.samael.villette.myapplication.models.Image;

/**
 * Created by Samaël Villette on 05/12/2017.
 */

public class PhotoAdapter extends ArrayAdapter
{

    public class ViewHolderPhotoAdapter
    {
        public ImageView image;

        public ViewHolderPhotoAdapter(ImageView image)
        {
            this.image = image;
        }
    }

    private Context context;
    private int layout;
    private Image[] images;

    public PhotoAdapter(@NonNull Context context, int layout, @NonNull Image[] images)
    {
        super(context, layout, images);
        this.context = context;
        this.layout = layout;
        this.images = images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolderPhotoAdapter viewHolderPhotoAdapter;

        if (convertView == null)
        {
            convertView = (LinearLayout) LayoutInflater.from(context).inflate(this.layout, null);

            ImageView image = (ImageView) convertView.findViewById(R.id.grid_item_image);

            viewHolderPhotoAdapter = new ViewHolderPhotoAdapter(image);
            convertView.setTag(viewHolderPhotoAdapter);
        } else {
            viewHolderPhotoAdapter = (ViewHolderPhotoAdapter) convertView.getTag();
        }

        Image image = images[position];

        viewHolderPhotoAdapter.image.setImageResource(image.getResource());

        return convertView;
    }
}
