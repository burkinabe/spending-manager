package daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import entities.Budget;

@Dao
public interface BudgetDao {

    @Insert
    void insert(Budget budget);

    @Update
    void update(Budget budget);

    @Delete
    void delete(Budget budget);

    @Query("DELETE FROM Budget")
    void deleteAll();

    @Query("SELECT * FROM Budget")
    LiveData<List<Budget>> getAll();
}
