package ml.derek.adventure3d.components;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import ml.derek.adventure3d.GameEntity;

/**
 * Created by derek on 09/08/14.
 */
public class CameraFollow implements Component
{
	private PerspectiveCamera camera;

	public CameraFollow(PerspectiveCamera camera)
	{
		this.camera = camera;
	}

	@Override
	public void update(GameEntity entity, float delta)
	{
		float tilt = 30f;
		Vector3 axis = new Vector3(0, 1, 0);

		camera.lookAt(entity.getPos());
		//camera.rotateAround(entity.getPos(), new Vector3(0, 1, 0), 90);
		Vector3 pos = camera.position.cpy();
		camera.rotate(axis, -getCameraCurrentXYAngle(camera));
		camera.rotate(new Vector3(0, 1, 0), 90f);

		camera.position.set(entity.getPos().add(-tilt, 30f, 0));

	}

	public float getCameraCurrentXYAngle(PerspectiveCamera cam)
	{
		return (float)Math.atan2(cam.up.x, cam.up.z)* MathUtils.radiansToDegrees;
	}
}
