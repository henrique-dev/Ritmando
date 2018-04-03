package phdev.com.br.ritmando.cmp.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class Text extends Entity {

    public static final int TOP = 0;
    public static final int CENTER = 1;
    public static final int BOTTON = 2;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;

    private Rect originalArea;

    private boolean autoSize = false;
    public static boolean fontPers;
    private int horizontalAllignment;
    private int verticalAllignment;
    private String text;
    private String textToDraw[];
    private Paint textToDrawPaint[];
    //public static Font fonts[];
    private float textSize;
    private int colorText;

    private Paint strokePaint;
    private boolean strokeOn;

    public Text(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        this.originalArea = super.area;
        this.colorText = Color.RED;
        super.defaultPaint.setColor(colorText);
        super.defaultPaint.setAntiAlias(true);
        this.textSize = 100;
        this.text = text;
        this.verticalAllignment = CENTER;
        this.horizontalAllignment = CENTER;
        this.checkAndFormatText();
        this.prepareTextToDraw();
    }

    public Text(Rect area, String text) {
        super(area);
        this.originalArea = super.area;
        this.colorText = Color.RED;
        super.defaultPaint.setColor(colorText);
        super.defaultPaint.setAntiAlias(true);
        this.textSize = 100;
        this.text = text;
        this.verticalAllignment = CENTER;
        this.horizontalAllignment = CENTER;
        this.checkAndFormatText();
        this.prepareTextToDraw();
    }

    public void setTextSize(float textSize) {
        if (textSize <= 0)
            throw new Error("Tamanho da fonte inferior ou igual a 0.");
        this.textSize = textSize;
        this.prepareTextToDraw();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        this.checkAndFormatText();
        this.prepareTextToDraw();
    }

    public void setStroke(int color, float strokeWidth) {
        this.strokePaint = new Paint(super.defaultPaint);

        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.strokePaint.setColor(color);
        this.strokePaint.setStrokeWidth(strokeWidth);
        this.strokePaint.setAntiAlias(true);

        this.strokeOn = true;
    }

    public int getColor() {
        return this.colorText;
    }

    public void setColor(int color) {
        this.defaultPaint.setColor(color);
    }

    private boolean loadFont(String name) {
        return false;
    }

    private void prepareTextToDraw() {
        super.defaultPaint.setTextSize(this.textSize);

        this.setVerticalAllignment(verticalAllignment);
        this.setHorizontalAllignment(horizontalAllignment);
    }

    private void checkAndFormatText() {
        if (checkEspecialChars(this.text) > 0) {
            textToDraw = new String[checkEspecialChars(text) + 1];
            int counter = 0;
            for (int i=0; counter<text.length(); counter++) {
                if (textToDraw[i] == null)
                    textToDraw[i] = "";
                if (text.charAt(counter) == '\n')
                    i++;
                else
                    textToDraw[i] = textToDraw[i] + text.charAt(counter);
            }
        } else
            textToDraw = new String[]{text};
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

    public static int checkEspecialChars(String text) {
        int counter = 0;
        for (int i=0; i<text.length(); i++) {
            if (text.charAt(i) == '\n')
                counter++;
        }
        return counter;
    }

    public void setVerticalAllignment(int allignment) {
        Rect rectTextBounds = new Rect();
        super.defaultPaint.getTextBounds(text, 0, text.length(), rectTextBounds);

        switch (allignment) {
            case TOP:
                super.area.top = super.area.top - rectTextBounds.top;
                break;
            case CENTER:
                super.area.top = super.area.centerY() - ((int)(this.textSize * this.textToDraw.length)/2) - rectTextBounds.top;
                break;
            case BOTTON:
                super.area.top = super.area.bottom - ((int)(this.textSize * textToDraw.length)) - rectTextBounds.top;
                break;
        }
    }

    public void setHorizontalAllignment(int allignment) {
        switch (allignment) {
            case LEFT:
                super.defaultPaint.setTextAlign(Paint.Align.LEFT);
                super.area.left = this.originalArea.left;
                super.area.right = this.originalArea.right;
                break;
            case CENTER:
                super.defaultPaint.setTextAlign(Paint.Align.CENTER);
                super.area.left = this.originalArea.centerX();
                super.area.right = super.area.left + this.originalArea.width();
                break;
            case RIGHT:
                super.defaultPaint.setTextAlign(Paint.Align.RIGHT);
                super.area.left = this.originalArea.right;
                super.area.right = super.area.left + this.originalArea.width();
                break;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();
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
