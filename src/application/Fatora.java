package application;

public class Fatora{
	private String fatoraNumber;
	private String fatoraDate;
	private double shamel;
	private double notShamel;
	private double vat;
	
	
	public Fatora(String fatoraNumber, String fatoraDate, double shamel, double notShamel, double vat) {
		this.fatoraNumber = fatoraNumber;
		this.fatoraDate = fatoraDate;
		this.shamel = shamel;
		this.notShamel = notShamel;
		this.vat = vat;
	}


	public String getFatoraNumber() {
		return fatoraNumber;
	}


	public void setFatoraNumber(String fatoraNumber) {
		this.fatoraNumber = fatoraNumber;
	}


	public String getFatoraDate() {
		return fatoraDate;
	}


	public void setFatoraDate(String fatoraDate) {
		this.fatoraDate = fatoraDate;
	}


	public double getShamel() {
		return shamel;
	}


	public void setShamel(double shamel) {
		this.shamel = shamel;
	}


	public double getNotShamel() {
		return notShamel;
	}


	public void setNotShamel(double notShamel) {
		this.notShamel = notShamel;
	}


	public double getVat() {
		return vat;
	}
}
