package com.nicktoony.screeps;

import com.nicktoony.screeps.interfaces.Buildable;

/**
 * Created by nick on 02/08/15.
 */
public class ConstructionSite implements Buildable {
    public String id;
    public int progress;
    public int progressTotal;
    public RoomPosition pos;
}
