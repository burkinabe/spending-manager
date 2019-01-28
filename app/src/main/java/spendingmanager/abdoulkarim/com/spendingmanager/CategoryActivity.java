package spendingmanager.abdoulkarim.com.spendingmanager;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import adapter.CategoryAdapter;
import entities.Category;
import viewmodels.CategoryViewModel;

public class CategoryActivity extends AppCompatActivity implements CategoryFragment.OnFragmentInteractionListener{

    private RecyclerView categoryRecyclerview;
    private CategoryAdapter categoryAdapter;
    private CategoryViewModel categoryViewModel;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryRecyclerview = findViewById(R.id.category_recyclerview);
        categoryAdapter = new CategoryAdapter(this);
        categoryRecyclerview.setAdapter(categoryAdapter);
        categoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categories = new ArrayList<>();

        categoryViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                categoryAdapter.setCategories(categories);
                categoryRecyclerview.setAdapter(categoryAdapter);
            }
        });

        FloatingActionButton fab = findViewById(R.id.category_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                CategoryFragment fragment = new CategoryFragment();
                fragment.show(fragmentManager, "Category Fragment");
            }
        });
    }

    public void saveCategory(Category category) {
        categoryViewModel.insert(category);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
