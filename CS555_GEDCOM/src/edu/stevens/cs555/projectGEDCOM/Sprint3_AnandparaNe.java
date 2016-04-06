package edu.stevens.cs555.projectGEDCOM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

public class Sprint3_AnandparaNe {
	private ArrayList<Individual> individual = new ArrayList<Individual>();
	private ArrayList<Family> family = new ArrayList<Family>();
	private ArrayList<String> result = new ArrayList<String>();
	private HashMap<String, Individual> inMap = new HashMap<String, Individual>();

	public ArrayList<String> getResult() {
		return result;
	}

	public Sprint3_AnandparaNe(String Input) throws IOException {
		Project03 p3 = new Project03(Input);
		p3.informFilter();
		individual = p3.getIndividual();
		family = p3.getFamily();
		for (int i = 0; i < individual.size(); i++) {
			inMap.put(individual.get(i).getID(), individual.get(i));
		}
		Collections.sort(individual);
		Collections.sort(family);
	}
	//US22	Unique IDs
	public void checkUniqueIDs() {
		Family first = null;
		for (int i = 0; i < family.size(); i++) {
			if (i == 0) {
				first = family.get(i);
			} else {
				Family second = family.get(i);
				if (first.getID().equals(second.getID())) {
					String tmp = "Error US22: Family : " + first.getID() + 
							" whose husbandID is " + first.getHusb() + " and wifeID is " + first.getWife()
							+ " has the same familyID with family : "  + second.getID() + 
							" whose husbandID is " + second.getHusb() + " and wifeID is " + second.getWife();
					result.add(tmp);
				}
				first = second;
			}
		}
		Individual firstI = null;
		for (int i = 0; i < individual.size(); i++) {
			if (i == 0) {
				firstI = individual.get(i);
			} else {
				Individual secondI = individual.get(i);
				if (firstI.getID().equals(secondI.getID())) {
					String tmp = "Error US22: Individual : " + firstI.getID() + 
							" whose name is " + firstI.getName()
							+ " has the same individualID with indvidual : "  + secondI.getID() + 
							" whose name is " + secondI.getName();
					result.add(tmp);
				}
				firstI = secondI;
			}
		}
		
	}
	//US23	Unique name and birth date
	public void checkUniqueNameAndBirthDate() {
		ArrayList<String> checkNameDate = new ArrayList<String>();
		for (int i = 0; i < individual.size(); i++) {
			Individual tmpi = individual.get(i);
			if (tmpi.getName()!=null && tmpi.getBirt() != null)
				checkNameDate.add("Name:" + tmpi.getName() + ",Birth date:" + tmpi.getBirt() + "#" + tmpi.getID());
		}
		Collections.sort(checkNameDate);
		String first = null;
		for (int i = 0; i < checkNameDate.size(); i++) {
			if (i == 0) {
				first = checkNameDate.get(i);
			} else {
				String second = checkNameDate.get(i);
				String[] tmpf = first.split("#");
				String[] tmps = second.split("#");
				if(tmpf[0].equals(tmps[0])) {
					String tmp = "Error US23 : Individual :" + tmpf[1] + " whose " + tmpf[0] +
							" has the same name and birth date with Individual :" + tmps[1] + " whose " + tmps[0];
					result.add(tmp);
				}
				first = second;
			}
		}

	}
}