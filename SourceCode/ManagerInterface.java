import java.time.LocalDate;

interface ManagerInterface{
	/* adds new employee in the company and returns unique employeeId */
	public int addNewEmployee(String name, double rateOrSalary, double commissionRate, PaymentType paymentType, EmployeeType etype);
	/* deletes employee with id @empId from company */
	public boolean deleteEmployee(int empId);

	/* submit time card for employee with hourly rates: id @ empId*/
	public void addTimeCard(int empId, LocalDate date, double hours);
	/* post sales reciept for commission */
	public void postSalesReceipt(int empId, LocalDate date, double amountOfSales);

	/* submit any kind of union service charge to employee with id @empId */
	public void submitUnionCharges(int empId, double sCharge);

	/* change employee details: hourly rate and dues rate*/
	public void changeEmployeeHourlyRate(int empId, double hourlyRate);
	public void changeEmployeeDuesRate(int empId, double duesRate);

	/* employee @empId gets paid if it's the correct day */
	public void getPaid(int empId, LocalDate date);
}