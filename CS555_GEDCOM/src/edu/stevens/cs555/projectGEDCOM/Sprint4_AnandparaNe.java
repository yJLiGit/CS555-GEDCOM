package edu.stevens.cs555.projectGEDCOM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

public class Sprint4_AnandparaNe {
	private ArrayList<Individual> individual = new ArrayList<Individual>();
	private ArrayList<Family> family = new ArrayList<Family>();
	private ArrayList<String> result = new ArrayList<String>();
	private HashMap<String, Individual> inMap = new HashMap<String, Individual>();

	public ArrayList<String> getResult() {
		return result;
	}

	public Sprint4_AnandparaNe(String Input) throws IOException {
		Project03 p3 = new Project03(Input);
		p3.informFilter();
		individual = p3.getIndividual();
		family = p3.getFamily();
		for (int i = 0; i < individual.size(); i++) {
			inMap.put(individual.get(i).getID(), individual.get(i));
		}
	}
	//US35	List recent births
	public void checkListRecentBirths() {
		StringBuffer sb = new StringBuffer("Info US35: Here are the recent births in the last 30 days: ");
		int counter = 0;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30); 
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		for (int i = 0; i < individual.size(); i++) {
			Individual tmp = individual.get(i);
			String[] bir = tmp.getBirt()  == null ? null :tmp.getBirt().split("\\s+");
			if (bir != null && bir.length > 2) {
				int year = Integer.parseInt(bir[2]);
				int month = BaseMethod.monTranslation(bir[1]);
				int day = Integer.parseInt(bir[0]);
				if (y < year) {
					sb.append("ID :" + tmp.getID() + " whose name is " + tmp.getName() 
					+ " was borned on " + tmp.getBirt() + ", ");
					counter++;
				} else if (y == year) {
					if (m < month) {
						sb.append("ID :" + tmp.getID() + " whose name is " + tmp.getName() 
						+ " was borned on " + tmp.getBirt() + ", ");
						counter++;
					} else if (m == month) {
						if (d <= day) {
							sb.append("ID :" + tmp.getID() + " whose name is " + tmp.getName() 
							+ " was borned on " + tmp.getBirt() + ", ");
							counter++;
						}
					}
				}
			}
		}
		if (counter > 0) {
			result.add(sb.substring(0, sb.length() - 2));
		}
	}
	//US36	List recent deaths
		public void checkListRecentDeaths() {
			StringBuffer sb = new StringBuffer("Info US36: Here are the recent deaths in the last 30 days: ");
			int counter = 0;
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -30); 
			int y = cal.get(Calendar.YEAR);
			int m = cal.get(Calendar.MONTH) + 1;
			int d = cal.get(Calendar.DATE);
			for (int i = 0; i < individual.size(); i++) {
				Individual tmp = individual.get(i);
				String[] dea = tmp.getDeat()  == null ? null :tmp.getDeat().split("\\s+");
				if (dea != null && dea.length > 2) {
					int year = Integer.parseInt(dea[2]);
					int month = BaseMethod.monTranslation(dea[1]);
					int day = Integer.parseInt(dea[0]);
					if (y < year) {
						sb.append("ID :" + tmp.getID() + " whose name is " + tmp.getName() 
						+ " was died on " + tmp.getDeat() + ", ");
						counter++;
					} else if (y == year) {
						if (m < month) {
							sb.append("ID :" + tmp.getID() + " whose name is " + tmp.getName() 
							+ " was died on " + tmp.getDeat() + ", ");
							counter++;
						} else if (m == month) {
							if (d <= day) {
								sb.append("ID :" + tmp.getID() + " whose name is " + tmp.getName() 
								+ " was died on " + tmp.getDeat() + ", ");
								counter++;
							}
						}
					}
				}
			}
			if (counter > 0) {
				result.add(sb.substring(0, sb.length() - 2));
			}
		}
	
}