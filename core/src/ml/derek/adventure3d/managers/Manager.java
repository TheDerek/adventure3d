package ml.derek.adventure3d.managers;

import com.badlogic.gdx.utils.Array;
import ml.derek.adventure3d.GameEntity;
import ml.derek.adventure3d.components.Component;
import ml.derek.adventure3d.entities.Entity;

/**
 * Created by derek on 11/08/14.
 */
public abstract class Manager
{
	private Class<? extends Component>[] components;

	public Manager(Class<? extends Component>... components)
	{
		this.components = components;
	}

	public abstract void processEntities(Array<Entity> GameEntity);

	public boolean canProcess(GameEntity e)
	{
		boolean a[] = new boolean[components.length];
		int i = 0;
		for(Class<? extends Component> c : components)
		{
			a[i] = false;
			for(Component pc : e.getComponents())
				if(c.isAssignableFrom(pc.getClass()))
					a[i] = true;

			i++;
		}

		for(boolean b : a) if(!b) return false;

		return true;
	}
}
