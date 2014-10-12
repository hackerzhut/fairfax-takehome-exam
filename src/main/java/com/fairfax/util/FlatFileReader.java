package com.fairfax.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FlatFileReader {
	
	private String fileName;
	
	public FlatFileReader(String fileName){
		this.fileName = fileName;
	}
	
	public List<List<String>> readFile(){
		Scanner scanner = null;
		List<List<String>> records = new ArrayList<List<String>>();
		try {
	    	scanner = new Scanner(new FileInputStream(fileName));
			scanner.useDelimiter(System.getProperty("line.separator"));
			
			while (scanner.hasNext()) {
				String[] data = (scanner.next()).split(",");
				if(data.length < 3){
					throw new RuntimeException("Error parsing file: "+fileName+ " : "+data);
				}
				records.add(Arrays.asList(data));
			}
			
	    } catch (Exception ex) {
	    	throw new RuntimeException("Error parsing file: "+fileName+ " : "+ex.getMessage());
		} finally{
			scanner.close();
		}
		return records;
	}

}
