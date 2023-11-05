import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;

public class GravityDown extends Item
{
	public GravityDown(int x)
	{
		super(x);
	}
	public void performAction(BallG b)
	{
		if(b.getGravity() > 3)
		{
			b.setGravity(b.getGravity() - 3);
			if(b.getGravity() < 3)
			{
				b.setGravity(3);
			}
		}
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.GREEN);
		super.paint(g);
	}
}