import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;

public class ScorePlus extends Item
{
	private StartGame appletInfo;
	public ScorePlus(int x, StartGame appletInfo)
	{
		super(x);
		this.appletInfo = appletInfo;
	}
	public void performAction(BallG b, StartGame sg)
	{
		Random r = new Random();
		sg.setScore(sg.getScore() + 500 + r.nextInt(2000));
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		super.paint(g);
	}
}