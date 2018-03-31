package fr.android.griseau.finalproject;

/**
 * Created by alberic on 29/03/2018.
 */

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

// OPERATIONNEL


public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_MATCHES = "matches";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DB_ID = "_dbid";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TEAM1 = "team1";
    public static final String COLUMN_TEAM2 = "team2";
    public static final String COLUMN_WINNER = "winner";
    public static final String COLUMN_SCORE1 = "score1";
    public static final String COLUMN_SCORE2 = "score2";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_ADDERCOACHID = "adderCoachId";

    private static final String DATABASE_NAME = "android.db";
    private static final int DATABASE_VERSION = 2;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MATCHES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_DB_ID
            + " integer, " + COLUMN_DATE
            + " date, " + COLUMN_TEAM1
            + " varchar(255), " + COLUMN_TEAM2
            + " varchar(255), " + COLUMN_WINNER
            + " integer, " + COLUMN_SCORE1
            + " integer, " + COLUMN_SCORE2
            + " integer, " + COLUMN_LOCATION
            + " point, " + COLUMN_ADDERCOACHID
            + " integer );";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(
            SQLiteDatabase database) {


        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCHES);
        onCreate(db);
    }
}
