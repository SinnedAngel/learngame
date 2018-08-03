package arm.man.testsnake.implementation;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

import arm.man.testsnake.framework.Audio;
import arm.man.testsnake.framework.Music;
import arm.man.testsnake.framework.Sound;

/**
 * Created by ARM on 03/08/18.
 */

public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music createMusic(String file) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(file);
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + file + "'");
        }
    }

    @Override
    public Sound createSound(String file) {
        return null;
    }
}
