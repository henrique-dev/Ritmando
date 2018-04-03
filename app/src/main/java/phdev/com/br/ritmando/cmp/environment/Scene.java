package phdev.com.br.ritmando.cmp.environment;

import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Created by ks_le on 01/04/2018.
 */

public abstract class Scene extends Entity implements Component {

    protected Scene(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
