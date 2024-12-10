package com.example.d308vacationplanner.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.d308vacationplanner.dao.excursionDAO;
import com.example.d308vacationplanner.dao.vacationDAO;
import com.example.d308vacationplanner.entities.Excursion;
import com.example.d308vacationplanner.entities.Vacation;


@Database(entities = {Vacation.class, Excursion.class}, version = 1, exportSchema = false)
public abstract class VacationDatabaseBuilder extends RoomDatabase {

    public abstract vacationDAO vacationDAO();
    public abstract excursionDAO excursionDAO();
    private static volatile VacationDatabaseBuilder INSTANCE;

    static VacationDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VacationDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), VacationDatabaseBuilder.class, "MyVacationDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
