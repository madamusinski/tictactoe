package pl.madamusinski.tictactoe.service;

/**
 * @author Mateusz Adamusiński
 * interface used for spawning players depending on given arguments when running program
 */
public interface PlayerSpawner {

    /**
     * method used for creating adding players to local container of players
     * @param numberOfPlayers
     */
    void spawnPlayers(int numberOfPlayers);
}
