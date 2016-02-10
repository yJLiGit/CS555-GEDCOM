package edu.stevens.cs555.projectGEDCOM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Project03 {
	private ArrayList<String> level = new ArrayList<String>();
	private ArrayList<String> tag = new ArrayList<String>();
	private ArrayList<String> Arguments = new ArrayList<String>();
	private ArrayList<Individual> individual = new ArrayList<Individual>();
	private ArrayList<Family> family = new ArrayList<Family>();

	Project03(String path) throws IOException
	{
		GEDCOM_Formatter fm = new GEDCOM_Formatter(path);
		fm.check();
		this.level = fm.getLevel();
		this.tag = fm.getTag();
		this.Arguments = fm.getArguments();
	}
	public void informFilter()
	{
		Boolean status = false; // to check whether it is an individual or family
		Family tmpFa = null;
		Individual tmpIn = null;

		for (int i = 0; i < tag.size(); i++)
		{
			if (level.get(i).equals("0") && !tag.get(i).equals("Invalid tag"))
			{
				//information for individual
				if(tag.get(i).equals("INDI"))
				{
					tmpIn = new Individual(); //reference to the object
					tmpIn.setID(Arguments.get(i));
					individual.add(tmpIn);
					status = true;
				}
				else if (tag.get(i).equals("FAM"))
				{
					tmpFa = new Family();					
					tmpFa.setID(Arguments.get(i));
					family.add(tmpFa);
					status = true;

				}
				else
				{
					if (!tag.get(i).equals("NOTE"))
					{
						status = false;
					}
				}
			}
			if (status && !tag.get(i).equals("Invalid tag"))
			{
				//individual
				if(tag.get(i).equals("NAME"))
				{
					tmpIn.setName(Arguments.get(i));
				}
				else if (tag.get(i).equals("SEX"))
				{
					tmpIn.setSex(Arguments.get(i));

				}
				//Date for individual
				else if (tag.get(i).equals("BIRT"))
				{
					i++;
					if(tag.get(i).equals("DATE"))
					{
						tmpIn.setBirt(Arguments.get(i));
					}
					else
					{
						i--;
					}
				}
				else if (tag.get(i).equals("DEAT"))
				{
					i++;
					if(tag.get(i).equals("DATE"))
					{
						tmpIn.setDeat(Arguments.get(i));
					}
					else
					{
						i--;
					}
				}
				//family
				else if (tag.get(i).equals("WIFE"))
				{
					tmpFa.setWife(Arguments.get(i));
				}
				else if (tag.get(i).equals("HUSB"))
				{
					tmpFa.setHusb(Arguments.get(i));

				}
				//Date for family
				else if (tag.get(i).equals("MARR"))
				{
					i++;
					if(tag.get(i).equals("DATE"))
					{
						tmpFa.setMarr(Arguments.get(i));
					}
					else
					{
						i--;
					}
				}
				else if (tag.get(i).equals("DIV"))
				{
					i++;
					if(tag.get(i).equals("DATE"))
					{
						tmpFa.setDiv(Arguments.get(i));
					}
					else
					{
						i--;
					}
				}
				
			}
			
		}
	}
	public void Output(String path) throws IOException
	{
		Collections.sort(individual);
		Collections.sort(family);
		HashMap<String,String> IDName = new HashMap<String,String>();
		File output = new File(path);
		if (!output.exists()) {
		    output.createNewFile();
		}
		FileWriter writer = new FileWriter(output.getAbsoluteFile());
		BufferedWriter bufwriter = new BufferedWriter(writer);
				
		bufwriter.write("Individual information ordered by ID: "  + "\n");
		for (int i = 0; i < individual.size(); i ++)
		{
			IDName.put(individual.get(i).getID(), individual.get(i).getName()  + "\n");
			bufwriter.write ("Individual ID: " + individual.get(i).getID()  + "\n");
			bufwriter.write ("Name: " + individual.get(i).getName()  + "\n");
		}
		bufwriter.write("Family information ordered by ID: " + "\n");
		for (int i = 0; i < family.size(); i ++)
		{
			bufwriter.write ("Family ID: " + family.get(i).getID()  + "\n");
			//it contains space for the lines below , so no need to add "\n"
			if (family.get(i).getHusb() != null && family.get(i).getWife() != null)
			{
				bufwriter.write ("Husband ID: " + family.get(i).getHusb() +  ", Husband Name: " + IDName.get(family.get(i).getHusb()));
				bufwriter.write ("Wife ID: " + family.get(i).getWife() + ", Wife Name: " + IDName.get(family.get(i).getWife()));
			}
			else 
			{
				bufwriter.write("The family members (husband and wife) are not defined correctly for family: " + family.get(i).getID()  + "\n");
			}
		}
		bufwriter.close();
	}
	
}
