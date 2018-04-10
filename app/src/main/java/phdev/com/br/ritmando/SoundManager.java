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
import phdev.com.br.ritmando.cmp.sound.Sound;

public final class SoundManager {

    private int musicPlayingIndex = 0;
    private List<Music> musicList;

    private List<Sound> soundList;

    private Context context;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    SoundManager(final Context context) {
        this.context = context;
        this.musicList = new ArrayList<>();
        this.soundList = new ArrayList<>();
        if (this.soundPool == null)
            this.soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    }

    public void addMusicToList(Music music) {
        this.musicList.add(music);
    }

    public void clearMusicList() {
        this.musicList.clear();
    }

    public void addShortSoundToList(Sound sound) {
        sound.setPoolId(this.soundPool.load(this.context, sound.getResourceId(), 1));
        this.soundList.add(sound);
    }

    public void clearSoundList() {
        for (Sound sound : this.soundList) {
            this.soundPool.unload(sound.getPoolId());
        }
        this.soundList.clear();
    }

    public void playMusic(int id) {
        for (Music music : this.musicList) {
            if (id == music.getResourceId())
                loadAndPlayMusic( music.getResourceId() );
        }
    }

    public void playSound(int id) {
        for (Sound sound : this.soundList)
            if (id == sound.getResourceId()) {
                this.soundPool.play(sound.getPoolId(), sound.getLeftVolume(), sound.getRightVolume(), 1, sound.getLoop(), sound.getRate());
                GameLog.error(this, "SOUNDOU");
            }
    }

    private void loadAndPlayMusic(final int id) {
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
                    SoundManager.this.mediaPlayer = MediaPlayer.create(context, id);
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
