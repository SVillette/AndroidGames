package net.samael.villette.myapplication.adapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.samael.villette.myapplication.R;
import net.samael.villette.myapplication.models.Power4.P4TypePawn;

import java.util.ArrayList;

/**
 * Created by Samaël Villette on 04/10/2017.
 */

public class ColorSpinnerAdapter extends ArrayAdapter
{

    public class ViewHolderColorSpinnerAdapter
    {
        public ImageView imageView;
        public TextView textView;

        public ViewHolderColorSpinnerAdapter(ImageView imageView, TextView textView)
        {
            this.imageView = imageView;
            this.textView = textView;
        }
    }

    private Context context;
    private int layout;
    private ArrayList<P4TypePawn> typePawns;

    public ColorSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull ArrayList<P4TypePawn> typePawns)
    {
        super(context, resource, textViewResourceId, typePawns);
        Log.e("spinner", context + " " + resource  + " " + textViewResourceId + " " + typePawns);

        this.context = context;
        this.layout = resource;
        this.typePawns = typePawns;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        ViewHolderColorSpinnerAdapter viewHolder;

        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(this.layout, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.type_pawn_image);
            TextView textView = (TextView) view.findViewById(R.id.type_pawn_tv);

            viewHolder = new ViewHolderColorSpinnerAdapter(imageView, textView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderColorSpinnerAdapter) view.getTag();
        }

        viewHolder.imageView.setImageResource(typePawns.get(position).getImage());
        viewHolder.textView.setText(typePawns.get(position).getColor());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        ViewHolderColorSpinnerAdapter viewHolder;

        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(this.layout, null);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.type_pawn_image);
            TextView textView = (TextView) convertView.findViewById(R.id.type_pawn_tv);

            viewHolder = new ViewHolderColorSpinnerAdapter(imageView, textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderColorSpinnerAdapter) convertView    .getTag();
        }

        viewHolder.imageView.setImageResource(typePawns.get(position).getImage());
        viewHolder.textView.setText(typePawns.get(position).getColor());

        return convertView;
    }
}
