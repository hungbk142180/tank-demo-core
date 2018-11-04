package base;

import tklibs.AudioUtils;

import javax.sound.sampled.Clip;
import java.util.HashMap;

public class SoundManage {
    static HashMap<String, Clip> sounds = new HashMap<>();
    static String sourceDirectory = "assets/tank_audio/";
    public static void loadSounds(String ...fileNames) {
        sounds.clear();
        for(String fileName : fileNames) {
            sounds.put(fileName, AudioUtils.loadSound(sourceDirectory + fileName));
        }
    }

    public static void playSound(String fileName) {
        Clip clip = sounds.get(fileName);
        if(clip != null) {
            AudioUtils.playSound(clip);
        }
    }
}
