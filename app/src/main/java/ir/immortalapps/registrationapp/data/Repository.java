package ir.immortalapps.registrationapp.data;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ir.immortalapps.registrationapp.data.db.AppDatabase;
import ir.immortalapps.registrationapp.data.db.User;
import ir.immortalapps.registrationapp.data.db.UserDao;

public class Repository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public Repository(Context context) {
        userDao = AppDatabase.getInstance(context).getUserDao();
        try {
            allUsers = new GetAllUsersAsyncTask(userDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user){
         new AddUserAsyncTask(userDao).execute(user);
    }

    public void deleteUser(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void updateUser(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    public LiveData<User> getUser(Integer userId) throws ExecutionException, InterruptedException {
        return new GetUserAsyncTask(userDao).execute(userId).get();
    }


    public static class AddUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public AddUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.addUser(users[0]);

            return null;
        }
    }

    public static class GetAllUsersAsyncTask extends AsyncTask<Void,Void,LiveData<List<User>>>{
        private UserDao userDao;
        public GetAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected LiveData<List<User>> doInBackground(Void... voids) {
            LiveData<List<User>> users= userDao.getAllUsers();
            return users;
        }

    }

    public static class DeleteUserAsyncTask extends AsyncTask<User,Void, Void>{
        private UserDao userDao;

        public DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteUser(users[0]);
            return null;
        }
    }

    public static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao userDao;

        public UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users[0]);
            return null;
        }
    }

    public static class GetUserAsyncTask extends AsyncTask<Integer,Void,LiveData<User>>{
        private UserDao userDao;

        public GetUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected LiveData<User> doInBackground(Integer... integers) {
            return userDao.getUser(integers[0]);
        }
    }



}
