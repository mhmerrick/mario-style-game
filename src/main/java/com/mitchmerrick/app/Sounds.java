package com.mitchmerrick.app;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Sounds 
{
	Clip[] clips;
	int pos;

	public Sounds(String filename, int copies)
	{
		pos = 0;
		clips = new Clip[copies];
		for(int i = 0; i < copies; i++) 
		{
			try
			{
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(filename));
				AudioFormat format = inputStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				clips[i] = (Clip)AudioSystem.getLine(info);
				clips[i].open(inputStream);
			}
			catch(Exception e)
			{
				e.printStackTrace(System.err);
				System.out.println("Could not load " + filename.toString());
				System.exit(1);
			}
		}
	}

	public void play()
	{
		clips[pos].setFramePosition(0);
		clips[pos].loop(0);
		if(++pos >= clips.length)
			pos = 0;
	}
}
