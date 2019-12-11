package Santa2019;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import Engine.Entity;
import Engine.GameGraphics;
import Engine.Vector2;

public class Map extends Entity {
	private String m_name;
	private int m_mapWidth;
	private int m_mapHeight;
	private int m_tileWidth;
	private int m_tileHeight;
	private int[] m_data;
	
	public Map() {                                                                                                                                                                                                                
		super("map");
		this.m_name = "";
		this.m_mapWidth = 0;
		this.m_mapHeight = 0;
		this.m_tileWidth = 0;
		this.m_tileHeight = 0;
	}
	
	public void create(int w, int h, int tw, int th) {
		this.m_mapWidth = w;
		this.m_mapHeight = h;
		this.m_tileWidth = tw;
		this.m_tileHeight = th;
		
		this.m_data = new int[w*h];
		this.fill(0);
	}
	
	public void load(java.io.File file) {
    java.io.FileReader fr;
    java.io.BufferedReader br;
    String line;
    String strs[];
    int i,j;
    int mw,mh,tw,th;
    int tile;
    

    try {	    
  		fr = new java.io.FileReader(file);
      br = new java.io.BufferedReader(fr);

      this.m_name = br.readLine(); // name;
	    mw = Integer.parseInt(br.readLine()); // width;
	    mh = Integer.parseInt(br.readLine()); // height;
	    tw = Integer.parseInt(br.readLine()); // tilewidth;
	    th = Integer.parseInt(br.readLine()); // tileheight;
	    
	    this.create(mw,mh,tw,th);
	    
	    for(j=0;j<this.getMapHeight();j++) {
		    line = br.readLine(); // row[j]...
	    	strs = line.split(";");
	    	if (strs.length==this.getMapWidth()) {
	    		for(i=0;i<this.getMapWidth();i++) {
	    			tile = Integer.parseInt(strs[i]);
	    			this.setTile(i, j, tile);
	    		}
	    	} else {
	    		System.out.println("Map:load:Invalid file");
	    		
	    		br.close();
	    		fr.close();
	    		
	    		return;
	    	}
	    }
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
	}
	
	public void save(java.io.File file) {
    java.io.FileWriter fw;
    java.io.BufferedWriter bw;
    String line;
    int i,j;
    int tile;
    

    try {	 
  		fw = new java.io.FileWriter(file);
      bw = new java.io.BufferedWriter(fw);

      bw.write(this.m_name);bw.newLine();
    	bw.write("" + this.getMapWidth());bw.newLine();
    	bw.write("" + this.getMapHeight());bw.newLine();
    	bw.write("" + this.getTileWidth());bw.newLine();
    	bw.write("" + this.getTileHeight());bw.newLine();
    	
	    for(j=0;j<this.getMapHeight();j++) {
	    	line = "";
	    	for(i=0;i<this.getMapWidth();i++) {
	    		tile = this.getTile(i,j);
	    		line+=""+tile;
	    		if (i<(this.getMapWidth()-1)) {
	    			line+=";";
	    		}
	    	}
	    	bw.write(line);
    		bw.newLine();
	    }
	    
			bw.close();
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
	}

	public void fill(int tile) {
		int i;
		for(i=0;i<(this.getMapWidth()*this.getMapHeight());i++) {
			this.m_data[i] = tile;
		}
	}
	
	public void paint(GameGraphics graphics) {
		int i,j;
		int w,h;
		int tw,th;
		int tile;
		int px,py;
		
		w = this.getMapWidth();
		h = this.getMapHeight();
		tw = this.getTileWidth();
		th = this.getTileHeight();
		
		py = 0;
		for(j=0;j<h;j++) {
			px = 0;
			for(i=0;i<w;i++) {
				tile = this.getTile(i, j);
				this.paintTile(graphics, px, py, tw, th, tile);
				px+=tw;				
			}
			py+=th;			
		}
	}
	
	public void paintTile(GameGraphics graphics, int x, int y, int w, int h, int tile) {
		if (tile!=-1) {
			graphics.drawRect(x, y, w, h, Color.white);
		}
	}
	
	public void setTile(int index, int tile) {
		if ((index>=0)&&(index<(this.getMapWidth()*this.getMapHeight()))) {
			this.m_data[index]=tile;
		}
	}
	
	public void setTile(int x, int y, int tile) {
		int index = y*this.getMapWidth()+x;
		this.setTile(index, tile);
	}
	
	public int getTile(int index) {
		if ((index>=0)&&(index<(this.getMapWidth()*this.getMapHeight()))) {
			return this.m_data[index];
		}
		return -1;
	}
	
	public int getTile(int x, int y) {
		int index = y*this.getMapWidth()+x;
		return this.getTile(index);
	}
	
	public void setName(String name) {this.m_name=name;}
	public String getName() {return this.m_name;}
	public int getMapWidth() {return this.m_mapWidth;}
	public int getMapHeight() {return this.m_mapHeight;}
	public int getTileWidth() {return this.m_tileWidth;}
	public int getTileHeight() {return this.m_tileHeight;}
}
