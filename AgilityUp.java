import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;


public class AgilityUp extends Item
{
	public AgilityUp(int x)
	{
		super(x);
	}
	public void performAction(BallG b)
	{
		if(b.getAgility() < 8)
		{
			b.setAgility(b.getAgility() + 1);
		}
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.GREEN);
		super.paint(g);
	}
}