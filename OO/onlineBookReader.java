//7.5 Online Book Reader: Design the data structures for an online book reader system.
//user membership/search/read/one active user at a time/one book at a time by this user

public class OnlineReaderSystem {
	private Library library;
	private UserManger userManager;
	private Display display;

	private Book activeBook;
	private User activeUser;

	public OnlineReaderSystem() {
		userManager = new UserManger();
		library = new Library();
		display = new Display();
	}

	public Library getLibrary() {return library;}
	public UserManger getUserManager() {return userManager;}
	public Display getDisplay() {return display;}
	public Book getActiveBook() {return activeBook;}
	public void setActiveBook(Book book) {
		activeBook = book;
		display.displayBook(book);
	}
	public User getActiveUser() {return activeUser;}
	public void setActiveUser(User user) {
		activeUser = user;
		display.displayUser(user);
	}
}

public class Library {
	private HashMap<Integer, Book> books;

	public Book addBook(int id, String details) {
		if (books.containsKey(id)) {
			return null;
		}
		Book book = new Book(id, details);
		books.put(id, book);
		return book;
	}
	public boolean remove(Book b) {
		return remove(b.getID());
	}
	public boolean remove(int id) {
		if (!books.containsKey(id)) return false;
		books.remove(id);
		return true;
	}

	public Book find(int id) {
		return books.get(id);
	}
}

public class UserManger {
	private HashMap<Integer, User> users;

	public User addUser(int id, String details, int accountType) {
		if (users.containsKey(id)) return null;
		User user = new User(id, details, accountType);
		users.put(id, user);
		return user;
	}

	public boolean remove(User u) {
		return remove(u.getID());
	}

	public boolean remove(int id) {
		if (!users.containsKey(id)) return false;
		users.remove(id);
		return true;
	}
}


public class Display {
	private Book activeBook;
	private User activeUser;
	private int pageNumber = 0;

	public void displayUser(User user) {
		activeUser = user;
		refreshUsername();
	}

	public void displayBook(Book book) {
		pageNumber = 0;
		activeBook = book;
		refreshTitle();
		refreshDetails();
		refreshPage();
	}

	public void turnPageForward() {
		pageNumber++;
		refreshPage();
	}

	public void turnPageBackward() {
		pageNumber--;
		refreshPage();
	}

	public void refreshUsername() {...}
	public void refreshTitle() {...}
	public void refreshDetails() {...}
	public void refreshPage() {...}
}

public class Book {
	private int bookId;
	private String details;

	public Book(int id, String det) {
		bookId = id;
		details = det;
	}

	public int getID() {return bookId;}
	public void setId() {bookId = id;}
	public String getDetails() {return details;}
	public void setDetails(String d) {details = d;}
}

public class User {
	private int userId;
	private String details;
	private int accountType;

	public void renewMembership() {}

	public User(int id, String det, int accountType) {
		userId = id;
		details = det;
		this.accountType = accountType;
	}

	public int getID() {return userId;}
	public void setID(int id) {userId = id;}
	public String getDetails() {return details;}
	public void setDetails(String details) {this.details = details;}

	public int getAccountType() {return accountType;}
	public int setAccountType(int t) {accountType = t;}
}



