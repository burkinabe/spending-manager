package viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import entities.Category;
import repositories.CategoryRepository;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository categoryRepository;
    private LiveData<List<Category>> allCategories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
        allCategories = categoryRepository.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    public void insert(Category category) {
        categoryRepository.insert(category);
    }
}
