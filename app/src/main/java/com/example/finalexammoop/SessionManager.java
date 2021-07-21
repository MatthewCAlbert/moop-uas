package com.example.finalexammoop;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String USER_DB = "user_db";
    public static final String CURRENT_USER = "current_user";

    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public boolean login(String username, String password){
        List<UserModel> db= this.getViewDb();
        UserModel loggedInUser = null;
        if( db == null ) return false;
        for( int i = 0 ; i < db.size(); i++ ){
            UserModel cUser = db.get(i);
            if( cUser.getUsername().equals(username) && cUser.getPassword().equals(password) ){
                loggedInUser = cUser;
            }
        }
        if( loggedInUser != null ){
            loginCurrentUser(loggedInUser);
            return true;
        }else{
            System.out.println("Username / password salah");
            return false;
        }
    }

    public boolean usernameExist( String username ){
        List<UserModel> db= this.getViewDb();
        UserModel loggedInUser = null;
        if( db == null ) return false;
        for( int i = 0 ; i < db.size(); i++ ){
            if( db.get(i).getUsername().equals(username) ){
                return true;
            }
        }
        return false;
    }

    public boolean register(UserModel user){
        List<UserModel> db = this.getMutableDb();
        if( db == null ){
            db = new ArrayList<UserModel>();
        }else{
            if( this.usernameExist(user.getUsername()) ) return false;
        }
        db.add(user);
        Gson gson = new Gson();
        String json = gson.toJson(db);
        editor.putString(this.USER_DB, json);
        editor.commit();
        return true;
    }

    public UserModel getUserDetail(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(this.CURRENT_USER, null);
        UserModel obj = gson.fromJson(json, UserModel.class);
        return obj;
    }

    private List<UserModel> getViewDb(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(this.USER_DB, null);
        return stringToArray(json, UserModel[].class);
    }
    private ArrayList<UserModel> getMutableDb(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(this.USER_DB, null);
        return gson.fromJson(json, ArrayList.class);
    }

    private void loginCurrentUser(UserModel user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(this.CURRENT_USER, json);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return getUserDetail() != null;
    }

    public void logout(){
        editor.putString(this.CURRENT_USER, "");
        editor.commit();
    }

    // Utils
    private static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
    }
}