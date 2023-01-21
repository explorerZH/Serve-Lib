import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;

public class ManageCore {
  /*
   * We create a ArrayList to store info of book items,
   * every element in this list is an array of String.
   *
   * This String array has 8 elements: 0-Book Name 1-Book ID 2-Category
   * 3-Status 4-Entry Date 5-Last Borrowing Date 6-Due Date 7-Borrower
   *
   * Book ID: Its style likes this: 5-192-2. First numbered as a certain category,
   * second numberd as a certain kind of book,
   * third numbered as which book it is in this kind of book.
   *
   * Category: We consider that there are total 5 kinds of books in the library:
   * 1-Novel 2-Arts 3-Science 4-Education 5-Sports
   *
   * Status: We consider that there are total 3 kinds of status in the library:
   * Availble, Undue, Due.
   *
   * About the Date: Its style likes this: 2001.08.21.
   * Borrowing time borrower can hold is 50 days.
   */

  static List<String[]> book_list = new ArrayList<String[]>(); //store book items

  static Scanner scan = new Scanner(System.in);
  static int identity_id = -1;
  static int user_option_id = -1;
  static int user_booklistnum_id = -1;
  static int user_otherneed_id = -1;
  static int admini_option_id = -1;
  static int admini_otherneed_id = -1;
  static int admini_change_item_id = -1;
  static int admini_lend_return_item_id = -1;
  static int safenum = 5;
  static String today_date;
  static String user_input_category;
  static String user_input_keyword;
  static String admini_input_keyword;
  static String admini_password;
  private static String right_password = "20870314";


  //Methods don't label name are written by Han Zhang in 2023
  //menu is showing info for user, get method is to acquire id user choose
  public static void start_page() {
    System.out.println("Welcome to Library Manage System!");
    System.out.println();
    System.out.println("Please select your identity:(enter the number)");
    System.out.println();
    System.out.println("1)User    2)Administrator");
  }

  public static int get_identity() {
    int identity = -1;
    // Process input data
    while (true) {
      try {
        identity = scan.nextInt();
        scan.nextLine();
        while (identity != 1 && identity != 2) {
          System.out.println("Identity choosing is wrong, please choose between 1 and 2 again.");
          System.out.println();
          System.out.println("1)User    2)Administrator");
          scan.nextLine();
          identity = scan.nextInt();
        }
        return identity;
      } catch (Exception e) {
        System.out.println("Identity choosing is wrong, please choose between 1 and 2 again.");
        System.out.println();
        System.out.println("1)User    2)Administrator");
        scan.nextLine();
      }
    }
  }

  public static void user_menu() {
    System.out.println();
    System.out.println("Hello user! What would you like to do?(enter your number)");
    System.out.println();
    System.out.println("1)Check Book List   2)Search a Book");
  }

  public static void administrator_menu() {
    System.out.println();
    System.out.println("Hi administrator! What would you like to do?(enter your number)");
    System.out.println();
    System.out.println("1)Check All Info in Book List   2)Search a Book"
           + "   3)Add/Delete a Book Item   4)Lend/Return a Book");
  }

  public static void user_booklist_menu() {
    System.out.println();
    System.out.println("If you want to check the whole "
           + "booklist or be recommended books by category?");
    System.out.println();
    System.out.println("Please select your requirement:(enter the number)");
    System.out.println();
    System.out.println("1)Whole Booklist    2)Recommend books");
  }

  public static void user_theother_menu() {
    System.out.println();
    System.out.println("Do you want to continue the service or quit the system?");
    System.out.println();
    System.out.println("Please select your requirement:(enter the number)");
    System.out.println();
    System.out.println("1)Check Book List    2)Search a Book    3)Quit the system");
  }

  public static void admini_other_menu() {
    System.out.println();
    System.out.println("Do you want to continue the service or quit the system?");
    System.out.println();
    System.out.println("Please select your requirement:(enter the number)");
    System.out.println();
    System.out.println("1)Check All Info in Book List   " + "2)Search a Book  "
           + " 3)Add/Delete a Book Item   4)Lend/Return a Book   5)Quit the system");

  }

  public static void date_menu() {
    System.out.println();
    System.out.println(
            "Password is right!, before you go into "
                   + "the system, please input today's date:(format like:2020.1.13)");
  }

