package org.linesofcode.jjurczok.screeps;

import org.linesofcode.jjurczok.screeps.global.ScreepsObject;
import org.linesofcode.jjurczok.screeps.interfaces.DepositableStructure;
import org.linesofcode.jjurczok.screeps.structures.Controller;
import org.linesofcode.jjurczok.screeps.structures.Structure;
import org.stjs.javascript.Array;
import org.stjs.javascript.Map;

/**
 * Created by nick on 26/07/15.
 */
public abstract class Creep extends ScreepsObject {
    public String name;
    public RoomPosition pos;
    public Map<String, Object> memory;
    public Array<String> body;
    public Carry carry;
    public int carryCapacity;

    public void harvest(Source target) {

    }

    public abstract int moveTo(Object target, Map<String, ?> opts);

    public abstract int transferEnergy(DepositableStructure target);

    public abstract void pickup(Energy energy);

    public abstract void upgradeController(Controller controller);

    public abstract void build(ConstructionSite buildable);

    public abstract void repair(Structure buildable);
}
