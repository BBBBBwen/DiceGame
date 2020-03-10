package view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging
 * behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
    private static final Logger logger = Logger
            .getLogger(GameEngineCallback.class.getName());

    public GameEngineCallbackImpl() {
        // FINE shows rolling output, INFO only shows result
        logger.setLevel(Level.FINE);
    }

    @Override
    public void playerDieUpdate(Player player, Die die, GameEngine gameEngine) {
        logger.log(Level.FINE, String.format("%s die %s rolled to %s",
                player.getPlayerName(), die.getNumber(), die));
    }

    @Override
    public void playerResult(Player player, DicePair result,
            GameEngine gameEngine) {
        logger.log(Level.INFO, String.format("%s *RESULT*: %s",
                player.getPlayerName(), result));
    }

    @Override
    public void houseDieUpdate(Die die, GameEngine gameEngine) {
        logger.log(Level.FINE, String.format("The House die %s rolled to %s",
                die.getNumber(), die));

    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {
        logger.log(Level.FINE, String.format("House *RESULT*: %s", result));
    }

    // TODO implement rest of interface
}
