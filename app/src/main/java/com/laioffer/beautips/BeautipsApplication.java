package com.laioffer.beautips;

import android.app.Application;

import com.ashokvarma.gander.Gander;
import com.ashokvarma.gander.imdb.GanderIMDB;
import com.ashokvarma.gander.persistence.GanderPersistence;

public class BeautipsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Gander.setGanderStorage(GanderPersistence.getInstance(this));
        Gander.setGanderStorage(GanderIMDB.getInstance());
    }
}
