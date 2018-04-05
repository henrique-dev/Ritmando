package phdev.com.br.ritmando.cmp.graphics;

import android.graphics.Rect;

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
 */

public class Sprite {

    private Texture texture;

    public Sprite(Texture texture) {
        this.texture = texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public static Rect[] getSpriteFromTexture(Texture texture, int numberSpritesLines, int numberSpritesColumns, int maxSprites){
        int counter = 0;
        int spriteWidth = texture.getBitmap().getWidth() / numberSpritesColumns;
        int spriteHeight = texture.getBitmap().getHeight() / numberSpritesLines;
        Rect rects[] = new Rect[maxSprites];
        int cont = 0;
        for(int i=0; i<numberSpritesLines; i++){
            for(int j=0; j<numberSpritesColumns; j++){
                rects[cont++] = new Rect( j*spriteWidth, i*spriteHeight, (j*spriteWidth) + spriteWidth, (i*spriteHeight) + spriteHeight);
                counter++;
                if (counter == maxSprites)
                    break;
            }
        }
        return rects;
    }

    /*
    @Deprecated
    public static Sprite[] getSpriteFromTexture(Texture texture, int numberSpritesLines, int numberSpritesColumns, int maxSprites){
        int counter = 0;
        int spriteWidth = texture.getBitmap().getWidth() / numberSpritesColumns;
        int spriteHeight = texture.getBitmap().getHeight() / numberSpritesLines;
        Sprite sprites[] = new Sprite[maxSprites];
        int cont = 0;
        for(int i=0; i<numberSpritesLines; i++){
            for(int j=0; j<numberSpritesColumns; j++){
                sprites[cont++] = new Sprite( new Texture( Bitmap.createBitmap(texture.getBitmap(), j*spriteWidth, i*spriteHeight, spriteWidth, spriteHeight)));
                counter++;
                if (counter == maxSprites)
                    break;
            }
        }

        return sprites;
    }
    */

    public static Sprite[] getSpritesFromSprites(Sprite[] sprites, int indexBegin, int indexEnd, boolean reverse){
        Sprite cSprite[] = null;
        cSprite = new Sprite[(indexEnd+1)-indexBegin];
        int tCounter = 0;
        for (int i=indexBegin; i<=indexEnd; i++){

            if (!reverse){
                cSprite[tCounter] = sprites[i];
            }
            else {
                cSprite[cSprite.length-1 - tCounter] = sprites[i];
            }
            tCounter++;
        }
        return cSprite;
    }

}
