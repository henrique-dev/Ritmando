package phdev.com.br.ritmando.cmp.window;

import android.graphics.Rect;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public class ListLayout implements Layout{

    public static final int HORIZONTAK_ALINGMENT = 0;
    public static final int VERTICAL_ALINGMENT = 1;

    private int alignment;
    private int spaceH;
    private int spaceW;

    private Entity entity;
    //private ArrayList<Component> components;

    public ListLayout() {
        this.alignment = VERTICAL_ALINGMENT;
        this.spaceW = 0;
        this.spaceH = 0;
    }

    public ListLayout(int alignment) {
        this.alignment = alignment;
        this.spaceW = 0;
        this.spaceH = 0;
    }

    public ListLayout(int alignment, int spaceW, int spaceH) {
        this.alignment = alignment;
        this.spaceH = spaceH;
        this.spaceW = spaceW;
    }

    @Override
    public void set(Entity entity) {
        this.entity = entity;
        this.format();
    }

    @Override
    public void format() {
        ArrayList<Component> tmpComponent = ((Window)this.entity).getComponents();
        if (!(tmpComponent.size() > 0))
            return;

        int x = this.entity.getArea().left;
        int y = this.entity.getArea().top;
        if (this.alignment == HORIZONTAK_ALINGMENT) {
            int height = this.entity.getArea().height() - this.spaceH * 2;
            int cmpWidth = (this.entity.getArea().width() - this.spaceW * (tmpComponent.size()+1)) / tmpComponent.size();
            for (int i=0; i<tmpComponent.size(); i++) {
                Entity tmpEnt = ((Entity)tmpComponent.get(i));
                tmpEnt.setArea(new Rect(
                        (this.spaceW + this.spaceW*i) + x + (i * cmpWidth),
                        this.spaceH + y,
                        (this.spaceW + this.spaceW*i) + x + ((i+1) * cmpWidth),
                        this.spaceH + y + height));
            }
        } else if (this.alignment == VERTICAL_ALINGMENT) {
            int width = this.entity.getArea().width() - this.spaceW * 2;
            int cmpHeight = (this.entity.getArea().height() - this.spaceH * (tmpComponent.size()+1)) / tmpComponent.size();
            for (int i=0; i<tmpComponent.size(); i++) {
                Entity tmpEnt = ((Entity)tmpComponent.get(i));
                tmpEnt.setArea(new Rect(
                        this.spaceW + x,
                        (this.spaceH + this.spaceH*i) + y + (i * cmpHeight),
                        this.spaceW + x + width,
                        (this.spaceH + this.spaceH*i) + y + ((i+1) * cmpHeight)));
            }
        }
    }


}
