package com.nicktoony.scrAI.Managers;

import com.nicktoony.helpers.Lodash;
import com.nicktoony.helpers.LodashCallback1;
import com.nicktoony.scrAI.Constants;
import com.nicktoony.scrAI.Controllers.RoomController;
import com.nicktoony.scrAI.World.Tasks.TaskBuild;
import com.nicktoony.scrAI.World.Tasks.TaskDeposit;
import com.nicktoony.scrAI.World.Tasks.TaskRepair;
import com.nicktoony.screeps.ConstructionSite;
import com.nicktoony.screeps.Depositable;
import com.nicktoony.screeps.GlobalVariables;
import com.nicktoony.screeps.Structure;
import org.stjs.javascript.Array;

/**
 * Created by nick on 26/07/15.
 */
public class StructureManager extends ManagerTimer {
    private int totalStorageCalculation = 0;

    public StructureManager(final RoomController roomController) {
        super(roomController, "StructureManager", Constants.DELAY_STRUCTURE_MANAGER);

        if (!super.canRun()) {
            return;
        }
        super.hasRun();

        // Fetch ALL energy
        Array<ConstructionSite> foundSites = (Array<ConstructionSite>) this.roomController.getRoom().find(GlobalVariables.FIND_MY_STRUCTURES,
                null);

        Lodash.forIn(foundSites, new LodashCallback1<Structure>() {
            @Override
            public boolean invoke(Structure structure) {
                if (roomController.getTasksManager().getMemory().$get(structure.id) == null && structure.hits < structure.hitsMax) {
                    roomController.getTasksManager().addTask(new TaskRepair(roomController, structure.id, structure));
                }

                if (structure.structureType == GlobalVariables.STRUCTURE_EXTENSION) {
                    if (roomController.getTasksManager().getMemory().$get(structure.id) == null) {
                        roomController.getTasksManager().addTask(new TaskDeposit(roomController, structure.id, structure));
                    }

                    totalStorageCalculation += structure.energyCapacity;
                } else if (structure.structureType == GlobalVariables.STRUCTURE_SPAWN) {
                    totalStorageCalculation += structure.energyCapacity;
                }

                return true;
            }
        }, this);

        roomController.setRoomTotalStorage(totalStorageCalculation);
    }
}