  public static void password_menu() {
    System.out.println();
    System.out.println(
            "To identify whether you are a administrator,"
                    + " please input the correct password for this system:");
  }

  public static void wrong_password_menu() {
    System.out.println("Your input wrong, please try it again:(only have " + safenum + " times)");
  }

  public static void change_item_menu() {
    System.out.println();
    System.out.println("Do you want to add or delete a book item?");
    System.out.println();
    System.out.println("Please select your requirement:(enter the number)");
    System.out.println();
    System.out.println("1)Add a Book Item    2)Delete a Book Item");
  }

  public static void lend_return_item_menu() {
    System.out.println();
    System.out.println("Do reader want to lend or return a book?");
    System.out.println();
    System.out.println("Please select your requirement:(enter the number)");
    System.out.println();
    System.out.println("1)Lend a Book    2)Return a Book");
  }

  public static void quit_menu() {
    System.out.println();
    System.out.println("Thanks for your using, welcome to use Library Manage System next time!");
  }

  public static String get_today_date() {
    String todayDate;
    todayDate = scan.nextLine();
    return todayDate;
  }

  public static int get_user_option() {
    int userOption = -1;
    // Process input data
    while (true) {
      try {
        userOption = scan.nextInt();
        while (userOption != 1 && userOption != 2) {
          System.out.println("User option is wrong, please choose between 1 and 2 again.");
          System.out.println();
          System.out.println("1)Check Book List    2)Search a Book");
          scan.nextLine();
          userOption = scan.nextInt();
        }
        return userOption;
      } catch (Exception e) {
        System.out.println("User option is wrong, please choose between 1 and 2 again.");
        System.out.println();
        System.out.println("1)Check Book List    2)Search a Book");
        scan.nextLine();
      }
    }
  }

  public static int get_admini_option() {
    int adminiOption = -1;
    // Process input data
    while (true) {
      try {
        adminiOption = scan.nextInt();
        while (adminiOption != 1 && adminiOption != 2 && adminiOption != 3 && adminiOption != 4) {
          System.out.println("Administrator option is wrong, please choose among 1 to 4 again.");
          System.out.println();
          System.out.println("1)Check All Info in Book List  "
                 + " 2)Search a Book   3)Add/Delete a Book Item   4)Lend/Return a Book");
          scan.nextLine();
          adminiOption = scan.nextInt();
        }
        return adminiOption;
      } catch (Exception e) {
        System.out.println("Administrator option is wro"
                + "ng, please choose among 1 to 4 again.");
        System.out.println();
        System.out.println("1)Check All Info in Book List  "
               + " 2)Search a Book   3)Add/Delete a Book Item   4)Lend/Return a Book");
        scan.nextLine();
      }
    }

  }

  public static int get_user_booklistnum() {
    int userBooklistnum = -1;
    // Process input data
    while (true) {
      try {
        userBooklistnum = scan.nextInt();
        while (userBooklistnum != 1 && userBooklistnum != 2) {
          System.out.println("User option is wrong, please choose between 1 and 2 again.");
          System.out.println();
          System.out.println("1)Whole Booklist    2)Recommend books");
          scan.nextLine();
          userBooklistnum = scan.nextInt();
        }
        return userBooklistnum;
      } catch (Exception e) {
        System.out.println("User option is wrong, please choose between 1 and 2 again.");
        System.out.println();
        System.out.println("1)Whole Booklist    2)Recommend books");
        scan.nextLine();
      }
    }

  }

  public static int get_user_otherneed_id() {
    int userOtherneed = -1;
    // Process input data
    while (true) {
      try {
        userOtherneed = scan.nextInt();
        while (userOtherneed != 1 && userOtherneed != 2 && userOtherneed != 3) {
          System.out.println("User option is wrong, please choose among 1 to 3 again.");
          System.out.println();
          System.out.println("1)Check Book List    2)Search a Book   3)Quit the system");
          scan.nextLine();
          userOtherneed = scan.nextInt();
        }
        return userOtherneed;
      } catch (Exception e) {
        System.out.println("User option is wrong, please choose among 1 to 3 again.");
        System.out.println();
        System.out.println("1)Check Book List    2)Search a Book   3)Quit the system");
        scan.nextLine();
      }
    }
  }

