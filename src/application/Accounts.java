package application;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Accounts {
	private long accountNumber;
	private String name;
	private double amount;
	private String note;
	private File accountsFile = new File("Accounts.txt");
	private static boolean flag = true;
	// array list <Accounts>    >> kashef >> Name >> amount 
	private static double[] coinVal;
	public Accounts() {
		
	}
	public Accounts(long accountNumber /*textField*/ , String name /* Text */, double amount, String note) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.amount = amount;
		this.note = note;
		
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getName() {
		return name;
		
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static double fromIlsToUsd(double ils) throws Exception {
		if(flag) {
			try {
				coinVal = Coins.readFromWeb();
			}
			catch(Exception e) {
				System.out.println("The coin cant read from web ");
			}
			flag = false ;
		}
		return ils / coinVal[0];
	}

	public static double fromIlsToJod(double ils) throws Exception {
		if(flag) {
			try {
				coinVal = Coins.readFromWeb();
			}
			catch(Exception e) {
				System.out.println("The coin cant read from web ");
			}
			flag = false ;
		}
		return ils / coinVal[1];
	}

	public static double fromUsdToIls(double usd) throws Exception {
		if(flag) {
			try {
				coinVal = Coins.readFromWeb();
			}
			catch(Exception e) {
				System.out.println("The coin cant read from web ");
			}
			flag = false ;
		}
		return usd * coinVal[0];
	}

	public static double fromUsdToJod(double usd) throws Exception {
		if(flag) {
			try {
				coinVal = Coins.readFromWeb();
			}
			catch(Exception e) {
				System.out.println("The coin cant read from web ");
			}
			flag = false ;
		}
		return usd / coinVal[2];
	}

	public static double fromJodToIls(double Jod) throws Exception {
		if(flag) {
			try {
				coinVal = Coins.readFromWeb();
			}
			catch(Exception e) {
				System.out.println("The coin cant read from web ");
			}
			flag = false ;
		}
		return Jod * coinVal[1];
	}

	public static double fromJodToUsd(double Jod) throws Exception {
		if(flag) {
			try {
				coinVal = Coins.readFromWeb();
			}
			catch(Exception e) {
				System.out.println("The coin cant read from web ");
			}
			flag = false ;
		}
		return Jod * coinVal[2];
	}

	public String toString() {
		return "" + this.accountNumber + "," + this.name+","+this.amount+","+this.note;
	}
	
	
}
