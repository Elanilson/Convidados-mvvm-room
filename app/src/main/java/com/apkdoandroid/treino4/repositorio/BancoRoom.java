package com.apkdoandroid.treino4.repositorio;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.apkdoandroid.treino4.model.Convidado;

@Database(entities = {Convidado.class},version = 1)
public abstract class BancoRoom extends RoomDatabase {

    public static BancoRoom INSTACE;

    public abstract ConvidadoDao convidadoDao();

    public static BancoRoom getInstance(Context context){

        if(INSTACE == null){
             INSTACE = Room.databaseBuilder(context,BancoRoom.class,"Convidados")
                     .allowMainThreadQueries()
                     .addCallback(new Callback() {
                         @Override
                         public void onCreate(@NonNull SupportSQLiteDatabase db) {
                             super.onCreate(db);
                         }
                     })
                     .addMigrations(MiGRATION_1_2)
                     .build();
        }
        return INSTACE;
    }
    private static Migration MiGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
}
