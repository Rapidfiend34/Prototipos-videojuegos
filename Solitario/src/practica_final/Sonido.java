/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_final;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Aaron Fernandez Ramon 45186605T
 *         Marc Gomez Barceló 43232431K
 */

//https://www.dropbox.com/s/cdz0q4eupl7kn6f/Video_Explicativo_REC.mp4?dl=0

//Creamos un objeto para implementar el sonido respecto a las interacciones del usuario con sus cartas
public class Sonido {

    private Clip clip;
    private AudioInputStream sound;

    public void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            e.getMessage();
        }
    }
   //Sonara el clip
    public void play() {
        clip.start();
    }
    //Se parara el clip
    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
}
