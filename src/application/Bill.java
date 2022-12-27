package application;

import java.util.ArrayList;

public class Bill  {
	private String billnum;
	private String date;
	private String shamel ;
	private String unshamel;
	private String vat;
	private String type;
	public Bill() {
		
	}
	
	
	public Bill(String billnum, String date, String shamel, String unshamel, String vat , String type) {
		super();
		this.billnum = billnum;
		this.date = date;
		this.shamel = shamel;
		this.unshamel = unshamel;
		this.vat = vat;
		this.type = type;
	}
	public String getBillnum() {
		return billnum;
	}
	public void setBillnum(String billnum) {
		this.billnum = billnum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getShamel() {
		return shamel;
	}
	public void setShamel(String shamel) {
		this.shamel = shamel;
	}
	public String getUnshamel() {
		return unshamel;
	}
	public void setUnshamel(String unshamel) {
		this.unshamel = unshamel;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String toStringa() { 
		return "( فاتورة المبيعات) رقم الفاتورة   =   "  + billnum + ", التاريخ  =  " + date + " , المبلغ شامل ضريبة القيمة المضافة  =  " + shamel + " ,  المبلغ غير شامل ضريبة القيمة المضافة  = " + unshamel
				+ " , ضريبة القيمه المضافة  =  " + vat ;
	}
	
	
	public String toString2() { 
		return "( Seals Bill)   Bill Number     =   "  + billnum + ", date   =   " + date + "  , Amount include VAT   =   " + shamel + " , Amount exclude VAT   =   " + unshamel
				+ " , VAT   =   " + vat ;
	}

	
	public String toString() {
		return  billnum + ":" + date + ":" + shamel +":"+ unshamel
				+ ":" + vat +":"+this.type;
	}
	

}
