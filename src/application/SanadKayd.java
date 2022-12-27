package application;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class SanadKayd extends Inputs{
	private Accounts account;
	private String Cnum;
	Scanner fileScan ;
	
	
	public SanadKayd(long sanNum , String coinName  , String date , Accounts account) throws Exception{
		if(!this.sanadsFile.exists()) {
			try {
				this.sanadsFile.createNewFile();
			}
			catch(Exception e) {
				System.out.println("Sanad File Not Created ");
			}
		}
		this.setSanadNumber(sanNum);
		this.setCoinName(coinName);
		this.setDate(date);;
		this.account = account;
	}

	@Override
	public boolean saveSanad() throws Exception {
		String ok = "";
		try {
			fileScan = new Scanner(this.sanadsFile);
			while(fileScan.hasNextLine()) {
			ok+=fileScan.nextLine()+"\n";	
			
			}
		}
		catch(Exception e) {
			throw new Exception("File not Found");
		}
		
		try(PrintWriter p = new PrintWriter(this.sanadsFile)){
			if(ok != "") {
				p.append(ok+this.toString()+"\n");	
			}
			else {
				p.append(this.toString()+"\n");
			}
			return true;
		}
		
		
	}
	

	@Override
	public boolean tarheelSanad() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean printSanad() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tarAndPrintSanad() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		return "Kayd,"+this.getSanadNumber()+","+this.getDate()+","+this.getCoinName()+","
				+","+this.account.toString()+","+this.Cnum;
	}

	public String getCnum() {
		return Cnum;
	}

	public void setCnum(String cnum) {
		Cnum = cnum;
	}

}
