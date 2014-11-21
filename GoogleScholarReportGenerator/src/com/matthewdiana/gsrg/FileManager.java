package com.matthewdiana.gsrg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

	public static ArrayList<Professor> readProfessorsFromFile() throws IOException {
		ArrayList<Professor> professors = new ArrayList<>();
		BufferedReader input = new BufferedReader(new FileReader("urls.txt"));
		String line = input.readLine();
		String currentDepartment = "";
		while (line != null) {
			
			// If line starts with # (comment) or empty, skip it.
			if (line.startsWith("#") || line.equals("")) {
				line = input.readLine();
				continue;
			}
			
			// If line starts with =, change the current department.
			if (line.startsWith("=")) {
				currentDepartment = line.substring(line.indexOf("=")+1, line.length()).trim();
				line = input.readLine();
				continue;
			}
									
			int endOfFirstName = line.indexOf(" ") + 1;
			int endOfLastName = line.lastIndexOf(" ") + 1;
			
			String firstName = line.substring(0, endOfFirstName).trim();
			String lastName = line.substring(endOfFirstName, endOfLastName).trim();
			String url = line.substring(endOfLastName, line.length()).trim();
			
			professors.add(new Professor(firstName, lastName, currentDepartment, url));
			
			line = input.readLine();
		}
		input.close();
		return professors;
	}
}