package org.linesofcode.jjurczok.screeps.structures;

import org.linesofcode.jjurczok.screeps.Creep;
import org.linesofcode.jjurczok.screeps.global.PartTypes;
import org.linesofcode.jjurczok.screeps.global.ResponseTypes;
import org.linesofcode.jjurczok.screeps.helpers.SpawningCreep;
import org.linesofcode.jjurczok.screeps.interfaces.DepositableStructure;
import org.stjs.javascript.Array;
import org.stjs.javascript.Map;

/**
 * Created by nick on 26/07/15.
 */
public abstract class Spawn extends DepositableStructure {

    public SpawningCreep spawning;
    public String name;
    public Map<String, Object> memory;

    public abstract ResponseTypes canCreateCreep(Array<PartTypes> abilities, String name);

    public abstract ResponseTypes createCreep(Array<PartTypes> abilities, String name, Map<String, Object> memory);

    @Override
    public abstract ResponseTypes transferEnergy(Creep creep, int amount);
}
