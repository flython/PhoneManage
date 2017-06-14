package hui.zhen.lai.phonemanage.DB;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import hui.zhen.lai.phonemanage.DB.DatabaseHelper;
import hui.zhen.lai.phonemanage.model.Contact;

/**
 * Created by Profe on 2017/6/14 0014.
 */

public class ContactsDao {
    final String DB_NAME = "contact.db";
    final String TABLE  = "contact";

    private SQLiteDatabase db = null;
    public ContactsDao(Context context) {
        this.db = new DatabaseHelper(context, DB_NAME).getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public Boolean insert(Contact contact){
        ContentValues values = new ContentValues();
        values.put("name",contact.getName());
        values.put("num1",contact.getNum1());
        if(db.insert(TABLE,null,values) == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public  Boolean delete(Integer id){
        String whereClause = "id=?";
        String[] whereArgs = new String[]{id.toString()};
        db.delete(TABLE,whereClause,whereArgs);
        return true;
    }

    public  Boolean update(Contact contact) {
        ContentValues values = new ContentValues();
        values.put("name",contact.getName());
        values.put("num1",contact.getNum1());
        String whereClause = "id=?";
        String[] whereArgs = new String[]{contact.getId().toString()};
        db.update(TABLE,values,whereClause,whereArgs);

        return true;
    }

    public Cursor query(Integer id) {
        return db.rawQuery("select * from " + TABLE + " where id=?", new String[]{id.toString()});
    }

    public Cursor queryAll() {
        return db.rawQuery("select * from " + TABLE, null);
    }

    public Boolean delDB(Context context) {
        db.close();
        context.deleteDatabase(DB_NAME);
        return true;
    }

}
