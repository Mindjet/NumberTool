package mindjet.com.numbertool.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mindjet.com.numbertool.Bean.InfoItem;

/**
 * @author Mindjet
 * @date 2016/9/10
 */
public class InfoItemDao {

    private DBHelper dbHelper;
    private String tableName;

    public InfoItemDao(Context context, String tableName) {

        dbHelper = new DBHelper(context, tableName);
        this.tableName = tableName;

    }


    public void insert(InfoItem info) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql_add = "insert into " + tableName + "(number, province, city, areacode, zip, company, type, date)"
                + " " +
                "values(?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] objects = new Object[]{info.getNumber(), info.getProvince(), info.getCity(), info.getAreacode(),
                info.getZip(), info.getCompany(), info.getType(), info.getDate()};

        db.execSQL(sql_add, objects);
        db.close();

    }

    public void delete(String pNum) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql_delete = "delete from " + tableName + " where number=?";
        Object[] objects = new Object[]{pNum};

        db.execSQL(sql_delete, objects);
        db.close();

    }

    public void clear() {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql_clear = "delete from " + tableName;    // db.delete(tableName,null,null);
        db.execSQL(sql_clear);

        db.close();

    }

    public InfoItem search(String pNum) {

        InfoItem infoItem = new InfoItem();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql_search = "select * from " + tableName + " where number=?";
        String[] strings = new String[]{pNum};
        Cursor cursor = db.rawQuery(sql_search, strings);

        cursor.moveToNext();

        infoItem.setNumber(cursor.getString(cursor.getColumnIndex("number")));
        infoItem.setProvince(cursor.getString(cursor.getColumnIndex("province")));
        infoItem.setCity(cursor.getString(cursor.getColumnIndex("city")));
        infoItem.setAreacode(cursor.getString(cursor.getColumnIndex("areacode")));
        infoItem.setZip(cursor.getString(cursor.getColumnIndex("zip")));
        infoItem.setCompany(cursor.getString(cursor.getColumnIndex("company")));
        infoItem.setType(cursor.getString(cursor.getColumnIndex("type")));
        infoItem.setDate(cursor.getString(cursor.getColumnIndex("date")));

        cursor.close();
        db.close();

        return infoItem;

    }

    public boolean isDuplicate(String pNum) {

        boolean isDuplicate;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql_duplicate = "select * from " + tableName + " where number=?";
        String[] string = new String[]{pNum};

        Cursor cursor = db.rawQuery(sql_duplicate, string);
        isDuplicate = cursor.getCount() != 0;

        cursor.close();
        db.close();

        return isDuplicate;

    }

    public List<InfoItem> getAll() {

        List<InfoItem> infoItemList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql_all = "select * from " + tableName;
        Cursor cursor = db.rawQuery(sql_all, null);

        while (cursor.moveToNext()) {

            InfoItem infoItem = new InfoItem();
            infoItem.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            infoItem.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            infoItem.setCity(cursor.getString(cursor.getColumnIndex("city")));
            infoItem.setAreacode(cursor.getString(cursor.getColumnIndex("areacode")));
            infoItem.setZip(cursor.getString(cursor.getColumnIndex("zip")));
            infoItem.setCompany(cursor.getString(cursor.getColumnIndex("company")));
            infoItem.setType(cursor.getString(cursor.getColumnIndex("type")));
            infoItem.setDate(cursor.getString(cursor.getColumnIndex("date")));
            infoItemList.add(infoItem);

        }

        cursor.close();
        db.close();

        return infoItemList;

    }

}
