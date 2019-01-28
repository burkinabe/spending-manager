package repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

import daos.DepenseDao;
import database.SpendingDatabase;
import entities.Depense;

public class DepenseRepository {

    private DepenseDao depenseDao;
    private LiveData<List<Depense>> allDepense;

    public DepenseRepository(Application application) {
        SpendingDatabase database = SpendingDatabase.getDatabase(application);
        depenseDao = database.depenseDao();
        allDepense = depenseDao.getAll();
    }

    public LiveData<List<Depense>> getAllDepense() {
        return allDepense;
    }

    public void insert(Depense depense) {
        try {
            new InsertAsyncTask(depenseDao).execute(depense).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Depense, Void, Void> {

        private DepenseDao depenseDao;

        InsertAsyncTask(DepenseDao depenseDao) {
            this.depenseDao = depenseDao;
        }

        @Override
        protected Void doInBackground(Depense... depenses) {
            depenseDao.insert(depenses[0]);
            return null;
        }
    }
}
