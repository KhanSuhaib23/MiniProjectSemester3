package sem3.game.entity;  //TODO

import sem3.game.graphics.Display;
import sem3.game.graphics.sprite.Sprite;
import sem3.game.level.Level;

public abstract class Entity { // Anything that is not the map is an entity

	protected int x, y; // Position
	protected int direction = 1;  // 1 -> right -1 -> left
	public int x0, x1, y0, y1;
	protected Sprite[] sprite; // Sprite that the entity uses
	protected Level level; // Level in which the entity spawns
	protected boolean removed = false; // After getting destoryed the entity is removed and no longer updated or rendered
	protected Sprite currSprite; // Entities can have various sprites, we choose the currSprite we want to disply
	protected int currentAnimation = 0;
	protected int collision = 0;  // Side 1 Top Bottom 2
	protected int animCounter = 0;
	protected int animationSpeed = 21;
	private boolean animStarted = false;

	public Entity(int x, int y, Sprite[] sprite, Level level) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.level = level;
	}

	public abstract void update();

	public abstract void render(Display display);

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
	
	public boolean collision(int x, int y) {
		boolean solid = false;

		int xt = 0;
		int yt = 0;
		for (int c = 0; c < 4; c++) {
			xt = (x + c % 2 * 45 + 0) / 48;
			yt = (y + c / 2 * 95 + 0) / 48;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	protected boolean playAnimation(Sprite[] sprite, int frames, int startIndex) {  
		if (!animStarted) {
			currentAnimation = startIndex;
			animStarted = true;
		}
		if (currentAnimation == startIndex + frames) return true;
		currSprite = sprite[currentAnimation];
		currentAnimation++;
		return false;
//		if (currentAnimation >= frames - 1) {
//			currentAnimation = startIndex;
//			return true;
//		}
//		currSprite = sprite[currentAnimation];
//		currentAnimation++;
//		return false;
	}
	
	protected void calculateCollisionBox(int xIndent, int yIndent) {
		x0 = x + xIndent;
		x1 = x + sprite[0].width - xIndent;
		y0 = y + yIndent;
		y1 = y + sprite[0].height - yIndent;
	}
	
	public boolean spriteCollision() {
		boolean xCollision = false;
		boolean yCollision = false;
		for (int i = 0; i < level.mobs.size(); i++) {
			if (Math.abs(level.mobs.get(i).x0 - x1) < Math.abs(level.mobs.get(i).x1 - x0)) {
				if (level.mobs.get(i).x0 - x1 <= 0) {
					collision = 1;
					xCollision = true;
				}
			} else if (Math.abs(x0 - level.mobs.get(i).x1) < Math.abs(x1 - level.mobs.get(i).x0)){
				if (x0 - level.mobs.get(i).x1 <= 0) {
					xCollision = true;
					collision = 1;
				}
			}
			if (Math.abs(level.mobs.get(i).y0 - y1) < Math.abs(level.mobs.get(i).y1 - y0)) {
				if (level.mobs.get(i).y0 - y1 <= 0) {
					yCollision = true;
					collision = 2;
				}
			} else if (Math.abs(y0 - level.mobs.get(i).y1) < Math.abs(y1 - level.mobs.get(i).y0)){
				if (y0 - level.mobs.get(i).y1 <= 0) {
					yCollision = true;
					collision = 2;
				}
			}
			if (xCollision && yCollision) {
				return (xCollision && yCollision);
			}
		}
		return (xCollision && yCollision);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
