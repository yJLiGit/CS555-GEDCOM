package edu.stevens.cs555.projectGEDCOM;

public class GEDCOM_Main {
	public static void main(String args[]) throws Exception{		
		String Input = "Testdata_for_P03.ged"; //specify the path to read ged file
		String Output = "P02_output.txt"; //specify the path for output
		GEDCOM_Formatter fm = new GEDCOM_Formatter(Input);
		fm.check();
		fm.outPut(Output);
		System.out.print("P02 Data has been written to " + Output);
	}
}
