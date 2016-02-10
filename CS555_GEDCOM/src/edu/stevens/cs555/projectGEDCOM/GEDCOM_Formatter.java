package edu.stevens.cs555.projectGEDCOM;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class GEDCOM_Formatter {
	private ArrayList<String> gedcom = new ArrayList<String>();
	private ArrayList<String> stringToPrint = new ArrayList<String>();
	private ArrayList<String> level = new ArrayList<String>();
	private ArrayList<String> tag = new ArrayList<String>();
	private ArrayList<String> Arguments = new ArrayList<String>();
	private ArrayList<String> tagset = new  ArrayList<String>();
	
	//get Arguments, tag, level
	ArrayList<String> getLevel()
	{
		return level;
	}
	ArrayList<String> getTag()
	{
		return tag;
	}
	ArrayList<String> getArguments()
	{
		return Arguments;
	}
	
	//Read the information from given path (GEDCOM txt file) into variables 
	public GEDCOM_Formatter(String path) throws IOException
	{
		FileReader read = new FileReader(path);
		BufferedReader buffer = new BufferedReader(read);
		String tmp;
		while((tmp = buffer.readLine()) != null)
		{
			gedcom.add(tmp);
		}
		buffer.close();
	}
	//check and extract Arguments, tag, level from each lines
	public void check() throws IOException
	{
		//read the properties for tag set
		Properties pro = new Properties();
		FileInputStream reader = new FileInputStream("gedchk.properties");
		InputStream in = new BufferedInputStream (reader);
		pro.load(in);
		String tagValues = pro.getProperty("Tags");
		String[] tmpSet = tagValues.split(",");
		for (int i = 0; i < tmpSet.length; i++)
		{
			tagset.add(tmpSet[i]);
		}
		//check the validation of each line: 
		for (int i =0 ; i < gedcom.size(); i++)
		{
			String[] tmp = gedcom.get(i).split(" ");
			if(tmp.length == 0)
			{
				//do nothing
			}
			else if (tmp.length == 1)
			{
				stringToPrint.add(gedcom.get(i));
				Arguments.add("");
				int tmpchk = Integer.parseInt(tmp[0]);
				if (tmpchk >= 0 && tmpchk <= 2)
				{
					level.add(tmp[0]);
					tag.add("Invalid tag");
				}
				else
				{
					level.add("Invaild level");
					tag.add("Invalid tag");
				}
			}
			else if (tmp.length == 2)
			{
				stringToPrint.add(gedcom.get(i));
				Arguments.add("");
				int tmpchk = Integer.parseInt(tmp[0]);
				if (tmpchk >= 0 && tmpchk <= 2)
				{
					level.add(tmp[0]);
				}
				else
				{
					level.add("Invaild level");
				}
				
				if(tagset.contains(tmp[1]))
				{
					tag.add(tmp[1]);
				}
				else
				{
					tag.add("Invalid tag");
				}
				
			}
			else 
			{
				stringToPrint.add(gedcom.get(i));
				int tmpchk = Integer.parseInt(tmp[0]);
				if (tmpchk >= 0 && tmpchk <= 2)
				{
					level.add(tmp[0]);
				}
				else
				{
					level.add("Invaild level");
				}
				//check for level 0,  tag: INDI and FAM case,to add tag
				if (tmpchk == 0 && (tmp[2].equals("INDI")||tmp[2].equals("FAM")))
				{
					tag.add(tmp[2]);
					Arguments.add(tmp[1]);
				}
				// for other case:
				else
				{
					if(tagset.contains(tmp[1]))
					{
						tag.add(tmp[1]);
						//update for the Arguments:
						String str = "";
						for (int k = 2; k < tmp.length; k++)
						{
							if (k != tmp.length - 1)
							{
								//add splitter " "
								str = str + tmp[k] + " ";
							}
							else
							{
								str += tmp[k];
							}
						}
						Arguments.add(str);	
					}
					else
					{
						tag.add("Invalid tag");
						Arguments.add("invalid Arguments");
					}
				}
			}				
		}
	}
	//for project 2
	public void outPut(String path) throws IOException
	{
		File output = new File(path);
		if (!output.exists()) {
		    output.createNewFile();
		}
		FileWriter writer = new FileWriter(output.getAbsoluteFile());
		BufferedWriter bufwriter = new BufferedWriter(writer);
		for(int i = 0; i < stringToPrint.size(); i++)
		{
			bufwriter.write(stringToPrint.get(i) + "\n");
			bufwriter.write(level.get(i) + "\n");
			bufwriter.write(tag.get(i) + "\n");
			//System.out.println(level.get(i) + " " + tag.get(i) + " : " + Arguments.get(i));
		}
		bufwriter.close();
	}
}
