package Santa2019;

import java.awt.Color;

import Engine.Entity;
import Engine.GameGraphics;
import Engine.Vector2;

public class SnowFlake extends Entity
{
  private Vector2 m_position;
  private Vector2 m_speed;
  
  public SnowFlake() {
  	super("snowflake");
  	
    this.m_position = new Vector2();
    this.m_speed = new Vector2();      
    this.m_speed.setCoordinates(0, (Math.random()*0.5) + 0.01);
  }
  
  public void update(int elapsed) {
  	Santa2019 engine = (Santa2019)this.getGameEngine();
    Vector2 position = new Vector2(this.getPosition());
    Vector2 speed = new Vector2(this.m_speed);
    Vector2 v = new Vector2();
    
    speed.x = Math.sin(position.y*0.3)*0.15;
    Vector2.add(speed, position, v);
    position.setVector(v);
    
    if (position.y>engine.getHeight()) {
      position.x = Math.random()*engine.getWidth();
      position.y = -1;
    }
    
    this.getPosition().setVector(position);
  }
  
  public void paint(GameGraphics graphics) {
  	Santa2019 engine = (Santa2019)this.getGameEngine();
    int x = (int)this.getPosition().x;
    int y = (int)this.getPosition().y;
    engine.getGraphics().drawRect(x, y, 1, 1, Color.white);
  }
  
  public Vector2 getPosition() {return this.m_position;}
  public Vector2 getSpeed() {return this.m_speed;}
}