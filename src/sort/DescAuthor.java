package sort;

import book.BookDTO;

import java.util.Comparator;

public class DescAuthor implements Comparator<BookDTO> {

    @Override
    public int compare(BookDTO o1, BookDTO o2) {
        return o2.getAuthor().compareTo(o1.getAuthor());
    }
}
