import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;


public class AgilityDown extends Item
{
	public AgilityDown(int x)
	{
		super(x);
	}
	public void performAction(BallG b)
	{
		if(b.getAgility() >= 2)
		{
			b.setAgility(b.getAgility() - 1);
		}
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		super.paint(g);
	}
}