package Main;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;

import Functional.D3Sound;
import Functional.TrueCoordinates;
import HRTF.Hrtf;
import HRTF.HrtfSession;

public class PlayEffected extends Thread {
	JButton leftButton, rightButton, fileButton;
	JLabel fileLabel;
	static boolean isLeft = true;
	public PlayEffected(JButton leftButton, JButton rightButton, JButton fileButton, JLabel fileLabel) {
		// TODO Auto-generated constructor stub
		this.leftButton = leftButton;
		this.rightButton = rightButton;
		this.fileLabel = fileLabel;
		this.fileButton = fileButton;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String command = "timidity " + SoundEffect.filePath + " -Ow -o out.wav";
   		
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			if (p.exitValue() != 0) {
				System.err.println("Error when converting file format");
			}
			HrtfSession sessions = new HrtfSession(Hrtf.getCipicSubject("58"), 0, 0);
		    D3Sound sound = new D3Sound(44100*4, new File("out.wav"), sessions);
		    int x = -10;
		    int y = 0;
		    int indexX = 1, indexY = -1;
	    	do{
	    		if(x == -10) {
	    			indexX = 1;
	    		} else if(x == 10) {
	    			indexX = -1;
	    		}
	    		if(y == -10) {
	    			indexY = 1;
	    		} else if(y == 10) {
	    			indexY = -1;
	    		}
	    		if(isLeft) {
		    		x += indexX;
	    		} else {
	    			x -= indexX;		    		
	    		}
	    		y += indexY;
	    		TrueCoordinates shotCoords = new TrueCoordinates(x, y, 0);
		    	shotCoords.setOrigin(new TrueCoordinates(0, 0, 0).azimuth);
		    	sound.changeSoundDirection(shotCoords.getAdjustedAzimuth(), shotCoords.getAdjustedElevation());
	    	} while(sound.step());	 
	    	leftButton.setEnabled(false);
	    	rightButton.setEnabled(false);
	    	fileButton.setEnabled(true); 
	    	fileLabel.setText("");
   		 	fileButton.setText("Choose a File"); 
 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


}
