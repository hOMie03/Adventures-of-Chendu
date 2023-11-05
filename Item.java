import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;

public class Item
{
	private int x, y, dx, radius;
	private StartGame sg;
	private boolean createNew = false;
	
	public boolean isCreateNew()
	{
		return createNew;
	}
	public void setCreateNew(boolean createNew)
	{
		this.createNew = createNew;
	}

	public Item(int x)
	{
		this.x = x;
		Random r = new Random();
		y = r.nextInt(400) + radius;
		radius = 15;
		dx = -2;
	}
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void update(StartGame sg, BallG b)
	{
		x += dx;
		this.sg = sg;
		checkForCollision(b);
		if(x < 0 - radius)
		{
			createNew = true;
		}
	}
	private void checkForCollision(BallG b)
	{
		int ballX = b.getX();
		int ballY = b.getY();
		int ballR= b.getRadius();

		int a = x - ballX;
		int bb = y - ballY;
		int collide = radius + ballR;
		double c = Math.sqrt((double)(a*a) + (double)(bb*bb)); //dist betwn obj centers
		if(c < collide)
		{
			performAction(b);
			createNew = true;
		}
	}
	private void performAction(BallG b)
	{
		
	}
	public void paint(Graphics g)
	{
		//g.setColor(Color.YELLOW);
		g.fillOval(x-radius,y-radius,radius*2,radius*2);
	}
}