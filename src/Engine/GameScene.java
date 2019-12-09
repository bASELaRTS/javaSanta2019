package Engine;

public class GameScene {
  private GameEngine m_engine;
  
  public GameScene(GameEngine engine) {
    super();
    
    this.setEngine(engine);    
  }
  
  public void activated() {}
  public void update() {}  
  public void paint() {}
  
  public void setEngine(GameEngine engine) {this.m_engine=engine;}
  public GameEngine getEngine() {return this.m_engine;}
}
