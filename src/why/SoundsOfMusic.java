package why;

import java.io.File;
import java.io.FileInputStream;
import javax.sound.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundsOfMusic {
	private File wavFile;
	private Clip clip;
	
	public SoundsOfMusic(String url)
	{
		wavFile = new File(url);
		wavStart(wavFile);
	}
	
	private void wavStart(File sound)
	{
		try{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			clip.loop(1);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void wavEnd()
	{
		clip.close();
	}
}
