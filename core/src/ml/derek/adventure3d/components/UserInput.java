package ml.derek.adventure3d.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import ml.derek.adventure3d.GameEntity;

/**
 * Created by derek on 09/08/14.
 */
public class UserInput implements Component
{
	private final float speed;
	private int keys[] = {Input.Keys.A, Input.Keys.S, Input.Keys.W, Input.Keys.D};
	private PerspectiveCamera camera;
	float rot = 0;

	private String walkAnimation;
	private String idleAnimation;
	Vector3 dir;

	public UserInput(float speed, PerspectiveCamera camera, String walkAnimation, String idleAnimation)
	{
		this.speed = speed;
		this.camera = camera;
		this.walkAnimation = walkAnimation;
		this.idleAnimation = idleAnimation;

		dir = new Vector3();
	}

	@Override
	public void update(GameEntity entity, float delta)
	{
		float mod = 0;


		dir.setZero();

		float ns = Math.abs(speed * delta);

		if(Gdx.input.isKeyPressed(Input.Keys.S))
		{
			dir.add(-ns, 0, 0);
		}


		if(Gdx.input.isKeyPressed(Input.Keys.W))
		{
			dir.add(ns, 0, 0);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D))
		{
			dir.add(0, 0, ns);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.A))
		{
			dir.add(0, 0, -ns);
		}


		boolean pressed = false;
		for(int key : keys)
		{
			if(Gdx.input.isKeyPressed(key))
				pressed = true;
		}

		if(entity.getCurrentAnimation()== null)
			entity.controller.setAnimation(idleAnimation, -1);

		if(pressed && !entity.getCurrentAnimation().animation.id.equals(walkAnimation))
			entity.controller.setAnimation(walkAnimation, -1, 5f, null);
		else if(!pressed && entity.getCurrentAnimation().animation.id.equals(walkAnimation))
			entity.controller.setAnimation(idleAnimation, -1);

		Vector3 axis = new Vector3(0, 1, 0);
		//entity.setRotation(new Vector3(0f, 1f, 0f), rot);
		//entity.translate(dir);
		dir = dir.nor().scl(ns);

		if(!dir.isZero())
			Gdx.app.log("GameInfo", dir.toString());

		entity.translate(dir);

		Vector3 mouse = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		//Vector3 vangle = mouse.sub(entity.getPos());
		//float angle = vangle.ang

		if(!dir.isZero())
			rot =  MathUtils.atan2(-dir.z, dir.x) * MathUtils.radiansToDegrees;

		entity.setRotation(axis, rot);
		//entity.setRotation(axis, angle * MathUtils.radiansToDegrees);



	}
}
