package sort;

import book.BookDTO;

import java.util.Comparator;

public class DescIsbn implements Comparator<BookDTO> {
    @Override
    public int compare(BookDTO o1, BookDTO o2) {
        return o1.getIsbn() < o2.getIsbn() ? 1 : o1.getIsbn() > o2.getIsbn() ? -1 : 0;
    }
}
