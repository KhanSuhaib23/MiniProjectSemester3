package sem3.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import sem3.game.Game;
import sem3.game.entity.Entity;
import sem3.game.entity.mobs.Mob;
import sem3.game.entity.projectiles.Projectiles;
import sem3.game.graphics.Display;
import sem3.game.graphics.sprite.SpriteSheet;
import sem3.game.level.tile.Tile;

public class Level { // Updating things that happen in the level

	public int width, height; // Width and height of the level in tiles precision
	private int[] tiles; // A tiles array to help us determine which tile to render

	public List<Projectiles> projectiles = new ArrayList<Projectiles>(); // Array List projectiles to contain all the Projectile object
	public List<Mob> mobs = new ArrayList<Mob>();

	public Level(String path) { // Loading a level from a path
		loadLevel(path);
	}

	private void loadLevel(String path) { // Loading the level using BufferedImage ImageIO
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addProjectile(Projectiles p) { // Add Projectile method called from the projectile called when anyone shoots a projectile
		projectiles.add(p);
	}
	
	public void addMobs(Mob m) {
		mobs.add(m);
	}

	public void update() { // Updating method which updates all the entities (except the player)
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			if (projectiles.get(i).isRemoved()) {
				projectiles.remove(i);
			}
		}
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).update();
			if (mobs.get(i).isRemoved()) {
				mobs.remove(i);
			}
		}
	}

	public void render(Display display, int xScroll, int yScroll) { // Rendering the level in the window based of the position of the player
		display.setOffSet(xScroll, yScroll); // We set offSet with the position of player
		int x0 = xScroll / Game.TILE_SIZE; // We use the corner point method of rendering a level
		int x1 = (xScroll + display.WIDTH) / Game.TILE_SIZE;
		int y0 = yScroll / Game.TILE_SIZE;
		int y1 = (yScroll + display.HEIGHT) / Game.TILE_SIZE;

		for (int y = y0; y <= y1; y++) { // Getting the tile at a coordinate and rendering them
			for (int x = x0; x <= x1; x++) {
				getTile(x, y).render(display, x, y);
			}
		}
		for (int i = 0; i < projectiles.size(); i++) { // Rendering the projectiles
			projectiles.get(i).render(display);
		}
		for (int i = 0; i < mobs.size(); i++) { // Rendering the entities
			mobs.get(i).render(display);
		}
		
	}

	public Tile getTile(int x, int y) { // Getting the tile to be used (for either rendering or collision detection)
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) return Tile.tile12;
		else if (tiles[x + y * this.width] == Tile.COL_TILE0) return Tile.tile0;
		else if (tiles[x + y * this.width] == Tile.COL_TILE1) return Tile.tile1;
		else if (tiles[x + y * this.width] == Tile.COL_TILE2) return Tile.tile2;
		else if (tiles[x + y * this.width] == Tile.COL_TILE3) return Tile.tile3;
		else if (tiles[x + y * this.width] == Tile.COL_TILE4) return Tile.tile4;
		else if (tiles[x + y * this.width] == Tile.COL_TILE5) return Tile.tile5;
		else if (tiles[x + y * this.width] == Tile.COL_TILE6) return Tile.tile6;
		else if (tiles[x + y * this.width] == Tile.COL_TILE7) return Tile.tile7;
		else if (tiles[x + y * this.width] == Tile.COL_TILE8) return Tile.tile8;
		else if (tiles[x + y * this.width] == Tile.COL_TILE9) return Tile.tile9;
		else if (tiles[x + y * this.width] == Tile.COL_TILE10) return Tile.tile10;
		else if (tiles[x + y * this.width] == Tile.COL_TILE11) return Tile.tile11;
		else if (tiles[x + y * this.width] == Tile.COL_TILE12) return Tile.tile12;
		else if (tiles[x + y * this.width] == Tile.COL_TILE13) return Tile.tile13;
		else if (tiles[x + y * this.width] == Tile.COL_TILE14) return Tile.tile14;
		else return Tile.tile12;
	}
}
