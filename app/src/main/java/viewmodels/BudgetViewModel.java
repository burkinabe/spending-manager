package viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import entities.Budget;
import repositories.BudgetRepository;

public class BudgetViewModel extends AndroidViewModel {

    private BudgetRepository budgetRepository;
    private LiveData<List<Budget>> allBudgets;


    public BudgetViewModel(@NonNull Application application) {
        super(application);
        budgetRepository = new BudgetRepository(application);
        allBudgets = budgetRepository.getAllBudgets();
    }

    public LiveData<List<Budget>> getAllBudgets() {
        return allBudgets;
    }

    public void insert(Budget budget) {
        budgetRepository.insert(budget);
    }
}
