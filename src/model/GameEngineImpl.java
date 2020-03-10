/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Collection;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * @author bowen
 *
 */
public class GameEngineImpl implements GameEngine {
    private Collection<GameEngineCallback> gameEngineCallbackList;
    private Collection<Player> playerList;

    public GameEngineImpl() {
        gameEngineCallbackList = new ArrayList<>();
        playerList = new ArrayList<>();
    }

    @Override
    public void rollPlayer(Player player, int initialDelay1, int finalDelay1,
            int delayIncrement1, int initialDelay2, int finalDelay2,
            int delayIncrement2) {
        DicePair result = rolling(player, initialDelay1, finalDelay1,
                delayIncrement1, initialDelay2, finalDelay2, delayIncrement2);

        for (GameEngineCallback item : gameEngineCallbackList)
            item.playerResult(player, result, this);

        player.setResult(result);
    }

    @Override
    public void rollHouse(int initialDelay1, int finalDelay1,
            int delayIncrement1, int initialDelay2, int finalDelay2,
            int delayIncrement2) {
        DicePair result = rolling(null, initialDelay1, finalDelay1,
                delayIncrement1, initialDelay2, finalDelay2, delayIncrement2);

        for (GameEngineCallback item : gameEngineCallbackList)
            item.houseResult(result, this);

        for (Player player : playerList)
            applyWinLoss(player, result);

    }

    @Override
    public void applyWinLoss(Player player, DicePair houseResult) {
        if (player.getResult().compareTo(houseResult) < 0) {
            player.setPoints(player.getPoints() - player.getBet());
        } else if (player.getResult().compareTo(houseResult) > 0) {
            player.setPoints(player.getPoints() + player.getBet());
        } else {
            player.resetBet();
        }

    }

    @Override
    public void addPlayer(Player player) {
        boolean sameId = false;
        for (Player item : playerList)
            if (item.getPlayerId().equals(player.getPlayerId())) {
                playerList.remove(item);
                playerList.add(player);
                sameId = true;
                break;
            }

        if (!sameId)
            playerList.add(player);
    }

    @Override
    public Player getPlayer(String id) {
        for (Player player : playerList) {
            if (player.getPlayerId().equals(id)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public boolean removePlayer(Player player) {
        return playerList.remove(player);

    }

    @Override
    public boolean placeBet(Player player, int bet) {
        return player.setBet(bet);
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        gameEngineCallbackList.add(gameEngineCallback);
    }

    @Override
    public boolean removeGameEngineCallback(
            GameEngineCallback gameEngineCallback) {
        return gameEngineCallbackList.remove(gameEngineCallback);
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return playerList;
    }

    /*
     * helper function for getting final dice result && player == null when it
     * is house turn instead of player turn
     */
    private DicePair rolling(Player player, int initialDelay1, int finalDelay1,
            int delayIncrement1, int initialDelay2, int finalDelay2,
            int delayIncrement2) {
        int count = 0;
        DicePair result = null;

        while (finalDelay1 != initialDelay1 + count
                && finalDelay2 != initialDelay2 + count) {
            result = new DicePairImpl();
            if (count % delayIncrement1 == 0) {
                for (GameEngineCallback item : gameEngineCallbackList) {
                    if (player != null)
                        item.playerDieUpdate(player, result.getDie1(), this);
                    else
                        item.houseDieUpdate(result.getDie1(), this);

                }
            }

            if (count % delayIncrement2 == 0) {
                for (GameEngineCallback item : gameEngineCallbackList) {
                    if (player != null)
                        item.playerDieUpdate(player, result.getDie2(), this);
                    else
                        item.houseDieUpdate(result.getDie2(), this);
                }
            }
            ++count;
        }
        return result;
    }
}
