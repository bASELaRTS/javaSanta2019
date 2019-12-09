package Santa2019;

import java.awt.Color;

import Engine.*;

public class SceneMain extends GameScene {
  private SnowFlake[] m_snowFlakes;
  
  public SceneMain(GameEngine engine) {
    super(engine);
    
    int i;    
    this.m_snowFlakes = new SnowFlake[50];
    for(i=0;i<this.m_snowFlakes.length;i++) {
      this.m_snowFlakes[i] = new SnowFlake();
      this.m_snowFlakes[i].getPosition().setCoordinates(Math.random()*engine.getWidth(),Math.random()*engine.getHeight());
    }
  }
  
  public void update() {
    int i;
    SnowFlake snowFlake;
    for(i=0;i<this.m_snowFlakes.length;i++) {
      snowFlake = this.m_snowFlakes[i];
      snowFlake.update(this.getEngine());
    }
  }
  
  public void paint() {
    int i;
    SnowFlake snowFlake;
    for(i=0;i<this.m_snowFlakes.length;i++) {
      snowFlake = this.m_snowFlakes[i];
      snowFlake.paint(this.getEngine());
    }
    
    String str;
    Vector2 v = new Vector2();
    Santa2019 engine = ((Santa2019)this.getEngine()); 
    str = "FPS: " + engine.getTimer().getFPS();
    engine.getFont(0).measureFont(str,v);    
    engine.getFont(0).drawFont((int)(engine.getWidth() - v.x - 2), 2, engine.getGraphics(), str);    
  }
  
  public class SnowFlake
  {
    private Vector2 m_position;
    
    public SnowFlake() {
      this.m_position = new Vector2();
    }
    
    public void update(GameEngine engine) {
      Vector2 position = new Vector2(this.getPosition());
      position.x += Math.sin(position.y*0.5)*0.15;
      position.y += 0.2;
      if (position.y>engine.getHeight()) {
        position.x = Math.random()*engine.getWidth();
        position.y = -1;
      }
      this.getPosition().setVector(position);
    }
    
    public void paint(GameEngine engine) {
      int x = (int)this.getPosition().x;
      int y = (int)this.getPosition().y;
      engine.getGraphics().drawRect(x, y, 1, 1, Color.white);
    }
    
    public Vector2 getPosition() {return this.m_position;}
  }
}
