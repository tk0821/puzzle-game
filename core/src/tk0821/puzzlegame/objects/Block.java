package tk0821.puzzlegame.objects;

import com.badlogic.gdx.graphics.Color;

import tk0821.puzzlegame.game.GameScreen;

public class Block {

	public static final int SIZE = 100; // 1ブロックの大きさ

	// ブロックの色
	public static final int BLUE = 0;
	public static final int RED = 1;
	public static final int GREEN = 2;
	public static final int YELLOW = 3;
	public static final int NONE = 4;

	private int x;
	private int y;
	private int color;

	public Block(int x, int y, int color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean hasColor() {
		return (color != NONE);
	}

	public void draw() {
		setShapeRendererColor();
		GameScreen.shapeRenderer.rect(x, y, SIZE, SIZE);
	}

	public void draw(int margin) {
		setShapeRendererColor();
		GameScreen.shapeRenderer.rect(x + margin, y + margin, SIZE - (margin * 2), SIZE - (margin * 2));
	}
	
	private void setShapeRendererColor() {
		switch (color) {
		case Block.BLUE:
			GameScreen.shapeRenderer.setColor(Color.BLUE);
			break;
		case Block.RED:
			GameScreen.shapeRenderer.setColor(Color.RED);
			break;
		case Block.GREEN:
			GameScreen.shapeRenderer.setColor(Color.GREEN);
			break;
		case Block.YELLOW:
			GameScreen.shapeRenderer.setColor(Color.YELLOW);
			break;
		default:
			GameScreen.shapeRenderer.setColor(Color.DARK_GRAY);
		}
	}
}
