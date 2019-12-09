package Engine;

public class EntityManager {  
  private LinkedList m_list;
  private GameEngine m_engine;
  
  public EntityManager(GameEngine engine) {
    this.m_list = new LinkedList();
    this.m_engine = engine;
  }
  
  public void add(Entity entity) {
    entity.setGameEngine(this.m_engine);
    entity.initialize();
    this.m_list.add(new LinkedListObject(entity));
  }
  
  public void clear() {this.m_list.clear();}
  public int count() {return this.m_list.count();}
  public LinkedListObject first() {return this.m_list.getFirst();}

  public void update() {
    LinkedListObject o,n;
    Entity e;
    
    o = this.m_list.getFirst();
    while (o!=null) {
      n = o.getNext();
      e = (Entity)o.getObject();
      if (e.getRemove()) {
        this.m_list.remove(o);
      } else {
        e.update(this.m_engine.getTimer().getElapsed());
      }
      o = n;
    }
  }
  
  public void paint() {
    LinkedListObject o,n;
    Entity e;
    
    o = this.m_list.getFirst();
    while (o!=null) {
      n = o.getNext();
      e = (Entity)o.getObject();
      if ((!e.getRemove())&&(e.getVisible())) {
        e.paint(this.m_engine.getGraphics());
      }
      o = n;    
    }
  }
}
