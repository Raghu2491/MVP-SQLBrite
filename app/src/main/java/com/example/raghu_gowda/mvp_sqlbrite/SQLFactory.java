package com.example.raghu_gowda.mvp_sqlbrite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SQLFactory implements UserQuery {


    SQLHelper helper;
    public static BriteDatabase db;
    public static Observable<SqlBrite.Query> users;

    public SQLFactory(Context context) {
        helper=new SQLHelper(context);
        SqlBrite sqlBrite = SqlBrite.create();
        db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());


    }

    public Long insert(){
        SQLiteDatabase db1=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(SQLHelper.USER_NAME,"ArJay");
        contentValues.put(SQLHelper.USER_ADDRESS,"1 Main St");


        return db1.insert(SQLHelper.TABLE_NAME,null,contentValues);


    }

    @Override
    public Observable<SqlBrite.Query> queryDB() {
        String query="Select * from "+ SQLHelper.TABLE_NAME;
        return  users=db.createQuery(SQLHelper.DATABASE_NAME,query);
    }

    public class SQLHelper extends SQLiteOpenHelper{

        public static final int DATABASE_VERSION= 1;
        public static final String DATABASE_NAME="My_Users2";
        public static final String USER_ID="id";
        public static final String USER_ADDRESS="address";
        public static final String USER_NAME="name";
        public static final String TABLE_NAME="users2";
        public static final String CREATE_TABLE="CREATE TABLE " + TABLE_NAME + "(" +USER_ID +" INTEGER PRIMARYKEY AUTO INCREMENT, " + USER_NAME + " VARCHAR(255) ,"+ USER_ADDRESS +" VARCHAR(255) );";

        Context context;

        public SQLHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
