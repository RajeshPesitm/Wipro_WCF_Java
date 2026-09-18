package SortingCreative;

import java.util.ArrayList;
import java.util.Comparator;

class BubbleSortList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public void sort(Comparator<? super T> comparator) {

        int n = size();

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (comparator.compare(get(j), get(j + 1)) > 0) {

                    T temp = get(j);
                    set(j, get(j + 1));
                    set(j + 1, temp);
                }
            }
        }
    }
}
