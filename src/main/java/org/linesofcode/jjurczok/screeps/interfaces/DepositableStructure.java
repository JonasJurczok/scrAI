package org.linesofcode.jjurczok.screeps.interfaces;

import org.linesofcode.jjurczok.screeps.Creep;
import org.linesofcode.jjurczok.screeps.global.ResponseTypes;
import org.linesofcode.jjurczok.screeps.structures.Structure;

/**
 * Created by nick on 30/07/15.
 */
public abstract class DepositableStructure extends Structure {
    public int energy = 0;
    public int energyCapacity = 0;

    public abstract ResponseTypes transferEnergy(Creep creep, int amount);
}
