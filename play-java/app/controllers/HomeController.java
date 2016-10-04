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
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        String loginstr = "";
        return ok(index.render(loginstr));
    }
    
    public Result orderIndex(){
        String user = session("connected");
        User record = User.find.byId(user);
        String orderstr = "welcome " +record.username+ ", please take your order~";
        return ok(order.render(orderstr));
    }
    
    public Result order(){
        Orders this_order = Form.form(Orders.class).bindFromRequest().get();
        this_order.save();
        
        String orderstr = "ordered success! do you want more?";
        return ok(order.render(orderstr));
        // return redirect(routes.HomeController.orderIndex());
    }
    
    public Result viewOrder(){
        String user = session("connected");
        if(user == null) {
            String unlogmessage = "your are not login yet.";
            return ok(index.render(unlogmessage));
        }
        User record = User.find.byId(user);
        List<Orders> orders = new Model.Finder(String.class, Orders.class).all();
        // String orderid = orders.get(0).number.toString();
        String unauthmessage = "";
        if(!record.isadmin) {
            unauthmessage = "only admin can view this page.";
            orders.clear();
        }
        return ok(viewOrder.render(orders, unauthmessage));
    }

}
