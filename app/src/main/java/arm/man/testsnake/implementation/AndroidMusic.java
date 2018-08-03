package arm.man.testsnake.implementation;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

import arm.man.testsnake.framework.Music;

/**
 * Created by ARM on 03/08/18.
 */

public class AndroidMusic implements Music, MediaPlayer.OnCompletionListener, MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener {
    MediaPlayer mediaPlayer;
    boolean isPrepared;

    public AndroidMusic(AssetFileDescriptor assetDesriptor) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetDesriptor.getFileDescriptor(), assetDesriptor.getStartOffset(), assetDesriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load music");
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this) {
            isPrepared = false;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        synchronized (this){
            isPrepared = true;
        }
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }

    @Override
    public void play() {
        if (this.mediaPlayer.isPlaying())
            return;

        try {
            synchronized (this) {
                if (!isPrepared)
                    mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.stop();

            synchronized (this) {
                isPrepared = false;
            }
        }
    }

    @Override
    public void pause() {
        if (this.mediaPlayer.isPlaying())
            this.mediaPlayer.pause();
    }

    @Override
    public void setLooping(boolean looping) {
        this.mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        this.mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public boolean isPlaying() {
        return this.mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        return this.mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if (this.mediaPlayer.isPlaying())
            this.mediaPlayer.stop();

        this.mediaPlayer.release();
    }

    @Override
    public void seekBegin() {
        this.mediaPlayer.seekTo(0);
    }
}
