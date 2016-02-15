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
}
