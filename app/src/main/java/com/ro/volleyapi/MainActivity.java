package com.ro.volleyapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

TextView aaa ;
    ArrayList<DetailsModel> detailsModels;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        detailsModels = new ArrayList<>();

        String api ="https://jsonplaceholder.typicode.com/posts";


        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        Log.e("api", "onResponse: " + response.toString());
//                        aaa.setText(response);

                        try {

                            JSONArray jsonArray = new JSONArray(response);

                            for (int i=0; i<jsonArray.length();i++){

                                //Here we get sigle object of API
                                JSONObject singleObject = jsonArray.getJSONObject(i);

                                DetailsModel details = new DetailsModel(
                                        singleObject.getInt("userId"),
                                        singleObject.getInt("id"),
                                        singleObject.getString("title"),
                                        singleObject.getString("body")
                                );
                                detailsModels.add(details);

                                Log.d("array", "onResponse: "+detailsModels.size());
                             }

                            recyclerView.setAdapter(new DetailsAdapter(MainActivity.this,detailsModels));

                        } catch (JSONException e) {

                            e.printStackTrace();

                            Log.d("Json Response error",e.getMessage());

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("aa", "onErrorResponse: " + error.getLocalizedMessage());

                Toast.makeText(MainActivity.this, "error" + error, Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);
    }

}