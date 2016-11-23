package com.barranquero.deportes.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.barranquero.deportes.R;
import com.barranquero.deportes.Repository;
import com.barranquero.deportes.model.Sport;

import java.util.List;

/**
 * Adapter for the ListActivity
 */
public class SportAdapter extends ArrayAdapter<Sport> {
    private Context context;
    List<Sport> sportList;

    /**
     * Constructor
     * @param context Current context
     */
    public SportAdapter(Context context) {
        super(context, R.layout.item_sport);
        this.context = context;
        sportList = Repository.getInstance(context).getSports();
    }

    @Override
    public int getCount() {
        return sportList.size();
    }

    @Nullable
    @Override
    public Sport getItem(int position) {
        return sportList.get(position);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater layoutInflater = (LayoutInflater.from(context));
        final SportHolder holder;
        if (view == null) {
            holder = new SportHolder();
            view = layoutInflater.inflate(R.layout.item_sport, parent, false);
            holder.imgSport = (ImageView) view.findViewById(R.id.imgSport);
            holder.txvSport = (TextView) view.findViewById(R.id.txvSport);
            holder.cbxLike = (CheckBox) view.findViewById(R.id.cbxLike);
            holder.cbxLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    sportList.get(position).setLiked(b);
                }
            });
            view.setTag(holder);
        } else {
            holder = (SportHolder) view.getTag();
        }
        holder.imgSport.setImageResource(sportList.get(position).getImageId());
        holder.txvSport.setText(sportList.get(position).getName());
        readPrefs(position);
        holder.cbxLike.setChecked(sportList.get(position).isLiked());

        return view;
    }

    /**
     * Method which reads whether an sport is liked or not
     *
     * @param position The current position in the sports list
     */
    private void readPrefs(int position) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(context.getResources().getString(R.string.pref_file), Context.MODE_PRIVATE);
        String id;
        if (sharedPrefs.contains(id = Integer.toString(sportList.get(position).getId()))) {
            sportList.get(position).setLiked(sharedPrefs.getBoolean(id, false));
        }
    }

    /**
     * Method which records if a sport is liked or disliked in a shared preferences file.
     * Whether a sport is disliked is saved as well, just in case you like a sport, and in the future, you don't anymore, so this value can be changed.
     */
    public void savePrefs() {
        SharedPreferences sharedPrefs = context.getSharedPreferences(context.getResources().getString(R.string.pref_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        for (Sport sport :
                sportList) {
            editor.putBoolean(Integer.toString(sport.getId()), sport.isLiked());
        }
        editor.commit();
    }

    public class SportHolder {
        public ImageView imgSport;
        public TextView txvSport;
        public CheckBox cbxLike;
    }
}
