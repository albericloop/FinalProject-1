package fr.android.griseau.finalproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_match);
        final ListView listMatch = (ListView) findViewById(R.id.ListMatch);
        int ID = getIntent().getIntExtra("ID", -1);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response);
                    JSONArray jsonResponse = new JSONArray(response);
                    List<Match> list = new ArrayList<>();
                    for (int i=0; i<jsonResponse.length(); i++)
                    {
                        JSONObject object = new JSONObject(jsonResponse.getString(i));
                        list.add(new Match(object.getString("team1"), object.getString("team2"), object.getInt("winner")));
                    }
                    MatchAdapter adapter = new MatchAdapter(ViewMatchActivity.this, list);
                    listMatch.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ViewMatchRequest viewRequest = new ViewMatchRequest(ID, getString(R.string.ip_address), responseListener);
        RequestQueue queue = Volley.newRequestQueue(ViewMatchActivity.this);
        queue.add(viewRequest);
    }



}
