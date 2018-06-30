package sem3.game.level.tile;

import sem3.game.Game;
import sem3.game.graphics.Display;
import sem3.game.graphics.sprite.Sprite;

public class FatalTile extends Tile { // If the player touches these tiles he dies

	public FatalTile(Sprite sprite) {
		super(sprite);
	}

	public void render(Display display, int x, int y) {
		display.renderTile(x * Game.TILE_SIZE, y * Game.TILE_SIZE, this);
	}

	public boolean solid() {
		return true;
	}

	public boolean fatal() {
		return true;
	}

}
