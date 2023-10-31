package tk0821.puzzlegame.objects;

import com.badlogic.gdx.graphics.Color;

import tk0821.puzzlegame.game.GameScreen;

public class InfoBoard {
	// ネクスト
	public static final int NEXT_X = 700;
	public static final int NEXT_Y = 400;
	public static final float NEXT_SIZE = 2.5f;
	public static final int NEXT_BLOCK_X = NEXT_X + (int) (Block.SIZE * NEXT_SIZE - Block.SIZE * 2) / 2;
	public static final int NEXT_BLOCK_Y = NEXT_Y + (int) (Block.SIZE * NEXT_SIZE - Block.SIZE * 2) / 2;

	// インフォ
	public static final int INFO_X = 700;
	public static final int INFO_Y = 70;
	static final float INFO_SIZE_X = 5f;
	static final float INFO_SIZE_Y = 3f;
	
	public InfoBoard() {
		
	}
	
	public void draw(){
		GameScreen.shapeRenderer.setColor(Color.LIGHT_GRAY);
		GameScreen.shapeRenderer.rect(NEXT_X, NEXT_Y, NEXT_SIZE * Block.SIZE, NEXT_SIZE * Block.SIZE);// ネクストの枠
		GameScreen.shapeRenderer.rect(INFO_X, INFO_Y, INFO_SIZE_X * Block.SIZE, INFO_SIZE_Y * Block.SIZE);// infoの枠
	}
}
