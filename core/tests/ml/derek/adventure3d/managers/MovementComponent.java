package ml.derek.adventure3d.managers;

import com.badlogic.gdx.math.Vector2;
import ml.derek.adventure3d.GameEntity;
import ml.derek.adventure3d.components.Component;

/**
 * Created by derek on 11/08/14.
 */
public class MovementComponent implements Component
{
	private Vector2 position;
	private Vector2 velocity;

	public MovementComponent(Vector2 position, Vector2 velocity)
	{

		this.position = position;
		this.velocity = velocity;
	}

	@Override
	public String toString()
	{
		return "Position: " + position + ", Velocity: " + velocity;
	}

	@Override
	public void update(GameEntity entity, float delta)
	{

	}

	public Vector2 getVelocity()
	{
		return velocity;
	}

	public void setVelocity(Vector2 velocity)
	{
		this.velocity = velocity;
	}

	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}
}
