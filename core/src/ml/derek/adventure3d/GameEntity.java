package ml.derek.adventure3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import ml.derek.adventure3d.components.Component;

/**
 * Created by derek on 09/08/14.
 */
public class GameEntity
{
	public Vector3 center = new Vector3();
	public Vector3 dimensions = new Vector3();
	private final ModelInstance instance;
	public float radius;
	private BoundingBox bounds = new BoundingBox();

	public final AnimationController controller;
	public AnimationController.AnimationDesc walk;
	public AnimationController.AnimationDesc idle;

	public Array<Component> getComponents()
	{
		return components;
	}

	private Array<Component> components;

	public GameEntity(Model model, String rootNode, boolean mergeTransform)
	{
		instance = new ModelInstance(model);
		controller = new AnimationController(instance);

		instance.calculateBoundingBox(bounds);
		center.set(bounds.getCenter());
		dimensions.set(bounds.getDimensions());
		radius = dimensions.len() / 2f;

		components = new Array<Component>();

		for(Animation a : instance.animations)
			Gdx.app.log("Animation", a.id);


	}

	public void addComp(Component c)
	{
		components.add(c);
	}

	public void render(ModelBatch batch, Environment environment)
	{
		controller.update(Gdx.graphics.getDeltaTime());
		batch.render(instance, environment);
	}

	public void update(float delta)
	{
		for(Component c : components)
			c.update(this, delta);
	}

	public void translate(float x, float y, float z)
	{
		instance.transform.translate(x, y, z);
	}

	public void translate(Vector3 v)
	{
		Quaternion r = instance.transform.getRotation(new Quaternion());
		Vector3 axis = new Vector3();
		float angle = r.getAxisAngle(axis);

		setRotation(new Vector3(1, 1, 1), 0);
		instance.transform.translate(v);
		setRotation(axis, angle);
	}

	public AnimationController.AnimationDesc getCurrentAnimation()
	{
		return controller.current;
	}

	public Vector3 getPos()
	{
		return instance.transform.getTranslation(new Vector3(0, 0, 0));
	}

	public void rotate(Vector3 axis, float deg)
	{
		instance.transform.rotate(axis, deg);
	}

	public void setRotation(Vector3 axis, float deg)
	{
		//Reset Rotation
		//Quaternion rotation = new Quaternion();
		//instance.transform.getRotation(rotation);
		//rotate(axis, -rotation.getAngle());
		//rotate(axis, deg);

		Vector3 pos = getPos().cpy();
		Quaternion r = new Quaternion(axis, deg);

		Vector3 scale = instance.transform.getScale(new Vector3());

		instance.transform = new Matrix4(pos, r, scale);

		//rotation.set()
		//rotate(axis, -lastRot);

		//rotate(axis, deg);

	}
}
