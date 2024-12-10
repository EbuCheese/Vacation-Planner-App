package com.example.d308vacationplanner.db;

import android.app.Application;

import com.example.d308vacationplanner.dao.excursionDAO;
import com.example.d308vacationplanner.dao.vacationDAO;
import com.example.d308vacationplanner.entities.Excursion;
import com.example.d308vacationplanner.entities.Vacation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private excursionDAO mExcursionDAO;
    private vacationDAO mVacationDAO;

    private List<Vacation> mAllVacations;
    private List<Excursion> mAllExcursions;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        VacationDatabaseBuilder db = VacationDatabaseBuilder.getDatabase(application);
        mExcursionDAO = db.excursionDAO();
        mVacationDAO = db.vacationDAO();
    }

    // get the vacations/excursions from the db, or create new db if needed
    public List<Vacation> getmAllVacations() {
        databaseExecutor.execute(() -> {
            mAllVacations = mVacationDAO.getAllVacations();
        });
        // thread sleep test
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllVacations;
    }

    public List<Excursion> getmAllExcursions() {
        databaseExecutor.execute(() -> {
            mAllExcursions = mExcursionDAO.getAllExcursions();
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllExcursions;
    }



    //vacations & excursions creation methods
    public void insert (Vacation vacation) {
        databaseExecutor.execute(() -> {
            mVacationDAO.insert(vacation);
        });
        // if it was synchronous, you don't need this sleep test
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Excursion excursion) {
        databaseExecutor.execute(() -> {
            mExcursionDAO.insert(excursion);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //vacation & excursion update methods
    public void update(Vacation vacation) {
        databaseExecutor.execute(() -> {
            mVacationDAO.update(vacation);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Excursion excursion) {
        databaseExecutor.execute(() -> {
            mExcursionDAO.update(excursion);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //vacation & excursion delete method
    public void delete(Vacation vacation) {
        databaseExecutor.execute(() -> {
            mVacationDAO.delete(vacation);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Excursion excursion) {
        databaseExecutor.execute(() -> {
            mExcursionDAO.delete(excursion);
        });
        // if it was synchronous, you don't need this sleep test
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //get the excursions that have associations with vacation id
    public List<Excursion> getAssociatedExcursions(int vacationID) {
        databaseExecutor.execute(() -> {
            mAllExcursions = mExcursionDAO.getAssociatedExcursions(vacationID);
        });
        // if it was synchronous, you don't need this sleep test
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllExcursions;
    }


}
