package Engine;

import java.util.Vector;

public class GameSceneManager {
  private Vector<GameScene> m_scenes;
  private int m_activeSceneIndex;
  
  public GameSceneManager() {
    this.m_scenes = new Vector<GameScene>();
    this.m_activeSceneIndex=-1;
  }
  
  public void setActiveScene(int index) {
    if (this.m_activeSceneIndex!=index) {
      if ((index>=0)&&(index<this.m_scenes.size())){
        this.m_activeSceneIndex = index;
        this.m_scenes.elementAt(this.m_activeSceneIndex).activated();
      } else {
        this.m_activeSceneIndex = -1;
      }      
    }
  }
  
  public GameScene getActiveScene() {
    if (this.m_activeSceneIndex>=0) {
      return this.m_scenes.elementAt(this.m_activeSceneIndex);
    }
    return null;
  }
  
  public void add(GameScene scene) {this.m_scenes.add(scene);}    
  public int count() {return this.m_scenes.size();}    
}
