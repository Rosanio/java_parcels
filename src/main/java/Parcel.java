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

  public double costToShip(int deliverySpeed, int distance) {
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
    cost += distance*0.1;
    return cost;
  }
}
