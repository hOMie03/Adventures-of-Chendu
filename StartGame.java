import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;

public class StartGame extends Applet implements Runnable, KeyListener, MouseMotionListener, MouseListener
{
	private Image i;
	private Graphics doubleG;
	BallG b;
	Platform p[] = new Platform[7];
	Item item[] = new Item[3];
	private int score;
	double cityX = 0;
	double cityDx = 0.3;
	URL url;
	Image city;
	int levelcheck = 0;
	boolean gameOver = false;
	boolean mouseIn = false;
	
	public int getScore()
	{
		return score;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public void init()
	{
		setSize(800,600);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		try
		{
			url = getDocumentBase();
		}
		catch(Exception ex)
		{
			
		}
		city = getImage(url,"buildings.png");
		Pictures p = new Pictures(this);
		Pictures.wind.play();
		Pictures.music.loop();
		
	}
	public void start()
	{
		b = new BallG(this);
		score = 0;
		for(int i=0;i<p.length;i++)
		{
			Random r = new Random();
			p[i] = new Platform(i*120, 300);
		}
		for(int i=0;i<item.length;i++)
		{
			Random r = new Random();
			switch(r.nextInt(5))
			{
				case 0:
				item[i] = new GravityUp(getWidth()+2000*i);
				break;

				case 1:
				item[i] = new GravityDown(getWidth()+2000*i);
				break;
				
				case 2:
				item[i] = new AgilityUp(getWidth()+2000*i);
				break;

				case 3:
				item[i] = new AgilityDown(getWidth()+2000*i);
				break;
			
				case 4:
				item[i] = new ScorePlus(getWidth()+2000*i,this);
				break;
			}
		}
		Thread thread = new Thread(this);
		thread.start();
	}
	public void run()
	{
		while(true)
		{
			for(int i=0;i<p.length;i++)
			{
				int testX = p[i].getX();	
				if(testX < 0 - p[i].getWidth())
				{
					Random r = new Random();
					int fakei = i;
					if(i==0)
					{
						fakei = p.length;
					}
					p[i].setX(p[fakei-1].getX() + p[i].getWidth() + Pictures.level * r.nextInt(25));
				}
			}
			gameOver = b.getGameOver();
			if(levelcheck > 500)
			{
				Pictures.level++;
				levelcheck = 0;
			}
			levelcheck++;
			if(cityX > getWidth()*-1)
			{
				cityX -= cityDx;
			}
			else
			{
				cityX = 0;
			}
			if(!gameOver)
			{
				score++;
			}
			Random r = new Random();
			for(int i=0;i<item.length;i++)
			{
				if(item[i].isCreateNew())
				{
					item[i] = null;
					switch(r.nextInt(5))
					{
						case 0:
						item[i] = new GravityUp(getWidth() + 10 * r.nextInt(500));
						break;

						case 1:
						item[i] = new GravityDown(getWidth() + 10 * r.nextInt(500));
						break;
				
						case 2:
						item[i] = new AgilityUp(getWidth() + 10 * r.nextInt(500));
						break;

						case 3:
						item[i] = new AgilityDown(getWidth() + 10 * r.nextInt(500));
						break;
			
						case 4:
						item[i] = new ScorePlus(getWidth() + 10 * r.nextInt(500),this);
						break;
					}
					item[i].setCreateNew(false);
				}
			}
			
			b.update(this);	
			for(int i=0;i<p.length;i++)
			{
				p[i].update(this,b);
			}
			for(int i=0;i<item.length;i++)
			{
				item[i].update(this,b);
			}
			repaint();
			try
			{
				Thread.sleep(17);
			}
			catch(InterruptedException ie)
			{
				System.out.println("Error: " + ie);
			}
		}
	}
	public void stop()
	{
	}
	public void destroy()
	{
	}
	public void update(Graphics g)
	{
		if(i == null)
		{
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		doubleG.setColor(getForeground());
		paint(doubleG);
		
		g.drawImage(i, 0, 0, this);
	}
	public void paint(Graphics g)
	{
		g.setColor(new Color(15,77,147));
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(city, (int)cityX, 0, this);
		g.drawImage(city, (int)cityX + getWidth(), 0, this);
		b.paint(g,this);
		for(int i=0;i<p.length;i++)
		{
			p[i].paint(g);
		}
		for(int i=0;i<item.length;i++)
		{
			item[i].paint(g);
		}
		String s = Integer.toString(score);
		Font font = new Font("Serif",Font.BOLD,32);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(s, getWidth() - 150+2, 50+2);		
		g.setColor(new Color(198,226,255));
		g.drawString(s, getWidth() - 150, 50);
		if(gameOver)
		{
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 300, 300);
			//mousecheck
			g.drawRect(280,320,180,40);
			if(mouseIn)
			{
				g.setColor(Color.RED);
				g.drawString("Try Again?", 290, 350);
			}
			else
			{
				g.setColor(Color.WHITE);
				g.drawString("Try Again?", 290, 350);
			}
		}
		String s2 = "Level: " + Integer.toString(Pictures.level);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(s2, getWidth() - 700+2,50+2);		
		g.setColor(new Color(198,226,255));
		g.drawString(s2, getWidth() - 700, 50);
		
		if(Pictures.level < 2)
		{
			String tit = "Adventures of Chendu^^";
			g.setFont(font);	
			g.setColor(new Color(255,0,0));
			g.drawString(tit, getWidth() - 550+2,580+2);
			g.setColor(Color.WHITE);
			g.drawString(tit, getWidth() - 550, 580);	
		
			Font font2 = new Font("Arial",Font.BOLD,12);
			g.setFont(font2);
			String inst1 = "< & > = Left & Right movement";
			g.setColor(Color.WHITE);
			g.drawString(inst1, getWidth() - 800, 488);
			
			g.setColor(Color.RED);
			g.fillOval(getWidth()-800,500,20,20);
			String inst2 = " = Gravity Up/Agility Down";
			g.setColor(Color.WHITE);
			g.drawString(inst2, getWidth() - 800+20, 518);
	
			g.setColor(Color.GREEN);
			g.fillOval(getWidth()-800,530,20,20);
			String inst3 = " = Gravity Down/Agility Up";
			g.setColor(Color.WHITE);
			g.drawString(inst3, getWidth() - 800+20, 548);
	
			g.setColor(Color.BLUE);
			g.fillOval(getWidth()-800,560,20,20);
			String inst4 = " = Score Boost";
			g.setColor(Color.WHITE);
			g.drawString(inst4, getWidth() - 800+20, 578);
		}
		
	}
	
	public void keyPressed(KeyEvent ke)
	{
		switch(ke.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
			b.moveLeft();
			break;
			case KeyEvent.VK_RIGHT:
			b.moveRight();
			break;
		}
	}
	public void keyReleased(KeyEvent ke)
	{
	}
	public void keyTyped(KeyEvent ke)
	{
	}

	public void mouseDragged(MouseEvent me)
	{
	}
	public void mouseMoved(MouseEvent me)
	{
		if(gameOver)
		{
			if(me.getX() > 280 && me.getX() < 460)
			{
				if(me.getY() > 320 && me.getY() < 360)
				{
					mouseIn = true;
				}
			}
			if(me.getX() < 280 || me.getX() > 460)
			{
				mouseIn = false;
			}
			if(me.getY() < 320 || me.getY() > 360)
			{
				mouseIn = false;
			}
		}
	}

	public void mouseEntered(MouseEvent me)
	{
		
	}
	public void mouseExited(MouseEvent me)
	{
	}
	public void mousePressed(MouseEvent me)
	{
	}
	public void mouseReleased(MouseEvent me)
	{
	}
	public void mouseClicked(MouseEvent me)
	{
		if(mouseIn)
		{
			b = null;
			b = new BallG(this);
			score = 0;
			Pictures.level = 1;
			for(int i=0;i<p.length;i++)
			{
				Random r = new Random();
				p[i] = new Platform(i*120, 300);
			}
			for(int i=0;i<item.length;i++)
			{
				Random r = new Random();
				switch(r.nextInt(5))
				{
					case 0:
					item[i] = new GravityUp(getWidth()+2000*i);
					break;
	
					case 1:
					item[i] = new GravityDown(getWidth()+2000*i);
					break;
					
					case 2:
					item[i] = new AgilityUp(getWidth()+2000*i);
					break;

					case 3:
					item[i] = new AgilityDown(getWidth()+2000*i);
					break;
			
					case 4:
					item[i] = new ScorePlus(getWidth()+2000*i,this);
					break;
				}
			}
			mouseIn = false;
		}
	}
}

/* 
<applet code="StartGame.class" width="720" height="480"> 
</applet> 
*/  