package mindjet.com.numbertool.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Mindjet
 * @date 2016/9/10
 */
public class DBHelper extends SQLiteOpenHelper {

    private String tableName;

    public DBHelper(Context context, String tableName) {
        super(context, "MYDATABASE", null, 1);
        this.tableName = tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql_create_table = "create table if not exists " + tableName + " (_id integer primary key " +
                "autoincrement, number text not null, province text not null, city text not null, areacode text not " +
                "null, zip text not null, company text not null, type text not null);";

        sqLiteDatabase.execSQL(sql_create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
