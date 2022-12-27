package application;

import java.io.*;
import java.util.*;

public class SanadKabd extends Inputs{
	private Scanner fileScan ;
	private Accounts account;
	
	public SanadKabd(long sanNum , String coinName , long fatoraNum  , String date , Accounts account) throws Exception{
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
	public boolean saveSanad() throws Exception{
		String ok = "";
		
		try {
			fileScan = new Scanner(sanadsFile);
			while(fileScan.hasNextLine()) {
				String ana = "";
			ana=fileScan.nextLine()+"\n";
			String[] s = ana.split(" ");
		    ok+=ana;
			
			
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
	public boolean tarheelSanad() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean printSanad() {
		// print on painter 
		return false;
	}

	@Override
	public boolean tarAndPrintSanad() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		return "Kabd,"+this.getSanadNumber()+","+this.getDate()+","+this.getCoinName()+","
				+this.getFatoraNumber()+","+this.account.toString();
	}
	

}
