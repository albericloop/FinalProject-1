package fr.android.griseau.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddMatch extends Fragment {
    String winner;
    int ID;
    public FragmentAddMatch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        final TextView edWelcome = (TextView) getView().findViewById(R.id.edWelcome);
        ID = intent.getIntExtra("ID", -1);
        String surname = intent.getStringExtra("Surname");
        String message = "Welcome " + surname;
        final EditText team1 = getView().findViewById(R.id.team1);
        final EditText score1 = getView().findViewById(R.id.score1);
        final EditText team2 = getView().findViewById(R.id.team2);
        final EditText score2 = getView().findViewById(R.id.score2);


        edWelcome.setText(message);


        Button addMatch =  getView().findViewById(R.id.addMatch);
        addMatch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final String Team1=team1.getText().toString();
                //final String Score1=score1.getText().toString();
                final int Score1=Integer.parseInt(score1.getText().toString());
                final String Team2=team2.getText().toString();
                final int Score2=Integer.parseInt(score2.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity().getApplicationContext(), "réponse"+response+"", Toast.LENGTH_LONG).show();

                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success)
                            {
                                Toast.makeText(getActivity().getApplicationContext(), "match ajouté", Toast.LENGTH_LONG).show();
                                FragmentSeeMatch fragmentSeeMatch = new FragmentSeeMatch();
                                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, fragmentSeeMatch);
                                fragmentTransaction.commit();
                            }
                            else
                            {
                                Toast.makeText(getActivity().getApplicationContext(), "match non ajouté", Toast.LENGTH_LONG).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder((getContext()));
                                builder.setMessage("Register failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Log.e("VOLLEY", "ERROR");
                    }
                };

                if(Score1<Score2){
                    winner="2";
                }else{
                    winner="1";
                }

                //Date date = Calendar.getInstance().getTime();

                String id=String.valueOf(ID);
                String s1=String.valueOf(Score1);
                String s2=String.valueOf(Score2);


                Date c = Calendar.getInstance().getTime();
                //System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(c);
                //Toast.makeText(getApplicationContext(), ""+formattedDate+"", Toast.LENGTH_LONG).show();
                String date = String.valueOf(formattedDate);

                AddMatchRequest addMatchRequest = new AddMatchRequest(Team1, s1, Team2, s2, id, date, winner, getString(R.string.ip_address), responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(addMatchRequest);
            }
        });
    }

}
