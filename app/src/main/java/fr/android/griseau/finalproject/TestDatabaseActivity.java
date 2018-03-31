package fr.android.griseau.finalproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;

public class TestDatabaseActivity extends ListActivity {
    private BDDMatchesDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database);

        datasource = new BDDMatchesDataSource(this);
        datasource.open();

        List<BDDMatch> values = datasource.getAllBDDMatches();

        // utilisez SimpleCursorAdapter pour afficher les
        // éléments dans une ListView
        ArrayAdapter<BDDMatch> adapter = new ArrayAdapter<BDDMatch>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Sera appelée par l'attribut onClick
    // des boutons déclarés dans main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<BDDMatch> adapter = (ArrayAdapter<BDDMatch>) getListAdapter();
        BDDMatch bddmatch = null;
        switch (view.getId()) {
            case R.id.add:
                String[] bddmatches = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                // enregistrer le nouveau commentaire dans la base de données
                //bddmatch = datasource.createBDDMatch(bddmatches[nextInt]);
                adapter.add(bddmatch);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    bddmatch = (BDDMatch) getListAdapter().getItem(0);
                    datasource.deleteBDDMatch(bddmatch);
                    adapter.remove(bddmatch);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}