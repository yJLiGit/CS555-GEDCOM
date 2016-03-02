package edu.stevens.cs555.projectGEDCOM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GEDCOM_Main {
	public static void main(String args[]) throws Exception{
		//line oriented application
		String Input = args[0];
		String Output = args[1];
		//String Input = "Testdata_for_P04_Sprint1.ged"; //specify the path to read ged file
		//String Output = "P02_output.txt"; //specify the path for output
		//GEDCOM_Formatter fm = new GEDCOM_Formatter(Input);
		//String Output = "P04_Sprint1_output.txt";
		//fm.check();
		//fm.outPut(Output);
		//System.out.print("P02 Data has been written to " + Output);
		//Project03 p3 = new Project03(Input);
		//p3.informFilter();
		//p3.Output(Output);
		//System.out.print("P03 data has been written to : " + Output);
		Spring1_YijinLi rs1 = new Spring1_YijinLi(Input);
		rs1.checkDatesBeforeCurrentDate();
		rs1.checkBirthBeforeMarriage();
		Spring1_AnandparaNe rs2 = new Spring1_AnandparaNe(Input);
		rs2.checkBirthBeforeDeath();
		rs2.checkMarriageBeforeDivorce();
		ArrayList<String> result = new ArrayList<String>();
		result.addAll(rs1.getResult());
		result.addAll(rs2.getResult());
		print(Output,result);
		System.out.print("P04_Sprint1 data has been written to : " + Output);
	}
	public static void print(String path, ArrayList<String> result) throws IOException {
		File output = new File(path);
		if (!output.exists()) {
		    output.createNewFile();
		}
		FileWriter writer = new FileWriter(output.getAbsoluteFile());
		BufferedWriter bufwriter = new BufferedWriter(writer);
		bufwriter.write("Here are the output for Sprint1: "  + "\n");
		for (int i = 0; i < result.size(); i ++) {
			bufwriter.write (result.get(i) + "\n");
		}
		bufwriter.close();
	}
}
