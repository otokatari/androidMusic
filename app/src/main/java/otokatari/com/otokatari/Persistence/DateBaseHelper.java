package otokatari.com.otokatari.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DateBaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK="create table userData ("
            + "id text, "
            +"password text,"
            +"type int)";//创建表的语句
    private Context mContext;
    public DateBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){//初始化一个数据库
        super(context,name,factory,version);
        mContext=context;
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);//执行创建表的语句
        Toast.makeText(mContext,"Create succeed", Toast.LENGTH_SHORT).show();
    }
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
