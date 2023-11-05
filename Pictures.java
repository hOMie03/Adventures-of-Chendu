import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;

public class Pictures
{
	static Image platform, ball;
	URL url;
	static StartGame sg;
	static AudioClip music, wind, jump;
	static int level = 1;

	public Pictures(StartGame sg)
	{
		try
		{
			url = sg.getDocumentBase();
		}
		catch(Exception ex)
		{
			
		}
		
		music = sg.getAudioClip(url,"Music/BounceMusic.au");
		jump = sg.getAudioClip(url,"Music/jump.au");
		wind = sg.getAudioClip(url,"Music/wind.au");
		
		platform = sg.getImage(url,"SpriteBlock.png");
		this.sg = sg;
	}
}