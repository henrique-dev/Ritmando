package phdev.com.br.ritmando.cmp.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import phdev.com.br.ritmando.GameLog;
import phdev.com.br.ritmando.GameParameters;

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

public class Texture {

    final static int bitmapQuality = 20;

    private Bitmap bitmap;
    private Paint paint;
    private int width;
    private int height;

    public Texture(String path) {
        this.bitmap = openImage(path, -1, -1);
        this.paint = new Paint();
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }

    public Texture(String path, int reqWidth, int reqHeight) {
        this.bitmap = openImage(path, reqWidth, reqHeight);
        this.paint = new Paint();
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }

    public Texture(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.paint = new Paint();
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Paint getPaint() {
        return this.paint;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    private static Bitmap openImage(String path, int reqWidth, int reqHeight) {
        try {
            ByteArrayOutputStream finalBuffer = new ByteArrayOutputStream();
            InputStream buffer = GameParameters.getInstance().assetManager.open(path);

            int data = buffer.read();
            while (data != -1) {
                finalBuffer.write(data);
                data = buffer.read();
            }
            finalBuffer.flush();

            return byteArrayToBitmap(finalBuffer, reqWidth, reqHeight);
        } catch (IOException ioe) {
            GameLog.error(Texture.class, ioe.getMessage());
        }
        return null;
    }

    public void clipMe(int x, int y, int width, int height) {
        try {
            this.bitmap = Bitmap.createBitmap(this.bitmap, x, y, width, height);
        } catch (Exception e) {
            GameLog.error(this, e.getMessage());
        }
    }

    public void scaleMe(int width, int height) {
        try {
            this.bitmap = Bitmap.createScaledBitmap(this.bitmap, width, height, false);
        } catch (Exception e) {
            GameLog.error(this, e.getMessage());
        }
    }

    private static Bitmap byteArrayToBitmap(ByteArrayOutputStream buffer, int reqWidth, int reqHeight) {
        if (reqWidth != 1 && reqHeight != -1) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            BitmapFactory.decodeByteArray(buffer.toByteArray(), 0, buffer.size(), options);
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;

            ByteArrayOutputStream imagePosCompress = new ByteArrayOutputStream();

            Bitmap tempBitmap = BitmapFactory.decodeByteArray(buffer.toByteArray(), 0, buffer.size(), options);
            boolean result = tempBitmap.compress(Bitmap.CompressFormat.PNG, bitmapQuality, imagePosCompress);
            if (result)
                return BitmapFactory.decodeByteArray(imagePosCompress.toByteArray(), 0, imagePosCompress.size());
        }
        return BitmapFactory.decodeByteArray(buffer.toByteArray(), 0, buffer.size());
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth)
                inSampleSize *= 2;
        }
        return inSampleSize;
    }

}
