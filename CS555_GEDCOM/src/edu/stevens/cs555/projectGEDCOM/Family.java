package edu.stevens.cs555.projectGEDCOM;

public class Family implements Comparable{
	private String ID;
	private String Marr;
	private String Husb;
	private String Wife;
	private String Chil;
	private String Div;
	//private String Date; //BIRT, DEAT , DIV, or MARR
	/**
	 * @return the marr
	 */
	public String getMarr() {
		return Marr;
	}
	/**
	 * @param marr the marr to set
	 */
	public void setMarr(String marr) {
		Marr = marr;
	}
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}
	/**
	 * @return the husb
	 */
	public String getHusb() {
		return Husb;
	}
	/**
	 * @param husb the husb to set
	 */
	public void setHusb(String husb) {
		Husb = husb;
	}
	/**
	 * @return the wife
	 */
	public String getWife() {
		return Wife;
	}
	/**
	 * @param wife the wife to set
	 */
	public void setWife(String wife) {
		Wife = wife;
	}
	/**
	 * @return the chil
	 */
	public String getChil() {
		return Chil;
	}
	/**
	 * @param chil the chil to set
	 */
	public void setChil(String chil) {
		Chil = chil;
	}
	/**
	 * @return the div
	 */
	public String getDiv() {
		return Div;
	}
	/**
	 * @param div the div to set
	 */
	public void setDiv(String div) {
		Div = div;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return this.ID.compareTo(((Family)arg0).getID());
	}

}
