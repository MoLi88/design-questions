//9.2 Social Network: How would you design the data structures for a very large social network like Facebook or LinkedIn? Describe how you would design an algorithm to show the shortest path between two people(e.g. Me -> Bob -> Susan -> Jason -> You).

/*
Step1: Simplify the Problem -- small user group
construct a graph and do BFS, bidirectional BFS
*/

LinkedList<Person> findPathBiBFS(HashMap<Integer, Person> people, int source, int dest) {
  BFSData sourceData = new BFSData(people.get(source));
  BFSData destData = new BFSData(people.get(dest));
  while (!sourceData.isfinished() && !destData.isfinished()) {
    Person collision = searchLevel(people, sourceData, destData);
    if (collision != null) {
      return mergePaths(sourceData, destData, collision.getID());
    }
    collision = searchLevel(people, destData, sourceData);
    if (collision != null) {
      return mergePaths(sourceData, destData, collision.getID());
    }
  }
}


Person searchLevel(HashMap<Integer, Person> people, BFSData primary, BFSData secondary) {
  int count = primary.toVisit.size();
  for (int i = 0; i < count; i++) {
    PathNode pathNode = primary.toVisit().poll();
    int personId = pathNode.getPerson().getID();

    if (secondary.visited.containsKey(personId)) {
      return pathNode.getPerson;
    }
    Person person = pathNode.getPerson();
    ArrayList<Integer> friends = person.getFriends();
    for (int friendId : friends) {
      if (!primary.visited.containsKey(friendId)) {
        Person friend = people.get(friendId);
        PathNode next = new PathNode(friend, pathNode);
        primary.visited.put(friendId, next);
        primary.toVisit.add(next);
      }
    }
  }
}

LinkedList<Person> mergePaths(BFSData bfs1, BFSData bfs2, int connection) {
  PathNode end1 = bfs1.visited.get(connection);
  PathNode end2 = bfs2.visited.get(connection);
  LinkedList<Person> pathOne = end1.collapse(false);
  LinkedList<Person> pathTwo = end2.collapse(true); //reverse
  pathTwo.removeFirst(); //remove connection
  pathOne.addAll(pathTwo);
  return pathOne;
}

class PathNode {
  private Person person = null;
  private PathNode previousNode = null;
  public PathNode(Person p, PathNode previous) {
    person = p;
    previousNode = previous;
  }

  public Person getPerson() {return person;}

  public LinkedList<Person> collapse(boolean startsWithRoot) {
    LinkedList<Person> path = new LinkedList<Person>();
    PathNode node = this;
    while (node != null) {
      if (startsWithRoot) {
        path.addLast(node.person);
      } else {
        path.addLast(node.person);
      }
      node = node.previousNode;
    }
    return path;
  }
}


class BFSData {
  public Queue<PathNode> toVisit = new LinkedList<PathNode>();
  public HashMap<Integer, PathNode> visited = new HashMap<Integer, PathNode>();

  public BFSData(Person root) {
    PathNode sourcePath = new PathNode(root, null);
    toVisit.add(sourcePath);
    visited.put(root.getID(), sourcePath);
  }

  public boolean isfinished() {
    return toVisit.isEmpty();
  }
}



/*
Step 2 Handle the millions of users
Person data not on same VirtualMachineError
1. for each friend id: int machine_index = getMachineIDForUser(personID);
2. Go to machine #machine_index
3. On that machine, do Person friend = getPersonWithID(person_id);
 */

class Server {
  HashMap<Integer, Machine> machines = new HashMap<Integer, Machine>();
  HashMap<Integer, Integer> personToMathineMap = new HashMap<Integer, Integer>();

  public Machine getMachineWithID(int machineID) {
    return machines.get(machineID);
  }

  public int getMachineIDForUser(int personID) {
    Integer machineID = personToMathineMap.get(personID);
    return machineID == null ? -1 : machineID;
  } 

  public Person getPersonWithID(int personID) {
    Integer machineID = personToMathineMap.get(personID);
    if (machineID == null) return null;

    Machine machine = getMachineWithID(machineID);
    if (machine == null) return null;
    return machine.getPersonWithID(personID);
  }
}


Class Person {
  private ArrayList<Integer> friends = new ArrayList<Integer>();
  private int personID;
  private String info;

  public Person(int id) {this.personID = id};
  public String getInfo() {return info;}
  public void setInfo(info) {this.info = info};
  public ArrayList<Integer> getFriends() {return friends};
  public int getID() {return personID;}
  public void addFriend(int id) {friends.add(id);}
}

