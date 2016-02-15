import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {

    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delivery-speed", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Integer inputtedLength = Integer.parseInt(request.queryParams("length"));
      request.session().attribute("length", inputtedLength);
      Integer inputtedLength = Integer.parseInt(request.queryParams("height"));
      request.session().attribute("height", inputtedLength);
      Integer inputtedLength = Integer.parseInt(request.queryParams("width"));
      request.session().attribute("width", inputtedLength);
      Integer inputtedLength = Integer.parseInt(request.queryParams("weight"));
      request.session().attribute("weight", inputtedLength);

      model.put("template", "templates/delivery-speed.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/distance", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();


      Integer selectedSpeed = Integer.parseInt(request.queryParams("speed"));
      request.session().attribute("speed", selectedSpeed);


      model.put("template", "templates/distance.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/results", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Integer inputtedDistance = Integer.parseInt(request.queryParams("distance"));
      Parcel parcel = new Parcel(request.session().attribute("length"),request.session().attribute("height"), request.session().attribute("width"), request.session().attribute("weight"));

      double cost = parcel.costToShip(request.session().attribute("speed"), inputtedDistance);

      model.put("cost", cost);
      model.put("template", "templates/delivery-speed.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
