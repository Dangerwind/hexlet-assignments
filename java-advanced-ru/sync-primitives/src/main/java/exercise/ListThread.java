package exercise;

import java.util.List;

// BEGIN
public class ListThread extends Thread {

    SafetyList safetyList;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        // safetyList = new SafetyList();
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            safetyList.add(i);
        }
    }
}

// END
