package tk0821.puzzlegame.objects;

import com.badlogic.gdx.graphics.Color;

import tk0821.puzzlegame.game.GameScreen;

public class Board {
	// ボード
	public Block[][] gameBoard; // ボード (0,0)で左下

	public static final int BOARD_SIZE = 5;
	public static final int BOARD_X = 100;
	public static final int BOARD_Y = 110;

	private static final int FRAME_X = BOARD_X - (Block.SIZE / 2);
	private static final int FRAME_Y = BOARD_Y - (Block.SIZE / 2);
	private static final int FRAME_W = (BOARD_SIZE + 1) * Block.SIZE;
	private static final int FRAME_H = (BOARD_SIZE + 1) * Block.SIZE;

	public Board() {
		// ボード生成
		gameBoard = new Block[BOARD_SIZE][BOARD_SIZE];
		for (int y = 0; y < BOARD_SIZE; y++) {
			for (int x = 0; x < BOARD_SIZE; x++) {
				gameBoard[y][x] = new Block(BOARD_X + x * Block.SIZE, BOARD_Y + y * Block.SIZE, Block.NONE);
			}
		}
	}

	public Block getBlock(int x, int y) {
		return gameBoard[y][x];
	}

	public void setBlockColor(int x, int y, int color) {
		gameBoard[y][x].setColor(color);
	}

	public void draw() {
		GameScreen.shapeRenderer.setColor(Color.LIGHT_GRAY);
		drawFrame();
		drawBoard();
	}

	private void drawFrame() {
		// ボードの枠
		GameScreen.shapeRenderer.rect(FRAME_X, FRAME_Y, FRAME_W, FRAME_H);
	}

	private void drawBoard() {
		// ボードのマス
		for (int y = 0; y < BOARD_SIZE; y++) {
			for (int x = 0; x < BOARD_SIZE; x++) {
				int margin = gameBoard[y][x].hasColor() ? 1 : 3;
				gameBoard[y][x].draw(margin);
			}
		}
	}
}
