import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class Parcel {
  public static void main(String[] args) {}

  private int mLength;
  private int mHeight;
  private int mWidth;
  private int mWeight;

  public Parcel(int length, int height, int width, int weight) {
    mLength = length;
    mHeight = height;
    mWidth = width;
    mWeight = weight;
  }

  public int getLength(){
    return mLength;
  }

  public int getHeight(){
    return mHeight;
  }

  public int getWidth(){
    return mWidth;
  }

  public int getWeight(){
    return mWeight;
  }

  public int getVolume(){
    return mLength * mHeight * mWidth;
  }

  //Everything below is to setup a distance selector wherin two cities are selected and the distance between them is calculated
  public double calculateDistance(int index1, int index2) {
    //Initialize city hashmaps
    HashMap<String, Object> portland = new HashMap<String, Object>();
    portland.put("cityIndex", 1);
    portland.put("x", 50);
    portland.put("y", 50);
    HashMap<String, Object> sedona = new HashMap<String, Object>();
    sedona.put("cityIndex", 2);
    sedona.put("x", 100);
    sedona.put("y", 500);
    HashMap<String, Object> syracuse = new HashMap<String, Object>();
    syracuse.put("cityIndex", 3);
    syracuse.put("x", 800);
    syracuse.put("y", 50);
    HashMap<String, Object> denver = new HashMap<String, Object>();
    denver.put("cityIndex", 4);
    denver.put("x", 300);
    denver.put("y", 250);
    HashMap<String, Object> miami = new HashMap<String, Object>();
    miami.put("cityIndex", 5);
    miami.put("x", 800);
    miami.put("y", 750);
    HashMap<String, Object> madison = new HashMap<String, Object>();
    madison.put("cityIndex", 6);
    madison.put("x", 550);
    madison.put("y", 150);
    HashMap<String, Object> dallas = new HashMap<String, Object>();
    dallas.put("cityIndex", 7);
    dallas.put("x", 350);
    dallas.put("y", 750);

    //put hashmaps into an array
    HashMap[] cityArray = {portland, sedona, syracuse, denver, miami, madison, dallas};
    int x1 = 0;
    int x2 = 0;
    int y1 = 0;
    int y2 = 0;

    //grab appropriate x and y values
    for(HashMap city: cityArray) {
      if(city.containsValue(index1)) {
        x1 = (int)city.get("x");
        y1 = (int)city.get("y");
      }
      if(city.containsValue(index2)) {
        x2 = (int)city.get("x");
        y2 = (int)city.get("y");
      }
    }

    //calculate and return distance
    double distance = Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
    return distance;
  }

  public double costToShip(int deliverySpeed, double distance) {
    double cost = 0;
    cost += this.getVolume()*0.25;
    cost += mWeight*0.15;
    if(deliverySpeed == 0) {
      cost += 4.95;
    }
    if(deliverySpeed == 1) {
      cost += 2.95;
    }
    if(deliverySpeed == 2) {
      cost += 1.45;
    }
    cost += distance*0.05;
    return cost;
  }
}