  public static int get_admini_otherneed_id() {
    int adminiOtherneed = -1;
    // Process input data
    while (true) {
      try {
        adminiOtherneed = scan.nextInt();
        while (adminiOtherneed != 1 && adminiOtherneed != 2
                && adminiOtherneed != 3 && adminiOtherneed != 4
                && adminiOtherneed != 5) {
          System.out.println("Administrator option is wrong, please choose among 1 to 5 again.");
          System.out.println();
          System.out.println("1)Check All Info in Book List   2)Search a Book   "
                  + "3)Add/Delete a Book Item   4)Lend/Return a Book   5)Quit the system");
          scan.nextLine();
          adminiOtherneed = scan.nextInt();
        }
        return adminiOtherneed;
      } catch (Exception e) {
        System.out.println("Administrator option is wrong, please choose among 1 to 5 again.");
        System.out.println();
        System.out.println("1)Check All Info in Book List   "
               + "2)Search a Book   3)Add/Delete a Book Item  "
               + " 4)Lend/Return a Book   5)Quit the system");
        scan.nextLine();
      }
    }

  }

  public static int get_admini_change_item_id() {
    int adminiChangeItem = -1;
    // Process input data
    while (true) {
      try {
        adminiChangeItem = scan.nextInt();
        while (adminiChangeItem != 1 && adminiChangeItem != 2) {
          System.out.println("Administrator option is wrong, please choose between 1 and 2 again.");
          System.out.println();
          System.out.println("1)Add a Book Item   2)Delete a Book Item");
          scan.nextLine();
          adminiChangeItem = scan.nextInt();
        }
        return adminiChangeItem;
      } catch (Exception e) {
        System.out.println("Administrator option is wrong, please choose between 1 and 2 again.");
        System.out.println();
        System.out.println("1)Add a Book Item   2)Delete a Book Item");
        scan.nextLine();
        adminiChangeItem = scan.nextInt();
        scan.nextLine();
      }
    }
  }

  public static int get_admini_lend_return_item_id() {
    int adminiLendReturnItem = -1;
    // Process input data
    while (true) {
      try {
        adminiLendReturnItem = scan.nextInt();
        while (adminiLendReturnItem != 1 && adminiLendReturnItem != 2) {
          System.out.println("Administrator option is wrong, "
                 + "please choose between 1 and 2 again.");
          System.out.println();
          System.out.println("1)Lend a Book   2)Return a Book");
          scan.nextLine();
          adminiLendReturnItem = scan.nextInt();
        }
        return adminiLendReturnItem;
      } catch (Exception e) {
        System.out.println("Administrator option is wrong,"
               + " please choose between 1 and 2 again.");
        System.out.println();
        System.out.println("1)Lend a Book   2)Return a Book");
        scan.nextLine();
        adminiLendReturnItem = scan.nextInt();
        scan.nextLine();
      }
    }
  }

  public static String get_password() {
    String password;
    password = scan.nextLine();
    return password;
  }

  public static String category_menu_and_input() {
    System.out.println();
    System.out.println("Here we have 5 categories books in our "
            + "library:Novel, Arts, Science, Education, Sports.");
    System.out.println();
    System.out.println("Which category of book do you want?");
    System.out.println();
    System.out.print("Please input the string of category here:");
    String inputCategory;
    scan.nextLine();
    inputCategory = scan.nextLine();
    return inputCategory;
  }

  public static String keyword_menu_and_input() {
    System.out.println();
    System.out.println("Which keyword of book do you want?");
    System.out.println();
    System.out.print("Please input the keyword here:(just one word)");
    String inputKeyword;
    scan.nextLine();
    inputKeyword = scan.nextLine();
    return inputKeyword;
  }

  //Student name: Yuan Guo
  //The class is written in 2023
  public static void load_file_into_booklist() {
    String path = "bookbase_file.xls";
    Workbook booklist;
    try {
      InputStream inS = new FileInputStream(path);
      booklist = Workbook.getWorkbook(inS); //get workbook

      Sheet[] sheets = booklist.getSheets(); //get all the sheets
      Sheet sheet = sheets[0]; //get the first sheet

      int rows = sheet.getRows();
      int columns = sheet.getColumns();

      for (int i = 1; i < rows; i++) {
        String[] abook = new String[8];
        for (int j = 0; j < columns; j++) {
          Cell acell = sheet.getCell(j, i);
          String val = acell.getContents(); //get each cell and store its value into arrays
          abook[j] = val;
        }
        book_list.add(abook);
        //System.out.println(Arrays.toString(a_book));
      }
      //System.out.println("File has been read successfully!");
      inS.close();
    } catch (Exception e) {
      System.out.println("Initialization bookbase file has not been found");
    }
  }

