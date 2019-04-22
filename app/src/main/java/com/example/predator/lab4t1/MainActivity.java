package com.example.predator.lab4t1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView = null;
    private ArrayList<Competition> result = new ArrayList<>();
    public static final String EXTRA_MESSAGE = "com.example.predator.lab4t1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listaView);
        makeRequest();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Competition current = (Competition) result.get(position);
                int codeToPass = current.getAreaCode();
                Intent intent = new Intent(getApplicationContext(), CountryActivity.class);
                intent.putExtra(EXTRA_MESSAGE, codeToPass);
                startActivity(intent);
            }
        });
    }

    public void makeRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.football-data.org/v2/areas";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("areas");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String league = object.getString("name");
                        int areaCode = object.getInt("id");
                        Competition competitionToAdd = new Competition(league, areaCode);
                        result.add(competitionToAdd);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setupView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void setupView() {
        final ArrayAdapter<Competition> adapter;
        adapter = new CompetitionArrayAdapter(this, result);
        listView.setAdapter(adapter);
    }
}