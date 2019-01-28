package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import entities.Category;
import spendingmanager.abdoulkarim.com.spendingmanager.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final LayoutInflater inflater;
    private List<Category> categories;

    public CategoryAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.category_recyclerview_item, viewGroup, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        if (categories != null) {
            Category category = categories.get(i);
            categoryViewHolder.nomEditText.setText(category.getLibelle());
            categoryViewHolder.descriptionEditText.setText(category.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{

        private final EditText nomEditText;
        private final EditText descriptionEditText;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nomEditText = itemView.findViewById(R.id.category_name_textview);
            descriptionEditText = itemView.findViewById(R.id.category_description_textview);

            nomEditText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


    }
}
