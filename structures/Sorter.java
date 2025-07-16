package structures;

import models.Book;
import java.util.*;

public class Sorter {

    public static List<Book> mergeSortByTitle(List<Book> books) {
        if (books.size() <= 1) return books;
        int mid = books.size() / 2;
        List<Book> left = mergeSortByTitle(books.subList(0, mid));
        List<Book> right = mergeSortByTitle(books.subList(mid, books.size()));
        return mergeByTitle(left, right);
    }

    private static List<Book> mergeByTitle(List<Book> left, List<Book> right) {
        List<Book> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).title.compareToIgnoreCase(right.get(j).title) <= 0)
                result.add(left.get(i++));
            else
                result.add(right.get(j++));
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }

    public static List<Book> mergeSortByYear(List<Book> books) {
        if (books.size() <= 1) return books;
        int mid = books.size() / 2;
        List<Book> left = mergeSortByYear(books.subList(0, mid));
        List<Book> right = mergeSortByYear(books.subList(mid, books.size()));
        return mergeByYear(left, right);
    }

    private static List<Book> mergeByYear(List<Book> left, List<Book> right) {
        List<Book> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).year <= right.get(j).year)
                result.add(left.get(i++));
            else
                result.add(right.get(j++));
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }
}
