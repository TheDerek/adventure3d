package ml.derek.adventure3d.entities;

import com.badlogic.gdx.utils.Array;
import ml.derek.adventure3d.components.Component;

/**
 * Created by derek on 11/08/14.
 */
public class Entity
{
	private Array<Component> components;

	public Array<Component> getComponents()
	{
		return components;
	}

	public void addComponent(Component c)
	{
		components.add(c);
	}
}
