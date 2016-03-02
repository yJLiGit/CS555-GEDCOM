package edu.stevens.cs555.projectGEDCOM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Spring1_YijinLi {
	private ArrayList<Individual> individual = new ArrayList<Individual>();
	private ArrayList<Family> family = new ArrayList<Family>();
	private ArrayList<String> result  = new ArrayList<String>();
	private HashMap<String,Individual> inMap = new HashMap<String,Individual>();
	
	public ArrayList<String> getResult() {
		return result;
	}
	
	public Spring1_YijinLi(String Input) throws IOException {
		Project03 p3 = new Project03(Input);
		p3.informFilter();
		individual = p3.getIndividual();
		family = p3.getFamily();
		for (int i = 0; i < individual.size(); i++) {
			inMap.put(individual.get(i).getID(), individual.get(i));
		}
	}
	//US01	Dates before current date
	public void checkDatesBeforeCurrentDate(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DATE);		
		for (int i = 0; i < individual.size();i++) {
			//birth, 
			if (individual.get(i).getBirt() != null && individual.get(i).getBirt().split("\\s+").length > 2) {
				String b = individual.get(i).getBirt();
				//System.out.println(b);
				String[] dateInfo = b.split("\\s+");
				int y = Integer.parseInt(dateInfo[2]);
				int m = BaseMethod.monTranslation(dateInfo[1]);
				int d = Integer.parseInt(dateInfo[0]);
				String tmp = "Error US01: Birth date:" + individual.get(i).getBirt() +
				" of " + individual.get(i).getName() + " (" +individual.get(i).getID() + ") "
						+ "occurs after current date";
				if (y > year) {
					result.add(tmp);
				}
				else if (y == year) {
					if (m > month) {
						result.add(tmp);
					}
					else if (m == month) {
						if (d > day) {
							result.add(tmp);
						}
					}
				}
			}
			//death
			if (individual.get(i).getDeat() != null && individual.get(i).getBirt().split("\\s+").length > 2) {
				String b = individual.get(i).getDeat();
				String[] dateInfo = b.split("\\s+");
				int y = Integer.parseInt(dateInfo[2]);
				int m = BaseMethod.monTranslation(dateInfo[1]);
				int d = Integer.parseInt(dateInfo[0]);
				String tmp = "Error US01: death date:" + individual.get(i).getDeat() +
				" of " + individual.get(i).getName() + " (" +individual.get(i).getID() + ") "
						+ "occurs after current date";
				if (y > year) {
					result.add(tmp);
				}
				else if (y == year) {
					if (m > month) {
						result.add(tmp);
					}
					else if (m == month) {
						if (d > day) {
							result.add(tmp);
						}
					}
				}
			}
		}
		//family 
		for (int i = 0; i < family.size();i++) {
			//marriage 
			if (family.get(i).getMarr() != null && family.get(i).getMarr().split("\\s+").length > 2) {
				String b = family.get(i).getMarr();
				String[] dateInfo = b.split("\\s+");
				int y = Integer.parseInt(dateInfo[2]);
				int m = BaseMethod.monTranslation(dateInfo[1]);
				int d = Integer.parseInt(dateInfo[0]);
				String tmp = "Error US01: marry date:" + family.get(i).getMarr() +
				" for the wife: " + inMap.get(family.get(i).getWife()).getName() +
				" (" +family.get(i).getWife() + ") " + "and the husband : "
				+ inMap.get(family.get(i).getHusb()).getName() +
				" (" +family.get(i).getHusb() + ") " + " in family : " + 
				family.get(i).getID() + " occurs after current date";
				if (y > year) {
					result.add(tmp);
				}
				else if (y == year) {
					if (m > month) {
						result.add(tmp);
					}
					else if (m == month) {
						if (d > day) {
							result.add(tmp);
						}
					}
				}
			}
			//divorce
			if (family.get(i).getDiv() != null && family.get(i).getDiv().split("\\s+").length > 2) {
				String b = family.get(i).getDiv();
				String[] dateInfo = b.split("\\s+");
				int y = Integer.parseInt(dateInfo[2]);
				int m = BaseMethod.monTranslation(dateInfo[1]);
				int d = Integer.parseInt(dateInfo[0]);
				String tmp = "Error US01: divorce date:" + family.get(i).getDiv() +
				" for the wife: " + inMap.get(family.get(i).getWife()).getName() +
				" (" +family.get(i).getWife() + ") " + "and the husband : "
				+ inMap.get(family.get(i).getHusb()).getName() +
				" (" +family.get(i).getHusb() + ") " + " in family : " + 
				family.get(i).getID() + " occurs after current date";
				if (y > year) {
					result.add(tmp);
				}
				else if (y == year) {
					if (m > month) {
						result.add(tmp);
					}
					else if (m == month) {
						if (d > day) {
							result.add(tmp);
						}
					}
				}
			}
		}
	}
	//US02	Birth before marriage
	public void checkBirthBeforeMarriage(){
		for (int i = 0; i < family.size();i++) {
			//marriage
			if (family.get(i).getMarr() != null ) {
			String b = family.get(i).getMarr();
			String[] dateInfo = b.split("\\s+");
			Individual hus = inMap.get(family.get(i).getHusb());
			Individual wife = inMap.get(family.get(i).getWife());
			//Husband
			if (dateInfo.length > 2 && hus.getBirt() != null && hus.getBirt().split("\\s+").length > 2) {
				String[] husInfo = hus.getBirt().split("\\s+");

				int y = Integer.parseInt(husInfo[2]);
				int m = BaseMethod.monTranslation(husInfo[1]);
				int d = Integer.parseInt(husInfo[0]);
				int year = Integer.parseInt(dateInfo[2]);
				int month = BaseMethod.monTranslation(dateInfo[1]);
				int day = Integer.parseInt(dateInfo[0]);
				String tmp = "Error US02: birth date:" + hus.getBirt() + " of " 
					+ hus.getName() + " (" +hus.getID() + ") occurs after  his marriage date :" +
					family.get(i).getMarr();
				if (y > year) {
					result.add(tmp);
				}
				else if (y == year) {
					if (m > month) {
						result.add(tmp);
					}
					else if (m == month) {
						if (d > day) {
							result.add(tmp);
						}
					}
				}
			}
			//Wife
			if (dateInfo.length > 2 && wife.getBirt() != null && wife.getBirt().split("\\s+").length > 2) {
				String[] wifeInfo = wife.getBirt().split("\\s+");
				int y = Integer.parseInt(wifeInfo[2]);
				int m = BaseMethod.monTranslation(wifeInfo[1]);
				int d = Integer.parseInt(wifeInfo[0]);
				int year = Integer.parseInt(dateInfo[2]);
				int month = BaseMethod.monTranslation(dateInfo[1]);
				int day = Integer.parseInt(dateInfo[0]);
				String tmp = "Error US02: birth date:" + wife.getBirt() + " of " 
					+ wife.getName() + " (" +wife.getID() + ") occurs after her marriage date :" +
					family.get(i).getMarr();
				if (y > year) {
					result.add(tmp);
				}
				else if (y == year) {
					if (m > month) {
						result.add(tmp);
					}
					else if (m == month) {
						if (d > day) {
							result.add(tmp);
						}
					}
				}
			}
			}
		}
	}
}
