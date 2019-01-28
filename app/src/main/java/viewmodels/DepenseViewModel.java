package viewmodels;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import entities.Depense;
import repositories.DepenseRepository;

public class DepenseViewModel extends AndroidViewModel {

    private DepenseRepository depenseRepository;
    private LiveData<List<Depense>> allDepenses;

    public DepenseViewModel(@NonNull Application application) {
        super(application);
        depenseRepository = new DepenseRepository(application);
        allDepenses = depenseRepository.getAllDepense();
    }

    public LiveData<List<Depense>> getAllDepenses() {
        return allDepenses;
    }

    public void insert(Depense depense) {
        depenseRepository.insert(depense);
    }
}
