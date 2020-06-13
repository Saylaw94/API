package com.example.api.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.Employee;
import com.example.api.R;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    private ArrayList<Employee> myEmployee;

    public EmployeeAdapter(ArrayList<Employee> myEmployee) {
        this.myEmployee = myEmployee;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View employeeView = inflater.inflate(R.layout.item, parent, false);

        ViewHolder viewHolder = new ViewHolder(employeeView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        Employee employee = myEmployee.get(position);

        TextView name = holder.name, age = holder.age, salary = holder.salary;

        name.setText(employee.getName());
        age.setText(String.valueOf(employee.getAge()));
        salary.setText(String.valueOf(employee.getSalary()));

    }

    @Override
    public int getItemCount() {
        return myEmployee.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, age, salary;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            salary = itemView.findViewById(R.id.salary);

        }
    }
}
