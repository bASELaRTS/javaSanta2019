package Santa2019;

import java.awt.image.BufferedImage;

import Engine.*;

public class Camera {
  private GameEngine m_engine;
  private Vector2 m_position;
  
  public Camera(GameEngine engine) {
    this.m_position = new Vector2();
    this.m_engine = engine;
  }
  
  public void worldToCamera(Vector2 vworld, Vector2 vcamera) {
    Vector2.subtract(vworld, this.m_position, vcamera);
  }
  
  public void drawImage(BufferedImage image, int x, int y, int w, int h, GameGraphics graphics) {
    int px = (int)(x - this.m_position.x);
    int py = (int)(y - this.m_position.y);
    graphics.drawImage(px, py, w, h, image);
  }
  
  public Vector2 getPosition() {return this.m_position;}
}
