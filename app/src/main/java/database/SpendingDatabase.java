package database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import daos.BudgetDao;
import daos.CategoryDao;
import daos.DepenseDao;
import daos.GainDao;
import entities.Budget;
import entities.Category;
import entities.Depense;
import entities.Gain;

@Database(entities = {Budget.class, Depense.class, Category.class, Gain.class}, version = 2, exportSchema = false)
public abstract class SpendingDatabase extends RoomDatabase {

    public abstract GainDao gainDao();
    public abstract DepenseDao depenseDao();
    public abstract CategoryDao categoryDao();
    public abstract BudgetDao budgetDao();

    private static volatile SpendingDatabase INSTANCE;

    public static SpendingDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SpendingDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SpendingDatabase.class, "spending_database")
                            .addCallback(databaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static SpendingDatabase.Callback databaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
