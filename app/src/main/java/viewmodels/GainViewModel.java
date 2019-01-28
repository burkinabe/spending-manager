package viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import entities.Gain;
import repositories.GainRepository;

public class GainViewModel extends AndroidViewModel {

    private GainRepository gainRepository;
    private LiveData<List<Gain>> allGains;

    public GainViewModel(@NonNull Application application) {
        super(application);
        gainRepository = new GainRepository(application);
        allGains = gainRepository.getAllGains();
    }

    public LiveData<List<Gain>> getAllGains() {
        return allGains;
    }

    public void insert(Gain gain) {
        gainRepository.insert(gain);
    }
}
