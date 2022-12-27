package application;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class SanadSarf extends Inputs{
	private Accounts account;
	Scanner fileScan ;
	
	
	public SanadSarf(long sanNum , String coinName , long fatoraNum  , String date , Accounts account) throws Exception{
		if(!sanadsFile.exists()) {
			try {
				sanadsFile.createNewFile();
			}
			catch(Exception e) {
				System.out.println("Sanad File Not Created ");
			}
		}
		this.setSanadNumber(sanNum);
		this.setCoinName(coinName);
		this.setFatoraNumber(fatoraNum);
		this.setDate(date);;
		this.account = account;
	}
	@Override
	public boolean saveSanad() throws Exception {
		String ok = "";
		try {
			fileScan = new Scanner(sanadsFile);
			while(fileScan.hasNextLine()) {
			ok+=fileScan.nextLine()+"\n";
			String[] s = ok.split(" ");
			
			}
		}
		catch(Exception e) {
			throw new Exception("File not Found");
		}
		
		try(PrintWriter p = new PrintWriter(sanadsFile)){
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
		return "Sarf,"+this.getSanadNumber()+","+this.getDate()+","+this.getCoinName()+","
				+this.getFatoraNumber()+","+this.account.toString();
	}

}
