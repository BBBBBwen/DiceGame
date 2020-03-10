/**
 * 
 */
package model;

import model.interfaces.Die;

/**
 * @author bowen
 *
 */
public class DieImpl implements Die {
    private String[] indicator = new String[] { "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine" };
    private int number;
    private int value;
    private int numFaces;

    public DieImpl(int number, int value, int numFaces)
            throws IllegalArgumentException {
        this.number = number;
        this.value = value;
        this.numFaces = numFaces;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int getNumFaces() {
        return numFaces;
    }

    @Override
    public boolean equals(Die die) {
        return number == die.getNumber() && numFaces == die.getNumFaces();
    }

    public String toString() {
        return value <= 9 ? indicator[value - 1] : indicator[8];
    }
}
