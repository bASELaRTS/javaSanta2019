package Santa2019;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import Engine.*;

public class Player extends Entity {
	private static int Idle = 0;
	private static int Jumping = 1;
	
  private Vector2 m_position;
  private Vector2 m_speed;
  private int m_state;
  
  private Vector<BufferedImage> m_images;
  private int m_frame;
  
  public Player(GameEngine engine) {
    super("player");    
    this.setGameEngine(engine);
    this.setXY(32, 32);
    this.setSize(16, 16);
    
    this.m_images = new Vector<BufferedImage>();
    
    Layout layout;
    layout = new Layout();
    layout.load(new java.io.File("data/santa.layout"));
    
    BufferedImage sheet;
    sheet = GameGraphics.loadImage("data/santa.png");
    for(int i=0;i<layout.getBlocks().size();i++) {
      Layout.Block block = layout.getBlocks().elementAt(i);
      BufferedImage image = new BufferedImage(block.getWidth(),block.getHeight(),BufferedImage.TYPE_INT_ARGB);
      Graphics graphics = image.getGraphics();
      graphics.drawImage(sheet,0,0,block.getWidth(),block.getHeight(),block.getX(),block.getY(),block.getX()+block.getWidth(),block.getY()+block.getHeight(),null); 
      this.m_images.add(image);
    }
    
    this.m_position = new Vector2(this.getX(),this.getY());
    this.m_speed = new Vector2();    
    this.m_state = Player.Idle;
    this.m_frame = 0;
  }

  public void update(int elapsed) {
    Santa2019 engine = (Santa2019)this.getGameEngine();
    InputKeyboard keyboard = engine.getInput().getKeyboard();
    SceneMain scene = (SceneMain)engine.getSceneManager().getActiveScene();    
    Map map = (Map)scene.getEntities().find("collisionmap");
    
    Vector2 position = new Vector2(this.getPosition());
    Vector2 speed = new Vector2(this.getSpeed());
    Vector2 acceleration = new Vector2();
    Vector2 friction = new Vector2(0.8,1);
    Vector2 speedmax = new Vector2(2,5);
    Vector2 gravity = new Vector2(0,0.3);
    Vector2 v = new Vector2();
    int tx,ty;
    int tw,th;
    int tile;
    
    if (keyboard.getState(InputKeyboard.KEY_LEFT)) {
      acceleration.x = -1.5;
    } else if (keyboard.getState(InputKeyboard.KEY_RIGHT)) {
      acceleration.x = 1.5;
    }
    
    if (keyboard.getState(InputKeyboard.KEY_UP)) {
    	if (this.m_state==Player.Idle) {
    		this.m_state=Player.Jumping;
      	acceleration.y = -10;    		
    	}
    }
    
    Vector2.add(acceleration, speed, v);
    speed.setVector(v);
    Vector2.multiply(friction, speed, v);
    speed.setVector(v);
    Vector2.add(gravity, speed, v);
    speed.setVector(v);
    
    if ((speed.x>-0.5)&&(speed.x<0.5)) speed.x = 0;
    if (speed.x>speedmax.x) speed.x=speedmax.x;
    if (speed.x<-speedmax.x) speed.x=-speedmax.x; 
    if (speed.y>speedmax.y) speed.y=speedmax.y;
    if (speed.y<-speedmax.y) speed.y=-speedmax.y;
    
    
    Vector2.add(speed, position, v);
    position.setVector(v);    
    
    tw = map.getTileWidth();
    th = map.getTileHeight();
    
    // left
    tx = (int)Math.floor((position.x-1)/tw);
    ty = (int)Math.floor((position.y+(this.getHeight()*0.5))/th);    
    tile = map.getTile(tx, ty);
    if (tile>0) {
    	position.x = (tx*th)+th;
    	speed.x = 0;
    }
    
    // right
    tx = (int)Math.floor((position.x + (this.getWidth()))/tw);
    ty = (int)Math.floor((position.y+(this.getHeight()*0.5))/th);    
    tile = map.getTile(tx, ty);
    if (tile>0) {
    	position.x = (tx*th)-this.getWidth();
    	speed.x = 0;
    }
    
    // bottom
    tx = (int)Math.floor((position.x+(this.getWidth()*0.5))/tw);
    ty = (int)Math.floor((position.y+(this.getHeight()))/th);    
    tile = map.getTile(tx, ty);
    if (tile>0) {
    	position.y = (ty*th)-this.getHeight();
    	speed.y = 0;
    	this.m_state = Player.Idle;
    }
            
    this.getSpeed().setVector(speed);
    this.getPosition().setVector(position);
    
    // set camera
    v.setCoordinates(engine.getWidth()*0.5,engine.getHeight()*0.5);
    Vector2.subtract(position, v, scene.getCamera().getPosition());
  }
  
  public void paint(GameGraphics graphics) {
    int w = this.getWidth();
    int h = this.getHeight();
    int x = (int)this.getPosition().x;
    int y = (int)this.getPosition().y;
    
    //graphics.fillRect(x, y, w, h, Color.white);
    BufferedImage image = this.m_images.elementAt(this.m_frame);
    //graphics.drawImage(x, y, w, h, image);
    
    ((SceneMain)((Santa2019)this.getGameEngine()).getSceneManager().getActiveScene()).getCamera().drawImage(image, x, y, w, h, graphics);
  }
  
  public Vector2 getPosition() {return this.m_position;}
  public Vector2 getSpeed() {return this.m_speed;}
}
