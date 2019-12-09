package Engine;

public class GameEngine {
  private String m_name;
  private int m_width;
  private int m_height;
  private boolean m_quit;
  private boolean m_resizable;
  
  private GameGraphics m_graphics;
  private Timer m_timer;
  private Input m_input;
  private EntityManager m_entities;
  private GameSceneManager m_sceneManager;
  
  public GameEngine(String name, int width, int height) {
    this.setName(name);
    this.setWidth(width);
    this.setHeight(height);
    this.setResizable(false);
    
    this.m_timer = new Timer();
    this.m_input = new Input();
    this.m_graphics = new GameGraphics(this.getWidth(), this.getHeight());
    this.m_entities = new EntityManager(this);
    this.m_sceneManager = new GameSceneManager();    
  }
  
  public void update() {
    this.m_timer.update();    
    
    GameScene scene;    
    scene = this.getSceneManager().getActiveScene();
    if (scene!=null) {
      scene.update();
    }
    
    this.m_entities.update();
  }
  public void paint() {
    this.m_graphics.clear();
    
    GameScene scene;    
    scene = this.getSceneManager().getActiveScene();
    if (scene!=null) {
      scene.paint();
    }
    
    this.m_entities.paint();
  }
  
  public void quit() {this.m_quit = true;}
  public boolean isQuit() {return this.m_quit;}
  
  public void setName(String name) {this.m_name=name;}
  public String getName() {return this.m_name;}
  public void setWidth(int width) {this.m_width=width;}
  public int getWidth() {return this.m_width;}
  public void setHeight(int height) {this.m_height=height;}
  public int getHeight() {return this.m_height;}
  public void setResizable(boolean resize) {this.m_resizable=resize;}
  public boolean getResizable() {return this.m_resizable;}
  public Timer getTimer() {return this.m_timer;}
  public Input getInput() {return this.m_input;}
  public GameGraphics getGraphics() {return this.m_graphics;}
  public EntityManager getEntities() {return this.m_entities;}
  public GameSceneManager getSceneManager() {return this.m_sceneManager;}
}
