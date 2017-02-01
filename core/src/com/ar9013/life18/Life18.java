package com.ar9013.life18;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Vector;

public class Life18 extends ApplicationAdapter implements InputProcessor{

	private String TAG = "Life18";
	Stage stage;
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		Group group = new Group();

		Image tableImg = new Image(new Texture(Gdx.files.internal("deck.png")));
		Image aceImg = new Image(new Texture(Gdx.files.internal("ace.png")));
		Image kingImg = new Image(new Texture(Gdx.files.internal("king.jpg")));

		tableImg.setName("table");
		aceImg.setName("ace");
		kingImg.setName("king");

		// 加入的順序 跟畫的順序是一致的，最後畫的在最上面
		group.addActor(tableImg);
		group.addActor(kingImg);
		group.addActor(aceImg);

		stage.addActor(group);

		kingImg.setPosition(300,150);
		aceImg.setPosition(400,150);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {

	}

	@Override
	public boolean keyDown(int keycode) {
		Group group = (Group) stage.getActors().first();
	//	Image ace = (Image) group.getChildren().get(2);

		// 當有命名的時候，可以用名字找到 圖片元件，（要找的原因）可能會比較久
		Image ace = group.findActor("ace");

		if(keycode == Input.Keys.D){
			if(Gdx.input.isKeyPressed(Input.Keys.Z))
			{
				ace.setRotation(ace.getRotation()+1f);
			}else {
				group.setRotation(group.getRotation() + 1f);
			}
		}

		if(keycode == Input.Keys.A){
			if(Gdx.input.isKeyPressed(Input.Keys.X)){
				ace.setRotation(ace.getRotation()-1f);
			}else{
				group.setRotation(group.getRotation()-1f);
			}

		}

		if(keycode == Input.Keys.W){
			group.setColor(group.getColor().r,group.getColor().g,
					group.getColor().b,group.getColor().a+0.1f);

		}

		if(keycode == Input.Keys.S){
			group.setColor(group.getColor().r,group.getColor().g,
					group.getColor().b,group.getColor().a-0.1f);

		}

		if(keycode == Input.Keys.NUMPAD_1){ // 數字鍵盤 1
			ace.setZIndex(ace.getZIndex() -1);
		}

		if(keycode == Input.Keys.NUMPAD_2){ // 數字鍵盤 2
			ace.setZIndex(ace.getZIndex() +1);
		}

		return true;
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
		// 傳入screenX,screenY，座標原點位於左上角，想要轉換為 左下角座標原點的 stage 座標系

		Vector2 temp = stage.screenToStageCoordinates(new Vector2(screenX,screenY));
		Actor hitActor = stage.hit(temp.x,temp.y,false);

		if (hitActor != null){
			Gdx.app.log(TAG,hitActor.getName()); // 依照畫的順序，最上面的為 ace , king 第二, 最下面 table
		}


		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
