package Santa2019;

import java.awt.Color;

import Engine.*;

public class Player extends Entity {
  public Player(GameEngine engine) {
    super("player");    
    this.setGameEngine(engine);
    this.setXY(32, 32);
    this.setSize(16, 16);
  }
  
  public void paint(GameGraphics graphics) {
    int w = this.getWidth();
    int h = this.getHeight();
    int x = this.getX();
    int y = this.getY();
    
    graphics.fillRect(x, y, w, h, Color.white);
  }
}
