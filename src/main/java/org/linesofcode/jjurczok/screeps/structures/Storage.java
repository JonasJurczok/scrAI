package org.linesofcode.jjurczok.screeps.structures;

import org.linesofcode.jjurczok.screeps.helpers.Store;
import org.linesofcode.jjurczok.screeps.interfaces.DepositableStructure;

/**
 * Created by nick on 10/08/15.
 */
public abstract class Storage extends DepositableStructure {

    public Store store;
    public int storeCapacity;
}
