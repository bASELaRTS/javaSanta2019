package Engine;

public class Timer {
  private int m_fps;
  private int m_fpsCounter;
  private long m_fpsTimestamp;
  
  private int m_elapsed;
  private long m_elapsedTimestamp;
  
  public Timer() {
    long ms = System.currentTimeMillis();
    
    this.m_elapsed = 0;
    this.m_elapsedTimestamp = ms;
    
    this.m_fps = 0;
    this.m_fpsCounter = 0;
    this.m_fpsTimestamp = ms;
  }
  
  public void update() {
    long ms = System.currentTimeMillis();
    
    this.m_elapsed = (int)(ms - this.m_elapsedTimestamp);
    this.m_elapsedTimestamp = ms;
    
    if ((ms-this.m_fpsTimestamp)>=1000) {
      this.m_fpsTimestamp = ms;      
      this.m_fps = this.m_fpsCounter;
      this.m_fpsCounter = 0;
    }
    this.m_fpsCounter++;
  }
  
  public int getFPS() {return this.m_fps;}
  public int getElapsed() {return this.m_elapsed;}
}
