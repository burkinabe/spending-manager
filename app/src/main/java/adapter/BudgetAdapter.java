package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import entities.Budget;
import spendingmanager.abdoulkarim.com.spendingmanager.R;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder> {

    private final LayoutInflater inflater;
    private List<Budget> budgets;
    private Context context;

    public BudgetAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public BudgetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.budget_recyclerview_item, viewGroup, false);
        return new BudgetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetViewHolder budgetViewHolder, int i) {
        if (budgets != null) {
            Budget budget = budgets.get(i);
            budgetViewHolder.budgetItemTextView.setText(""+budget.getMontant());
        } else {
            budgetViewHolder.budgetItemTextView.setText(R.string.no_budget_text);
        }
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    @Override
    public int getItemCount() {
        if (budgets != null) {
            return budgets.size();
        }
        return 0;
    }

    class BudgetViewHolder extends RecyclerView.ViewHolder {

        private final TextView budgetItemTextView;

        public BudgetViewHolder(@NonNull View itemView) {
            super(itemView);
            budgetItemTextView = itemView.findViewById(R.id.budget_item_textview);
        }
    }
}
