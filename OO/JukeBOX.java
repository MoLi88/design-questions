//7.3 Jukebox： Design a musical jukebox using OO principles
//Basic components: JukeBox, CD, Song, Artist, Playlist, Display
//Playlist creation(add, delete, shuffle), CD selector, song selector, queuing up a song, get next song from playlist
//User  add/delete/credit information

public class Jukebox {
	private CDPlayer cdPlayer;
	private User user;
	private Set<CD> cdCollection;
	private SongSelector ts;
	public Jukebox(CDPlayer cdPlayer, User user, Set<CD> cdCollection, SongSelector ts) {...}
	public Song getCurrentSong() {return rs.getCurrentSong();}
	public void setUser(User u) {this.user = u;}
}

public class CDPlayer {
	private Playlist p;
	private CD c;

	public CDPlayer(CD c, Playlist p) {...}
	public CDPlayer(Playlist p) {this.p = p;}
	public CDPlayer(CD c) {this.c = c;}

	public void playSong(Song s) {...}

	public Playlist getPlayList() {return p;}
	public void setPlayList() {return c;}

	public CD getCD() {return c;}
	public void setCD(CD c) {this.c = c;}
}

public class Playlist {
	private Song song;
	private Queue<Song> queue;

	public Playlist(Song song, Queue<Song> queue) {...}
	public Song getNextToPlay() {
		return queue.peek();
	}
	public void queueUpSong(Song s) {
		queue.add(s);
	}
}

public class CD {... }

public class Song {...}

public class User {
	private String name;
	private long ID;
	public String getName{return name;}
	public void setName(String name) {this.name = name;}
	public long getID() {return ID;}
	public void setID(long ID) {this.ID = ID;}
	public User(String name, long ID) {...}
	public User getUser() {return this;}
	public static User addUser(String name, long ID) {...}
}