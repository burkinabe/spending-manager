package daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import entities.Gain;

@Dao
public interface GainDao {

    @Insert
    void insert(Gain gain);

    @Update
    void update(Gain gain);

    @Delete
    void delete(Gain gain);

    @Query("DELETE FROM Gain")
    void deleteAll();

    @Query("SELECT * FROM Gain")
    LiveData<List<Gain>> getAll();
}
