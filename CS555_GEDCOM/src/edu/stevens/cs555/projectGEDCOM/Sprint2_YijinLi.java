package edu.stevens.cs555.projectGEDCOM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Sprint2_YijinLi {
	private ArrayList<Individual> individual = new ArrayList<Individual>();
	private ArrayList<Family> family = new ArrayList<Family>();
	private ArrayList<String> result  = new ArrayList<String>();
	private HashMap<String,Individual> inMap = new HashMap<String,Individual>();
	
	public ArrayList<String> getResult() {
		return result;
	}
	
	public Sprint2_YijinLi(String Input) throws IOException {
		Project03 p3 = new Project03(Input);
		p3.informFilter();
		individual = p3.getIndividual();
		family = p3.getFamily();
		for (int i = 0; i < individual.size(); i++) {
			inMap.put(individual.get(i).getID(), individual.get(i));
		}
	}
	//US05	Marriage before death
	public void checkMarriageBeforeDeath(){
		for (int i = 0; i < family.size(); i++) {
			String marrageDate =null;
			String husDate = null;
			String wifeDate = null;
			if (family.get(i).getMarr() != null && family.get(i).getMarr().length() > 2)
				marrageDate = family.get(i).getMarr();
			
			if (family.get(i).getHusb() != null 
				&& inMap.get(family.get(i).getHusb()) != null 
				&& inMap.get(family.get(i).getHusb()).getDeat() != null) 
				husDate = inMap.get(family.get(i).getHusb()).getDeat();
			
			if (family.get(i).getWife() != null 
				&& inMap.get(family.get(i).getWife()) != null 
				&& inMap.get(family.get(i).getWife()).getDeat() != null) 
				wifeDate = inMap.get(family.get(i).getWife()).getDeat();
			if (marrageDate != null) {
				String[] mda = marrageDate.split("\\s+");
				int y =  Integer.parseInt(mda[2]);
				int m = BaseMethod.monTranslation(mda[1]);
				int d = Integer.parseInt(mda[0]);
				if (husDate != null) {
					String[] dateInfo = husDate.split("\\s+");
					int year = Integer.parseInt(dateInfo[2]);
					int month = BaseMethod.monTranslation(dateInfo[1]);
					int day = Integer.parseInt(dateInfo[0]);
					String tmp = "Error US05: Marriage date:" + marrageDate +
							" of the husband : " + inMap.get(family.get(i).getHusb()).getName() + " (" +inMap.get(family.get(i).getHusb()).getID() + ") "
									+ "occurs after his death date : " + husDate + " in family : " + family.get(i).getID();
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
				if (wifeDate != null) {
					String[] dateInfo = wifeDate.split("\\s+");
					int year = Integer.parseInt(dateInfo[2]);
					int month = BaseMethod.monTranslation(dateInfo[1]);
					int day = Integer.parseInt(dateInfo[0]);
					String tmp = "Error US05: Marriage date:" + marrageDate +
							" of the wife : " + inMap.get(family.get(i).getWife()).getName() + " (" +inMap.get(family.get(i).getWife()).getID() + ") "
									+ "occurs after her death date : " + wifeDate + " in family : " + family.get(i).getID();
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
	
	//US06	Divorce before death
	public void checkDivorceBeforeDeath(){
		for (int i = 0; i < family.size(); i++) {
			String divoiceDate =null;
			String husDate = null;
			String wifeDate = null;
			if (family.get(i).getDiv() != null && family.get(i).getDiv().length() > 2)
				divoiceDate = family.get(i).getDiv();
			
			if (family.get(i).getHusb() != null 
				&& inMap.get(family.get(i).getHusb()) != null 
				&& inMap.get(family.get(i).getHusb()).getDeat() != null) 
				husDate = inMap.get(family.get(i).getHusb()).getDeat();
			
			if (family.get(i).getWife() != null 
				&& inMap.get(family.get(i).getWife()) != null 
				&& inMap.get(family.get(i).getWife()).getDeat() != null) 
				wifeDate = inMap.get(family.get(i).getWife()).getDeat();
			if (divoiceDate != null) {
				String[] mda = divoiceDate.split("\\s+");
				int y =  Integer.parseInt(mda[2]);
				int m = BaseMethod.monTranslation(mda[1]);
				int d = Integer.parseInt(mda[0]);
				if (husDate != null) {
					String[] dateInfo = husDate.split("\\s+");
					int year = Integer.parseInt(dateInfo[2]);
					int month = BaseMethod.monTranslation(dateInfo[1]);
					int day = Integer.parseInt(dateInfo[0]);
					String tmp = "Error US06: Divoice date:" + divoiceDate +
							" of the husband : " + inMap.get(family.get(i).getHusb()).getName() + " (" +inMap.get(family.get(i).getHusb()).getID() + ") "
									+ "occurs after his death date : " + husDate + " in family : " + family.get(i).getID();
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
				if (wifeDate != null) {
					String[] dateInfo = wifeDate.split("\\s+");
					int year = Integer.parseInt(dateInfo[2]);
					int month = BaseMethod.monTranslation(dateInfo[1]);
					int day = Integer.parseInt(dateInfo[0]);
					String tmp = "Error US06: Divoice date:" + divoiceDate +
							" of the wife : " + inMap.get(family.get(i).getWife()).getName() + " (" +inMap.get(family.get(i).getWife()).getID() + ") "
									+ "occurs after her death date : " + wifeDate + " in family : " + family.get(i).getID();
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
