package objects;

import game.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public abstract class Obj 
{
	protected Shape shape;
	protected float[] speed, velocity;
	protected boolean collided;
	protected List<Obj> gameInst;
	
	abstract public void draw(Graphics g);
	
	abstract public void update(int delta);
	
	public Shape shape()
	{
		return shape;
	}
	
	public float[] setSpeed(float[] speed) 
	{
		if (speed != null)
			velocity = speed;
		return velocity;
	}
	
	abstract public void collide(Obj hitter, int delta);
	
	//abstract public float[] setSpeed(float[] speed);
	
}
