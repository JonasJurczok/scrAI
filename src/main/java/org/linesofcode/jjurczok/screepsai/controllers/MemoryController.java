package org.linesofcode.jjurczok.screepsai.controllers;

import org.stjs.javascript.JSCollections;
import org.stjs.javascript.Map;

public abstract class MemoryController {
    protected Map<String, Object> memory;

    public MemoryController(Map<String, Object> memory) {
        this.memory = memory;

        if (memory.$get("init") == null) {
            init();
        }
    }

    public void init() {
        memory.$put("init", true);
    }

    protected Map<String, Object> getMemory(String name) {
        if (memory.$get(name) == null) {
            memory.$put(name, JSCollections.$map());
        }
        return (Map<String, Object>) memory.$get(name);
    }


}
