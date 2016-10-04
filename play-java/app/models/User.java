package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

 @Entity
 public class User extends Model{
    //  public static Model.Find<Long, User> find = new Model.Find<Long, User>(){};

     @Id
     public String username;
     public String password;
     public boolean isadmin;
     
     public static Finder<String, User> find = new Finder<String,User>(User.class);

//     public String getUsername(){
//         return find.where().eq("username","xmy").findList()[0];
//     }
    
 }