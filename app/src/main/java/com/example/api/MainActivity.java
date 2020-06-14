package com.example.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.api.Adapter.EmployeeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Employee> my_employee;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.show_list);

         my_employee = new ArrayList<Employee>();

         queue = Volley.newRequestQueue(this);

        String url = "http://dummy.restapiexample.com/api/v1/employees";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++){

                        JSONObject employee = jsonArray.getJSONObject(i);

                        String name = employee.getString("employee_name");
                        int age = employee.getInt("employee_age");
                        int salary = employee.getInt("employee_salary");

                        Toast.makeText(MainActivity.this, String.valueOf(age), Toast.LENGTH_SHORT).show();

                        my_employee.add(new Employee(name, salary, age));
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });
//        my_employee.add(new Employee("name", 1111, 25));


        queue.add(request);
        EmployeeAdapter adapter = new EmployeeAdapter(my_employee);
        list.setAdapter(adapter);

        list.setLayoutManager(new LinearLayoutManager(this));
    }
}
