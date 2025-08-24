
package utils;
/**
 * The Counter class is a simple class that is used for counting things.
 */
public class Counter {
    private int count;
    /**
     * Constructs a new Counter with an initial count of 0.
     */
    public Counter() {
        count = 0;
    }
    /**
     * Increases the counter by the specified number.
     * @param number The number to increase the counter by.
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Decreases the counter by the specified number.
     * @param number The number to decrease the counter by.
     */
    public void decrease(int number) {
        count -= number;
    }
    /**
     * Returns the current value of the counter.
     * @return The current value of the counter.
     */
    public int getValue() {
        return count;
    }
}
