package ml.derek.adventure3d.managers;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import ml.derek.adventure3d.entities.Entity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ManagerTest
{
	MovementManager m;
	Array<Entity> entites;
	Entity e;
	MovementComponent c;

	Vector2 position;
	Vector2 velocity;

	@Before
	public void setUp() throws Exception
	{
		m = new MovementManager(MovementComponent.class);
		e = new Entity();

		position = new Vector2(2, 2);
		velocity = new Vector2(1, 3);
		c = new MovementComponent(position.cpy(), velocity.cpy());

		e.addComponent(c);
		entites.add(e);
	}

	@Test
	public void testCanProcess() throws Exception
	{
		m.processEntities(entites);
		assertEquals(c.getPosition(), position.add(velocity));
	}
}