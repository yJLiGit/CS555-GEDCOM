package edu.stevens.cs555.projectGEDCOM;

public class Individual implements Comparable{
	private String ID;
	private String Name;
	private String Sex;
	private String Birt;
	private String Deat;
	private String Famc;//child
	private String Fams;
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return Sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		Sex = sex;
	}
	/**
	 * @return the birt
	 */
	public String getBirt() {
		return Birt;
	}
	/**
	 * @param birt the birt to set
	 */
	public void setBirt(String birt) {
		Birt = birt;
	}
	/**
	 * @return the deat
	 */
	public String getDeat() {
		return Deat;
	}
	/**
	 * @param deat the deat to set
	 */
	public void setDeat(String deat) {
		Deat = deat;
	}
	/**
	 * @return the famc
	 */
	public String getFamc() {
		return Famc;
	}
	/**
	 * @param famc the famc to set
	 */
	public void setFamc(String famc) {
		Famc = famc;
	}
	/**
	 * @return the fams
	 */
	public String getFams() {
		return Fams;
	}
	/**
	 * @param fams the fams to set
	 */
	public void setFams(String fams) {
		Fams = fams;
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
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return this.ID.compareTo(((Individual)arg0).getID());
	}
}
