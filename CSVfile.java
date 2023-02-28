package com.bridzelabz.addressBookFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CSVfile implements FileIO{
	

	    public static final String FILE_PATH = "C:\\Users\\acer\\eclipse-workspace\\mavenproject\\src\\main\\java\\com\\bridzelabz\\addressBookFile";
	    public void writeDataToDestination(HashMap<String, AddressBook> addressBookHashMap) throws IOException {
	        File file = new File(FILE_PATH);

	        FileWriter outputfile = new FileWriter(file);

	        CSVWriter writer = new CSVWriter(outputfile);

	        for(Map.Entry<String, AddressBook> entry : addressBookHashMap.entrySet()){
	            String[] addressBookDetail = new String[2];
	            addressBookDetail[0] = entry.getKey();
	            addressBookDetail[1] = entry.getValue().toString();
	            writer.writeNext(addressBookDetail);
	        }

	        writer.close();

	    }

	    public void readDataFromSource() throws IOException {
	        try{
	            FileReader filereader = new FileReader(FILE_PATH);

	            CSVReader csvReader = new CSVReader(filereader);
	            String[] nextRecord;

	            while ((nextRecord = csvReader.readNext()) != null) {
	                for (String addressBookDetails : nextRecord) {
	                    System.out.print(addressBookDetails + "\t");
	                }
	                System.out.println();
	            }
	        } catch (CsvValidationException e){

	        }
	    }
	
}
