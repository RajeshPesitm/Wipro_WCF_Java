package SortingCreative;

import java.util.ArrayList;
import java.util.Comparator;

class InsertionSortList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public void sort(Comparator<? super T> comparator) {

        for (int i = 1; i < size(); i++) {

            T current = get(i);

            int j = i - 1;

            while (j >= 0 &&
                    comparator.compare(get(j), current) > 0) {

                set(j + 1, get(j));
                j--;
            }

            set(j + 1, current);
        }
    }
}
