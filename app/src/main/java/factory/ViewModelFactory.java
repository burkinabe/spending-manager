package factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import viewmodels.BudgetViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private BudgetViewModel dataViewModel;

    @Inject
    public ViewModelFactory(BudgetViewModel dataViewModel) {
        this.dataViewModel = dataViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BudgetViewModel.class)) {
            return (T) dataViewModel;
        }

        throw new IllegalArgumentException("Unknown class name");
    }
}
