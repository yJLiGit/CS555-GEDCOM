package edu.stevens.cs555.projectGEDCOM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Sprint2_AnandparaNe {
	private ArrayList<Individual> individual = new ArrayList<Individual>();
	private ArrayList<Family> family = new ArrayList<Family>();
	private ArrayList<String> result = new ArrayList<String>();
	private HashMap<String, Individual> inMap = new HashMap<String, Individual>();

	public ArrayList<String> getResult() {
		return result;
	}

	public Sprint2_AnandparaNe(String Input) throws IOException {
		Project03 p3 = new Project03(Input);
		p3.informFilter();
		individual = p3.getIndividual();
		family = p3.getFamily();
		for (int i = 0; i < individual.size(); i++) {
			inMap.put(individual.get(i).getID(), individual.get(i));
		}
	}

	// US07 Less then 150 years old
	public void checkLessThan150() {
		for (int i = 0; i < individual.size(); i++) {
			String[] bir = individual.get(i).getBirt()  == null ? null :individual.get(i).getBirt().split("\\s+");
			String[] dea = individual.get(i).getDeat() == null ? null :individual.get(i).getDeat().split("\\s+");
			if (dea != null && dea.length > 2) {
				if (bir != null && bir.length > 2) {
					int year = Integer.parseInt(bir[2]) + 150;
					int month = BaseMethod.monTranslation(bir[1]);
					int day = Integer.parseInt(bir[0]);
					int y = Integer.parseInt(dea[2]);
					int m = BaseMethod.monTranslation(dea[1]);
					int d = Integer.parseInt(dea[0]);

					String tmp = "Error US07: with a birth date:" + individual.get(i).getBirt() + " , "
							+ individual.get(i).getName() + " (" + individual.get(i).getID() + "), "
							+ "lived longer than 150 years old" + ",as his/her death date is " + ""
							+ individual.get(i).getDeat();
					if (y > year) {
						result.add(tmp);
					} else if (y == year) {
						if (m > month) {
							result.add(tmp);
						} else if (m == month) {
							if (d > day) {
								result.add(tmp);
							}
						}
					}
				} 
			}
			else {
					Calendar cal = Calendar.getInstance();

					if (bir != null && bir.length > 2) {
						int year = Integer.parseInt(bir[2]) + 150;
						int month = BaseMethod.monTranslation(bir[1]);
						int day = Integer.parseInt(bir[0]);
						int y = cal.get(Calendar.YEAR);
						int m = cal.get(Calendar.MONTH) + 1;
						int d = cal.get(Calendar.DATE);

						String tmp = "Error US07: with a birth date:" + individual.get(i).getBirt() + " , "
								+ individual.get(i).getName() + " (" + individual.get(i).getID() + "), "
								+ "lived longer than 150 years old" + ",as he/she is still alive currently";
						if (y > year) {
							result.add(tmp);
						} else if (y == year) {
							if (m > month) {
								result.add(tmp);
							} else if (m == month) {
								if (d > day) {
									result.add(tmp);
								}
							}
						}
					}

				}
			}
		}
	
	
	// US10	Marriage after 14
	public void checkMarriageAfter14() {
		for (int i = 0; i < family.size(); i++) {
			Individual husband = inMap.get(family.get(i).getHusb());
			Individual wife = inMap.get(family.get(i).getWife());
			String[] mar = family.get(i).getMarr() == null ? null : family.get(i).getMarr().split("\\s+");
			if (mar != null && mar.length > 2) {
				int year = Integer.parseInt(mar[2]);
				int month = BaseMethod.monTranslation(mar[1]);
				int day = Integer.parseInt(mar[0]);
				if (husband != null) {
					String[] bir = husband.getBirt() == null ? null : husband.getBirt().split("\\s+");
					if (bir != null && bir.length > 2 && Integer.parseInt(bir[2]) < year) {
						int y = Integer.parseInt(bir[2]) + 14;
						int m = BaseMethod.monTranslation(bir[1]);
						int d = Integer.parseInt(bir[0]);
						String tmp = "Error US10: Birth date:" + husband.getBirt() + " of husband :"
								+ husband.getName() + " (" + husband.getID() + "), of family : " + 
								family.get(i).getID() + " marry less than 14 years old,"
								+ "the marriage date is " + family.get(i).getMarr();
						if (y > year) {
							result.add(tmp);
						} else if (y == year) {
							if (m > month) {
								result.add(tmp);
							} else if (m == month) {
								if (d > day) {
									result.add(tmp);
								}
							}
						}
					}
				}
				if (wife != null) {
					String[] bir = wife.getBirt() == null ? null : wife.getBirt().split("\\s+");
					if (bir != null && bir.length > 2 && Integer.parseInt(bir[2]) < year) {
						int y = Integer.parseInt(bir[2]) + 14;
						int m = BaseMethod.monTranslation(bir[1]);
						int d = Integer.parseInt(bir[0]);
						String tmp = "Error US10: Birth date:" + wife.getBirt() + " of wife :"
								+ wife.getName() + " (" + wife.getID() + "), of family : " + 
								family.get(i).getID() + " marry less than 14 years old,"
								+ "the marriage date is " + family.get(i).getMarr();
						if (y > year) {
							result.add(tmp);
						} else if (y == year) {
							if (m > month) {
								result.add(tmp);
							} else if (m == month) {
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
	
}
