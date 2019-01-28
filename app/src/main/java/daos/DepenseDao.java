package daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import entities.Depense;

@Dao
public interface DepenseDao {

    @Insert
    void insert(Depense depense);

    @Update
    void update(Depense depense);

    @Delete
    void delete(Depense depense);

    @Query("DELETE FROM Depense")
    void deleteAll();

    @Query("SELECT * FROM Depense")
    LiveData<List<Depense>> getAll();
}
