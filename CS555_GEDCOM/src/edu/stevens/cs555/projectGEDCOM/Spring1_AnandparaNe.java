package edu.stevens.cs555.projectGEDCOM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//Refer to Spring1_YijinLi.Java to create my user stories
public class Spring1_AnandparaNe {
	private ArrayList<Individual> individual = new ArrayList<Individual>();
	private ArrayList<Family> family = new ArrayList<Family>();
	private ArrayList<String> result  = new ArrayList<String>();
	private HashMap<String,Individual> inMap = new HashMap<String,Individual>();
	
	public ArrayList<String> getResult() {
		return result;
	}
	public Spring1_AnandparaNe(String Input) throws IOException {
		Project03 p3 = new Project03(Input);
		p3.informFilter();
		individual = p3.getIndividual();
		family = p3.getFamily();
		for (int i = 0; i < individual.size(); i++) {
			inMap.put(individual.get(i).getID(), individual.get(i));
		}
	}
	//US03 Birth before death
	public void checkBirthBeforeDeath(){
		for (int i = 0; i < individual.size(); i++) {
			if (individual.get(i).getBirt() != null && individual.get(i).getDeat() != null) {
				String[] bir = individual.get(i).getBirt().split("\\s+");
				String[] dea = individual.get(i).getDeat().split("\\s+");
				if (bir.length > 2 && dea.length > 2) {
					int y = Integer.parseInt(bir[2]);
					int m = BaseMethod.monTranslation(bir[1]);
					int d = Integer.parseInt(bir[0]);
					int year = Integer.parseInt(dea[2]);
					int month = BaseMethod.monTranslation(dea[1]);
					int day = Integer.parseInt(dea[0]);
					String tmp = "Error US03: birth date:" + individual.get(i).getBirt() + " of " 
							+ individual.get(i).getName() + " (" +individual.get(i).getID() + ") occurs after his/her death date"
									+ " :" + individual.get(i).getDeat();
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
	//US04 Marriage before divorce
	public void checkMarriageBeforeDivorce(){
		for (int i = 0; i < family.size(); i++) {
			if (family.get(i).getMarr() != null && family.get(i).getDiv() != null) {
				String[] mar = family.get(i).getMarr().split("\\s+");
				String[] div = family.get(i).getDiv().split("\\s+");
				if (mar.length > 2 && div.length > 2) {
					int y = Integer.parseInt(mar[2]);
					int m = BaseMethod.monTranslation(mar[1]);
					int d = Integer.parseInt(mar[0]);
					int year = Integer.parseInt(div[2]);
					int month = BaseMethod.monTranslation(div[1]);
					int day = Integer.parseInt(div[0]);
					String tmp = "Error US04: marry date:" + family.get(i).getMarr() + " of family : " 
							+ family.get(i).getID() + " (" + "husband : " + inMap.get(family.get(i).getHusb()).getName()
							+ " - " + inMap.get(family.get(i).getHusb()).getID() + ", wife : "
							+ inMap.get(family.get(i).getWife()).getName()
							+ " - " + inMap.get(family.get(i).getWife()).getID()		
							+ ") occurs after their divorce date"
							+ " :" + family.get(i).getDiv();
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
