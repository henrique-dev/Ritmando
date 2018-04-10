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

package phdev.com.br.ritmando.cmp.sound;

public class Sound {

    private int resourceId;
    private int poolId;
    private float leftVolume;
    private float rightVolume;
    private int loop;
    private float rate;

    public Sound(int resourceId, float leftVolume, float rightVolume, int loop, float rate) {
        this.resourceId = resourceId;
        this.leftVolume = leftVolume;
        this.rightVolume = rightVolume;
        this.loop = loop;
        this.rate = rate;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getPoolId() {
        return poolId;
    }

    public void setPoolId(int poolId) {
        this.poolId = poolId;
    }

    public float getLeftVolume() {
        return leftVolume;
    }

    public void setLeftVolume(float leftVolume) {
        this.leftVolume = leftVolume;
    }

    public float getRightVolume() {
        return rightVolume;
    }

    public void setRightVolume(float rightVolume) {
        this.rightVolume = rightVolume;
    }

    public int getLoop() {
        return loop;
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
