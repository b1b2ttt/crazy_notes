# crazy_notes
#Introduction :tw-1f3b9:
###### This project is going to develop an application which could combine 3d sound effects with sounds created by musical instruments to provide a feeling of performing in different kinds of environments.

#Diagram :tw-1f3b6:

###### - First, users need to perform the sounds and our application equipped with the function of record will record it.
###### - Next, the application inputs the sounds and operates them by applying hrtf (head related transform function). 
###### - We are going to use java to satisfy our 3d sound requirements.
![](https://github.com/b1b2ttt/images/blob/master/crazy_note_01.png?raw=true)

# User Interface :tw-1f31f:
###### Considering the convenience and easy to use. The user interface should be concentrated on, since it is the only part which interacts with users. It should be designed carefully.
###### In the home page, users can choose different sound source: piano and drum sets. If a user chooses piano, he will see keyboards of the piano. Then he can press the keys to make a song just like on a real piano, and the song would be recorded by the application in order to do the 3D decoration. After the procession of the song is complete, a horn button is appearing. That means the great song with 3D sound effect is ready to play. during enjoying the song, although the user does not change his position or phone’s position, he should still have an illusion that this song is changing directions continuously.
![](https://github.com/b1b2ttt/images/blob/master/crazy_note_02.png?raw=true)   

![](https://github.com/b1b2ttt/images/blob/master/crazy_note_03.png?raw=true)     

![](https://github.com/b1b2ttt/images/blob/master/crazy_note_05.png?raw=true)

# Environment :tw-1f4bb:
###### - Windows10
###### - Eclipse
# Other dependencies :tw-1f4ce:
###### - Please install  "timidity.exe" . 
###### - TiMidity++ is an open source MIDI to WAVE converter and player.
###### - [Download Tmidity](https://sourceforge.net/projects/timidity/ "download Tmidity")

#Testing environment: 
###### Windows 10(other operating system such as macOS and Linux might occur some fatal exceptions since the tool    'timidity' we included in the project is based on Windows).

# Main function：
###### The main function is in the Main/Welcome, other main functions in other classes are only for testing.
# Running instruction:
	(1) Run the program, click start button.
	(2) Click Record button, you will see a record page.
	(3) Click record button and the record page will be minimized, now playing the piano, and the sound will be recorded.
	(4) If you want to stop recording, reopen the record page from the task bar below, and then click stop button, and then you can select to save the sound by click save button.
	(5) If you want to process your sound, click the process button on the record page. then you will see a process page.
	(6) Choose a file that you saved on your PC.
	(7) Click start process, and you can hear the 3d sound.
	(8) You can click either Left or Right button to change the sound movement direction during playing.
