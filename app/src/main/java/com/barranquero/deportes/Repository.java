package com.barranquero.deportes;

import android.content.Context;

import com.barranquero.deportes.model.Sport;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class which contains the data
 */
public class Repository {
    private static Repository instance;
    private static ArrayList<Sport> sports;
    private static Context context;

    public static Repository getInstance(Context c) {
        if (instance == null) {
            context = c;
            instance = new Repository();
        }
        return instance;
    }

    private Repository() {
        sports = new ArrayList<>();
        sports.add(new Sport(0, R.drawable.icon_basketball, context.getResources().getString(R.string.sp_basket)));
        sports.add(new Sport(1, R.drawable.icon_boxing, context.getResources().getString(R.string.sp_boxing)));
        sports.add(new Sport(2, R.drawable.icon_chess, context.getResources().getString(R.string.sp_chess)));
        sports.add(new Sport(3, R.drawable.icon_cycling, context.getResources().getString(R.string.sp_cycling)));
        sports.add(new Sport(4, R.drawable.icon_motor, context.getResources().getString(R.string.sp_f1)));
        sports.add(new Sport(5, R.drawable.icon_motorbike, context.getResources().getString(R.string.sp_motorbike)));
        sports.add(new Sport(6, R.drawable.icon_soccer, context.getResources().getString(R.string.sp_soccer)));
        sports.add(new Sport(7, R.drawable.icon_volleyball, context.getResources().getString(R.string.sp_volley)));
    }

    public List<Sport> getSports() {
        return sports;
    }
}