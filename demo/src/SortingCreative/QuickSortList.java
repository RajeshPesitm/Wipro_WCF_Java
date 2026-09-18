package SortingCreative;

import java.util.ArrayList;
import java.util.Comparator;

class QuickSortList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public void sort(Comparator<? super T> comparator) {

        quickSort(0, size() - 1, comparator);
    }

    private void quickSort(
            int low,
            int high,
            Comparator<? super T> comparator) {

        if (low < high) {

            int pivotIndex = partition(
                    low,
                    high,
                    comparator
            );

            quickSort(low, pivotIndex - 1, comparator);

            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(
            int low,
            int high,
            Comparator<? super T> comparator) {

        T pivot = get(high);

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (comparator.compare(get(j), pivot) <= 0) {

                i++;

                T temp = get(i);
                set(i, get(j));
                set(j, temp);
            }
        }

        T temp = get(i + 1);
        set(i + 1, get(high));
        set(high, temp);

        return i + 1;
    }
}
