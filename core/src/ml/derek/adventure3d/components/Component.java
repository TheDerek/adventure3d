package ml.derek.adventure3d.components;

import ml.derek.adventure3d.GameEntity;

/**
 * Created by derek on 09/08/14.
 */
public interface Component
{
	@Deprecated
	public void update(GameEntity entity, float delta);
}
