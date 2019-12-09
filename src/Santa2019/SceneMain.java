package Santa2019;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import Engine.*;

public class SceneMain extends GameScene {
	private EntityManager m_entities;
	
	private BufferedImage m_heart;
  
  public SceneMain(GameEngine engine) {
    super(engine);
    
    this.m_entities = new EntityManager(engine);
    
    this.m_entities.add(new SkyBackground(engine));
    
    this.m_heart = GameGraphics.loadImage("data/heart8x8.png");
    
    TileMap tilemap;
    tilemap = new TileMap();
    tilemap.setSize(engine.getWidth(), engine.getHeight());
    this.loadTiles("data/tiles/Tiles.layout", "data/tiles/Tiles.png", tilemap.getTiles());
    tilemap.load(new java.io.File("data/tilemap01.txt"));
    //tilemap.create(20,15,16,16);
    //tilemap.fill(-1);
    this.m_entities.add(tilemap);
    //tilemap.save(new java.io.File("data/tilemap01.txt"));
    
    int i;   
    for(i=0;i<100;i++) {
    	SnowFlake snowFlake = new SnowFlake();
      snowFlake.getPosition().setCoordinates(Math.random()*engine.getWidth(),Math.random()*engine.getHeight());
      this.m_entities.add(snowFlake);
    }
  }
  
  private void loadTiles(String flayout, String fimage, Vector<BufferedImage> images) {
  	int i;
  	String str;
    Layout layout;
    Layout.Block block;
    BufferedImage sheet;
    BufferedImage image;
    Graphics graphics;
    
    layout = new Layout();
    layout.load(new java.io.File(flayout));
  	
    sheet = GameGraphics.loadImage(fimage);
    
    for(i=0;i<16;i++) {
    	str = "winter_tile" + i;
    	block = layout.findBlock(str);
    	if (block!=null) {
    		image = new BufferedImage(block.getWidth(),block.getHeight(),BufferedImage.TYPE_INT_ARGB);
    		graphics = image.getGraphics();
    		graphics.drawImage(sheet, 0,0,block.getWidth(),block.getHeight(),block.getX(),block.getY(),block.getX()+block.getWidth(),block.getY()+block.getHeight(),null);
    		images.add(image);
    	}
    }
  }
  
  public void update() {
  	this.m_entities.update();
  }
  
  public void paint() {
  	this.m_entities.paint();
  	
  	Santa2019 engine = (Santa2019)this.getEngine();
  	Vector2 v = new Vector2();
  	String str;
  	Font font = engine.getFont(0);

  	str = "" + engine.getTimer().getFPS();
  	font.measureFont(str,v);
  	font.drawFont(engine.getWidth() - (int)(v.x) - 2, 2, engine.getGraphics(), str);

  	str = "" + this.m_entities.count();
  	font.measureFont(str,v);
  	font.drawFont(engine.getWidth() - (int)(v.x) - 2, 12, engine.getGraphics(), str);

  	str = "Merry Christmas";
  	font.measureFont(str,v);
  	font.drawFont((int)((engine.getWidth()-v.x)*0.5), (int)((engine.getHeight()-v.y)*0.5), engine.getGraphics(), str);
  	
  	engine.getGraphics().drawImage((int)((engine.getWidth()-v.x)*0.5)-10, (int)((engine.getHeight()-v.y)*0.5) + 1, 8, 8, this.m_heart);
  	engine.getGraphics().drawImage((int)((engine.getWidth()-v.x)*0.5)+(int)v.x+2, (int)((engine.getHeight()-v.y)*0.5) + 1, 8, 8, this.m_heart);
  }
}
