package Santa2019;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Vector;

import Engine.GameGraphics;
import Engine.Vector2;

public class TileMap extends Map {
	private Vector<BufferedImage> m_tiles;
	
	public TileMap() {
		super();
		this.setKey("tilemap");
		this.m_tiles = new Vector<BufferedImage>();
	}
	
	public void paintTile(GameGraphics graphics, int x, int y, int w, int h, int tile) {
    BufferedImage image;
    SceneMain scene = (SceneMain)((Santa2019)this.getGameEngine()).getSceneManager().getActiveScene();
    
		if ((tile>=0)&&(tile<this.m_tiles.size())) {
			image = this.m_tiles.elementAt(tile);
			if (image!=null) {
				//graphics.drawImage(x, y, w, h, image);
			  scene.getCamera().drawImage(image, x, y, w, h, graphics);
			} else {
				if (tile>0)
					//graphics.drawRect(x, y, w, h, Color.white);
				  scene.getCamera().drawImage(image, x, y, w, h, graphics);
			}
		}
	}
	
	public Vector<BufferedImage> getTiles(){return this.m_tiles;}
}
