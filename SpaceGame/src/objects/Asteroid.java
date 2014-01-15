package objects;

import game.Helper;
import game.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Asteroid extends Obj
{
	private final float TURNSPEED = 1f;
	private float[] points, sizeVars, angles;
	public float[] location, velocity;
	private float size;
	private int direction;
	
	public Asteroid(float[] location, float size, List<Obj> gameInst)
	{
		velocity = new float[2];
		points = initPts(location, size);
		this.location = location;
		this.size = size;
		this.gameInst = gameInst;
		
		if (Math.random() > .5)
			direction = 1;
		else
			direction = -1;
		
	}
	
	private float[] initPts(float[] location, float size)
	{
		// 5 to 10 vertices for an asteroid
		int numPts = (int) (Math.random()*10 + 5);
		float[] pts = new float[numPts*2];
		sizeVars = new float[numPts];
		angles = new float[numPts];
		
		for (int i = 0; i < numPts; i++)
		{
			float degree = (float) (Math.random()*360/numPts + i*360/numPts);
			angles[i] = degree;
			
			float sizeVar = (float) (Math.random()*size/2);
			sizeVars[i] = sizeVar;
			pts[2*i] = (size + sizeVar)*Helper.cos(degree) + location[0];
			pts[2*i + 1] = (size + sizeVar)*Helper.sin(degree) + location[1];
		}
		
		return pts;
	}
	
	public void draw(Graphics g)
	{
		
		shape = new Polygon(points);
		g.setColor(Color.gray);
		g.fill(shape);
	}
	
	public void update(int delta)
	{
		for (int i = 0; i < angles.length; i++)
		{
			angles[i] += TURNSPEED* direction * .01f*delta;
			points[2*i] = (size + sizeVars[i])*Helper.cos(angles[i]) + location[0];
			points[2*i + 1] = (size + sizeVars[i])*Helper.sin(angles[i]) + location[1];
		}
		
		location[0] += velocity[0]*delta*.01f;
		location[1] += velocity[1]*delta*.01f;
	}

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

	@Override
	public void collide(Obj hitter, int delta) 
	{
		collided = true;
		if (hitter instanceof Bullet)
			gameInst.remove();
		else if (hitter instanceof Asteroid)
		{
			float[] hSpeed = hitter.setSpeed(null);
			hSpeed[0] *= -1;
			hSpeed[1] *= -1;
			
			velocity[0] *= -1;
			velocity[1] *= -1;
		}
	}
}