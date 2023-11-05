import java.awt.*;
import java.applet.*;
import java.net.*;

public class BallG
{
	private int x = 400, y= 51, radius = 30, agility = 3, maxSpeed = 20;
	private double gravity = 15, energyloss = 1, dt = 0.2, xFriction = 0.951, gameDy = -75, dx = 0, dy = 0;
	private boolean game_over = false;
	private Image player;
	private URL url;
	
	public BallG(StartGame sg)
	{
		
		url = sg.getDocumentBase();
		player = sg.getImage(url,"Goti.png");
	}

	public BallG(int i,int j)
	{
		x = i;
		y = j;
	}
	public void setGameDy(double gameDy)
	{
		this.gameDy = gameDy;
	}
	public double getGameDy()
	{
		return gameDy;
	}
	public int getRadius()
	{
		return radius;
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
	public double getDx()
	{
		return dx;
	}
	public void setDx(double dx)
	{
		this.dx = dx;
	}
	public double getDy()
	{
		return dy;
	}
	public void setDy(double dy)
	{
		this.dy = dy;
	}
	public double getGravity()
	{
		return gravity;
	}
	public void setGravity(double gravity)
	{
		this.gravity = gravity;
	}
	
	public int getAgility()
	{
		return agility;
	}
	public void setAgility(int agility)
	{
		this.agility = agility;
	}
	public int getMaxSpeed()
	{
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed)
	{
		this.maxSpeed = maxSpeed;
	}
	
	public void moveRight()
	{
		if(dx+agility < maxSpeed)
		{
			dx += agility;
		}
	}

	public void moveLeft()
	{
		if(dx-agility > -maxSpeed)
		{
			dx -= agility;
		}
	}

	public void update(StartGame sg)
	{
		if(x+dx > sg.getWidth() - radius - 1)
		{
			x = sg.getWidth() - radius - 1;
			dx = -dx;
		}
		else if(x+dx < 0 + radius)
		{
			x = 0 + radius;
			dx = -dx;
		}
		else
		{
			x += dx;
		}
		if(y == sg.getHeight() - radius - 1)
		{
			dx *= xFriction;
			if(Math.abs(dx) < 0.8)
			{
					dx = 0;
			}
		}
			
		if(y -200 > sg.getHeight() - radius - 1)
		{
			game_over = true;
		}
		else
		{
			dy += gravity * dt; //velocity formula
			y += dy * dt + 0.5 * gravity * dt * dt; //pos formula
		}
	}
	public void paint(Graphics g,StartGame sg)
	{
		g.drawImage(player, x-radius,y-radius,radius*2,radius*2, sg);
	}
	public boolean getGameOver()
	{
		return game_over;
	}
}