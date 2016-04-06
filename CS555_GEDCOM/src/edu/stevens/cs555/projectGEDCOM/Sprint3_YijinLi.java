package edu.stevens.cs555.projectGEDCOM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Sprint3_YijinLi {
	private ArrayList<Individual> individual = new ArrayList<Individual>();
	private ArrayList<Family> family = new ArrayList<Family>();
	private ArrayList<String> result = new ArrayList<String>();
	private HashMap<String, Individual> inMap = new HashMap<String, Individual>();

	public ArrayList<String> getResult() {
		return result;
	}

	public Sprint3_YijinLi(String Input) throws IOException {
		Project03 p3 = new Project03(Input);
		p3.informFilter();
		individual = p3.getIndividual();
		family = p3.getFamily();
		for (int i = 0; i < individual.size(); i++) {
			inMap.put(individual.get(i).getID(), individual.get(i));
		}
	}

	// US16 Male last names
	public void checkMaleLastNames() {
		for (int i = 0; i < family.size(); i++) {
			//System.out.print("here:" + family.get(i).getChil().size());
			String faName = null;
			Family fam = family.get(i);
			boolean flag = false;
			if (fam.getHusb() != null && inMap.containsKey(fam.getHusb())) {
				Individual ind = inMap.get(fam.getHusb());
				if (ind.getName() != null && ind.getSex().equals("M")) {
					faName = ind.getName();
					String[] check = faName.split(" ");
					if (check.length == 2) {
						String checkName = check[1];
						StringBuffer buf = new StringBuffer("");
						buf.append(fam.getHusb() + ":" + faName + " ");
						//System.out.println(fam.getChil().size());
						for (int j = 0; j < fam.getChil().size(); j++) {
							if (inMap.containsKey(fam.getChil().get(j))) {
								Individual ind2 = inMap.get(fam.getChil().get(j));
								String[] check2 = ind2.getName().split(" ");
								String checkName2 = check2[1];
								if (!checkName.equals(checkName2) && ind2.getSex().equals("M")) {
									flag = true;
								}
								buf.append(fam.getChil().get(j) + ":" + ind2.getName() + " ");
							}
						}
						if (flag) {
							String tmp = "Error US16: Family : " + fam.getID()
									+ "'s male members do not share the same last name, here are the male members:"
									+ buf.toString();
							result.add(tmp);
						}
					}
				}
			}
		}
	}

	// US21 Correct gender for role
	public void checkCorrectGenderForRole() {
		for (int i = 0; i < family.size(); i++) {
			Family fam = family.get(i);
			if (fam.getHusb() != null && inMap.containsKey(fam.getHusb())) {
				Individual ind = inMap.get(fam.getHusb());
				if (!ind.getSex().equals("M")) {
					String tmp = "Error US21: Family :" + fam.getID() + "'s Husband :" + ind.getID() + ":"
							+ ind.getName() + "'s geneder : " + ind.getSex() + " is not correct";
					result.add(tmp);
				}
			}
			if (fam.getWife() != null && inMap.containsKey(fam.getWife())) {
				Individual ind = inMap.get(fam.getWife());
				if (!ind.getSex().equals("F")) {
					String tmp = "Error US21: Family :" + fam.getID() + "'s Wife :" + ind.getID() + ":" + ind.getName()
							+ "'s geneder : " + ind.getSex() + " is not correct";
					result.add(tmp);
				}
			}
		}
	}
}