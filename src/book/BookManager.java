package book;

import sort.*;

import java.util.ArrayList;
import java.util.HashMap;

public class BookManager {
    public static final int BOOK_MAX_COUNT = 100;

    private HashMap<String, ArrayList<BookDTO>> authorInquiry = new HashMap<>(BOOK_MAX_COUNT);
    private HashMap<String, BookDTO> titleInquiry = new HashMap<>(BOOK_MAX_COUNT);
    private HashMap<Integer, BookDTO> isbnInquiry = new HashMap<>(BOOK_MAX_COUNT);
    private ArrayList<BookDTO> bookList = new ArrayList<>(BOOK_MAX_COUNT);

    public void addBook(BookDTO book) {
        bookList.add(book);

        isbnInquiry.put(book.getIsbn(), book);
        titleInquiry.put(book.getTitle(), book);

        if (authorInquiry.get(book.getAuthor()) == null)
            authorInquiry.put(book.getAuthor(), new ArrayList<BookDTO>());

        authorInquiry.get(book.getAuthor()).add(book);
    }

    public void searchIsbn(int isbn) {
        if (isbnInquiry.get(isbn) == null) {
            System.out.println("해당 isbn 에 도서가 존재하지 않습니다.");
            return;
        }

        System.out.println(isbnInquiry.get(isbn).toString());
    }

    public void searchTitle(String title) {
        if (titleInquiry.get(title) == null) {
            System.out.println("해당 title 에 도서가 존재하지 않습니다.");
            return;
        }

        System.out.println(titleInquiry.get(title).toString());
    }

    public void searchAuthor(String author) {
        if (authorInquiry.get(author) == null) {
            System.out.println("해당 author 에 도서목록이 존재하지 않습니다.");
            return;
        }

        for(BookDTO authorBook : authorInquiry.get(author))
            System.out.println(authorBook.toString());

        System.out.println();
    }

    public void searchBookPrice(String inputtedValue) {
        BookDTO bookDTO = checkBookDTONull(inputtedValue);
        if (bookDTO != null)
            System.out.println(bookDTO.getTitle() + "의 가격은 " + bookDTO.getPrice() + " 입니다.");
        else
            System.out.println("검색하신 제목으로 책이 검색되지 않습니다.");
    }

    public void printAll() {
        for (BookDTO bookDTO : bookList)
            System.out.println(bookDTO.toString());

        System.out.println();
    }

    public void sortBookList(int selectedN) {
        if (bookList.isEmpty()) {
            System.out.println("도서 목록에 책이 하나도 없습니다.");
            return;
        }

        switch (selectedN) {
            case 1:
                bookList.sort(new AscIsbn());
                break;
            case 2:
                bookList.sort(new DescIsbn());
                break;
            case 3:
                bookList.sort(new AscTitle());
                break;
            case 4:
                bookList.sort(new DescTitle());
                break;
            case 5:
                bookList.sort(new AscAuthor());
                break;
            case 6:
                bookList.sort(new DescAuthor());
                break;
            case 7:
                bookList.sort(new AscMoney());
                break;
            case 8:
                bookList.sort(new DescMoney());
                break;
        }
    }

    public void alterBook(String inputtedValue, BookDTO newBookDTO) {
        BookDTO bookDTO = checkBookDTONull(inputtedValue);
        if (bookDTO != null) {
            isbnInquiry.put(bookDTO.getIsbn(), newBookDTO);
            titleInquiry.put(bookDTO.getTitle(), newBookDTO);

            ArrayList<BookDTO> authorBookList = authorInquiry.get(bookDTO.getAuthor());
            for(int i = 0; i < authorBookList.size(); i++) {
                if (authorBookList.get(i).getIsbn() == newBookDTO.getIsbn()) {
                    authorBookList.set(i, newBookDTO);
                    break;
                }
            }

           for(int i = 0; i < bookList.size(); i++) {
               if (bookList.get(i).getIsbn() == newBookDTO.getIsbn()) {
                   bookList.set(i, newBookDTO);
                   break;
               }
           }

            System.out.println(bookDTO.getTitle() + "의 책이" + newBookDTO.getTitle() + "으로 변경 되었습니다.");
        }
        else
            System.out.println("검색하신 제목으로 책이 검색되지 않습니다.");
    }

    public void deleteBook(String inputtedValue) {
        BookDTO bookDTO = checkBookDTONull(inputtedValue);
        if (bookDTO != null) {
            isbnInquiry.remove(bookDTO.getIsbn());
            titleInquiry.remove(bookDTO.getTitle());

            for(BookDTO book : authorInquiry.get(bookDTO.getAuthor())) {
                if (book.getIsbn() == bookDTO.getIsbn()) {
                    authorInquiry.get(bookDTO.getAuthor()).remove(bookDTO);
                    break;
                }
            }

            bookList.remove(bookDTO);

            System.out.println(bookDTO.getTitle() + "의 책이 삭제 되었습니다.");
        }
        else
            System.out.println("검색하신 제목으로 책이 검색되지 않습니다.");
    }

    private BookDTO checkBookDTONull(String title) {
        return titleInquiry.get(title);
    }
}
