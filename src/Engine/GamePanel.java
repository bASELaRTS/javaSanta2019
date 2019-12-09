package Engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
  private static final long serialVersionUID = 1L;

  private GameFrame m_frame;
  
  private GameEngine m_engine;
  private boolean m_threadRunning;
  private Thread m_thread;
  
  public GamePanel(GameEngine engine) {
    super();
    
    this.setGameEngine(engine);
    
    int w = engine.getWidth();
    int h = engine.getHeight();
    this.setMinimumSize(new Dimension(w,h));
    this.setPreferredSize(new Dimension(w,h));
    this.setFocusable(true);
    
    this.setGameFrame(null);
    
    this.addKeyListener(this);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    
    this.m_thread = new Thread(this);
    this.m_thread.start();
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    
    if (this.m_engine!=null) {
      BufferedImage img;
      int w,h;
      img = this.m_engine.getGraphics().getImage();
      w = this.m_engine.getGraphics().getWidth();
      h = this.m_engine.getGraphics().getHeight();
      g.drawImage(img,0,0,w,h,null);
    }
  }
  
  public void setGameEngine(GameEngine engine) {this.m_engine=engine;}
  public GameEngine getGameEngine() {return this.m_engine;}
  public void setGameFrame(GameFrame frame) {this.m_frame=frame;}

  public void run() {
    this.m_threadRunning = true;
    while(this.m_threadRunning) {
      try {
        if (this.m_engine!=null) {
          if (!this.m_engine.isQuit()) {
            this.m_engine.update();
            this.m_engine.paint();
            
            this.repaint();
          } else {
            this.m_threadRunning = false;
          }
        }

        Thread.sleep(5);
      } catch (InterruptedException e) {
        System.out.println("GameEngine:run:" + e.getMessage());
      }
    }

    if (this.m_frame!=null) {
      this.m_frame.raiseExit();
    }
  }

  public void keyTyped(KeyEvent e) {}
  public void keyPressed(KeyEvent e) {
    if (this.m_engine!=null) {
      this.m_engine.getInput().getKeyboard().setState(e.getKeyCode(), true);
    }
  }
  public void keyReleased(KeyEvent e) {
    if (this.m_engine!=null) {
      this.m_engine.getInput().getKeyboard().setState(e.getKeyCode(), false);
    }
  }

  public void mouseClicked(MouseEvent arg0) {}
  public void mouseEntered(MouseEvent arg0) {}
  public void mouseExited(MouseEvent arg0) {}
  public void mouseDragged(MouseEvent arg0) {
    if (this.m_engine!=null) {
      this.m_engine.getInput().getMouse().getPosition().setCoordinates(arg0.getX(), arg0.getY());
    }        
  }
  public void mouseMoved(MouseEvent arg0) {
    if (this.m_engine!=null) {
      this.m_engine.getInput().getMouse().getPosition().setCoordinates(arg0.getX(), arg0.getY());
    }    
  }
  public void mousePressed(MouseEvent arg0) {
    if (this.m_engine!=null) {
      this.m_engine.getInput().getMouse().setState(arg0.getButton(), true);
    }
  }
  public void mouseReleased(MouseEvent arg0) {
    if (this.m_engine!=null) {
      this.m_engine.getInput().getMouse().setState(arg0.getButton(), false);
    }    
  }
}
