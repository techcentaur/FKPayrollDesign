import java.time.LocalDate;

/**
record: of employee data
*/
public class EmployeeRecord {
	public int id;
	private String name;

	public PaymentType ptype;
	public EmployeeType etype;

	public double hourlyRate;
	public double duePayment;
	public double monthlySalary;
	public double commissionRate;
	public double commissionOnSales;

	public double unionDueRate;
	public double serviceCharges;

	public LocalDate lastPaidDate;
	public LocalDate lastPaidCommission;

	public EmployeeRecord(int id, String name, PaymentType ptype, EmployeeType etype, double hourlyRate, double duePayment, double monthlySalary, double commissionOnSales, double commissionRate, LocalDate lastPaidDate, LocalDate lastPaidCommission) {
		this.id = id;
		this.name = name;
		this.ptype = ptype;
		this.etype = etype;

		this.hourlyRate = hourlyRate;
		this.duePayment = duePayment;
		this.monthlySalary = monthlySalary;
		this.commissionRate = commissionRate;
		this.commissionOnSales = commissionOnSales;

		this.unionDueRate = 0.0;
		this.serviceCharges = 0.0;

		this.lastPaidDate = lastPaidDate;
		this.lastPaidCommission = lastPaidCommission;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String newName) {
		this.name = newName;
	}
}