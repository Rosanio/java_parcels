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
      Integer inputtedHeight = Integer.parseInt(request.queryParams("height"));
      request.session().attribute("height", inputtedHeight);
      Integer inputtedWidth = Integer.parseInt(request.queryParams("width"));
      request.session().attribute("width", inputtedWidth);
      Integer inputtedWeight = Integer.parseInt(request.queryParams("weight"));
      request.session().attribute("weight", inputtedWeight);

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

    post("/specials", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Integer selectedIndex1 = Integer.parseInt(request.queryParams("index1"));
      Integer selectedIndex2 = Integer.parseInt(request.queryParams("index2"));
      request.session().attribute("index1", selectedIndex1);
      request.session().attribute("index2", selectedIndex2);
      Integer volume = (int)request.session().attribute("length") * (int)request.session().attribute("width") * (int)request.session().attribute("height");
      Boolean bigBox = volume > 50;
      model.put("bigBox", bigBox);

      model.put("template", "templates/special-offers.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/results", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String giftWrap = request.queryParams("giftWrap");
      String bigBox = request.queryParams("bigBox");
      Parcel parcel = new
      Parcel(request.session().attribute("length"),request.session().attribute("height"), request.session().attribute("width"), request.session().attribute("weight"));
      double distance = parcel.calculateDistance(request.session().attribute("index1"), request.session().attribute("index2"));

      double cost = parcel.costToShip(request.session().attribute("speed"), distance);
      System.out.println("It works");
      if (giftWrap != null) {
        cost += parcel.giftWrap();
      }
      if (bigBox != null) {
        cost -= 1;
      }

      double divideBy = 100; //included to ensure output is divided properly, was returning whole numbers
      double roundedCost = Math.round(cost*divideBy)/divideBy;

      model.put("cost", roundedCost);
      model.put("template", "templates/results.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
