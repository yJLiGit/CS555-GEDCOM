package edu.stevens.cs555.projectGEDCOM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

public class Sprint4_YijinLi {
	private ArrayList<Individual> individual = new ArrayList<Individual>();
	private ArrayList<Family> family = new ArrayList<Family>();
	private ArrayList<String> result = new ArrayList<String>();
	private HashMap<String, Individual> inMap = new HashMap<String, Individual>();

	public ArrayList<String> getResult() {
		return result;
	}

	public Sprint4_YijinLi(String Input) throws IOException {
		Project03 p3 = new Project03(Input);
		p3.informFilter();
		individual = p3.getIndividual();
		family = p3.getFamily();
		for (int i = 0; i < individual.size(); i++) {
			inMap.put(individual.get(i).getID(), individual.get(i));
		}
	}

	// US25 Unique first names in families
	public void checkUniqueFirstNamesInFamilies() {
		for (int i = 0; i < family.size(); i++) {
			ArrayList<String> children = family.get(i).getChil();
			ArrayList<String> res = new ArrayList<String>();
			for (int j = 0; j < children.size(); j++) {
				if (inMap.containsKey(children.get(j))) {
					Individual ind = inMap.get(children.get(j));
					String tmp = ind.getName() + " with birth date: " + ind.getBirt() + " and id: " + ind.getID();
					res.add(new String(tmp));
				}
			}
			Collections.sort(res);	
			String check = null;
			ArrayList<String> resultList = new ArrayList<String>();
			for (int j=0; j < res.size(); j++) {
				if (j == 0) {
					check = res.get(j).split(" and id: ")[0];
				} else {
					if (check.equals(res.get(j).split(" and id: ")[0])) {
						if (!resultList.contains(res.get(j - 1))) {
							resultList.add(res.get(j - 1));
						}
						resultList.add(res.get(j));
						check = res.get(j).split(" and id: ")[0];
					}
				}
			}
			StringBuffer buffer = new StringBuffer("Error US25: more than one children with same "
					+ "name and birth date in family :" + family.get(i).getID() +" , here is the conflict list: " );
			for (int j = 0; j< resultList.size(); j++) {
				buffer.append(resultList.get(j) + ", ");
			}
			buffer.append(" for your information.");
			if (resultList.size() >= 2)
				result.add(buffer.toString());
		}
		
	}

	// US29	List deceased
	public void checkListDeceased() {
		Boolean check = false;
		StringBuffer buffer = new StringBuffer("Alert US29: here is the deceased list : ");
		for (int i = 0; i < individual.size(); i++) {
			if (individual.get(i).getDeat() != null) {
				check = true;
				Individual in = individual.get(i);
				buffer.append(in.getName() + " whose ID is " + in.getID() + " died on " + in.getDeat() + " , ");
			}	
		}
		if (check) 
			result.add(buffer.append("for your information").toString());
	}
}