package Engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import Engine.GameGraphics;
import Engine.Vector2;

public class Font {
  private Vector<Character> m_characters;
  private int m_defaultWidth;
  private int m_defaultHeight;
  private int m_defaultCharacterSpacing;
  
  private int m_characterWidth;
  private int m_characterHeight;
  
  public Font() {
    this.m_characters = new Vector<Character>();
    this.setDefaultWidth(8);
    this.setDefaultHeight(8);
    this.setDefaultCharacterSpacing(1);
  }
  
  public void loadFromDirectory(String path) {
    String filename;
    File fimage;
    Character ch;
    BufferedImage img;
    int i;
    int maxWidth;
    int maxHeight;
    
    maxWidth = Integer.MIN_VALUE;
    maxHeight = Integer.MIN_VALUE;
    
    for(i=32;i<127;i++) {
      filename = path + "\\" + Integer.toString(i) + ".png";
      fimage = new File(filename);
      if (fimage.exists()) {
        img = GameGraphics.loadImage(filename);
        ch = new Character();
        ch.setCharacter(i);
        ch.setImage(img);
        this.m_characters.add(ch);
        
        if (ch.getImage().getWidth()>=maxWidth) maxWidth = ch.getImage().getWidth();
        if (ch.getImage().getHeight()>=maxHeight) maxHeight = ch.getImage().getHeight();        
      }        
    }
    
    this.setCharacterWidth(maxWidth);
    this.setCharacterHeight(maxHeight);
  }
  
  public void loadFromLayout(String filenameLayout, String filenameBufferedImage) {
    Layout layout;
    Layout.Block block;
    BufferedImage image;
    BufferedImage sheet;
    Graphics graphics;
    Character ch;    
    int i;
    int maxWidth;
    int maxHeight;
    
    maxWidth = Integer.MIN_VALUE;
    maxHeight = Integer.MIN_VALUE;
    
    // load layout
    layout = new Layout();
    layout.load(new File(filenameLayout));
    
    // load image
    sheet = GameGraphics.loadImage(filenameBufferedImage);
    
    // clear existing font
    this.m_characters.clear();
    
    for(i=0;i<layout.getBlocks().size();i++) {
      block = layout.getBlocks().elementAt(i);      
      image = new BufferedImage(block.getWidth(),block.getHeight(),BufferedImage.TYPE_INT_ARGB);
      graphics = image.getGraphics();
      graphics.drawImage(
           sheet
          ,0, 0, block.getWidth(), block.getHeight()
          ,block.getX(), block.getY()
          ,block.getX() + block.getWidth()
          ,block.getY() + block.getHeight()
          ,null
      );
      
      ch = new Character();
      ch.setCharacter(Integer.parseInt(block.getKey()));
      ch.setImage(image);
      this.m_characters.add(ch);
      
      if (ch.getImage().getWidth()>=maxWidth) maxWidth = ch.getImage().getWidth();
      if (ch.getImage().getHeight()>=maxHeight) maxHeight = ch.getImage().getHeight();              
    }
    
    this.setCharacterWidth(maxWidth);
    this.setCharacterHeight(maxHeight);    
  }
    
  public void drawFont(int x, int y, GameGraphics graphics, String str) {
    int i,j;
    int dx;
    int w,h;
    Character ch;
    BufferedImage img;
    
    dx = x;
    for(i=0;i<str.length();i++) {
      j = str.charAt(i);
      w = this.getDefaultWidth();
      ch = this.findCharacter(j);
      if (ch!=null) {
        img = ch.getImage();
        w = img.getWidth(); 
        h = img.getHeight();
        graphics.drawImage(dx, y, w, h, img);
      }
      dx += w + this.getDefaultCharacterSpacing();
    }
  }
  
  public Vector2 measureFont(String str) {
    Vector2 v = new Vector2();
    this.measureFont(str, v);
    return v;
  }
  
  public void measureFont(String str, Vector2 v) {
    int i,j;
    int dx,dy;
    int w,h;
    Character ch;
    BufferedImage img;

    dx = 0;
    dy = 0;
    for(i=0;i<str.length();i++) {
      j = str.charAt(i);
      w = this.getDefaultWidth();
      ch = this.findCharacter(j);
      if (ch!=null) {
        img = ch.getImage();
        w = img.getWidth(); 
        h = img.getHeight();
        if (dy<h) dy=h;
      }
      dx += w + this.getDefaultCharacterSpacing();
    }
    
    v.setCoordinates(dx, dy);
  }
  
  private Character findCharacter(int character) {
    Character ch;
    int i;
    for(i=0;i<this.m_characters.size();i++) {
      ch = this.m_characters.elementAt(i);
      if (ch.getCharacter()==character) {
        return ch;
      }
    }
    return null;
  }
  
  public void setDefaultWidth(int width) {this.m_defaultWidth=width;}
  public int getDefaultWidth() {return this.m_defaultWidth;}
  public void setDefaultHeight(int height) {this.m_defaultHeight=height;}
  public int getDefaultHeight() {return this.m_defaultHeight;}
  public void setDefaultCharacterSpacing(int spacing) {this.m_defaultCharacterSpacing=spacing;}
  public int getDefaultCharacterSpacing() {return this.m_defaultCharacterSpacing;}
    
  public void setCharacterWidth(int width) {this.m_characterWidth=width;}
  public int getCharacterWidth() {return this.m_characterWidth;}
  public void setCharacterHeight(int height) {this.m_characterHeight=height;}
  public int getCharacterHeight() {return this.m_characterHeight;}
  
  public class Character {
    private int m_character;
    private BufferedImage m_image;
    
    public void setCharacter(int ch) {this.m_character=ch;}
    public int getCharacter() {return this.m_character;}
    public void setImage(BufferedImage image) {this.m_image=image;}
    public BufferedImage getImage() {return this.m_image;}
  }  
}