  //Student name: Yixin Niu
  //The class is written in 2023
  public static void show_whole_booklist(int identityId) {
    int sizeOfBooklist = book_list.size();
    if (identityId == 1) { // if the user is reader, hide some information
      standard_length_print("Name", 30);
      standard_length_print("ID", 10); //id
      standard_length_print("Category", 20);
      standard_length_print("Status", 15); //status
      for (int i = 0; i < sizeOfBooklist; i++) {
        System.out.println();
        standard_length_print(book_list.get(i)[0], 30);
        standard_length_print(book_list.get(i)[1], 10);
        standard_length_print(book_list.get(i)[2], 20);
        standard_length_print(book_list.get(i)[3], 15);
      }
    }
    if (identityId == 2) { // if the user is administrator, show whole Array
      standard_length_print("Name", 30);
      standard_length_print("ID", 10); //id
      standard_length_print("Category", 20);
      standard_length_print("Status", 15); //status
      standard_length_print("Entry Date", 15); //entry date
      standard_length_print("Last Borrow", 15); //last borrow
      standard_length_print("Due Date", 15); //due date
      standard_length_print("borrower name", 15); //borrower name
      for (int i = 0; i < sizeOfBooklist; i++) {
        System.out.println();
        standard_length_print(book_list.get(i)[0], 30);
        standard_length_print(book_list.get(i)[1], 10);
        standard_length_print(book_list.get(i)[2], 20);
        standard_length_print(book_list.get(i)[3], 15);
        standard_length_print(book_list.get(i)[4], 15);
        standard_length_print(book_list.get(i)[5], 15);
        standard_length_print(book_list.get(i)[6], 15);
        standard_length_print(book_list.get(i)[7], 15);
      }
    }
  }

  //Student name: Yuan Guo
  //The class is written in 2023
  public static void compare_and_output_booklist(int idtId, int optId, String inputCategory) {
    /*
     * *********************WARNING***************************
     * the value of input_category when passing a
     * category will be something like "Science","Arts"
     * *********************END*******************************
     * Here we will compare the input_category with the
     *  category we already have, you can just create 4-5 categories
     * and then compare
     * if input is different with all the categories we h
     * ave, try to make the menu to prompt the user to there
     * is no book of what user wants
     * upper or lower case doesn't matter
     */
    int bookListSize = book_list.size();
    int abookNamePos = 0; //pos is a short for position
    int abookCategoryPos = 2;
    boolean findIt = false;
    String[] tempBookName;
    tempBookName = new String[10]; //spilt book name
    //user id = 1 ; admin id = 2
    // option id = 1 ----> user search by categories;
    // option id = 2 ------> user or admin search by a key word.
    //user and admin displays are different
    if (optId == 1) {
      System.out.println();
      up_display(idtId);
      for (int i = 0; i < bookListSize; i++) {
        if (book_list.get(i)[abookCategoryPos].toLowerCase().equals(inputCategory.toLowerCase())) {
          standard_length_print(book_list.get(i)[abookNamePos], 30);
          standard_length_print(book_list.get(i)[1], 10); //id
          standard_length_print(book_list.get(i)[2], 20); //category
          standard_length_print(book_list.get(i)[3], 15); //status
          System.out.print("\n");
          findIt = true; //print search by category result
        }

      }
      if (findIt == false) {
        System.out.println("Sorry, not found!");
      }
    } else if (idtId == 1) { //user keyword search
      System.out.println();
      up_display(idtId);
      for (int i = 0; i < bookListSize; i++) {
        tempBookName = book_list.get(i)[0].split(" ");
        for (int j = 0; j < tempBookName.length; j++) {
          if (tempBookName[j].toLowerCase().equals(inputCategory.toLowerCase())) {
            standard_length_print(book_list.get(i)[abookNamePos], 30);
            standard_length_print(book_list.get(0)[1], 10); //id
            standard_length_print(book_list.get(i)[abookCategoryPos], 20);
            standard_length_print(book_list.get(i)[3], 15); //status
            System.out.print("\n");
            findIt = true;
          }
        }
      }
      if (findIt == false) {
        System.out.println("Sorry, not found!");
      }

    } else if (idtId == 2) {                           //admin key word search
      System.out.println();
      up_display(idtId);
      for (int i = 0; i < bookListSize; i++) { //go through each book
        tempBookName = book_list.get(i)[0].split(" ");
        for (int j = 0; j < tempBookName.length; j++) { //go through and compare each word
          if (tempBookName[j].toLowerCase().equals(inputCategory.toLowerCase())) {
            standard_length_print(book_list.get(i)[abookNamePos], 30);
            standard_length_print(book_list.get(i)[1], 10); //id
            standard_length_print(book_list.get(i)[abookCategoryPos], 20);
            standard_length_print(book_list.get(i)[3], 15); //status
            standard_length_print(book_list.get(i)[4], 15); //entry date
            standard_length_print(book_list.get(i)[5], 15); //last borrow
            standard_length_print(book_list.get(i)[6], 15); //due date
            standard_length_print(book_list.get(i)[7], 15); //borrower name
            System.out.print("\n");
            findIt = true;
          }
        }
      }
      if (findIt == false) {
        System.out.println("Sorry, not found!");
      }
    } else {
      System.out.println("Please check your input!");
    }

  }

