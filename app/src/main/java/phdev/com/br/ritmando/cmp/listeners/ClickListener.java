package phdev.com.br.ritmando.cmp.listeners;

import phdev.com.br.ritmando.cmp.listeners.events.Event;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public interface ClickListener extends ActionListener {

    void pressedPerformed(Event event);
    void releasePerformed(Event event);

}
