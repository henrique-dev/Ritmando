package phdev.com.br.ritmando.cmp.models;

import android.graphics.Paint;
import android.graphics.Rect;

import phdev.com.br.ritmando.cmp.models.Component;

/**
 * Created by ks_le on 01/04/2018.
 */

public abstract class Entity implements Component {

    protected Rect area;
    protected Paint defaultPaint;

    protected Entity(int x, int y, int width, int height) {
        this.area = new Rect(x, y, x + width, y + height);
        this.defaultPaint = new Paint();
    }

}
