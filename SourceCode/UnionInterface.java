import java.util.List;
import java.util.ArrayList;

interface UnionInterface{
	/* add a new member to union (set its due rate)*/
	public void addNewUnionMember(int i, Manager manager);
	/* remove member from union (this would also make the due rates zero in employee params*/
	public void removeMemberFromUnion(int i, Manager manager);

	/* post a service charge on particular employee with id @empId and charge @serviceCharge*/
	public void postServiceCharges(int empId, double serviceCharge, Manager manager);
	/* post a service charge on all union employees and charge @serviceCharge*/
	public void postServiceChargesAllMembers(double serviceCharge, Manager manager);
}