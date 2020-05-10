import java.util.List;
import java.util.ArrayList;

/**
UNION:
	union works independently charging their members
	but moderated by manager object for security and reliableness
Constructor:
	@params: List of employeeIds, default-due-rate, manager object
*/

public class Union implements UnionInterface{
	public List<Integer> members;
	public double defaultDueRate;

	/*constructors*/
	public Union(){
		this.members = new ArrayList<Integer>();
		this.defaultDueRate = 0.0;
	}
	public Union(double dueRate){
		this.defaultDueRate = dueRate;
		this.members = new ArrayList<Integer>();
	}
	public Union(List<Integer> newList, double dueRate, Manager manager){
		this.defaultDueRate = dueRate;

		for(int i: newList){
			this.members.add(i);
			manager.changeEmployeeDuesRate(i, this.defaultDueRate);
		}
	}

	/* add and remove members*/
	public void removeMemberFromUnion(int i, Manager manager){
		manager.changeEmployeeDuesRate(i, 0.0);
		this.members.remove(i);
	}
	public void addNewUnionMember(int i, Manager manager){
		this.members.add(i);
		manager.changeEmployeeDuesRate(i, this.defaultDueRate);
	}

	/* posting service charges on employees */
	public void postServiceCharges(int empId, double serviceCharge, Manager manager){
		manager.submitUnionCharges(empId, serviceCharge);
	}
	public void postServiceChargesAllMembers(double serviceCharge, Manager manager){
		for(int i: this.members){
			postServiceCharges(i, serviceCharge, manager);
		}
	}

}