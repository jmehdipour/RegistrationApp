package ir.immortalapps.registrationapp.data.db;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "UsersDB")
                    .build();
        }
        return appDatabase;
    }

    public abstract UserDao getUserDao();
}
