package com.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.data.local.BoredDao;
import com.model.BoredAction;

@Database(
        entities = {BoredAction.class},
        version = BoredDataBase.VERSION,
        exportSchema = false
)
public abstract class BoredDataBase extends RoomDatabase {
    public final static int VERSION = 1;
    public abstract BoredDao boredDao();


}
