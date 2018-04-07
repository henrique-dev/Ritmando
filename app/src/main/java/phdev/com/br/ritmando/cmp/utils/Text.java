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
package phdev.com.br.ritmando.cmp.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Classe para abstração e controle dos textos desenhados no canvas.
 * @version 1.0
 */
public class Text extends Entity {

    public static final int TOP = 0;
    public static final int CENTER_H = 1;
    public static final int CENTER_V = 2;
    public static final int BOTTOM = 3;
    public static final int LEFT = 4;
    public static final int RIGHT = 5;

    private Rect originalArea;

    private int horizontalAlignment = CENTER_H;
    private int verticalAlignment = CENTER_V;
    private String text;
    private String textToDraw[];
    private float textSize;
    private int colorText = Color.BLACK;

    private Paint strokePaint;
    private boolean strokeOn;

    private int spaceW = 10;
    private int spaceH = 50;

    private boolean textSizeAdjusted = true;

    public Text(int x, int y, int width, int height, String text) {
        super(new Rect(x, y, x + width, y + height));
        this.originalArea = super.area;
        super.defaultPaint.setColor(colorText);
        super.defaultPaint.setAntiAlias(true);
        this.text = text;
        automaticTextSize(this);
        checkAndFormatText(this);
        prepareTextToDraw(this);
    }

    public Text(Rect area, String text) {
        super(area);
        this.originalArea = super.area;
        super.defaultPaint.setColor(colorText);
        super.defaultPaint.setAntiAlias(true);
        this.text = text;
        automaticTextSize(this);
        checkAndFormatText(this);
        prepareTextToDraw(this);
    }

    public void setTextSize(float textSize) {
        if (textSize <= 0)
            throw new Error("Tamanho da fonte inferior ou igual a 0.");
        this.textSize = textSize;
        prepareTextToDraw(this);
    }

    @Override
    public void setArea(Rect area) {
        super.setArea(area);
        this.originalArea = super.area;
        if (this.textSizeAdjusted)
            automaticTextSize(this);
        checkAndFormatText(this);
        prepareTextToDraw(this);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        checkAndFormatText(this);
        prepareTextToDraw(this);
    }

    public void setStroke(int color, float strokeWidth) {
        this.strokePaint = new Paint(super.defaultPaint);

        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.strokePaint.setColor(color);
        this.strokePaint.setStrokeWidth(strokeWidth);
        this.strokePaint.setAntiAlias(true);

        this.strokeOn = true;
    }

    public boolean isTextSizeAdjusted() {
        return textSizeAdjusted;
    }

    public void setTextSizeAdjusted(boolean textSizeAdjusted) {
        this.textSizeAdjusted = textSizeAdjusted;
    }

    public int getColor() {
        return this.colorText;
    }

    public void setColor(int color) {
        this.defaultPaint.setColor(color);
    }

    private static void prepareTextToDraw(Text text) {
        text.defaultPaint.setTextSize(text.textSize);
        align(text);
    }

    private static void checkAndFormatText(Text text) {
        if (checkEspecialChars(text.text) > 0) {
            text.textToDraw = new String[checkEspecialChars(text.text) + 1];
            int counter = 0;
            for (int i=0; counter<text.text.length(); counter++) {
                if (text.textToDraw[i] == null)
                    text.textToDraw[i] = "";
                if (text.text.charAt(counter) == '\n')
                    i++;
                else
                    text.textToDraw[i] = text.textToDraw[i] + text.text.charAt(counter);
            }
        } else
            text.textToDraw = new String[]{text.text};
    }

    public static String[] getStringArrayFromText(String text) {
        String tmpText[];
        if(checkEspecialChars(text) > 0){
            tmpText = new String[checkEspecialChars(text) + 1];
            int cont = 0;
            for(int i=0; cont<text.length(); cont++){
                if(tmpText[i] == null)
                    tmpText[i] = "";
                if(text.charAt(cont) == '\n')
                    i++;
                else
                    tmpText[i] = tmpText[i] + text.charAt(cont);
            }
        }
        else
            tmpText = new String[]{text};
        return tmpText;
    }

    private static int checkEspecialChars(String text) {
        int counter = 0;
        for (int i=0; i<text.length(); i++) {
            if (text.charAt(i) == '\n')
                counter++;
        }
        return counter;
    }

    private static void automaticTextSize(Text text) {
        if (text.area.width() == 0 && text.area.height() == 0)
            return;

        Paint tmpPaint = new Paint(text.defaultPaint);
        float textSize = 1;
        tmpPaint.setTextSize(textSize);

        while (true) {
            Rect rectTextBounds = new Rect();
            tmpPaint.getTextBounds(text.text, 0, text.text.length(), rectTextBounds);
            if (text.area.height() > rectTextBounds.height() + text.spaceH * 2)
                textSize += 1;
            else
                break;
            tmpPaint.setTextSize(textSize);
        }

        while (true) {
            Rect rectTextBounds = new Rect();
            tmpPaint.getTextBounds(text.text, 0, text.text.length(), rectTextBounds);
            if (text.area.width() < rectTextBounds.width() + text.spaceW * 2)
                textSize -= 1;
            else
                break;
            tmpPaint.setTextSize(textSize);
        }
        text.setTextSize(textSize);
    }

    private static void align(Text text) {
        verticalAlign(text);
        horizontalAlign(text);
    }

    private static void verticalAlign(Text text) {
        int alignment = text.verticalAlignment;
        Rect rectTextBounds = new Rect();
        text.defaultPaint.getTextBounds(text.text, 0, text.text.length(), rectTextBounds);

        switch (alignment) {
            case TOP:
                text.area.top = text.area.top - rectTextBounds.top;
                break;
            case CENTER_V:
                text.area.top = text.area.centerY() - ((int)(text.textSize * text.textToDraw.length)/2) - rectTextBounds.top;
                break;
            case BOTTOM:
                text.area.top = text.area.bottom - ((int)(text.textSize * text.textToDraw.length)) - rectTextBounds.top;
                break;
        }
    }

    private static void horizontalAlign(Text text) {
        int alignment = text.horizontalAlignment;
        switch (alignment) {
            case LEFT:
                text.defaultPaint.setTextAlign(Paint.Align.LEFT);
                text.area.left = text.originalArea.left;
                text.area.right = text.originalArea.right;
                break;
            case CENTER_H:
                text.defaultPaint.setTextAlign(Paint.Align.CENTER);
                text.area.left = text.originalArea.centerX();
                text.area.right = text.area.left + text.originalArea.width();
                break;
            case RIGHT:
                text.defaultPaint.setTextAlign(Paint.Align.RIGHT);
                text.area.left = text.originalArea.right;
                text.area.right = text.area.left + text.originalArea.width();
                break;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();
        canvas.drawRect(this.originalArea, defaultPaint);
        for (int i=0; i<textToDraw.length; i++) {
            canvas.drawText(this.textToDraw[i], super.area.left, super.area.top + (i * (super.defaultPaint.getTextSize())), super.defaultPaint);
            if (strokeOn) {
                canvas.drawText(this.textToDraw[i], super.area.left, super.area.top + (i * (super.defaultPaint.getTextSize())), this.strokePaint);
            }
        }

        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
