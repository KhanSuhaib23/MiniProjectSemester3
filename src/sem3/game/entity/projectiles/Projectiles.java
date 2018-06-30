package sem3.game.entity.projectiles;

import sem3.game.entity.Entity;
import sem3.game.entity.mobs.Player;
import sem3.game.graphics.Display;
import sem3.game.graphics.sprite.Sprite;
import sem3.game.level.Level;

public class Projectiles extends Entity {

	public int xOrigin, yOrigin; // The place from which the projectile was shot
	protected int range = 600; // Range after which it is to be removed
	protected boolean doesDamage = true;
	protected int damage = 50;
	protected int direction = 0;

	public Projectiles(int x, int y, Sprite[] sprite, Level level, int direction) {
		super(x, y, sprite, level);
		xOrigin = x;
		yOrigin = y;
		this.direction = direction;
		if (direction == 1) {
			currSprite = sprite[0];
		} else if (direction == -1) {
			currSprite = sprite[5];
		}

	}

	public void update() {
		calculateCollisionBox(0, 0);
		if (x - xOrigin >= range) {
			remove();
			return;
		}
		x += 10 * direction;
		if (direction == 1) {
			if (spriteCollision()) {
				doesDamage = false;
				if (playAnimation(sprite, 5, 0)) {
					remove();
				}
			} else if (collision(x, y)) {
				if (playAnimation(sprite, 5, 0)) {
					remove();
				}
			}
		} else if (direction == -1) {
			if (spriteCollision()) {
				doesDamage = false;
				if (playAnimation(sprite, 5, 5)) {
					remove();
				}
			} else if (collision(x, y)) {
				if (playAnimation(sprite, 5, 5)) {
					remove();
				}
			}
		}
	
	}

	public void render(Display display) {
		display.renderSprite(x, y, currSprite);
	}

	public boolean spriteCollision() {
		boolean xCollision = false;
		boolean yCollision = false;
		for (int i = 0; i < level.mobs.size(); i++) {
			xCollision = false;
			yCollision = false;
			if (Math.abs(level.mobs.get(i).x0 - x1) < Math.abs(level.mobs.get(i).x1 - x0)) {
				if (level.mobs.get(i).x0 - x1 <= 0) {
					xCollision = true;
				}
			} else if (Math.abs(x0 - level.mobs.get(i).x1) < Math.abs(x1 - level.mobs.get(i).x0)) {
				if (x0 - level.mobs.get(i).x1 <= 0) {
					xCollision = true;
				}
			}
			if (Math.abs(level.mobs.get(i).y0 - y1) < Math.abs(level.mobs.get(i).y1 - y0)) {
				if (level.mobs.get(i).y0 - y1 <= 0) {
					yCollision = true;
				}
			} else if (Math.abs(y0 - level.mobs.get(i).y1) < Math.abs(y1 - level.mobs.get(i).y0)) {
				if (y0 - level.mobs.get(i).y1 <= 0) {
					yCollision = true;
				}
			}
			if (xCollision && yCollision && doesDamage) {
				level.mobs.get(i).doDamage(damage);
				if (level.mobs.get(i).HP <= 0) {
					if (!(level.mobs.get(i) instanceof Player)) {
						level.mobs.remove(i);
					}
				}
			}
			if (xCollision && yCollision) {
				return (xCollision && yCollision);
			}
		}
		return (xCollision && yCollision);
	}

}
