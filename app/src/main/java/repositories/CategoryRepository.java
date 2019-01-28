package repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import daos.CategoryDao;
import database.SpendingDatabase;
import entities.Category;

public class CategoryRepository {

    private CategoryDao categoryDao;
    private LiveData<List<Category>> allCategories;

    public CategoryRepository(Application application) {
        SpendingDatabase database = SpendingDatabase.getDatabase(application);
        categoryDao = database.categoryDao();
        allCategories = categoryDao.getAll();
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }


    public void insert(Category category) {
        try {
            new InsertAsyncTask(categoryDao).execute(category).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDao categoryDao;

        InsertAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.insert(categories[0]);
            return null;
        }
    }
}
