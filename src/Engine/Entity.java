package Engine;

import java.awt.Color;

public class Entity {
  private String m_key;
  private int m_xposition;
  private int m_yposition;
  private int m_width;
  private int m_height;
  private boolean m_visible;
  private boolean m_remove;
  private GameEngine m_engine;
  
  public Entity(String key) {
    this.setKey(key);
    this.setWidth(32);
    this.setHeight(32);
    this.setVisible(true);
    this.setRemove(false);
    this.setGameEngine(null);
  }
  public void initialize() {}
  public void update(int elapsed) {}
  public void paint(GameGraphics graphics) {
    int x = this.getX();
    int y = this.getY();
    int w = this.getWidth();
    int h = this.getHeight();
    
    graphics.drawLine(x, y, x+w, y+h, Color.white);
    graphics.drawLine(x+w, y, x, y+h, Color.white);
    graphics.drawRect(x, y, w, h, Color.white);
  }
  
  public void setKey(String key) {this.m_key=key;}
  public String getKey() {return this.m_key;}
  public void setX(int xposition) {this.m_xposition=xposition;}
  public int getX() {return this.m_xposition;}
  public void setY(int yposition) {this.m_yposition=yposition;}
  public int getY() {return this.m_yposition;}
  public void setXY(int x, int y) {
    this.setX(x);
    this.setY(y);
  }
  public void setWidth(int width) {this.m_width=width;}
  public int getWidth() {return this.m_width;}
  public void setHeight(int height) {this.m_height=height;}
  public int getHeight() {return this.m_height;}
  public void setSize(int width, int height) {
    this.setWidth(width);
    this.setHeight(height);
  }
  public void setVisible(boolean visible) {this.m_visible=visible;}
  public boolean getVisible() {return this.m_visible;}
  public void setRemove(boolean remove) {this.m_remove=remove;}
  public boolean getRemove() {return this.m_remove;}
  public void setGameEngine(GameEngine engine) {this.m_engine=engine;}
  public GameEngine getGameEngine() {return this.m_engine;}
}