  //Student name: Yuan Guo
  //The class is written in 2023
  //print something in a modifiable length. Spaces are filled at right.
  public static void standard_length_print(String thing, int length) {
    String toPrint;
    String str;
    str = "%-" + length + "s";
    toPrint = String.format(str, thing);
    System.out.print(toPrint);
  }

  //Student name: Yuan Guo
  //The class is written in 2023
  //It just displays "name""id".....in a standard length before books are listed.
  public static void up_display(int idtId) {
    standard_length_print("Name", 30);
    standard_length_print("ID", 10); //id
    standard_length_print("Category", 20);
    standard_length_print("Status", 15); //status
    if (idtId == 2) {
      standard_length_print("Entry Date", 15); //entry date
      standard_length_print("Last Borrow", 15); //last borrow
      standard_length_print("Due Date", 15); //due date
      standard_length_print("borrower name", 15); //borrower name
    }
    System.out.print("\n");
  }

  //Student name: Yixin Niu
  //The class is written in 2023
  //this part can ask administrators to input the
  // information of the book and add it into the book_list.
  //and all data input is the String.
  public static void add_item_intolist() {
    System.out.println("Please input the name of the book.");
    scan.nextLine();
    String name = scan.nextLine();    // input the book name.
    System.out.println("Please input the id of the book.");
    String bookId = scan.nextLine();  // input the id of the book
    System.out.println("Please input the category of the book.");
    String category = scan.nextLine();   // input the category of the book.
    System.out.println("Please input the Entry time of the book.");
    String admissionTime = scan.nextLine();   // input the Entry time of the book.
    String[] inputNew = {name, bookId, category,
                         "Available", admissionTime, "None", "None", "None"};
    book_list.add(inputNew);  // add the information above to the book_list.
  }

  //Student name: Yixin Niu
  //The class is written in 2023
  //This part can delete the book relevant information
  // from the book_list according to the book id users input.
  //The data input and the data in book_list are all string.
  public static void delete_item_intolist() {
    System.out.println("Please input the ID of the book you want to delete.");
    scan.nextLine();
    String id = scan.nextLine();  // input the id of the book you want to delete.
    int num = 0;
    for (String[] array : book_list) {
      String val = array[1];
      if (!val.equals(id)) {
        num++;
      }
    }
    if (num == book_list.size()) {
      System.out.println("There's no book which fits in this ID.");
    } else {
      for (String[] array : book_list) {
        // check the id of the book in the book_list
        String val = array[1];
        // if the the book id is not what we search for,continue to check.
        if (!val.equals(id)) {
          continue;
        }
        book_list.remove(array);
        break;
      }
    }
  }

  //Student name: Xianghao Li
  //The class is written in 2023
  public static void lend_item() {
    int size = book_list.size();
    //the lend item function need booklist and its size
    //it will change the content of the booklist, no return
    get_lendinfo_change(book_list, size - 1);
  }

