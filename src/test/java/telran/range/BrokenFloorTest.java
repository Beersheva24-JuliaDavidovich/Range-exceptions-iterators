package telran.range;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BrokenFloorTest {

private int getMinimalBrokenFloor(BallBrokenFloor bbf) {
    int min = 0;
    int max = Integer.MAX_VALUE;
    int middle = (max + min) / 2;
    while (min <= max) {
        try {
            bbf.checkFloor(middle);
            min = middle + 1;
        } catch (Exception e) {
            max = middle - 1;
        } 
        middle = (max + min) / 2;
    }
    // int res = -1;
    // int middle = (min + max) / 2;
    //     try {
    //         bbf.checkFloor(middle);
    //         min = middle + 1;
    //     } catch (IllegalArgumentException e) {
    //         max = middle - 1;
    //     } catch (Exception e){
    //         max = middle - 1;
    //         res = middle;
    //     }
    return min;
    }

@Test
void minimalBrokenFloorTest() {
    int [] floors = {200, 17, 1001, 2000};
    for(int i = 0; i < floors.length; i++) {
        BallBrokenFloor bbf = new BallBrokenFloor(floors[i]);         assertEquals(bbf.getMinBrokenFloor(), getMinimalBrokenFloor(bbf));
    }
}
}