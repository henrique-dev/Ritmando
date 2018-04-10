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

public class Music {

    private int resourceId;
    private String info;
    private float leftVolume = 1;
    private float rightVolume = 1;

    public Music(int resourceId, String info, float leftVolume, float rightVolume) {
        this.resourceId = resourceId;
        this.info = info;
        this.leftVolume = leftVolume;
        this.rightVolume = rightVolume;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
}
