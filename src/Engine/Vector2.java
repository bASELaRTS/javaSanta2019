package Engine;

public class Vector2 {
  public double x;
  public double y;
  
  public Vector2() {
    this.setCoordinates(0, 0);
  }
  public Vector2(double dx, double dy) {
    this.setCoordinates(dx, dy);
  }
  public Vector2(Vector2 v) {
    this.setVector(v);
  }
  
  public void setCoordinates(double dx, double dy) {
    this.x = dx;
    this.y = dy;
  }
  
  public void setVector(Vector2 v) {
    this.setCoordinates(v.x, v.y);
  }
  
  public double length() {
    return Math.sqrt((this.x*this.x) + (this.y*this.y));
  }
  
  public void normalize() {
    double l = this.length();
    this.x = this.x / l;
    this.y = this.y / l;
  }
  
  public static void add(Vector2 v1, Vector2 v2, Vector2 v3) {
    v3.x = v1.x + v2.x;
    v3.y = v1.y + v2.y;
  }
  
  public static void subtract(Vector2 v1, Vector2 v2, Vector2 v3) {
    v3.x = v1.x - v2.x;
    v3.y = v1.y - v2.y;
  }

  public static void multiply(Vector2 v1, Vector2 v2, Vector2 v3) {
    v3.x = v1.x * v2.x;
    v3.y = v1.y * v2.y;
  }
}
