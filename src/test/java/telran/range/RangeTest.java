package telran.range;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import telran.range.exceptions.OutOfRangeMaxValueException;
import telran.range.exceptions.OutOfRangeMinValueException;

public class RangeTest {
    private static final int MIN = 50;
    private static final int MAX = 100;
    Range range = Range.getRange(MIN, MAX);
    @Test
    void wrongRangeCtreatingTest() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Range.getRange(MAX, MIN) );
    }
    @Test
    void rightNumberTest() throws Exception {
        range.checkNumber(55);
    }
    void wrongNumberTest() throws Exception {
        assertThrowsExactly(OutOfRangeMaxValueException.class,
         () -> range.checkNumber(MAX + 1));
         assertThrowsExactly(OutOfRangeMinValueException.class, () -> range.checkNumber(MIN - 1));
    }
    @Test
    void iteratorTest() {
        Range rangeInt = Range.getRange(-10,10);
        Predicate<Integer> negativePredicate = i -> i < 0;
        rangeInt.setPredicate(negativePredicate);
        Iterator<Integer> it = rangeInt.iterator();
        Integer [] expected = {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1};
        Integer [] actual = new Integer[expected.length];
        int index = 0;
        while (it.hasNext()) {
            actual[index++] = it.next();
        }
        assertArrayEquals(expected, actual);
        assertThrowsExactly(NoSuchElementException.class, it:: next);
        
        Predicate<Integer> oddPredicate = i -> i % 2 == 0;
        rangeInt.setPredicate(oddPredicate);
        Iterator<Integer> it1 = rangeInt.iterator();
        Integer [] expected1 = {-10, -8, -6, -4, -2, 0, 2, 4, 6, 8, 10};
        Integer [] actual1 = new Integer[expected1.length];
        int index1 = 0;
        while (it1.hasNext()) {
            actual1[index1++] = it1.next();
        }
        assertArrayEquals(expected1, actual1);
        assertThrowsExactly(NoSuchElementException.class, it1:: next);

    }
}