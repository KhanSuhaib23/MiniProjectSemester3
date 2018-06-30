package sem3.game.graphics.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet { // Loading various spritesheet containing various sprites

	public int width, height;
	public int[] pixels;
	public int sizeOfCell;

	public static SpriteSheet testSheet = new SpriteSheet("/textures/tiles/testTile.png", 48); // various static calls to itself
	public static SpriteSheet testSprite = new SpriteSheet("/textures/sprites/testSprite.png", 48);
	
	public static SpriteSheet playerSprites = new SpriteSheet("/textures/sprites/playerSprites.png", 48);
	public static SpriteSheet projectileSprite = new SpriteSheet("/textures/sprites/projectileSprites.png", 48);
	public static SpriteSheet enemySprite = new SpriteSheet("/textures/sprites/enemySprites.png", 48);
	public static SpriteSheet levelTiles = new SpriteSheet("/textures/tiles/levelTiles.png", 48);

	public SpriteSheet(String path, int sizeOfCell) {
		loadSpriteSheet(path);
		this.sizeOfCell = sizeOfCell;
	}

	private void loadSpriteSheet(String path) {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
