package com.matthewdiana.gsrg;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Professor implements Runnable {

	private String name;
	private String scholarURL;
	private ArrayList<Publication> publications;
	
	public Professor(String scholarURL) {
		this.scholarURL = scholarURL;
		publications = new ArrayList<>();
	}
	
	public void printPublications() {
		System.out.println(name);
		System.out.println("===");
		for (Publication p : publications) {
			System.out.println(p);
			System.out.println("===");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getScholarURL() {
		return scholarURL;
	}
	
	public ArrayList<Publication> getPublications() {
		return publications;
	}

	@Override
	public void run() {
		int pageCounter = 0;
		boolean hasNext = true;
		ArrayList<String> titlesList = new ArrayList<>();
		ArrayList<String> authorsList = new ArrayList<>();
		ArrayList<String> journalsList = new ArrayList<>();
		ArrayList<String> citedByList = new ArrayList<>();
		ArrayList<String> yearList = new ArrayList<>();
		ArrayList<String> scholarURLList = new ArrayList<>();
		//ArrayList<String> publicationURLList = new ArrayList<>();
		
		while (hasNext) {
			Document doc = null;
			try {
				doc = Jsoup.connect(scholarURL + "&pagesize=100&view_op=list_works&cstart=" + pageCounter).get();
			} catch (IOException e1) {
				System.out.println("Unable to connect to Google Scholar; check your internet connection.");
				//System.exit(0);
				break;
			}
			Elements next = doc.getElementsByClass("cit-dark-link");
			
			Element professorName = doc.select("div[id=gsc_prf_in]").first();
			name = professorName.text();
			
			Elements titleCol = doc.select("a[class=gsc_a_at]");
			Elements authorAndJournalCol = doc.select("div[class=gs_gray]");
			Elements citedByCol = doc.select("td[class=gsc_a_c]");
			Elements yearCol = doc.select("span[class=gsc_a_h]");
			Elements scholarLinks = doc.select("td[class=gsc_a_t]").select("a[href]");
			
			for (Element e : titleCol) {
				String title = null;
				title = e.text();
				if (!title.equals("Title")) {
					titlesList.add(title);
				} else {
					continue;
				}
			}
			
			for (int i = 0; i < authorAndJournalCol.size(); i+=2) {
				String author = authorAndJournalCol.get(i).text();
				String journal = authorAndJournalCol.get(i+1).text();
				if (!author.equals("")) {
					authorsList.add(author);
				} else {
					authorsList.add("N/A");
				}
				if (!journal.equals("")) {
					journalsList.add(journal);
				} else {
					journalsList.add("N/A");
				}
			}
			
			for (Element e : yearCol) {
				String year = null;
				year = e.text();
				if (!year.equals("Year")) {
					yearList.add(year);
				}
			}
			
			for (Element e : citedByCol) {
				String citedBy = null;
				citedBy = e.text();
				if (!citedBy.equals("")) {
					citedByList.add(citedBy);
				} else {
					citedByList.add("N/A");
				}
			}
			
			for (Element e : scholarLinks) {
				String url = null;
				url = e.attr("href");
				if (!url.equals("")) {
					scholarURLList.add("http://scholar.google.com" + url);
				}
			}
			
			// Move to the next page when done.
			if (!next.text().contains("Next >"))
				hasNext = false;
			
			pageCounter += 100;
		}
		
		for (int i = 0; i < titlesList.size(); i++) {
			publications.add(new Publication(titlesList.get(i), authorsList.get(i), journalsList.get(i), yearList.get(i), citedByList.get(i), scholarURLList.get(i)));
		}
	}
}
