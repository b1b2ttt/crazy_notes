package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import HRTF.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Functional.D3Sound;
import Functional.TrueCoordinates;
import HRTF.HrtfSession;
public class SoundEffect {
	static String filePath = "";
	static String converterPath = "";
	static SourceDataLine soundLine;
    static AudioFormat audioFormat;
    static DataLine.Info info;
	public SoundEffect(){
		converterPath = System.getProperty("user.dir");
		JFrame frame = new JFrame("Crazy Note");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = 700;
        int h = 400;
        frame.setSize(w, h);
        frame.setLocation(screenSize.width/2 - w/2, screenSize.height/2 - h/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();    
        placeComponents(frame, panel);
        
        frame.add(panel);
        
        frame.setUndecorated(true);
        frame.setVisible(true); 
	}
	private static void placeComponents(JFrame frame, JPanel panel) {
		 panel.setLayout(null);
		 JLabel titleLabel = new JLabel("Sound Process",JLabel.CENTER);
	     titleLabel.setBounds(0,75,700,40);
	     Font fTitle = new Font("微软雅黑",Font.BOLD,40); 
	     titleLabel.setFont(fTitle);
	     panel.add(titleLabel);
	     JButton fileButton = new JButton("Choose a File");
	     fileButton.setBounds(250,200,200,40);
	     Font fButton = new Font("微软雅黑",Font.BOLD,20); 
	     fileButton.setFont(fButton);
	     fileButton.setBackground(Color.ORANGE);
	     panel.add(fileButton);
	     JLabel fileLabel = new JLabel("",JLabel.CENTER);
	     fileLabel.setBounds(0,150,700,40);
	     Font fFont = new Font("微软雅黑",Font.PLAIN,30); 
	     fileLabel.setFont(fFont);
		 panel.add(fileLabel);
	     fileButton.addActionListener(new ActionListener(){
	     public void actionPerformed(ActionEvent e) {
	    	 boolean isChoose = fileButton.getText().startsWith("C");
	    	 if(isChoose) {
	    	 	JFileChooser jfc = new JFileChooser();
         		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
     			jfc.showDialog(new JLabel(), "Choose a mid file");
     			File file = jfc.getSelectedFile();
     			fileLabel.setText(file.getAbsolutePath());  
     			filePath = file.getAbsolutePath();
     			fileButton.setText("Start Process");
	    	 } else {
	    		 String command = "timidity " + filePath + " -Ow -o out.wav";
	    		 try {
					Process p = Runtime.getRuntime().exec(command);
					p.waitFor();
					if (p.exitValue() != 0) {
						System.err.println("Error when converting file format");
						frame.dispose();
					}
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		 try {
	    			HrtfSession sessions = new HrtfSession(Hrtf.getCipicSubject("58"), 0, 0);
	    		    D3Sound sound = new D3Sound(44100*4, new File("out.wav"), sessions);
	    		    int x = -5;
	    		    int y = 0;
    		    	do{
    		    		if(y >= 0 && y >= 5) {
    		    			y -= 1;
    		    		} else {
    		    			y += 1;
    		    		}
    		    		if(x >= -5 && x >= 5) {
    		    			x -= 1;
    		    		} else {
    		    			x += 1;
    		    		}
    		    		//System.out.println(x);
    		    		TrueCoordinates shotCoords = new TrueCoordinates(x, y, 0);
	    		    	shotCoords.setOrigin(new TrueCoordinates(0, 0, 0).azimuth);
	    		    	sound.changeSoundDirection(shotCoords.getAdjustedAzimuth(), shotCoords.getAdjustedElevation());
    		    	} while(sound.step());	    		    		    		    	    	       
         
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		 File file = new File("out.wav");
	    		 file.delete();
	    		 fileLabel.setText("");
	    		 fileButton.setText("Choose a File"); 
	    	 }
	        }
	     });
	     JButton backButton = new JButton("Back");
	     backButton.setBounds(250,300,200,40);
	     backButton.setFont(fButton);
	     backButton.setBackground(Color.red);
	     panel.add(backButton);
	     backButton.addActionListener(new ActionListener(){
	     public void actionPerformed(ActionEvent e) {
	    	 		frame.dispose();
	        	}
	     });
	        
	}
	public static void playWav(byte[] bt){
        try {
            if(soundLine != null)
            {
                soundLine.close();
            }
            soundLine = (SourceDataLine) AudioSystem.getLine(info);
            soundLine.open(audioFormat);
            soundLine.start();
        }catch (LineUnavailableException e){
            e.printStackTrace();
        }
        soundLine.write(bt, 0, bt.length);
    }
	public static byte[] buildNextStep(TrueCoordinates headRotation, HrtfSession sessions, D3Sound sound) {
        TrueCoordinates shotCoords = new TrueCoordinates(4, 0, 0);
        shotCoords.setOrigin(headRotation.azimuth);
        sound.changeSoundDirection(shotCoords.getAdjustedAzimuth(), shotCoords.getAdjustedElevation());
        sound.stepSilent();
        return sound.soundWithHeader();
    }
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new SoundEffect();
	}
}
