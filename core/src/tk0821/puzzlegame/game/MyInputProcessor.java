package tk0821.puzzlegame.game;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import tk0821.puzzlegame.objects.Block;
import tk0821.puzzlegame.objects.Board;
import tk0821.puzzlegame.objects.InfoBoard;
import tk0821.puzzlegame.objects.PieceL;

public class MyInputProcessor implements InputProcessor {

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (GameScreen.isGameOver) {
			return true;
		}

		if (button == Buttons.RIGHT) {
			GameScreen.currentPiece.rotate();
		}

		if (button == Buttons.LEFT) {
			Vector3 mousePos = new Vector3();
			mousePos.set(screenX, screenY, 0);
			GameScreen.camera.unproject(mousePos);

			// ボードのインデックス計算
			int x = (int) Math.floor((mousePos.x - Board.BOARD_X) / Block.SIZE);
			int y = (int) Math.floor((mousePos.y - Board.BOARD_Y) / Block.SIZE);

			// ボードにピースを置く
			if (GameScreen.currentPiece.checkPut(x, y)) {
				GameScreen.currentPiece.put(x, y);
				GameScreen.currentPiece = GameScreen.nextPiece;
				GameScreen.nextPiece = new PieceL(InfoBoard.NEXT_BLOCK_X, InfoBoard.NEXT_BLOCK_Y);
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if (GameScreen.isGameOver) {
			return true;
		}

		Vector3 mousePos = new Vector3();
		mousePos.set(screenX, screenY, 0);
		GameScreen.camera.unproject(mousePos);

		GameScreen.currentPiece.setXY(Math.round(mousePos.x) - Block.SIZE / 2, Math.round(mousePos.y) - Block.SIZE / 2);

		return true;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

}
