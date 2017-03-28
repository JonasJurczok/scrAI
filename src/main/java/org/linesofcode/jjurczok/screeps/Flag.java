package org.linesofcode.jjurczok.screeps;

import org.linesofcode.jjurczok.screeps.global.ColorTypes;
import org.linesofcode.jjurczok.screeps.global.ScreepsObject;
import org.stjs.javascript.Map;

/**
 * Created by nick on 17/08/15.
 */
public abstract class Flag extends ScreepsObject {
    public String name;
    public Map<String, Object> memory;
    public ColorTypes color;

    public abstract void remove();
}
