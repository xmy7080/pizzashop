package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

 @Entity
 public class Orders extends Model{
    //  public static Model.Find<Long, User> find = new Model.Find<Long, User>(){};

    //  @Id
    //  public Integer id;
     
     public String kind;
     public Integer number;
     public String username;
     
     public static Finder<String, Orders> find = new Finder<String,Orders>(Orders.class);

//     public String getUsername(){
//         return find.where().eq("username","xmy").findList()[0];
//     }
    
 }