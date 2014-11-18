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
		boolean displayDepartmentCBSelected = checkboxes.get(5).getSelection();
		
		try {
			fw = new FileWriter(outputFile);
			fw.write("<html>");
			fw.write("<head><title>Google Scholar Publication Report</title><script src=\"sorttable.js\"></script></head>");
			fw.write("<body><font face=\"calibri\"><center>");
			fw.write("<h1><b><u>Google Scholar Publication Report</u></b></h1>");
			fw.write("<h3>Generated for Binghamton University - " + dateFormat.format(date) + " <br />Fetched " + publicationCount + " publications written by " + professorCount + " professors</h3>");
			fw.write("<hr width=\"50%\" />");
			for (Professor prof : professors) {
				
				fw.write("<br />");
				
				fw.write("<font size=\"7\"><b>" + prof.getFullName() + "</b></font>");
				if (displayDepartmentCBSelected) fw.write("<br /><font size=\"5\">" + prof.getDepartment() + "</font>");
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
		boolean displayDepartmentCBSelected = checkboxes.get(5).getSelection();
		
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
			if (displayDepartmentCBSelected) fw.write("<th width=\"10%\">Department</th>");
			if (nameCBSelected) fw.write("<th width=\"25%\">Publication</th>");
			if (authorsCBSelected) fw.write("<th width=\"20%\">Authors</th>");
			if (journalCBSelected) fw.write("<th width=\"25%\">Journal</th>");
			if (yearCBSelected) fw.write("<th width=\"5%\">Year</th>");
			if (citationCountCBSelected) fw.write("<th width=\"5%\">Cited By</th>");
			fw.write("</tr>");
			
			for (Professor prof : professors) {
				for (Publication pub : prof.getPublications()) {
					fw.write("<tr>");
					fw.write("<td>" + prof.getFullName() + "</td>");
					if (displayDepartmentCBSelected) fw.write("<td>" + prof.getDepartment() + "</td>");
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
		boolean displayDepartmentCBSelected = checkboxes.get(5).getSelection();

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
				if (displayDepartmentCBSelected) fw.write(prof.getDepartment());
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
	
	public void generateCSVReportCombined() {
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
		boolean displayDepartmentCBSelected = checkboxes.get(5).getSelection();

		try {
			fw = new FileWriter(outputFile);
			fw.write("Google Scholar Publication Report");
			fw.write("\n");
			fw.write("Generated for Binghamton University - " + dateFormat.format(date));
			fw.write("\n");
			fw.write("Fetched " + publicationCount + " publications written by " + professorCount + " professors");
			fw.write("\n\n");
			
			fw.write("Professor"); fw.write(",");
			if (displayDepartmentCBSelected) {fw.write("Department"); fw.write(",");}
			if (nameCBSelected) {fw.write("Publication"); fw.write(",");}
			if (authorsCBSelected) {fw.write("Authors"); fw.write(",");}
			if (journalCBSelected) {fw.write("Journal"); fw.write(",");}
			if (yearCBSelected) {fw.write("Year"); fw.write(",");}
			if (citationCountCBSelected) {fw.write("Cited By"); fw.write(",");}
			fw.write("\n");
			
			for (Professor prof : professors) {
				for (Publication pub : prof.getPublications()) {
					fw.write(prof.getFullName()); fw.write(",");
					if (displayDepartmentCBSelected) {fw.write(prof.getDepartment()); fw.write(",");}
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
	
	public void generateBUPublicationsHTML() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		Date date = new Date();
		
		int publicationCount = 0;
		int professorCount = professors.size();
		
		for (Professor p : professors) {
			publicationCount += p.getPublications().size();
		}
		
		File outputFile = new File("publications.html");
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(outputFile);
			fw.write("<h1>Binghamton Publications - 2014</h1>");
			fw.write("<p>This listing was autogenerated using a Google Scholar crawler written for Binghamton University. If you have any questions or comments about the script, please <a href=\"mailto:mdiana2@binghamton.edu\">contact the creator</a>.</p>");
			fw.write("<p>Abstracts and full PDFs may be found through the <a href=\"http://library.binghamton.edu/\">University Libraries</a> website. Suggested databases are Scopus or ISI Web of Knowledge, both found in the <a href=\"http://metalink.binghamton.edu:8332/V/IRMKNTDTSHU2F7GBK4NP8YLPU8VRAFVCR5PUQYS1B4KA3S6X3M-17059?func=find-db-1\">database listing</a> under the Articles & Databases tab. You may also click the link provided for each article to its corresponding Google Scholar page.</p>");
			fw.write("<p>Last Updated: <i>" + date.toString() + "</i></p>");
			String currentDepartment = "";
			for (Professor prof : professors) {
				if (currentDepartment.equals("") || !currentDepartment.equals(prof.getDepartment())) {
					currentDepartment = prof.getDepartment();
					fw.write("<br /><h2>" + currentDepartment + "</h2>");
				}
				for (Publication pub : prof.getPublications()) {
					if (pub.getYear().equals("2014"))
						fw.write("<p>" + pub.getAuthors() + ", \"" + "<a href=\"" + pub.getScholarURL() + "\">" + pub.getTitle() + "</a>\", " + pub.getJournal() + "</p><br />");
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
	}
}
