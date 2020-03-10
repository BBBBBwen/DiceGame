package model;

import model.interfaces.DicePair;
import model.interfaces.Die;

/**
 * @author bowen
 *
 */
public class DicePairImpl implements DicePair {
    private Die[] dies;
    private int maxFaces = 6;

    public DicePairImpl() {
        dies = new Die[2];
        for (int i = 0; i < dies.length; ++i) {
            dies[i] = new DieImpl(i + 1, (int) (Math.random() * maxFaces) + 1,
                    maxFaces);
        }
    }

    @Override
    public Die getDie1() {
        return dies[0];
    }

    @Override
    public Die getDie2() {
        return dies[1];
    }

    @Override
    public int getTotal() {
        return dies[0].getValue() + dies[1].getValue();
    }

    @Override
    public boolean equals(DicePair dicePair) {
        return dies[0].equals(dicePair.getDie1())
                && dies[1].equals(dicePair.getDie2());
    }

    @Override
    public int compareTo(DicePair dicePair) {
        if (getTotal() < dicePair.getTotal())
            return -1;
        if (getTotal() == dicePair.getTotal())
            return 0;
        return 1;
    }

    public String toString() {
        return "Dice 1: " + dies[0] + ",  Dice 2: " + dies[1] + " .. Total: "
                + getTotal();
    }
}
