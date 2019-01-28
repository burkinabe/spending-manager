package entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Gain {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "montant")
    private Double montant;

    @ColumnInfo(name = "reste")
    private Double reste;

    public Gain() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getReste() {
        return reste;
    }

    public void setReste(Double reste) {
        this.reste = reste;
    }
}
