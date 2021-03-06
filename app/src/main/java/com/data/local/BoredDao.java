package com.data.local;

import android.view.View;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.model.BoredAction;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface BoredDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BoredAction boredAction);


    @Query("SELECT * FROM bored_action WHERE ukey IS :key")
    BoredAction get(String key);

    @Query("SELECT * FROM bored_action")
    List<BoredAction> getAll();

    @Delete
    void delete(BoredAction boredAction);


}
