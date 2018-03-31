package fr.android.griseau.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberic on 29/03/2018.
 */

public class CommentsDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_DATE,
            MySQLiteHelper.COLUMN_TEAM1,
            MySQLiteHelper.COLUMN_TEAM2,
            MySQLiteHelper.COLUMN_WINNER,
            MySQLiteHelper.COLUMN_SCORE1,
            MySQLiteHelper.COLUMN_SCORE2,
            MySQLiteHelper.COLUMN_LOCATION,
            MySQLiteHelper.COLUMN_ADDERCOACHID };

    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public BDDMatch createBDDMatch(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DB_ID, comment);
        values.put(MySQLiteHelper.COLUMN_DATE, comment);
        values.put(MySQLiteHelper.COLUMN_TEAM1, comment);
        values.put(MySQLiteHelper.COLUMN_TEAM2, comment);
        values.put(MySQLiteHelper.COLUMN_WINNER, comment);
        values.put(MySQLiteHelper.COLUMN_SCORE1, comment);
        values.put(MySQLiteHelper.COLUMN_SCORE2, comment);
        values.put(MySQLiteHelper.COLUMN_LOCATION, comment);
        values.put(MySQLiteHelper.COLUMN_ADDERCOACHID, comment);

        long insertId = database.insert(MySQLiteHelper.TABLE_MATCHES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MATCHES,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        BDDMatch newBDDMatch = cursorToBDDMatch(cursor);
        cursor.close();
        return newBDDMatch;
    }

    public void deleteComment(BDDMatch bddmatch) {
        long id = bddmatch.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_MATCHES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<BDDMatch> getAllBDDMatches() {
        List<BDDMatch> bddmatches = new ArrayList<BDDMatch>();

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