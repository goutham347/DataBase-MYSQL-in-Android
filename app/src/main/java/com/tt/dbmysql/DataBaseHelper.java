package com.tt.dbmysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vijay on 1/30/2017.
 */

public class DataBaseHelper  extends SQLiteOpenHelper{

    public static final String Database_Name = "StudentDetails";
    private static final int DATABASE_VERSION = 1;
    public static final String Table_Name = "Studentss";
    public static final String Std_ID = "Id";
    public static final String Std_NAME = "Name";
    public static final String Std_QUALI = "Qualification";
    public static final String Std_YOP = "YOP";
    public static final String Std_ROLLNO = "RollNo";

    public DataBaseHelper(Context context){
        super(context,Database_Name,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_STUDENT_TABLE =  " Create Table " + Table_Name + " (ID INTEGER  PRIMARY KEY AUTOINCREMENT,NAME TEXT,QUALIFICATION TEXT ,YOP INTEGER,ROLLNO INTEGER) ";
       // String CREATE_STUDENT_TABLE = "CREATE TABLE(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," + "Name TEXT," +"Qualification TEXT," + "YOP INTEGER," +"RollNo INTEGER)";
        sqLiteDatabase.execSQL(CREATE_STUDENT_TABLE);
//        sqLiteDatabase.execSQL("Create Table"+ Table_Name + "(Id INTEGER PRIMARY KEY, Name TEXT,Qualification TEXT,YOP INTEGER,RollNo INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + Table_Name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData( String username, String qualification,String yearofpassing,String rollno) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Std_NAME,username);
        contentValues.put(Std_QUALI,qualification);
        contentValues.put(Std_YOP,yearofpassing);
        contentValues.put(Std_ROLLNO,rollno);
        long result = sqLiteDatabase.insert(Table_Name,null,contentValues);

        if (result == -1)
            return false;
        else
            return  true;
    }

  public boolean  InsertDeleteSpace(String userid , String username, String qualification,String yearofpassing,String rollno) {
      SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(Std_ID, userid);
      contentValues.put(Std_NAME,username);
      contentValues.put(Std_QUALI,qualification);
      contentValues.put(Std_YOP,yearofpassing);
      contentValues.put(Std_ROLLNO,rollno);
      long result = sqLiteDatabase.insert(Table_Name,null,contentValues);

      if (result == -1)
          return false;
      else
          return  true;
  }

    public boolean UpdateData(String userid , String username, String qualification,String yearofpassing,String rollno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Std_ID, userid);
        contentValues.put(Std_NAME,username);
        contentValues.put(Std_QUALI,qualification);
        contentValues.put(Std_YOP,yearofpassing);
        contentValues.put(Std_ROLLNO,rollno);
        db.update(Table_Name,contentValues,"ID = ?",new String[] {userid});
        return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorresult = db.rawQuery("select * from "+ Table_Name,null);
        return cursorresult;
    }
    public Integer DeleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name,"Id = ?",new String[] {id});
    }

    public void DeleteAll(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from " + Table_Name);
        sqLiteDatabase.close();
    }
}
