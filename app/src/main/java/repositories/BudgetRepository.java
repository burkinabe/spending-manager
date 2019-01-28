package repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

import daos.BudgetDao;
import database.SpendingDatabase;
import entities.Budget;
import entities.Gain;

public class BudgetRepository {

    private BudgetDao budgetDao;
    private LiveData<List<Budget>> allBudgets;

    public BudgetRepository(Application application) {
        SpendingDatabase database = SpendingDatabase.getDatabase(application);
        budgetDao = database.budgetDao();
        allBudgets = budgetDao.getAll();
    }

    public LiveData<List<Budget>> getAllBudgets() {
//        try {
//            return new ListAsyncTask(budgetDao).execute().get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//            return null;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return null;
//        }

        return allBudgets;
    }

    public void insert(Budget budget) {
        try {
            new InsertAsyncTask(budgetDao).execute(budget).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Budget, Void, Void> {

        private BudgetDao budgetDao;

        InsertAsyncTask(BudgetDao budgetDao) {
            this.budgetDao = budgetDao;
        }

        @Override
        protected Void doInBackground(Budget... budgets) {
            budgetDao.insert(budgets[0]);
            return null;
        }
    }

    private static class ListAsyncTask extends AsyncTask<Void, Void, LiveData<List<Budget>>> {

        private BudgetDao budgetDao;

        ListAsyncTask(BudgetDao budgetDao) {
            this.budgetDao = budgetDao;
        }

        @Override
        protected LiveData<List<Budget>> doInBackground(Void... voids) {

            return budgetDao.getAll();
        }
    }
}
