package Santa2019;

import java.awt.Color;
import java.util.Vector;

import Engine.Entity;
import Engine.GameEngine;
import Engine.GameGraphics;

public class SkyBackground extends Entity
{
	private Vector<SkyColor> m_colors;
	
	public SkyBackground(GameEngine engine) {
		super("skybackground");
		
		this.m_colors = new Vector<SkyColor>();
		
		this.addColor(0, 0, 0, 0);
		this.addColor(40, 0, 150, 120);
		this.addColor(0, 148, 255, 240);
				
		this.setWidth(engine.getWidth());
		this.setHeight(engine.getHeight());
	}
	
	public void addColor(int r, int g, int b, int h) {
		SkyColor color = new SkyColor(r,g,b,h);
		this.m_colors.add(color);
	}
	
	public void paint(GameGraphics graphics) {
		double ddr,ddg,ddb;
		double dr,dg,db;
		double dh;
		int i,j;
		int w = this.getWidth();
		
		for(j=0;j<(this.m_colors.size()-1);j++) {
			SkyColor skyColor1 = this.m_colors.elementAt(j);
			SkyColor skyColor2 = this.m_colors.elementAt(j+1);
			
			dh = (skyColor2.getHeight() - skyColor1.getHeight());
			ddr = ((skyColor2.getRed() - skyColor1.getRed()))/dh;
			ddg = ((skyColor2.getGreen() - skyColor1.getGreen()))/dh;
			ddb = ((skyColor2.getBlue() - skyColor1.getBlue()))/dh;	
			
			dr = skyColor1.getRed();
			dg = skyColor1.getGreen();
			db = skyColor1.getBlue();
			for(i=skyColor1.getHeight();i<skyColor2.getHeight();i++) {
				Color color = new Color((int)dr,(int)dg,(int)db);
				graphics.drawHLine(0, i, w, color);
				
				dr+=ddr;
				dg+=ddg;
				db+=ddb;
				
				if (dr<0)dr=0;
				if (dr>255)dr=255;
				if (dg<0)dg=0;
				if (db>255)dg=255;
				if (db<0)db=0;
				if (db>255)db=255;
			}
		}		
	}
	
	public class SkyColor {
		private int m_red;
		private int m_green;
		private int m_blue;
		private int m_height;
		
		public SkyColor(int r, int g, int b, int h) {
			this.m_red = r;
			this.m_green = g;
			this.m_blue = b;
			this.m_height = h;
		}
		
		public int getRed() {return this.m_red;}
		public int getGreen() {return this.m_green;}
		public int getBlue() {return this.m_blue;}
		public int getHeight() {return this.m_height;}
	}
}