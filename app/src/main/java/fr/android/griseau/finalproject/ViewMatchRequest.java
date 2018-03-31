package fr.android.griseau.finalproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thomas on 30/03/2018.
 */

public class ViewMatchRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "LoadMatch.php";
    private Map<String, String> params;

    public ViewMatchRequest(int CoachId, String IP, Response.Listener<String> listener) {
        super(Method.POST, IP + REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("IDcoach", String.valueOf(CoachId));
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
