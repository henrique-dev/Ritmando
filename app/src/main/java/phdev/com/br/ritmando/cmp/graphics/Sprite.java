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

package phdev.com.br.ritmando.cmp.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import phdev.com.br.ritmando.GameParameters;

/**
 * @author Paulo Henrique Gonçalves Bacelar
 * @version 1.0
 * Tem como objetivo fornecer um intermédio entre uma entidade e a textura.
 */
public class Sprite {

    private static boolean debugSprite = true;

    private Texture texture;
    private Rect imageClip;
    private Paint paint;

    private Paint debugPaint1;
    private Paint debugPaint2;

    private Matrix matrix;
    private boolean invertH;
    private boolean invertV;

    private float degrees;

    /**
     * Cria um sprite a partir de uma textura e a área da textura.
     *
     * @param texture textura consumida pelo sprite.
     * @param imageClip area consumida da textura pelo sprite.
     * @throws Error caso a textura ou a area inserida sejam nulas.
     */
    public Sprite(Texture texture, Rect imageClip) {
        if (texture == null || imageClip == null)
            throw new Error("A textura e a area do sprite não podem ser null");
        this.texture = texture;
        this.imageClip = imageClip;
        this.matrix = new Matrix();
        this.paint = new Paint();
        this.debugPaint1 = new Paint();
        this.debugPaint1.setColor(Color.GRAY);
        this.debugPaint2 = new Paint();
        this.debugPaint2.setColor(Color.RED);
    }

    /**
     * Redefine a textura e a área da textura do sprite.
     *
     * @param texture textura consumida pelo sprite.
     * @param imageClip area consumida da textura pelo sprite.
     */
    public void set(Texture texture, Rect imageClip) {
        if (texture == null || imageClip == null)
            throw new Error("A textura e a area do sprite não podem ser null");
        this.texture = texture;
        this.imageClip = imageClip;
    }

    /**
     * Retorna a textura do sprite.
     *
     * @return textura que o sprite consome.
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Retorna a área consumida da textura pelo sprite.
     *
     * @return area da textura que o sprite consome.
     */
    public Rect getImageClip() {
        return this.imageClip;
    }

    /**
     * Redefine o @{@link Paint} do sprite.
     *
     * @param paint novo @{@link Paint} para o sprite.
     */
    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    /**
     * Retorna o @{@link Paint} utilizado pelo sprite.
     *
     * @return @{@link Paint} do sprite.
     */
    public Paint getPaint() {
        return this.paint;
    }

    /**
     * Inverte a orientação horizontal do sprite.
     */
    public void invertH() {
        this.invertH = !invertH;
        this.matrix.postScale(-1, 1);
    }

    /**
     * Inverte a orientação vertical do sprite.
     */
    public void invertV() {
        this.invertV = !invertV;
        this.matrix.postScale(1, -1);
    }

    /**
     * Rotaciona o sprite.
     *
     * @param degrees grau de inclinação do sprite.
     */
    @Deprecated
    public void rotate(float degrees) {
        this.degrees = degrees;
        this.matrix.postRotate(degrees, this.imageClip.centerX(), this.imageClip.centerY());
    }

    public void draw(Canvas canvas, int x, int y) {
        int savedState = canvas.save();
        if (invertV || invertH || degrees != 0) {
            canvas.setMatrix(this.matrix);
            canvas.translate(
                    this.invertH ? -GameParameters.getInstance().screenSize.width() : 0,
                    this.invertV ? -GameParameters.getInstance().screenSize.height() : 0
            );
            x = this.invertH ? GameParameters.getInstance().screenSize.width() - x - this.imageClip.width() : x;
            y = this.invertV ? GameParameters.getInstance().screenSize.height() - y - this.imageClip.height() : y;

            //canvas.rotate(this.degrees, this.imageClip.centerX(), this.imageClip.centerY());
        }

        if (debugSprite) {
            canvas.drawRect(new Rect(x, y, x + this.imageClip.width(), y + this.imageClip.height()), this.debugPaint1);
            canvas.drawCircle(x, y, 15, this.debugPaint2);
        }

        canvas.drawBitmap(this.texture.getBitmap(), this.imageClip, new Rect(x, y, x + this.imageClip.width(), y + this.imageClip.height()), this.texture.getPaint());
        canvas.restoreToCount(savedState);
    }

    public static Sprite[] getSpriteFromTexture(Texture texture, int numberSpritesLines, int numberSpritesColumns, int maxSprites){
        int counter = 0;
        int spriteWidth = texture.getBitmap().getWidth() / numberSpritesColumns;
        int spriteHeight = texture.getBitmap().getHeight() / numberSpritesLines;
        Sprite sprites[] = new Sprite[maxSprites];
        int cont = 0;
        for(int i=0; i<numberSpritesLines; i++){
            for(int j=0; j<numberSpritesColumns; j++){
                sprites[cont++] = new Sprite(texture, new Rect( j*spriteWidth, i*spriteHeight, (j*spriteWidth) + spriteWidth, (i*spriteHeight) + spriteHeight));
                counter++;
                if (counter == maxSprites)
                    break;
            }
        }
        return sprites;
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
