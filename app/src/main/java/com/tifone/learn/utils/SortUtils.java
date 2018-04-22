package com.tifone.learn.utils;

/**
 * Created by tongkao.chen on 2018/3/20.
 */

public class SortUtils {
    public String sort() {
        int[] data = {1,77,45,98,23,90,122,1231,123,2};
        int[] results = bubbleSort2(data);
        StringBuilder builder = new StringBuilder("");
        for (int result : results) {
            builder.append(result);
            builder.append(" : ");
        }

        if (builder.length() > 3) {
            builder.setLength(builder.length() - 3);
        }
        //
        builder.append("\n bubble: ");
        results = bubbleSortLearn(data);
        for (int result : results) {
            builder.append(result);
            builder.append(" : ");
        }
        if (builder.length() > 3) {
            builder.setLength(builder.length() - 3);
        }
        //
        builder.append("\n selection: ");
        results = selectionSortLearn(data);
        for (int result : results) {
            builder.append(result);
            builder.append(" : ");
        }
        if (builder.length() > 3) {
            builder.setLength(builder.length() - 3);
        }
        //
        builder.append("\n insertion: ");
        results = insertionSortLearn(data);
        for (int result : results) {
            builder.append(result);
            builder.append(" : ");
        }
        if (builder.length() > 3) {
            builder.setLength(builder.length() - 3);
        }
        return builder.toString();
    }
    private int[] bubbleSort(int[] src) {
        int temp = 0;
        for (int i = 0; i < src.length - 1; i ++) {
            temp = src[i];
            for (int j = i; j < src.length -1; j++) {
                if (temp < src[j + 1]) {
                    src[i] = src[j + 1];
                    src[j + 1] = temp;
                    temp = src[i];
                }
            }
        }
        return src;
    }
    private int[] bubbleSort2(int[] src) {
        int temp = 0;
        boolean isSorted;
        for (int i = 0; i < src.length - 1; i ++) {
            isSorted = true;
            for (int j = src.length - 1; j > i; j--) {
                if (src[j] > src[j - 1]) {
                    temp = src[j];
                    src[j] = src[j - 1];
                    src[j - 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
        return src;
    }
    private int[] bubbleSort3(int[] src) {
        int temp = 0;
        for (int i = 0; i < src.length - 1; i++) {
            for (int j = 0; j < src.length -1 - i; j++) {
                if (src[j] > src[j + 1]) {
                    temp = src[j];
                    src[j] = src[j+1];
                    src[j+1] = temp;
                }
            }
        }
        return src;
    }
    private int[] selectSort(int[] src) {
        int temp = 0;
        int index = 0;
        for (int i = 0; i < src.length - 1; i++) {
            index = i;
            for (int j = i + 1; j < src.length; j ++) {
                if (src[index] > src[j]) {
                    index = j;
                }
            }
            if (index != i) {
                temp = src[i];
                src[i] = src[index];
                src[index] = temp;
            }
        }
        return src;
    }

    private int[] insertionSort(int[] src) {
        int index = -1;
        for (int i = 1; i < src.length; i++) {
            index = -1;
            for (int j = 0; j < i; j++) {
                if (src[i] > src[j]) {
                    index = j;
                }
            }
            if (index != -1) {
                int temp = src[i];
                src[i] = src[index];
                src[index] = temp;
            }
        }
        return src;
    }

    private int[] insertionSort2(int[] src) {
        for (int i = 0; i < src.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (src[j] < src[j-1]) {
                    int temp = src[j];
                    src[j] = src[j-1];
                    src[j-1] = temp;
                } else {
                    break;
                }
            }
        }
        return src;
    }

    private int[] bubbleSortLearn(int[] src) {

        for (int i = 0; i < src.length -1; i++) {
            for (int j = 0 ; j < src.length - 1 - i; j++) {
                if (src[j] < src[j+1]) {
                    int temp = src[j];
                    src[j] = src[j+1];
                    src[j+1] = temp;
                }
            }
        }
        return  src;
    }
    private int[] insertionSortLearn(int[] src) {
        for (int i = 0; i < src.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (src[j] < src[j-1]) {
                    int temp = src[j];
                    src[j] = src[j-1];
                    src[j-1] = temp;
                } else {
                    break;
                }
            }
        }
        return src;
    }

    private int[] selectionSortLearn(int[] src) {
        for (int i = 0; i < src.length - 1; i++) {
            for (int j = i + 1; j < src.length; j++) {
                if (src[i] > src[j]) {
                    int temp = src[i];
                    src[i] = src[j];
                    src[j] = temp;
                }
            }
        }

        return src;
    }

}
