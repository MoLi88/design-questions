//7.7 Chat Server: Explain how you would design a chat server. In particular, provide details about the various backend components, classes and methods. What would be the hardest problems to solve?

public class UserManager {
	private static UserManager instance;
	private HashMap<Integer, User> usersById;
	private HashMap<String,  User> UsersByAccountName;
	private HashMap<Integer, User> onlineUsers;

	public static UserManager getInstance() {
		if(instance == null) instance = new UserManager();
		return instance;
	}

	public void addUser(User fromUser, String toAccountName) {...}
	public void approveAddRequest(AddRequest req) {...} 
	public void rejectAddRequest(AddRequest req) {...}
	public void UserSignedOn(String accountName) {...}
	public void UserSignedOff(String accountName) {...}
}

public class User {
	private int id;
	private UserStatus staus = null;
	private HashMap<Integer, PrivateChat> PrivateChats;

	private ArrayList<GroupChat> groupChats;
	private HashMap<Integer, AddRequest> receivedAddRequests; //other person's id to request
	private HashMap<Integer, AddRequest> sentRequests;
	private HashMap<Integer, User> contacts;

	private String accountName;
	private String fullName;

	public User(int id, String accountName, String fullName) {...}
	public boolean sendMessegeToUser(User to, String content) {...}
	public boolean sendMessageToGroupChat(int id, String content) {...}
	public void setStatus(UserStatus status) {...}
	public boolean addContact(User user) {...}
	public void receivedAddRequests(AddRequest req) {...}
	public void sentAddRequest(AddRequest req) {...}
	public void removeAddRequest(AddRequest req) {...}
	public void requestAddUser(String accountName) {...}
	public void addConversation(GroupChat conversation) {...}
	public int getId() {...}
	public String getAccountName() {...}
	public String getFullName() {...}
}

public abstract class Conversation() {
	protected ArrayList<User> participants;
	protected int id;
	protected ArrayList<Message> messages;
	public ArrayList<Message> getMessages;
	public boolean addMessage(Message m) {...}
	public int getId() {...}
}

public class GroupChat extends Conversation {
	public void removeParticipant(User user) {}
	public void addParticipant(User user) {}
}

public class PrivateChat extends Conversation {
	public PrivateChat(User user1, User user2) {....}
	public User getOtherParticipant(User user) {...}
}

public class Message {
	private String content;
	private Date date;
	public Message{String content, Date date} {...}
	public String getContent()
	public Date getDate()
}

public class AddRequest {
	private User fromUser;
	private User toUser;
	private Date date;
	RequestStatus status;

	public AddRequest(User from, User to, Date date) {}
	public RequestStatus getStatus()
	public User getFromUser()
	public User getToUser()
	public Date getDate()
}


public class UserStatus {
	private String message;
	private UserStatusType type;
	public UserStatus(UserStatusType type, String message)
	public UserStatusType getStatusType()
	public String getMessage()
}

public enum UserStatusType {
	Offline, Away, Idle, Available, Busy
}


public enum RequestStatus {
	Unread, Read, Accepted, Rejected
}

