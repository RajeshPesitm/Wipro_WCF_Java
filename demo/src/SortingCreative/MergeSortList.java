package SortingCreative;

import java.util.ArrayList;
import java.util.Comparator;

class MergeSortList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public void sort(Comparator<? super T> comparator) {

        if (size() > 1) {
            mergeSort(0, size() - 1, comparator);
        }
    }

    private void mergeSort(
            int left,
            int right,
            Comparator<? super T> comparator) {

        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(left, middle, comparator);

        mergeSort(middle + 1, right, comparator);

        merge(left, middle, right, comparator);
    }

    private void merge(
            int left,
            int middle,
            int right,
            Comparator<? super T> comparator) {

        ArrayList<T> temp = new ArrayList<>();

        int i = left;
        int j = middle + 1;

        while (i <= middle && j <= right) {

            if (comparator.compare(get(i), get(j)) <= 0) {
                temp.add(get(i));
                i++;
            } else {
                temp.add(get(j));
                j++;
            }
        }

        while (i <= middle) {
            temp.add(get(i));
            i++;
        }

        while (j <= right) {
            temp.add(get(j));
            j++;
        }

        for (int k = 0; k < temp.size(); k++) {
            set(left + k, temp.get(k));
        }
    }
}
