import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;

public class Platform
{
	int dx;
	int x, y, width, height;
	Image plat;
	URL url;
	float frame = 0;
	
	public Platform(int x, int y)
	{
		this.x = x;
		this.y = y;
		width = 120;
		height = 40;
		dx = -1;
		plat = Pictures.platform;
	}
	public void update(StartGame sg, BallG b)
	{
		int tester = (int)(frame + 0.1);
		if(tester < 3)
		{
			frame += 0.1;
		}
		else
		{
			frame = 0;
		}
		x += -(Pictures.level);
		checkForCollision(b);
		if(x < 0 - width)
		{
			Random r = new Random();
			y = sg.getHeight() - 40 - r.nextInt(400);
			//x = sg.getWidth() + r.nextInt(300);
		}
	}
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}

	private void checkForCollision(BallG b)
	{
		int ballX = b.getX();
		int ballY = b.getY();
		int radius= b.getRadius();
		if(ballY+radius > y &&  ballY+radius < y+height)
		{
			if(ballX > x && ballX < x+width)
			{
				double newDY = b.getGameDy();
				b.setY(y-radius);
				b.setDy(newDY);
				Pictures.jump.play();
			}
		}
	}
	public void paint(Graphics g)
	{
		g.drawImage(plat, x, y, x + 2*(width/2), y + 2*(height/2), 40*(int)frame, 0, 120, 40*(int)frame + 40, Pictures.sg);
	}
}