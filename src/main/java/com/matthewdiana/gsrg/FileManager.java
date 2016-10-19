package com.matthewdiana.gsrg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	
	private static final String urlsFile = "urls.txt";
	
	public static String fetchInstitution() throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(urlsFile));
		String line = input.readLine();
		
		// Read institution variable from beginning of urls.txt. If we begin reading URLs, institution not found and we return null
		while (line != null) {
			if (line.startsWith("$institution")) {
				input.close();
				return line.substring(line.indexOf("=")+1, line.length());
			} else if (!line.startsWith("#") && !line.startsWith("$") && !line.equals(""))
				break;
			line = input.readLine();
		}
		input.close();
		return null;
	}

	public static ArrayList<Professor> readProfessorsFromFile() throws IOException {
		ArrayList<Professor> professors = new ArrayList<>();
		BufferedReader input = new BufferedReader(new FileReader(urlsFile));
		String line = input.readLine();
		String currentDepartment = "";
		while (line != null) {
			
			// If line starts with # (comment), $ (variable), or empty, skip it.
			if (line.startsWith("#") || line.startsWith("$") || line.equals("")) {
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