  //Student name: Xianghao Li
  //The class is written in 2023
  //the lend item function need booklist and its size
  //it will change the content of the booklist, no return
  public static void get_lendinfo_change(List<String[]> bl, int size) {
    System.out.println("Please enter the borrower's Name:");
    scan.nextLine();
    String borrowerName = new String();
    borrowerName = scan.nextLine();
    System.out.println("Please enter borrowing date:");
    String borrowDate = new String();
    borrowDate = scan.nextLine();
    System.out.println("Please enter bookID:");
    String bookId = scan.nextLine();
    System.out.println("Please enter return date(50 days for borrowing):");
    String returnDate = scan.nextLine();
    //binary search need the booklist, the bookID and the list's size
    //return the book with the input bookID
    int bookToBeChanged = binarySearch(bl, bookId, size);
    //change the book's info
    bl.get(bookToBeChanged)[3] = "Undue";
    bl.get(bookToBeChanged)[5] = borrowDate;
    bl.get(bookToBeChanged)[6] = returnDate;
    bl.get(bookToBeChanged)[7] = borrowerName;
  }

  //Student name: Xianghao Li
  //The class is written in 2023
  //using binary search, returning the book with the input bookID
  public static int binarySearch(List<String[]> bl, String bookId, int size) {
    int high = size;
    int low = 0;
    int mid;
    while (low <= high) {
      mid = (low + high) / 2;
      if ((bookId.compareTo(bl.get(mid)[1])) > 0) {
        low = mid + 1;
      } else if ((bookId.compareTo(bl.get(mid)[1])) < 0) {
        high = mid - 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  //Student name: Xianghao Li
  //The class is written in 2023
  public static void return_item() {
    int size = book_list.size();
    get_returninfo_change(book_list, size - 1);
  }

  //Student name: Xianghao Li
  //The class is written in 2023
  public static void get_returninfo_change(List<String[]> bl, int size) {
    System.out.println("Please enter bookID:");
    scan.nextLine();
    String bookId = scan.nextLine();
    int bookToBeChanged = binarySearch(bl, bookId, size);
    bl.get(bookToBeChanged)[3] = "Available";
    bl.get(bookToBeChanged)[6] = "None";
    bl.get(bookToBeChanged)[7] = "None";
  }

  //Student name: Xianghao Li
  //The class is written in 2023
  public static void sort_by_bookid() {
    int sizeOfBooklist = book_list.size();
    //use the quicksork algorithm we realize by our own
    quickSort(book_list, 0, sizeOfBooklist - 1);
  }

  //Student name: Xianghao Li
  //The class is written in 2023
  public static void quickSort(List<String[]> bl, int left, int right) {
    int i;
    if (right <= left) {
      return;
    }
    i = partition(bl, left, right);
    if ((i - left) > (right - i)) {
      quickSort(bl, i + 1, right);
      quickSort(bl, left, i - 1);
    } else {
      quickSort(bl, left, i - 1);
      quickSort(bl, i + 1, right);
    }
  }

  //Student name: Xianghao Li
  //The class is written in 2023
  public static int partition(List<String[]> bl, int left, int right) {
    String temp = bl.get(left)[1];
    String a = new String();
    a = bl.get(left)[0];
    String b = new String();
    b = bl.get(left)[2];
    String c = new String();
    c = bl.get(left)[3];
    String d = new String();
    d = bl.get(left)[4];
    String e = new String();
    e = bl.get(left)[5];
    String f = new String();
    f = bl.get(left)[6];
    String g = new String();
    g = bl.get(left)[7];
    int p = left;
    for (int i = left + 1; i <= right; i++) {
      if (temp.compareTo(bl.get(i)[1]) > 0) {
        p++;
        if (p != i) {
          Collections.swap(bl, p, i);
        }
      }
    }
    for (int j = 0; j < 8; j++) {
      bl.get(left)[j] = bl.get(p)[j];
    }

    bl.get(p)[1] = temp;
    bl.get(p)[0] = a;
    bl.get(p)[2] = b;
    bl.get(p)[3] = c;
    bl.get(p)[4] = d;
    bl.get(p)[5] = e;
    bl.get(p)[6] = f;
    bl.get(p)[7] = g;

    return p;

  }

  //Student name: Haoru Wang
  //The class is written in 2023
  public static void update_time_status(String todayDate) {
    // We assume that the loan period is 50 days

    // Formatted date
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
    try {
      Date nowDate = format.parse(todayDate);
      long nowTime = nowDate.getTime();
      for (String[] book : book_list) {
        if (!book[6].equals("None")) {
          long time = format.parse(book[6]).getTime();
          if (nowTime >= time) {
            book[3] = "Due";
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  //Student name: Han Zhang
  //The class is written in 2023
  public static void main(String[] args) {
    //Initialization bookbase into book_list in the external excel file
    // (xls) updated at 2023.01.17. The book info is made up by our own.
    load_file_into_booklist();

    sort_by_bookid();
    start_page();
    identity_id = get_identity();
    if (identity_id == 1) {
      user_menu();
      user_option_id = get_user_option();
      while (user_otherneed_id == -1 || user_otherneed_id != 3) {
        if (user_option_id == 1) {
          user_booklist_menu();
          user_booklistnum_id = get_user_booklistnum();
          if (user_booklistnum_id == 1) {
            show_whole_booklist(identity_id);
            user_theother_menu();
            user_otherneed_id = get_user_otherneed_id();
            if (user_otherneed_id != 3) {
              user_option_id = user_otherneed_id;
            }
          } else if (user_booklistnum_id == 2) {
            user_input_category = category_menu_and_input();
            compare_and_output_booklist(identity_id, user_option_id, user_input_category);
            user_theother_menu();
            user_otherneed_id = get_user_otherneed_id();
            if (user_otherneed_id != 3) {
              user_option_id = user_otherneed_id;
            }
          }
        } else if (user_option_id == 2) {
          user_input_keyword = keyword_menu_and_input();
          compare_and_output_booklist(identity_id, user_option_id, user_input_keyword);
          user_theother_menu();
          user_otherneed_id = get_user_otherneed_id();
          if (user_otherneed_id != 3) {
            user_option_id = user_otherneed_id;
          }
        }
      }
      quit_menu();
    } else if (identity_id == 2) {
      password_menu();
      admini_password = get_password();
      while (!(admini_password.equals(right_password)) && safenum != 1) {
        safenum--;
        wrong_password_menu();
        admini_password = get_password();
      }
      if (safenum != 1) {
        date_menu();
        today_date = get_today_date();
        update_time_status(today_date);
        administrator_menu();
        admini_option_id = get_admini_option();
        while (admini_otherneed_id == -1 || admini_otherneed_id != 5) {
          if (admini_option_id == 1) {
            //show all the info of booklist
            show_whole_booklist(identity_id);
            admini_other_menu();
            admini_otherneed_id = get_admini_otherneed_id();
            if (admini_otherneed_id != 5) {
              admini_option_id = admini_otherneed_id;
            }
          } else if (admini_option_id == 2) {
            admini_input_keyword = keyword_menu_and_input();
            compare_and_output_booklist(identity_id, admini_option_id, admini_input_keyword);
            admini_other_menu();
            admini_otherneed_id = get_admini_otherneed_id();
            if (admini_otherneed_id != 5) {
              admini_option_id = admini_otherneed_id;
            }
          } else if (admini_option_id == 3) {
            change_item_menu();
            admini_change_item_id = get_admini_change_item_id();
            if (admini_change_item_id == 1) {
              add_item_intolist();
              sort_by_bookid();
            } else if (admini_change_item_id == 2) {
              delete_item_intolist();
            }
            admini_other_menu();
            admini_otherneed_id = get_admini_otherneed_id();
            if (admini_otherneed_id != 5) {
              admini_option_id = admini_otherneed_id;
            }
          } else if (admini_option_id == 4) {
            lend_return_item_menu();
            admini_lend_return_item_id = get_admini_lend_return_item_id();
            if (admini_lend_return_item_id == 1) {
              lend_item();
              System.out.println("Changing succeed!");
            } else if (admini_lend_return_item_id == 2) {
              return_item();
              System.out.println("Changing succeed!");
            }
            admini_other_menu();
            admini_otherneed_id = get_admini_otherneed_id();
            if (admini_otherneed_id != 5) {
              admini_option_id = admini_otherneed_id;
            }
          }
        }
        quit_menu();
      } else {
        System.out.println("You input wrong password for 5 time"
                + "s, you are not allowed to go into this system!");
      }

    }
  }

}

