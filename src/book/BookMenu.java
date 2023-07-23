package book;

import java.util.Scanner;

public class BookMenu {
    private BookManager bookMgr = new BookManager();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {
        exit:
        while (true) {
            System.out.println("========== 메뉴 ==========");
            System.out.println("1. 책 추가");
            System.out.println("2. 책 조회");
            System.out.println("3. 책 정렬");
            System.out.println("4. 책 수정");
            System.out.println("5. 책 삭제");
            System.out.println("6. 프로그램 종료");

            int selectedN = sc.nextInt();
            sc.nextLine();

            switch (selectedN) {
                case 1:
                    while (true) {
                        System.out.print("제목 입력: ");
                        final String TITLE = sc.nextLine();

                        System.out.print("작가 입력: ");
                        final String AUTHOR = sc.nextLine();

                        System.out.print("가격 입력: ");
                        final int PRICE = sc.nextInt();

                        bookMgr.addBook(new BookDTO(TITLE, AUTHOR, PRICE));

                        System.out.print("1번 입력 시 계속 입력모드: ");
                        sc.nextLine();
                        if (!sc.nextLine().equals("1"))
                            break;
                    }
                    break;
                case 2:
                    printInquiryList();
                    break;
                case 3:
                    printSortList();
                    break;
                case 4:
                    System.out.print("수정할 도서의 제목 입력: ");
                    final String INPUTTED = sc.nextLine();

                    System.out.print("제목 입력: ");
                    final String TITLE = sc.nextLine();

                    System.out.print("작가 입력: ");
                    final String AUTHOR = sc.nextLine();

                    System.out.print("가격 입력: ");
                    final int PRICE = sc.nextInt();

                    bookMgr.alterBook(INPUTTED, new BookDTO(TITLE, AUTHOR, PRICE));

                    sc.nextLine();
                    break;
                case 5:
                    System.out.print("삭제할 도서의 제목 입력: ");
                    bookMgr.deleteBook(sc.nextLine());
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    break exit;
                default:
                    System.out.println("재입력 바랍니다.");
                    break;
            }

        }
    }

    private void printInquiryList() {
        while (true) {
            System.out.println("1. isbn 조회");
            System.out.println("2. 제목 조회");
            System.out.println("3. 작가 조회");
            System.out.println("4. 책 가격 조회");
            System.out.println("5. 전체 조회");

            int selectedN = sc.nextInt();
            sc.nextLine();

            while (selectedN < 1 && 5 < selectedN) {
                System.out.println("재입력 바랍니다.");
                selectedN = sc.nextInt();
                sc.nextLine();
            }

            switch (selectedN) {
                case 1:
                    System.out.print("isbn 입력: ");
                    final int INPUTTED_ISBN = sc.nextInt();
                    bookMgr.searchIsbn(INPUTTED_ISBN);

                    sc.nextLine();
                    break;
                case 2:
                    System.out.print("제목 입력: ");
                    final String INPUTTED_TITLE = sc.nextLine();
                    bookMgr.searchTitle(INPUTTED_TITLE);
                    break;
                case 3:
                    System.out.print("작가 입력: ");
                    final String INPUTTED_AUTHOR = sc.nextLine();
                    bookMgr.searchAuthor(INPUTTED_AUTHOR);
                    break;
                case 4:
                    System.out.print("제목 입력: ");
                    String inputted = sc.nextLine();
                    bookMgr.searchBookPrice(inputted);
                    break;
                case 5:
                    bookMgr.printAll();
                    break;
            }

            System.out.println();

            System.out.print("1번 입력 시 계속 입력모드: ");
            if (!sc.nextLine().equals("1"))
                break;
        }
    }

    private void printSortList() {
        while (true) {
            System.out.println("1. isbn 기준 오름차순 정렬하기");
            System.out.println("2. isbn 기준 내림차순 정렬하기");
            System.out.println("3. 책 제목 기준 오름차순 정렬하기");
            System.out.println("4. 책 제목 기준 내림차순 정렬하기");
            System.out.println("5. 저자 기준 오름차순 정렬하기");
            System.out.println("6. 저자 기준 내림차순 정렬하기");
            System.out.println("7. 책 가격 기준 오름차순 정렬하기");
            System.out.println("8. 책 가격 기준 내림차순 정렬하기");

            int selectedN = sc.nextInt();
            sc.nextLine();

            while (selectedN < 1 && 8 < selectedN) {
                System.out.println("재입력 바랍니다.");
                selectedN = sc.nextInt();
                sc.nextLine();
            }

            bookMgr.sortBookList(selectedN);

            System.out.println();

            System.out.print("1번 입력 시 계속 입력모드: ");
            if (!sc.nextLine().equals("1"))
                break;
        }
    }
}
