package fr.android.griseau.finalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSeeMatch extends Fragment {


    public FragmentSeeMatch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.activity_view_match, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ListView listMatch = (ListView) getView().findViewById(R.id.ListMatch);
        int ID = getActivity().getIntent().getIntExtra("ID", -1);

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
                    MatchAdapter adapter = new MatchAdapter(getContext(), list);
                    listMatch.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        ViewMatchRequest viewRequest = new ViewMatchRequest(ID, getString(R.string.ip_address), responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(viewRequest);
    }



}
