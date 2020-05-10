import java.time.LocalDate;
import java.util.Hashtable;
import java.time.DayOfWeek;

/**
main Manager class 
manages all operations on employees
*/
public class Manager implements ManagerInterface{
	public static int employeeIdDistributor=0;
	private Hashtable<Integer, EmployeeRecord> dataBase;

	public Manager(){
		this.dataBase = new Hashtable<Integer, EmployeeRecord>();
	}

	/* add new employee public function*/
	public int addNewEmployee(String name, double rateOrSalary, double commissionRate, PaymentType paymentType, EmployeeType etype){
		int empId = this.employeeIdDistributor;
		this.employeeIdDistributor += 1;

		if(etype == EmployeeType.FLAT_SALARY){
			addNewFlatSalaryEmployee(empId, name, rateOrSalary, commissionRate, paymentType);
		}else if(etype == EmployeeType.HOURLY_RATE){
			addNewHourlyRateEmployee(empId, name, rateOrSalary, commissionRate, paymentType);
		}

		return empId;
	}
	/* private functions for individual employee-type | for clean intitalisation*/
	private void addNewFlatSalaryEmployee(int id, String name, double monthlySalary, double commissionRate, PaymentType paymentType){
		EmployeeRecord rec = new EmployeeRecord(id, name, paymentType, EmployeeType.FLAT_SALARY,
							0.0, 0.0,
							monthlySalary, 0.0, commissionRate,
							Date.getLastFriday(LocalDate.now()),
							Date.getLastFriday(LocalDate.now()));
		this.dataBase.put(id, rec);
	}
	private void addNewHourlyRateEmployee(int id, String name, double hourlyRate, double commissionRate, PaymentType paymentType){
		EmployeeRecord rec = new EmployeeRecord(id, name, paymentType, EmployeeType.HOURLY_RATE,
							hourlyRate, 0.0,
							0.0, 0.0, commissionRate,
							Date.getLastFriday(LocalDate.now()),
							Date.getLastFriday(LocalDate.now()));
		this.dataBase.put(id, rec);
	}

	public boolean deleteEmployee(int empId){
		try {
			this.dataBase.remove(empId);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	public void addTimeCard(int empId, LocalDate date, double hours){
		EmployeeRecord record = this.dataBase.get(empId);

		if(hours > 8.0){
			record.duePayment += 8.0 * record.hourlyRate;
			record.duePayment += (hours - 8.0) *  1.5 * record.hourlyRate;
		}else{
			record.duePayment += hours * record.hourlyRate; 
		}
	}

	public void postSalesReceipt(int empId, LocalDate date, double amountOfSales){
		EmployeeRecord record = this.dataBase.get(empId);
		record.commissionOnSales += (amountOfSales * record.commissionRate);
	}

	public void submitUnionCharges(int empId, double sCharge){		
		EmployeeRecord record = this.dataBase.get(empId);
		record.serviceCharges += sCharge;
	}

	public void changeEmployeeHourlyRate(int empId, double hourlyRate){
		EmployeeRecord record = this.dataBase.get(empId);
		record.hourlyRate = hourlyRate;
	}
	public void changeEmployeeDuesRate(int empId, double duesRate){
		EmployeeRecord record = this.dataBase.get(empId);
		record.unionDueRate = duesRate;
	}

	public void getPaid(int empId, LocalDate date){
		EmployeeRecord record = this.dataBase.get(empId);

		double totalPaymentDue = 0.0;
		if(record.etype == EmployeeType.HOURLY_RATE){
			if(date.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
				if(record.lastPaidCommission.equals(Date.getLastToLastFriday(date))){
					totalPaymentDue += record.commissionOnSales * record.commissionRate;

					record.commissionOnSales = 0.0;
					record.lastPaidCommission = date;
				}

				totalPaymentDue += record.duePayment;
				record.duePayment = 0.0;
				record.lastPaidDate = date;

				totalPaymentDue -= (record.unionDueRate + record.serviceCharges);
				record.serviceCharges = 0.0;

				PaymentMethod.makePayment(empId, record.ptype, totalPaymentDue);
			}
		}else if(record.etype == EmployeeType.FLAT_SALARY){
			if(date.compareTo(Date.lastWorkingDayOfMonth(date)) == 0){
				totalPaymentDue += record.monthlySalary;
				totalPaymentDue -= (4 * record.unionDueRate + record.serviceCharges);
				// unionDueRate are weekly, we have to deduct 4 times it, since this is monthly payment

				record.serviceCharges = 0.0;
			}
			if(date.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
				if(record.lastPaidCommission.compareTo(Date.getLastToLastFriday(date)) == 0){
					totalPaymentDue += record.commissionOnSales * record.commissionRate;

					record.commissionOnSales = 0.0;
					record.lastPaidCommission = date;
				}
			}
			if(totalPaymentDue != 0.0){
				PaymentMethod.makePayment(empId, record.ptype, totalPaymentDue);
			}
		}
	}
}