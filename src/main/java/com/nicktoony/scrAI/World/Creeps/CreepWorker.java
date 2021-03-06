package com.nicktoony.scrAI.World.Creeps;

import com.nicktoony.helpers.Lodash;
import com.nicktoony.helpers.LodashCallback;
import com.nicktoony.helpers.LodashCallback1;
import com.nicktoony.helpers.LodashCallback2;
import com.nicktoony.scrAI.Constants;
import com.nicktoony.scrAI.Controllers.RoomController;
import com.nicktoony.scrAI.World.Tasks.Task;
import com.nicktoony.screeps.*;
import com.nicktoony.screeps.global.PartTypes;
import org.stjs.javascript.Array;
import org.stjs.javascript.Global;
import org.stjs.javascript.JSCollections;
import org.stjs.javascript.Map;

/**
 * Created by nick on 26/07/15.
 */
public class CreepWorker extends CreepWrapper {
    public CreepWorker(RoomController roomController, Creep creep) {
        super(roomController, creep);
    }

    private String taskId;
    private Task task;
    private CreepWorker myself;

    @Override
    public void init() {
        memory.$put("taskId", taskId);
    }

    @Override
    public void create() {
        taskId = (String) memory.$get("taskId");
        task = roomController.getTasksManager().getTask(taskId);
        myself = this;
    }

    @Override
    public void step() {
        if (task == null) {

            Lodash.forIn(roomController.getTasksManager().getSortedTasks(), new LodashCallback2<Task, String>() {
                @Override
                public boolean invoke(Task possibleTask, String id) {
                    if (possibleTask.canAssign(myself)) {
                        if (task == null || possibleTask.getPriority() > task.getPriority()) {
                            task = possibleTask;
                            taskId = possibleTask.getAssociatedId();
                        }
                    }
                    return true;
                }
            }, this);

            memory.$put("taskId", taskId);

        } else {

            if (!task.canAct(this) || !task.act(this)) {
                taskId = null;
                task = null;
                memory.$put("taskId", taskId);
            }

        }

    }

    public boolean moveTo(RoomPosition position) {
        // if reached target
        if (this.creep.pos.inRangeTo(position, 1)) {
            return true;
        } else {
            // move to target
            Map<String, Object> parameters = JSCollections.$map();
//            parameters.$put("ignoreCreeps", !(this.creep.pos.inRangeTo(position, 2)));
            parameters.$put("ignoreDestructibleStructures", false);
//            parameters.$put("heuristicWeight", 100);
            parameters.$put("reusePath", Constants.SETTINGS_PATH_REUSE); // reuse the path for a long time
            parameters.$put("noPathFinding", roomController.hasPathFound); // if have already done some pathfinding.. delay it.
            this.creep.moveTo(position, parameters);

            Map<String, Object> moveMemory = (Map<String, Object>)this.creep.memory.$get("_move");
            if (moveMemory != null) {
                Integer time = (Integer) (moveMemory).$get("time");
                roomController.hasPathFound = time != null && time == Game.time;
            }

            return false;
        }
    }

    public static CreepDefinition define(RoomController roomController) {

        Array<PartTypes> abilities = new Array<PartTypes>();

        int comboCost = Constants.WORK_COST + Constants.CARRY_COST + Constants.MOVE_COST;
        int totalWorkParts = Math.max(1, (int) Math.floor(roomController.getRoomTotalStorage() / comboCost));
        for (int i = 0; i < totalWorkParts; i++) {
            abilities.push(PartTypes.WORK, PartTypes.MOVE, PartTypes.CARRY);
        }

        return new CreepDefinition(Constants.CREEP_WORKER_ID, Constants.CREEP_WORKER_NAME,
                abilities, roomController, null);
    }

    public int getCarryCapacity() {
        return this.creep.carryCapacity;
    }

    public String getTaskId() {
        return taskId;
    }

    @Override
    public Creep getCreep() {
        return super.getCreep();
    }
}
