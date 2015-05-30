package sorting;

import java.util.ArrayList;

public class SelectionSorter {
    public void SelectionSort(ArrayList<Integer> a) {
        if (a == null || a.size() <= 1)
            return;

        for (int i = 0; i < a.size() - 1; ++i) {
            for (int j = i + 1; j < a.size(); ++j) {
                if (a.get(j) < a.get(i)) {
                    Integer temp = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, temp);
                }
            }
        }
    }
}
