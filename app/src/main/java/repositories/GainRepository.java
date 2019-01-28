package repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import daos.GainDao;
import database.SpendingDatabase;
import entities.Gain;

public class GainRepository {

    private GainDao gainDao;
    private LiveData<List<Gain>> allGains;

    public GainRepository(Application application) {
        SpendingDatabase spendingDatabase = SpendingDatabase.getDatabase(application);
        gainDao = spendingDatabase.gainDao();
        allGains = gainDao.getAll();
    }

    public LiveData<List<Gain>> getAllGains() {
        return allGains;
    }


    public void insert(Gain gain) {
        try {
            new InsertAsyncTask(gainDao).execute(gain).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Gain, Void, Void> {

        private GainDao gainDao;

        InsertAsyncTask(GainDao gainDao) {
            this.gainDao = gainDao;
        }

        @Override
        protected Void doInBackground(Gain... gains) {
            gainDao.insert(gains[0]);
            return null;
        }
    }
}
