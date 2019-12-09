package Santa2019;

import java.awt.Color;

import Engine.Entity;
import Engine.GameEngine;
import Engine.GameGraphics;

public class SkyBackground extends Entity
{
	private Color m_colorTop;
	private Color m_colorBottom;
	
	public SkyBackground(GameEngine engine) {
		super("skybackground");
		
		this.setColorTop(new Color(72,0,255));
		this.setColorBottom(new Color(0,148,255));
		
		this.setWidth(engine.getWidth());
		this.setHeight(engine.getHeight());
	}
	
	public void paint(GameGraphics graphics) {
		double ddr,ddg,ddb;
		double dr,dg,db;
		int i;
		int w = this.getWidth();
		int h = this.getHeight();
		ddr = (this.m_colorBottom.getRed() - this.m_colorTop.getRed())/(double)h;
		ddg = (this.m_colorBottom.getGreen() - this.m_colorTop.getGreen())/(double)h;
		ddb = (this.m_colorBottom.getBlue()-this.m_colorTop.getBlue())/(double)h;
		dr = this.m_colorTop.getRed();
		dg = this.m_colorTop.getGreen();
		db = this.m_colorTop.getBlue();  		
		for(i=0;i<h;i++) {
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
	
	public void setColorTop(Color color) {this.m_colorTop=color;}
	public void setColorBottom(Color color) {this.m_colorBottom=color;}
}