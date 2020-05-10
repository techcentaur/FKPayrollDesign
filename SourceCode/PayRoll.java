import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
TEST: This a file for testing the code
*/
public class PayRoll{
	public static void main(String[] args){
		Manager manager = new Manager();
		
		int empId1 = manager.addNewEmployee("Ankit", 30.0,  0.05, PaymentType.BANK, EmployeeType.HOURLY_RATE);
		int empId2 = manager.addNewEmployee("Bunty", 3000.0,  0.50, PaymentType.POSTAL_ADDRESS, EmployeeType.FLAT_SALARY);
		int empId3 = manager.addNewEmployee("Chabhit", 20.0,  0.20, PaymentType.PAYMASTER, EmployeeType.HOURLY_RATE);

		List<Integer> empls = new ArrayList<Integer>();
		empls.add(empId1);
		empls.add(empId2);
		empls.add(empId3);

		Union un = new Union(11.0);
		un.addNewUnionMember(empId1, manager);

		LocalDate today = LocalDate.now();
		for(int i=0; i<30; i++){
			LocalDate day = today.plusDays(i);
			System.out.println("[!]  Date: " + day +" - " + day.getDayOfWeek());
			
			for(int id: empls){
				manager.addTimeCard(id, day, 9.0);
				manager.postSalesReceipt(id, day, 10);
				manager.getPaid(id, day);
			}
			un.postServiceChargesAllMembers(0.007, manager);
		}

		un.removeMemberFromUnion(empId1, manager);
		manager.deleteEmployee(empId1);
		
		empls.remove(empId1);
		manager.changeEmployeeHourlyRate(empId3, 30.0);

		System.out.println("\n[?] Deleted employee!\n");

		for(int i=0; i<30; i++){
			LocalDate day = today.plusDays(i);
			System.out.println("[!]  Date: " + day +" - " + day.getDayOfWeek());
			
			for(int id: empls){
				manager.addTimeCard(id, day, 9.0);
				manager.postSalesReceipt(id, day, 10);
				manager.getPaid(id, day);
			}
			un.postServiceChargesAllMembers(0.007, manager);
		}
	}
}