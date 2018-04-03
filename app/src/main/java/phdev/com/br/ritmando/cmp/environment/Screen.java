package phdev.com.br.ritmando.cmp.environment;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Created by ks_le on 01/04/2018.
 */

public abstract class Screen extends Entity implements Component {

    private ArrayList<Scene> scenes;

    protected Screen(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.scenes = new ArrayList<>();
    }

    protected void addScene(Scene scene) {
        this.scenes.add(scene);
    }

    protected void removeScene(Scene scene) {
        this.scenes.remove(scene);
    }

    protected Scene getScene(int index) {
        return scenes.get(index);
    }
}
