package controllers;

import play.mvc.*;

import views.html.*;

import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import models.User;
import models.Orders;
import java.util.List;
import play.*;
import com.avaje.ebean.Model;

import static play.libs.Json.toJson;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class Users extends Controller {
    
    public Result signupPage(){
        String message = "";
        return ok(signup.render(message));
    }
    
    public Result signup(){
        User user = Form.form(User.class).bindFromRequest().get();
        User record = User.find.byId(user.username);
        if (record == null){
            user.save();
            String message = "new user created successful, please log in.";
            return ok(index.render(message));
        }
        else{
            String message = "username has been used, try another.";
            return ok(signup.render(message));
        }
    }
    public Result loginSubmit(){
        User user = Form.form(User.class).bindFromRequest().get();
        boolean login = true;
        User record = User.find.byId(user.username);
        if (record == null) login = false;
        else
            if (!user.password.equals(record.password)) login = false;
        
        // user.save();
        //DynamicForm dynamicForm = Form.form().bindFromRequest();
        //Logger.info("Username is: " + dynamicForm.get("username"));
        //Logger.info("Password is: " + dynamicForm.get("password"));
        if (login){//if user login matched record, goes into order page
            session("connected", user.username);
            return redirect(routes.HomeController.orderIndex());
        }
        else{//if not, return to login page
            String unlogmessage = "log in unsuccessful";
            return ok(index.render(unlogmessage));
        }
            
    }
    
    public Result logout(){
        session().clear();
        String unlogmessage = "log out successful.";
        return ok(index.render(unlogmessage));
    }

}