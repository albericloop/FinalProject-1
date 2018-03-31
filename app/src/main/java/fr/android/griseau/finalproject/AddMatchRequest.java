package fr.android.griseau.finalproject;

import android.app.Activity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by alberic on 26/03/2018.
 */

public class AddMatchRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "AddMatch.php";
    private Map<String, String> params;

    public AddMatchRequest(String DBID,String team1, String score1, String team2, String score2, String ID, String date, String winner, String location, String IP, Response.Listener<String> listener){
        super(Method.POST, IP + REGISTER_REQUEST_URL, listener, null);

        params=new HashMap<>();

        params.put("idBDD", DBID);
        params.put("date", date);
        params.put("team1", team1);
        params.put("score1", score1);
        params.put("team2", team2);
        params.put("score2", score2);
        params.put("id", ID);
        params.put("winner", winner);
        //params.put("winner", location);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
