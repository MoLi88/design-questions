// 7.2 Call Center: Imagine you have a call center with three levels of employees: respondent, manager and director. An imcoming telephone call must be first allocated to respondent who is free. If the respondent can't handle the call, he or she must escalate the call to a manager. If the manager is not free or not able to handle it, then the call should be escalated to a director. Design the classes and data structures for this problem. Implement a method dispatchCall() which assigns a call to the first available employee.

public class callHandler {
	private final int LEVELS = 3;

	private final int NUM_RESPONDENTS = 10;
	private final int NUM_MANAGERS = 4;
	private final int NUM_DIRECTORS = 2;

	List<List<Employee>> employeeLevels;
	List<List<Call>> callQueues;

	public CallHandler() {
		// initialize handler with employess and queue
	}

	public Employee getHandlerForCall(Call call) {
		//get first employee who can handle this call
	}

	public void dispatchCall(Caller caller) {
		Call call = new Call(caller);
		dispatchCall(call);
	}

	public void dispatchCall(Call call) {
		Employee emp = getHandlerForCall(call);
		if (emp != null) {
			emp.receiveCall(call);
			call.setHander(emp);
		} else {
			// place the call into corresponding call queue
			call.reply("Please wait for the free employee to reply");
			callQueues.get(call.getRank().getValue()).add(call);
		}
	}

	public boolean assginCall(Employee emp) {
		//return true if we assgined a call to a free employee
	}

}

public class Call {
	private Rank rank; //min rank of employees could handle this
	private Caller caller;
	private Employee handler;
	public Call(Caller c) {
		rank = Rank.Respondent;
		caller = c;
	}

	public setHander(Employee e) {handler = e};
	public void reply(String message) {...}
	public getRank() { return rank;}
	public setRank(Rank r) {rank = r;}
	public Rank incrementRank() {...}
	public void disconnect() {...}
}

abstract class Employee {
	private Call currentCall = null;
	protected Rank rank;

	public Employee(CallHandler handler) {...}

	public void receiveCall(Call call) {not available...}

	public void callCompleted(Call call) {available...}

	public void escalateAndReassgin() {...}

	public boolean assginNewCall() {...}

	public boolean ifFree() {return currentCall == null}

	public Rank getRank() {return rank}

}

class Director extends Employee {
	public Director() {
		rank = Rank.Director;
	}
}


class Manager extends Employee {
	public Director() {
		rank = Rank.Manager;
	}
}

class Respondent extends Employee {
	public Director() {
		rank = Rank.Respondent;
	}
}
