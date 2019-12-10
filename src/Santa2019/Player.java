package Santa2019;

import java.awt.Color;

import Engine.*;

public class Player extends Entity {
  private Vector2 m_position;
  private Vector2 m_speed;
  
  public Player(GameEngine engine) {
    super("player");    
    this.setGameEngine(engine);
    this.setXY(32, 32);
    this.setSize(16, 16);
    
    this.m_position = new Vector2(this.getX(),this.getY());
    this.m_speed = new Vector2();
  }

  public void update(int elapsed) {
    Santa2019 engine = (Santa2019)this.getGameEngine();
    InputKeyboard keyboard = engine.getInput().getKeyboard();
    Vector2 position = new Vector2(this.getPosition());
    Vector2 speed = new Vector2(this.getSpeed());
    Vector2 acceleration = new Vector2();
    Vector2 v = new Vector2();
    double maxspeedx = 3;
        
    if (keyboard.getState(InputKeyboard.KEY_LEFT)) {
      acceleration.x = -0.3;
    } else if (keyboard.getState(InputKeyboard.KEY_RIGHT)) {
      acceleration.x = 0.3;
    }
    
    Vector2.add(acceleration, speed, v);
    speed.setVector(v);
    if (speed.x>5) speed.x=maxspeedx;
    if (speed.x<-5) speed.x=-maxspeedx;
    
    Vector2.add(speed, position, v);
    position.setVector(v);    
    if (position.x<0) position.x=0;
    if (position.x>(engine.getWidth()-this.getWidth())) position.x=(engine.getWidth()-this.getWidth());
    
    this.getSpeed().setVector(speed);
    this.getPosition().setVector(position);
  }
  
  public void paint(GameGraphics graphics) {
    int w = this.getWidth();
    int h = this.getHeight();
    int x = this.getX();
    int y = this.getY();
    
    graphics.fillRect(x, y, w, h, Color.white);
  }
  
  public Vector2 getPosition() {return this.m_position;}
  public Vector2 getSpeed() {return this.m_speed;}
}
