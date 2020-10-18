package net.jcip.examples;

import net.jcip.annotations.*;

import java.util.Random;

/**
 * UnsafeSequence
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class UnsafeSequence {
    private int value;

    Random a = new Random();

    /**
     * Returns a unique value.
     */
    public int getNext() {
        int i = value + 1;
        try {
            Thread.sleep(a.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value = i;
        return value;
    }
}
