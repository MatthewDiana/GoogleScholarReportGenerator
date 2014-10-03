package com.matthewdiana.gsrg;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.widgets.Button;

public class Report {

	private ArrayList<Professor> professors;
	private ArrayList<Button> checkboxes;
	
	public Report(ArrayList<Professor> professors, ArrayList<Button> checkboxes) {
		this.professors = professors;
		this.checkboxes = checkboxes;
	}
	
	public void generateHTMLReport() throws IOException {
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		Date date = new Date();
		
		int publicationCount = 0;
		int professorCount = professors.size();
		
		for (Professor p : professors) {
			publicationCount += p.getPublications().size();
		}
		
		File outputFile = new File("report.html");
		FileWriter fw = null;
		
		boolean nameCBSelected = checkboxes.get(0).getSelection();
		boolean authorsCBSelected = checkboxes.get(1).getSelection();
		boolean journalCBSelected = checkboxes.get(2).getSelection();
		boolean yearCBSelected = checkboxes.get(3).getSelection();
		boolean citationCountCBSelected = checkboxes.get(4).getSelection();
		
		try {
			fw = new FileWriter(outputFile);
			fw.write("<html>");
			fw.write("<head><title>Google Scholar Publication Report</title><script src=\"sorttable.js\"></script></head>");
			fw.write("<body><font face=\"calibri\"><center>");
			fw.write("<h1><b><u>Google Scholar Publication Report</u></b></h1>");
			fw.write("<h3>Generated for Binghamton University - " + dateFormat.format(date) + " <br />Fetched " + publicationCount + " publications written by " + professorCount + " professors</h3>");
			
			for (Professor prof : professors) {
				
				fw.write("<hr width=\"50%\" /> <br />");
				
				fw.write("<font size=\"16\"><b>" + prof.getFullName() + "</b></font>");
				fw.write("<table class=\"sortable\" border=\"3\" width=\"95%\">");
				
				fw.write("<tr>");
				if (nameCBSelected) fw.write("<th width=\"30%\">Publication</th>");
				if (authorsCBSelected) fw.write("<th width=\"30%\">Authors</th>");
				if (journalCBSelected) fw.write("<th width=\"30%\">Journal</th>");
				if (yearCBSelected) fw.write("<th width=\"5%\">Year</th>");
				if (citationCountCBSelected) fw.write("<th width=\"5%\">Cited By</th>");
				fw.write("</tr>");
				
				for (Publication pub : prof.getPublications()) {
					fw.write("<tr>");
					if (nameCBSelected) fw.write("<td><a href=\"" + pub.getScholarURL() + "\" target=\"_blank\">" + pub.getTitle() + "</a></td>");
					if (authorsCBSelected) fw.write("<td>" + pub.getAuthors() + "</td>");
					if (journalCBSelected) fw.write("<td>" + pub.getJournal() + "</td>");
					if (yearCBSelected) fw.write("<td>" + pub.getYear() + "</td>");
					if (citationCountCBSelected) fw.write("<td>" + pub.getCitationNum() + "</td>");
					fw.write("</tr>");
				}
				
				fw.write("</table><br />");
				
			}
			
			fw.write("</center></font></body>");
			fw.write("</html>");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Desktop.getDesktop().open(new File("report.html"));
		
	}
	
	public void generateHTMLReportCombined() throws IOException {
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		Date date = new Date();
		
		int publicationCount = 0;
		int professorCount = professors.size();
		
		for (Professor p : professors) {
			publicationCount += p.getPublications().size();
		}
		
		File outputFile = new File("report.html");
		FileWriter fw = null;
		
		boolean nameCBSelected = checkboxes.get(0).getSelection();
		boolean authorsCBSelected = checkboxes.get(1).getSelection();
		boolean journalCBSelected = checkboxes.get(2).getSelection();
		boolean yearCBSelected = checkboxes.get(3).getSelection();
		boolean citationCountCBSelected = checkboxes.get(4).getSelection();
		
		try {
			fw = new FileWriter(outputFile);
			fw.write("<html>");
			fw.write("<head><title>Google Scholar Publication Report</title><script src=\"sorttable.js\"></script></head>");
			fw.write("<body><font face=\"calibri\"><center>");
			fw.write("<h1><b><u>Google Scholar Publication Report</u></b></h1>");
			fw.write("<h3>Generated for Binghamton University - " + dateFormat.format(date) + " <br />Fetched " + publicationCount + " publications written by " + professorCount + " professors</h3>");
			fw.write("<hr width=\"50%\" /> <br />");
			fw.write("<table class=\"sortable\" border=\"3\" width=\"95%\">");

			fw.write("<tr>");
			fw.write("<th width=\"10%\">Professor</th>");
			if (nameCBSelected) fw.write("<th width=\"30%\">Publication</th>");
			if (authorsCBSelected) fw.write("<th width=\"20%\">Authors</th>");
			if (journalCBSelected) fw.write("<th width=\"30%\">Journal</th>");
			if (yearCBSelected) fw.write("<th width=\"5%\">Year</th>");
			if (citationCountCBSelected) fw.write("<th width=\"5%\">Cited By</th>");
			fw.write("</tr>");
			
			for (Professor prof : professors) {
				for (Publication pub : prof.getPublications()) {
					fw.write("<tr>");
					fw.write("<td>" + prof.getFullName() + "</td>");
					if (nameCBSelected) fw.write("<td><a href=\"" + pub.getScholarURL() + "\" target=\"_blank\">" + pub.getTitle() + "</a></td>");
					if (authorsCBSelected) fw.write("<td>" + pub.getAuthors() + "</td>");
					if (journalCBSelected) fw.write("<td>" + pub.getJournal() + "</td>");
					if (yearCBSelected) fw.write("<td>" + pub.getYear() + "</td>");
					if (citationCountCBSelected) fw.write("<td>" + pub.getCitationNum() + "</td>");
					fw.write("</tr>");
				}
			}
			
			
			fw.write("</table><br />");
			fw.write("</center></font></body>");
			fw.write("</html>");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Desktop.getDesktop().open(new File("report.html"));
		
	}
	
	public void generateCSVReport() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		Date date = new Date();
		
		int publicationCount = 0;
		int professorCount = professors.size();
		
		for (Professor p : professors) {
			publicationCount += p.getPublications().size();
		}
		
		File outputFile = new File("report.csv");
		FileWriter fw = null;
		
		boolean nameCBSelected = checkboxes.get(0).getSelection();
		boolean authorsCBSelected = checkboxes.get(1).getSelection();
		boolean journalCBSelected = checkboxes.get(2).getSelection();
		boolean yearCBSelected = checkboxes.get(3).getSelection();
		boolean citationCountCBSelected = checkboxes.get(4).getSelection();
		
		try {
			fw = new FileWriter(outputFile);
			fw.write("Google Scholar Publication Report");
			fw.write("\n");
			fw.write("Generated for Binghamton University - " + dateFormat.format(date));
			fw.write("\n");
			fw.write("Fetched " + publicationCount + " publications written by " + professorCount + " professors");
			fw.write("\n\n");
			
			for (Professor prof : professors) {
				fw.write(prof.getFullName());
				fw.write("\n");
				if (nameCBSelected) {fw.write("Publication"); fw.write(",");}
				if (authorsCBSelected) {fw.write("Authors"); fw.write(",");}
				if (journalCBSelected) {fw.write("Journal"); fw.write(",");}
				if (yearCBSelected) {fw.write("Year"); fw.write(",");}
				if (citationCountCBSelected) {fw.write("Cited By"); fw.write(",");}
				fw.write("\n");
				for (Publication pub : prof.getPublications()) {
					if (nameCBSelected) {fw.write(pub.getTitle().replace(",", ";")); fw.write(",");}
					if (authorsCBSelected) {fw.write(pub.getAuthors().replace(",", " &")); fw.write(",");}
					if (journalCBSelected) {fw.write(pub.getJournal().replace(",", "---")); fw.write(",");}
					if (yearCBSelected) {fw.write(pub.getYear()); fw.write(",");}
					if (citationCountCBSelected) {fw.write(pub.getCitationNum()); fw.write(",");}
					fw.write("\n");
				}
				fw.write("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Desktop.getDesktop().open(new File("report.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generateCVSReportCombined() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		Date date = new Date();
		
		int publicationCount = 0;
		int professorCount = professors.size();
		
		for (Professor p : professors) {
			publicationCount += p.getPublications().size();
		}
		
		File outputFile = new File("report.csv");
		FileWriter fw = null;
		
		boolean nameCBSelected = checkboxes.get(0).getSelection();
		boolean authorsCBSelected = checkboxes.get(1).getSelection();
		boolean journalCBSelected = checkboxes.get(2).getSelection();
		boolean yearCBSelected = checkboxes.get(3).getSelection();
		boolean citationCountCBSelected = checkboxes.get(4).getSelection();
		
		try {
			fw = new FileWriter(outputFile);
			fw.write("Google Scholar Publication Report");
			fw.write("\n");
			fw.write("Generated for Binghamton University - " + dateFormat.format(date));
			fw.write("\n");
			fw.write("Fetched " + publicationCount + " publications written by " + professorCount + " professors");
			fw.write("\n\n");
			
			fw.write("Professor"); fw.write(",");
			if (nameCBSelected) {fw.write("Publication"); fw.write(",");}
			if (authorsCBSelected) {fw.write("Authors"); fw.write(",");}
			if (journalCBSelected) {fw.write("Journal"); fw.write(",");}
			if (yearCBSelected) {fw.write("Year"); fw.write(",");}
			if (citationCountCBSelected) {fw.write("Cited By"); fw.write(",");}
			fw.write("\n");
			
			for (Professor prof : professors) {
				for (Publication pub : prof.getPublications()) {
					fw.write(prof.getFullName()); fw.write(",");
					if (nameCBSelected) {fw.write(pub.getTitle().replace(",", ";")); fw.write(",");}
					if (authorsCBSelected) {fw.write(pub.getAuthors().replace(",", " &")); fw.write(",");}
					if (journalCBSelected) {fw.write(pub.getJournal().replace(",", "---")); fw.write(",");}
					if (yearCBSelected) {fw.write(pub.getYear()); fw.write(",");}
					if (citationCountCBSelected) {fw.write(pub.getCitationNum()); fw.write(",");}
					fw.write("\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Desktop.getDesktop().open(new File("report.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
