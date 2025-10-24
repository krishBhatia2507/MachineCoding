package main.java.acko.Models;

public class Sport {
    private String sportId;
    private String name;
    private String description;
    private int maxPlayers;

    public Sport(String sportId, String name, String description, int maxPlayers) {
        this.sportId = sportId;
        this.name = name;
        this.description = description;
        this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}