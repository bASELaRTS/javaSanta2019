package Engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GameGraphics {
  private int m_width;
  private int m_height;
  
  private BufferedImage m_buffer;
  private Graphics m_graphics;

  private Color m_backgroundColor;
  
  public GameGraphics(int width, int height) {
    this.setWidth(width);
    this.setHeight(height);
    this.setBackgroundColor(new Color(0,0,0));
    
    this.m_buffer = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
    this.m_graphics = this.m_buffer.getGraphics();     
  }
  
  public void clear() {
    this.m_graphics.setColor(this.getBackgroundColor());
    this.m_graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
  }
  
  public void drawLine(int x1, int y1, int x2, int y2, Color color) {
    if (x1==x2) {
      this.drawVLine(x1, y1, y2, color);
    } else if (y1==y2) {
      this.drawHLine(x1, y1, x2, color);
    } else {
      this.m_graphics.setColor(color);
      this.m_graphics.drawLine(x1, y1, x2, y2);
    }    
  }
  
  public void drawVLine(int x1, int y1, int y2, Color color) {
    this.m_graphics.setColor(color);
    this.m_graphics.drawLine(x1, y1, x1, y2);
  }
  
  public void drawImage(int x, int y, int width, int height, BufferedImage img) {
    if (((x+width)>0)&&(x<this.m_width)&&((y+height)>0)&&(y<this.m_height)) {
      this.m_graphics.drawImage(img, x, y, width, height, null);
    }
  }

  public void drawRect(int x, int y, int width, int height, Color color) {
    this.m_graphics.setColor(color);
    this.m_graphics.drawRect(x, y, width, height);
  }
  
  public void fillRect(int x, int y, int width, int height, Color color) {
    this.m_graphics.setColor(color);
    this.m_graphics.fillRect(x, y, width, height);
  }
  
  public void fillCircle(int x, int y, int r, Color color) {
    this.m_graphics.setColor(color);
    this.m_graphics.fillOval(x-r, y-r, r+r, r+r);
  }
  
  public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color) {
    int i,j,k;
    int xs[];
    int xe[];
    double ds;    
    double dl;
    double dsx;
    double dsl;
    
    if (y1>y2) {
      i = x1;
      x1 = x2;
      x2 = i;
      
      i = y1;
      y1=y2;
      y2=i;
    }
    
    if (y1>y3) {
      i = x1;
      x1 = x3;
      x3 = i;
      
      i = y1;
      y1 = y3;
      y3 = i;
    }

    if (y2>y3) {
      i = x2;
      x2 = x3;
      x3 = i;
      
      i = y2;
      y2 = y3;
      y3 = i;
    }
    
    k = 0;
    i = (y3-y1);
    xs = new int[i];
    xe = new int[i];
    dsx = x1;
    dsl = x1;
    dl = (double)(x3-x1)/(y3-y1);

    ds = (double)(x2-x1)/(y2-y1);
    for(j=y1;j<y2;j++) {
      xs[k] = (int)dsx;
      xe[k] = (int)dsl;
      
      if (xs[k]>xe[k]) {
        i=xs[k];
        xs[k]=xe[k];
        xe[k]=i;
      }
      
      k++;
      dsx+=ds;
      dsl+=dl;
    }
    
    ds = (double)(x3-x2)/(y3-y2);
    for(j=y2;j<y3;j++) {
      xs[k] = (int)dsx;
      xe[k] = (int)dsl;
      
      if (xs[k]>xe[k]) {
        i=xs[k];
        xs[k]=xe[k];
        xe[k]=i;
      }
      
      k++;
      dsx+=ds;
      dsl+=dl;
    }
    
    for(i=0;i<k;i++) {
      this.drawHLine(xs[i], y1+i, xe[i],color);
    }
  }
  
  public void drawHLine(int x1, int y1, int x2, Color color) {
    this.m_graphics.setColor(color);
    this.m_graphics.drawLine(x1, y1, x2, y1);
  }
    
  public void drawString(int x, int y, String text, Color color) {
    this.m_graphics.setColor(color);
    this.m_graphics.drawString(text, x, y);
  }
  public int measureStringWidth(String text) {
    return this.m_graphics.getFontMetrics().stringWidth(text);
  }
    
  public void setWidth(int width) {this.m_width=width;}
  public int getWidth() {return this.m_width;}
  public void setHeight(int height) {this.m_height=height;}
  public int getHeight() {return this.m_height;}
  
  public BufferedImage getImage() {return this.m_buffer;}
  public void setBackgroundColor(Color color) {this.m_backgroundColor=color;}
  public Color getBackgroundColor() {return this.m_backgroundColor;}
  
  // static functions
  public static BufferedImage loadImage(String filename) {
    File file = new File(filename);
    return GameGraphics.loadImage(file);
  }
  public static BufferedImage loadImage(File file) {
    try {      
      return ImageIO.read(file);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
    return null;
  }
}
