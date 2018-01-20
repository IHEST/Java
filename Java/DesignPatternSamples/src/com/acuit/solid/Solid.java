package com.acuit.solid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Journal {
	private final List<String> entries = new ArrayList<>();
	private static  int count = 0;

	public void addEntry(String text){
		entries.add("" + (++count)+" :" + " " + text);
	}
	private void removeEntry(int indx){
		entries.remove(indx);
	}
	public String toString(){
		return String.join(System.lineSeparator(), entries);
	}
}
class Persistence{
	public void saveToFile( Journal journal,
			String filename,
			boolean  overwrite) throws FileNotFoundException{
		if(overwrite || new File(filename).exists()){
			try  {
				System.out.println("inside try1");
				PrintStream Out = new PrintStream(filename);
				Out.println(journal.toString());
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		else if ( !overwrite|| !new File(filename).exists()){
			try  {
				System.out.println("inside file create path");
				System.out.println(journal);
				System.out.println( "file created: " + new File(filename).createNewFile());

				PrintStream Out = new PrintStream(filename);
				System.out.println(journal);
				Out.println(journal.toString());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} 
		}
	}
}

class  Solid{

	public static void main(String[] args) throws Exception{
		Journal journal = new Journal();
		journal.addEntry("I cried");
		journal.addEntry("I ate");

		Persistence  pers = new Persistence();
		String name = 
				"C:\\Development\\journal.txt";
		pers.saveToFile(journal, name, false);
		Runtime.getRuntime().exec("notepad.exe " + name);

	}
}
