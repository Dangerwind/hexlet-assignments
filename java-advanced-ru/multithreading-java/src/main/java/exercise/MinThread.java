package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// BEGIN
public class MinThread extends Thread {

    static int min;
    int[] inputData;

    public MinThread(int[] inputData) {
        this.inputData = inputData;
    }

    public static int getMin() {
        return min;
    }

    @Override
    public void run() {
        if (inputData.length > 0) {
            min = Arrays.stream(inputData).min().getAsInt();
        }
    }


}
// END
