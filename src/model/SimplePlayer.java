package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

/**
 * @author bowen
 *
 */
public class SimplePlayer implements Player {
    private String playerId;
    private String playerName;
    private int points;
    private int bet;
    private DicePair rollResult;

    public SimplePlayer(String playerId, String playerName, int initialPoints) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = initialPoints;
        this.rollResult = null;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;

    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;

    }

    @Override
    public String getPlayerId() {
        return playerId;
    }

    @Override
    public boolean setBet(int bet) {
        if (bet <= points) {
            this.bet = bet;
            return true;
        }
        resetBet();
        return false;
    }

    @Override
    public int getBet() {
        return bet;
    }

    @Override
    public void resetBet() {
        this.bet = 0;

    }

    @Override
    public DicePair getResult() {
        return rollResult;
    }

    @Override
    public void setResult(DicePair rollResult) {
        this.rollResult = rollResult;
    }

    public String toString() {
        return String.format(
                "Player: id=%s, name=%s, bet=%s, points=%s, RESULT .. %s",
                playerId, playerName, bet, points, rollResult);
    }
}
