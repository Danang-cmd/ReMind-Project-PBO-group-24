package util;

import java.util.*;

public class ShuffleUtil {
    public static ArrayList<Integer> getRandomCards() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }
}
