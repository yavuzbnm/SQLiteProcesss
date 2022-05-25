package com.yavuzselim.sqliteprocesss;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase database =this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians( id INTEGER PRIMARY KEY ,VARCHAR name ,INT age)");

            database.execSQL("INSERT INTO musicians(name,age) VALUES ('yavuz',28");
            database.execSQL("INSERT INTO musicians(name,age) VALUES ('selim',23)");


            //veritabanın dan okuma yapmak için cursor kullanıyoruz
            //rawQuery kullanarak veritabanında sorgulama yapıyoruz
            Cursor cursor=database.rawQuery("SELECT * FROM musicians",null);


            //hangi sutunlara gidecegimizi yazıyoruz
            //indexler veritabanında belirlediğimiz indexlerle aynı olmak zornda
            int nameIx=cursor.getColumnIndex("name");
            int ageIx=cursor.getColumnIndex("age");
            int idIx=cursor.getColumnIndex("id");


            //cursor ilerlesin okusun bizde yapmak istediklerimizi while döngüsü içine yazıyoruz
            while (cursor.moveToNext()){
                System.out.println("Name:" + cursor.getString(nameIx) );
                System.out.println("Age:" + cursor.getString(ageIx) );
                System.out.println("id:" + cursor.getString(idIx));

            }
            cursor.close();

        }catch (Exception e){
            //hata bulursak hatamızı yazdırır
            e.printStackTrace();
        }
    }
}