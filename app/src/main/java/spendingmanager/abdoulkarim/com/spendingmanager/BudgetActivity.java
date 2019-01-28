package spendingmanager.abdoulkarim.com.spendingmanager;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import adapter.BudgetAdapter;
import entities.Budget;
import factory.ViewModelFactory;
import viewmodels.BudgetViewModel;

public class BudgetActivity extends AppCompatActivity implements BudgetFragment.OnFragmentInteractionListener {

    private RecyclerView budgetRecyclerView;
    private  BudgetAdapter budgetAdapter;
    private BudgetViewModel budgetViewModel;
    private ViewModelFactory viewModelFactory;
    private List<Budget> budgets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        budgetRecyclerView = findViewById(R.id.budget_recyclerview);
        budgetAdapter = new BudgetAdapter(this);
        budgetRecyclerView.setAdapter(budgetAdapter);
        budgetRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModelFactory = new ViewModelFactory(budgetViewModel);
        budgetViewModel = ViewModelProviders.of(this).get(BudgetViewModel.class);
        budgets = new ArrayList<>();

        budgetViewModel.getAllBudgets().observe(this, new Observer<List<Budget>>() {
            @Override
            public void onChanged(@Nullable List<Budget> budgets) {
                Log.d("BUDGETACTIVITYOP", ""+budgets.size());
                budgetAdapter.setBudgets(budgets);
                budgetRecyclerView.setAdapter(budgetAdapter);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                BudgetFragment fragment = new BudgetFragment();
                fragment.show(fragmentManager, "Budget Fragement");
            }
        });
    }

    public void saveBudget(Budget budget) {
        budgetViewModel.insert(budget);
//        budgets.add(budget);
//        budgetAdapter.setBudgets(budgets);
//        budgetRecyclerView.setAdapter(budgetAdapter);
    }

    private void loadAllBudget() {
        if (budgetViewModel.getAllBudgets().getValue() != null) {
            budgets.addAll(budgetViewModel.getAllBudgets().getValue());
            Log.d("BUDGETACTIVITYOP", budgetViewModel.getAllBudgets().getValue().size()+"");
            budgetAdapter.setBudgets(budgets);
            budgetRecyclerView.setAdapter(budgetAdapter);
        }

        Log.d("BUDGETACTIVITYOP", "IS EMPTY");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
