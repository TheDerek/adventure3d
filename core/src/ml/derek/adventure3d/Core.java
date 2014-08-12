package ml.derek.adventure3d;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalShadowLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.Array;
import ml.derek.adventure3d.components.CameraFollow;
import ml.derek.adventure3d.components.UserInput;

/**
 * See: http://blog.xoppa.com/loading-models-using-libgdx/
 * @author Xoppa
 */
public class Core implements ApplicationListener {
	public Environment environment;
	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public Model model;
	public ModelInstance instance;

	public Array<GameEntity> entites;
	private AssetManager assets;
	private DirectionalShadowLight shadowLight;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_INFO);

		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		//shadowLight = new DirectionalShadowLight(1024, 1024, 30f, 30f, 1f, 100f);
		//shadowLight.set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f);
		//environment.add(shadowLight);
		//environment.shadowMap = shadowLight;

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(1f, 1f, 1f);
		cam.lookAt(0, 0, 0);
		cam.near = 1f;
		cam.far = 300f;


		cam.update();

		entites = new Array<GameEntity>();

		assets = new AssetManager();
		assets.load("data/knight/knight.g3db", Model.class);
		assets.load("data/monster/monster.g3db", Model.class);
		assets.load("data/ground/ground.g3db", Model.class);
		assets.finishLoading();

		GameEntity knight = new GameEntity(assets.get("data/knight/knight.g3db", Model.class), "eh", true);
		knight.addComp(new UserInput(14f, cam, "Armature|walk", "Armature|idle"));
		knight.addComp(new CameraFollow(cam));
		//knight.controller.setAnimation("Armature|idle", -1);

		GameEntity monster = new GameEntity(assets.get("data/monster/monster.g3db", Model.class), "eh", true);
		monster.controller.setAnimation("Cube|idle", -1);


		GameEntity ground = new GameEntity(assets.get("data/ground/ground.g3db", Model.class), "eh", true);

		entites.add(knight);
		entites.add(monster);
		entites.add(ground);

		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
	}

	@Override
	public void render()
	{
		//camController.update();
		cam.update();

		Gdx.graphics.getGL20().glClearColor(0, 191, 255, 1);
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);
		for(GameEntity e : entites)
			e.render(modelBatch, environment);

		modelBatch.end();

		update(Gdx.graphics.getDeltaTime());
	}


	public void update(float delta)
	{
		for(GameEntity e : entites)
			e.update(delta);
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		assets.dispose();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
