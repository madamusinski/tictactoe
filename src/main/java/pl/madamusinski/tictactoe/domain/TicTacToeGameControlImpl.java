package pl.madamusinski.tictactoe.domain;

import org.springframework.stereotype.Component;
import pl.madamusinski.tictactoe.domain.player.Player;

@Component
public class TicTacToeGameControlImpl implements TicTacToeGameControl<Player> {

    private Player player;

    @Override
    public void makeTurn() {

    }

    @Override
    public Player currentPlayerTurn() throws Exception {
        if(player!=null)
            return player;
        else
            throw new Exception("No player found with active turn!");
    }

    @Override
    public void setPlayerWithTurn(Player player) {
        if(player.isMyTurn())
            this.player = player;

    }
}
