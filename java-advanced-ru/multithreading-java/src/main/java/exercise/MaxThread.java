package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// BEGIN
public class MaxThread extends Thread {

    static int max;
    int[] inputData;

    public MaxThread(int[] inputData) {
        this.inputData = inputData;
    }

    public static int getMax() {
        return max;
    }

    @Override
    public void run() {
        var datas = new ArrayList<Integer>();
        if (inputData.length > 0) {
            max = Arrays.stream(inputData).max().getAsInt();
        }

    }
}

// END
