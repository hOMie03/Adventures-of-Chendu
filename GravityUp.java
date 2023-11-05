import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;


public class GravityUp extends Item
{
	public GravityUp(int x)
	{
		super(x);
	}
	public void performAction(BallG b)
	{
		b.setGravity(b.getGravity() + 3);
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		super.paint(g);
	}
}