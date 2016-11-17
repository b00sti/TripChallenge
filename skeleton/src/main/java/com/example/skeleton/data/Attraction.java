package com.example.skeleton.data;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-03
 */

public class Attraction {
    private String id;
    private String name;
    private boolean visited;

    public Attraction(String id, String name, boolean visited) {
        this.id = id;
        this.name = name;
        this.visited = visited;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
