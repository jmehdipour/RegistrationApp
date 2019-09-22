package ir.immortalapps.registrationapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ir.immortalapps.registrationapp.data.Repository;
import ir.immortalapps.registrationapp.data.db.User;

public class MainActivityViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<User>> users;
    private LiveData<User> user;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application.getApplicationContext());
        users = repository.getAllUsers();
    }


    public void addUser(User user){
        repository.addUser(user);
    }

    public void deleteUser(User user){
        repository.deleteUser(user);
    }

    public void updateUser(User user){
        repository.updateUser(user);
    }

    public LiveData<List<User>> getAllUsers(){
         return users;
    }

    public void getUser(Integer userId){
        try {
            user = repository.getUser(userId);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<User> getUser() {
        return user;
    }
}
