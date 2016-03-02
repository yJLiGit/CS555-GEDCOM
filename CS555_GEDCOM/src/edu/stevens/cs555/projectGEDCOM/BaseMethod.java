package edu.stevens.cs555.projectGEDCOM;

public class BaseMethod {
	public static int monTranslation(String m) {
		switch (m) {
			case "JAN": 
				return 1;
			case "FEB": 
				return 2;
			case "MAR": 
				return 3;
			case "APR": 
				return 4;
			case "MAY": 
				return 5;
			case "JUN": 
				return 6;
			case "JUL": 
				return 7;
			case "AUG": 
				return 8;
			case "SEP": 
				return 9;
			case "OCT": 
				return 10;
			case "NOV": 
				return 11;
			default:
				return 12;
		}
	}
}
