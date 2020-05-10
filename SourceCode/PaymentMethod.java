interface PaymentMethodsInterface{
	/**
	for abstract methods: functions for transactions between various
	monetary institutions
	*/
}

public class PaymentMethod implements PaymentMethodsInterface{
	public static void makePayment(int empid, PaymentType type, double amount){
		if(type.equals(PaymentType.POSTAL_ADDRESS)){
			System.out.println("\t[*] ID: " + empid + ", Amount: " + amount + ", Type: postal-address!");
		}else if(type.equals(PaymentType.PAYMASTER)){
			System.out.println("\t[*] ID: " + empid + ", Amount: " + amount + ", Type: held at paymaster!");
		}else if(type.equals(PaymentType.BANK)){
			System.out.println("\t[*] ID: " + empid + ", Amount: " + amount + ", Type: paid to bank!");
		}else{
			System.out.println("[?] Not Allowed!");
		}
	}

}