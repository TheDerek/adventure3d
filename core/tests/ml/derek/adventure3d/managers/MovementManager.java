package ml.derek.adventure3d.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import ml.derek.adventure3d.GameEntity;
import ml.derek.adventure3d.components.Component;
import ml.derek.adventure3d.entities.Entity;

/**
 * Created by derek on 11/08/14.
 */
public class MovementManager extends Manager
{

	public MovementManager(Class<? extends Component>... entities)
	{
		super(entities);
	}

	@Override
	public void processEntities(Array<Entity> entities)
	{
		for(Entity e : entities)
		{
			Gdx.app.log("Unit Test", e.toString());
		}
	}
}
