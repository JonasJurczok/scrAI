package org.linesofcode.jjurczok.screeps.structures;

import org.linesofcode.jjurczok.screeps.global.ScreepsObject;
import org.linesofcode.jjurczok.screeps.global.StructureTypes;
import org.linesofcode.jjurczok.screeps.helpers.OwnerProperties;

/**
 * Created by nick on 02/08/15.
 */
public abstract class Structure extends ScreepsObject {
    public StructureTypes structureType;
    public int hits = 0;
    public int hitsMax = 0;
    public boolean my = false;
    public OwnerProperties owner = null;

    public abstract int destroy();

    public abstract int notifyWhenAttacked(boolean enabled);
}
