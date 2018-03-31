package fr.android.griseau.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.Response;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberic on 29/03/2018.
 */

public class BDDMatchesDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_DB_ID,
            MySQLiteHelper.COLUMN_DATE,
            MySQLiteHelper.COLUMN_TEAM1,
            MySQLiteHelper.COLUMN_TEAM2,
            MySQLiteHelper.COLUMN_WINNER,
            MySQLiteHelper.COLUMN_SCORE1,
            MySQLiteHelper.COLUMN_SCORE2,
            MySQLiteHelper.COLUMN_LOCATION,
            MySQLiteHelper.COLUMN_ADDERCOACHID };

    public BDDMatchesDataSource(Context context) {

        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public BDDMatch createBDDMatch(String DBID, String team1, String score1, String team2, String score2, String ID, String date, String winner, String location){

            open();

            ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DB_ID, DBID);
        values.put(MySQLiteHelper.COLUMN_DATE, date);
        values.put(MySQLiteHelper.COLUMN_TEAM1, team1);
        values.put(MySQLiteHelper.COLUMN_TEAM2, team2);
        values.put(MySQLiteHelper.COLUMN_WINNER, winner);
        values.put(MySQLiteHelper.COLUMN_SCORE1, score1);
        values.put(MySQLiteHelper.COLUMN_SCORE2, score2);
        values.put(MySQLiteHelper.COLUMN_LOCATION, location);
        values.put(MySQLiteHelper.COLUMN_ADDERCOACHID, ID);


        long insertId = database.
                insert(
                        MySQLiteHelper.TABLE_MATCHES,
                null,
                values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_MATCHES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();

        BDDMatch newBDDMatch = cursorToBDDMatch(cursor);
        cursor.close();
        close();
        return newBDDMatch;
    }

    public void deleteBDDMatch(BDDMatch bddmatch) {
        long id = bddmatch.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_MATCHES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<BDDMatch> getAllBDDMatches() {

        List<BDDMatch> bddmatches = new ArrayList<BDDMatch>();
        open();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MATCHES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BDDMatch bddmatch = cursorToBDDMatch(cursor);
            bddmatches.add(bddmatch);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return bddmatches;
    }

    private BDDMatch cursorToBDDMatch(Cursor cursor) {
        BDDMatch bddmatch = new BDDMatch();
        bddmatch.setId(cursor.getLong(0));
        bddmatch.setBDDMatch(cursor.getString(1));
        return bddmatch;
    }
}