package application;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Inputs {
	
	private long sanadNumber;
	private String coinName;
	private long fatoraNumber;
	private String status;
	private String date;
	private String note;
	public static File sanadsFile = new File("SanadFile.txt");
	
	public abstract boolean saveSanad() throws Exception;
	public abstract boolean tarheelSanad() throws Exception;
	public abstract boolean printSanad() throws Exception;
	public abstract boolean tarAndPrintSanad() throws Exception;
	
	public static void deletFromSaveFile(String accNumber) throws Exception{
		try {
			Scanner in = new Scanner(sanadsFile);
			String[] s;
			String ok="";
			while(in.hasNextLine()) {
				s=in.nextLine().split(" ");
				if(!accNumber.equalsIgnoreCase(s[1])) {
					ok+=in.nextLine()+"\n";
				}
			}
			try(PrintWriter p = new PrintWriter(sanadsFile)){
				p.append(ok);
			}
		}
		catch(Exception e ) {
			throw new Exception("Error in System.");
		}
	}
	
	public long getSanadNumber() {
		return sanadNumber;
	}

	public void setSanadNumber(long sanadNumber) {
		this.sanadNumber = sanadNumber;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public long getFatoraNumber() {
		return fatoraNumber;
	}

	public void setFatoraNumber(long fatoraNumber) {
		this.fatoraNumber = fatoraNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
