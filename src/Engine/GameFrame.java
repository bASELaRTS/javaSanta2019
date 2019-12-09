package Engine;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
  private static final long serialVersionUID = 1L;

  private GamePanel m_panel;
  
  public GameFrame(GameEngine engine) {
    super();
    
    this.m_panel = new GamePanel(engine);
    this.m_panel.setGameFrame(this);
    
    this.setTitle(engine.getName());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setResizable(engine.getResizable());
    this.add(this.m_panel);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }
  
  public void raiseExit() {
    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }
}
