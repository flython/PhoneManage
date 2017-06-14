package hui.zhen.lai.phonemanage.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Profe on 2017/6/14 0014.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        //必须通过super调用父类当中的构造函数
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, int version){
        this(context,name,null,version);
    }

    public DatabaseHelper(Context context, String name){
        this(context,name,VERSION);
    }
    //该函数是在第一次创建的时候执行，实际上是第一次得到SQLiteDatabase对象的时候才会调用这个方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_CONTACT = "create table contact(id INTEGER PRIMARY KEY, name varvhar(20), " +
                "num1 varchar(20))";
        //execSQL用于执行SQL语句
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        //TODO:再说
        System.out.println("upgrade a database");
    }
}
