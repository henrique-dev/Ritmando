/*
 * Copyright (C) 2018 Paulo Henrique Gonçalves Bacelar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package phdev.com.br.ritmando;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.ArrayList;
import java.util.List;

import phdev.com.br.ritmando.cmp.sound.Music;
import phdev.com.br.ritmando.cmp.sound.ShortSound;

public final class SoundManager {

    private int musicPlayingIndex = 0;
    private List<Music> musicList;

    private List<ShortSound> shortSoundList;

    private Context context;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    SoundManager(Context context) {
        this.context = context;
        this.musicList = new ArrayList<>();
        this.shortSoundList = new ArrayList<>();
        if (this.soundPool == null)
            this.soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    }

    public int addMusicToList(Music music) {
        this.musicList.add(music);
        return this.musicList.size()-1;
    }

    public void clearMusicList() {
        this.musicList.clear();
    }

    public int addShortSoundToList(ShortSound shortSound) {
        shortSound.setPoolId(this.soundPool.load(this.context, shortSound.getResourceId(), 1));
        this.shortSoundList.add(shortSound);
        return this.shortSoundList.size()-1;
    }

    public void clearSoundList() {
        for (ShortSound shortSound : this.shortSoundList) {
            this.soundPool.unload(shortSound.getPoolId());
        }
        this.shortSoundList.clear();
    }

    @Deprecated
    public void playMusic(int id) {
        loadAndPlayMusic(musicList.get(id));
        /*
        for (Music music : this.musicList) {
            if (id == music.getResourceId())
                loadAndPlayMusic( music.getResourceId() );
        }*/
    }

    @Deprecated
    public void playSound(int id) {
        this.soundPool.play(shortSoundList.get(id).getPoolId(), shortSoundList.get(id).getLeftVolume(),
                shortSoundList.get(id).getRightVolume(), 1, shortSoundList.get(id).getLoop(), shortSoundList.get(id).getRate());
        /*
        for (ShortSound shortSound : this.shortSoundList)
            if (id == shortSound.getResourceId()) {
                this.soundPool.play(shortSound.getPoolId(), shortSound.getLeftVolume(), shortSound.getRightVolume(), 1, shortSound.getLoop(), shortSound.getRate());
            }*/
    }

    private void loadAndPlayMusic(final Music music) {
        new Thread(){
            @Override
            public void run() {
                if (SoundManager.this.mediaPlayer != null) {
                    try {
                        if (SoundManager.this.mediaPlayer.isPlaying())
                            SoundManager.this.mediaPlayer.stop();
                        SoundManager.this.mediaPlayer.release();
                    } catch (Exception e) {
                        GameLog.error(this, e.getMessage());
                    }
                }
                try {
                    SoundManager.this.mediaPlayer = MediaPlayer.create(context, music.getResourceId());
                    SoundManager.this.mediaPlayer.start();
                } catch (Exception e) {
                    GameLog.error(this, e.getMessage());
                }
            }
        }.start();
    }

    void release() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        if (this.soundPool != null)
            this.soundPool.release();
    }

}
