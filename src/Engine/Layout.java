package Engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Layout {
  private Vector<Block> m_blocks;
  
  public Layout() {
    this.m_blocks = new Vector<Block>();
  }      
  
  public void load(File file) {
    int i,c;
    String s;
    String[] ss;
    Block block;
    java.io.FileReader fr;
    java.io.BufferedReader br;

    this.getBlocks().clear();
    
    try {
      fr = new FileReader(file);
      br = new BufferedReader(fr);
      
      c = Integer.parseInt(br.readLine());
      for(i=0;i<c;i++) {
        s = br.readLine();
        ss = s.split(";");
        
        block = new Block();
        block.setKey(ss[0]);
        block.setXY(Integer.parseInt(ss[1]), Integer.parseInt(ss[2]));
        block.setSize(Integer.parseInt(ss[3]), Integer.parseInt(ss[4]));
        this.getBlocks().add(block);
      }
      
      br.close();
      fr.close();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }    
  }
  
  public void save(File file) {
    int i;    
    String s;
    Block block;
    java.io.FileWriter fw;
    java.io.BufferedWriter bw;

    try {
      fw = new FileWriter(file);
      bw = new BufferedWriter(fw);

      s = "" + this.getBlocks().size();      
      bw.write(s);
      bw.newLine();
      
      for(i=0;i<this.getBlocks().size();i++) {
        block = this.getBlocks().elementAt(i);
        
        s = block.getKey();
        s += ";" + block.getX();
        s += ";" + block.getY();
        s += ";" + block.getWidth();
        s += ";" + block.getHeight();
        bw.write(s);
        bw.newLine();
      }
      bw.flush();
      bw.close();
      fw.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }        
  }
  
  public Block findBlock(String key) {
  	int i;
  	Block block;
  	for(i=0;i<this.m_blocks.size();i++) {
  		block = this.m_blocks.elementAt(i);
  		if (block.getKey().equals(key)) {
  			return block;
  		}
  	}
  	return null;
  }
  
  public Vector<Block> getBlocks(){return this.m_blocks;}
  
  public class Block {
    private int m_x;
    private int m_y;
    private int m_width;
    private int m_height;
    private String m_key;
    
    public Block() {
      this.setKey("");
      this.setXY(0, 0);
      this.setSize(0, 0);
    }
    
    public void setX(int x) {this.m_x=x;}
    public int getX() {return this.m_x;}
    public void setY(int y) {this.m_y=y;}
    public int getY() {return this.m_y;}
    public void setXY(int x, int y) {
      this.setX(x);
      this.setY(y);
    }
    
    public void setWidth(int w) {this.m_width=w;}
    public int getWidth() {return this.m_width;}
    public void setHeight(int h) {this.m_height=h;}
    public int getHeight() {return this.m_height;}
    public void setSize(int w, int h) {
      this.setWidth(w);
      this.setHeight(h);
    }
    
    public void setKey(String key) {this.m_key=key;}
    public String getKey() {return this.m_key;}
  }
}
