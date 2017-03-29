package org.linesofcode.jjurczok;

import org.linesofcode.jjurczok.screeps.Game;
import org.linesofcode.jjurczok.screeps.Room;
import org.linesofcode.jjurczok.screeps.callbacks.Lodash;
import org.linesofcode.jjurczok.screeps.callbacks.LodashCallback2;
import org.linesofcode.jjurczok.screepsai.controllers.RoomController;
import org.stjs.javascript.Array;
import org.stjs.javascript.Global;

public class GlobalController {

    public static void main(String[] args) {
        // How long did it take to load?
        Global.console.log("Tick Started, used CPU: " + Game.cpu.getUsed());

        new GlobalController();

        // How long to complete?
        Global.console.log("Tick Finished, used CPU: " + Game.cpu.getUsed());
    }

    private Array<RoomController> roomControllers;

    public GlobalController() {
        roomControllers = new Array<>();
        // For each room in game
        Lodash.forIn(Game.rooms, (LodashCallback2<Room, String>) (room, name) -> {
            // Create a room controller
            roomControllers.push(new RoomController(room));
            return true;
        }, this);

        // For each room controller
        roomControllers.forEach(RoomController::step);
    }
}
