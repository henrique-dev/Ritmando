package phdev.com.br.ritmando.cmp.window.utils;

import android.graphics.Rect;

import java.util.ArrayList;

import phdev.com.br.ritmando.GameLog;
import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.window.Button;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public class ListLayout implements Layout{

    public static final int HORIZONTAK_ALLINGMENT = 0;
    public static final int VERTICAL_ALLINGMENT = 1;

    private int allingment;

    private ArrayList<Component> components;

    public ListLayout() {

    }

    public ListLayout(int allingment) {

    }

    @Override
    public void set(ArrayList<Component> components) {
        this.components = components;
    }

    @Override
    public void format() {
        this.components.add(new Button(new Rect()));
    }
}
