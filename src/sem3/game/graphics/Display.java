package sem3.game.graphics;

import sem3.game.graphics.sprite.Sprite;
import sem3.game.level.tile.Tile;

public class Display { // Handles all of the rendering methods

	public final int WIDTH, HEIGHT;
	private int pixels[];
	private int xOffSet, yOffSet;

	public Display(int width, int height, int[] pixels) {
		WIDTH = width;
		HEIGHT = height;
		this.pixels = pixels;
	}

	public void clear() { // Clears the screen every frame
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xffffffff;
		}
	}

	public void render() { // Render method to render color to screen ,only for testing purposes
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xffff00ff;
		}
	}

	public void renderTile(int xPos, int yPos, Tile tile) { // Tile are part of the map
		xPos -= xOffSet; // Setting offSet based on the position of the player so that tiles and sprite appear in an absolute position
		yPos -= yOffSet;
		for (int y = 0; y < tile.sprite.height; y++) {
			int yAbsolute = yPos + y;
			if (yAbsolute < 0 || yAbsolute >= HEIGHT) continue;
			for (int x = 0; x < tile.sprite.width; x++) {
				int xAbsolute = xPos + x;
				if (xAbsolute < 0 || xAbsolute >= WIDTH) continue;
				int color = tile.sprite.pixels[x + y * tile.sprite.width];
				if (color != 0xff303040) {
					this.pixels[xAbsolute + yAbsolute * WIDTH] = color;
				}
			}
		}
	}

	public void renderSprite(int xPos, int yPos, Sprite sprite) { // Rendering a sprite (ie player, enemy, object, etc)
		xPos -= xOffSet;
		yPos -= yOffSet;
		for (int y = 0; y < sprite.height; y++) {
			int yAbsolute = yPos + y;
			if (yAbsolute < 0 || yAbsolute >= HEIGHT) continue;
			for (int x = 0; x < sprite.width; x++) {
				int xAbsolute = xPos + x;
				if (xAbsolute < 0 || xAbsolute >= WIDTH) continue;
				int color = sprite.pixels[x + y * sprite.width];
				if (color != 0xff303040) {
					this.pixels[xAbsolute + yAbsolute * WIDTH] = color;
				}
			}
		}
	}

	public void renderUI(int xPos, int yPos, int width, int height, int HP, boolean fixedToWindow) {
		if (!fixedToWindow) { // If we don't offSet we render this to a fixed position on the window
			xPos -= xOffSet;
			yPos -= yOffSet;
		}
		for (int y = 0; y < height; y++) {
			int yAbsolute = yPos + y;
			if (yAbsolute < 0 || yAbsolute >= HEIGHT) continue;
			for (int x = 0; x < width; x++) {
				int xAbsolute = xPos + x;
				if (xAbsolute < 0 || xAbsolute >= WIDTH) continue;
				if (x <= HP) {
					this.pixels[xAbsolute + yAbsolute * WIDTH] = 0xffff0000;
				} else {
					this.pixels[xAbsolute + yAbsolute * WIDTH] = 0xffffffff;
				}
			}
		}
	}

	public void setOffSet(int xOffSet, int yOffSet) { // Sets the offSet based on player location, so that object appear based on position on map
		this.xOffSet = xOffSet;
		this.yOffSet = yOffSet;
	}
}
