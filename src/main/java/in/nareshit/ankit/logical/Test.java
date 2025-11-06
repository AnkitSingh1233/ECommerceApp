package in.nareshit.ankit.logical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Bank {
	String accountHolder;
	long accountNumber;
	long balance;
	String password;
	List<String> li= new  ArrayList();
	

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getBlance() {
		return balance;
	}

	public void setBalance(long blance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void deposite(long amount) {
		if(amount>0) {
			balance=balance+amount;
			li.add("deposite "+balance);
		}
	}
	long withdraw;
	public void withdraw(long amount,String inputPassword) {
		
		if(!this.password.equals(inputPassword)) {
		  System.out.println("Password is incorrect");
		  return;
		}
		
		if(amount>0&&amount<balance) {
			withdraw=balance-amount;
			li.add("withdraw "+withdraw);
		}else {
			System.out.println("insuffecient Balance");
		}
		
		
	}
	
	public void showBalance(String inputPassword ) {
		if(!this.password.equals(inputPassword)) {
		System.out.println("Password is incorrectt");
	     return;
		}
		System.out.println(" Remaing Available Balance:::::::"+(balance-withdraw));
		
	}
	
	public void data() {
		for(String da:li) {
			System.out.println(da);
		}
	}
	

}

public class Test {

	public static void main(String[] args) {

		/*
		 * Bank b = new Bank(); b.setAccountHolder("ankit singh");
		 * b.setAccountNumber(12345); b.deposite(1000); b.setPassword("ankit@19");
		 * b.withdraw(800,"ankit@19"); b.data();
		 * 
		 * 
		 * b.showBalance("ankit@19"); System.out.println("Account Holder name::::" +
		 * b.getAccountHolder());
		 * System.out.println("Account number:::"+b.accountNumber);
		 */
		
		List<String> list=Arrays.asList();
		System.out.println(list.isEmpty());
	}
}
