package Santa2019;

import Engine.*;

public class Santa2019 extends GameEngine {
  private Font[] m_fonts;
  
  public Santa2019() {
    super("Santa2019",256,256);
    
    this.m_fonts = new Font[1];    
    this.m_fonts[0] = new Font();
    this.m_fonts[0].loadFromLayout(
         "data/font/font.layout"
        ,"data/font/font.png"
    );
    
    this.getSceneManager().add(new SceneMain(this));
    this.getSceneManager().setActiveScene(0);
  }
  
  public Font getFont(int index) {return this.m_fonts[index];}
  
  public static void main(String[] args) {
    new GameFrame(new Santa2019());
  }  
}
