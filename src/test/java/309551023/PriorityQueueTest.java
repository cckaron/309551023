import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class PriorityQueueTest{
    // generate test cases
    public static Stream<Arguments> getTestCases(){
        return Stream.of(
                arguments(new int[]{1, 5, 3, 7}, new int[]{1, 3, 5, 7}),
                arguments(new int[]{2, 1, 4, 3}, new int[]{1, 2, 3, 4}),
                arguments(new int[]{6, 9, 7, 8}, new int[]{6, 7, 8, 9}),
                arguments(new int[]{9, 5, 3, 2}, new int[]{2, 3, 5, 9}),
                arguments(new int[]{1, 4, 7, 8}, new int[]{1, 4, 7, 8})
        );
    }

    // parameterized testing
    @ParameterizedTest
    @MethodSource("getTestCases")
    public void parameterizedTest(int[] input, int[] expected){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int arg : input){
            pq.offer(arg);
        }
        for (int arg : expected){
            assertEquals(arg, pq.poll());
        }
    }

    /* Exception Testing */
    @Test
    public void whenExceptionThrown_thenInitialCapacityNotGreaterThanOne(){
        // wrong initial capacity
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue<Integer>(-2, null);
        });
    }

    @Test
    public void whenExceptionThrown_thenOfferIsNull(){
        // offer null value
        Exception e = assertThrows(NullPointerException.class, () -> {
            new PriorityQueue<Integer>().offer(null);
        });
    }

    @Test
    public void whenExceptionThrown_thenIterateOnNullQueue(){
        // iterate empty value (require non-null)
        Exception e = assertThrows(NullPointerException.class, () -> {
            new PriorityQueue<Integer>().forEach(null);
        });

    }

    @Test
    public void whenExceptionThrown_thenNoElementCanRemove(){
        // iterate empty value (require non-null)
        Exception e = assertThrows(NoSuchElementException.class, () -> {
            new PriorityQueue<Integer>().remove();
        });
    }

}