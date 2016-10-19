package com.matthewdiana.gsrg;

public class Publication {

	private String title;
	private String authors;
	private String journal;
	private String year;
	private String citationNum;
	private String scholarURL;
	private String publicationURL;
	
	public Publication(String title, String authors, String journal, String year, String citationNum, String scholarURL) {
		this.title = title;
		this.authors = authors;
		this.journal = journal;
		this.year = year;
		this.citationNum = citationNum;
		this.scholarURL = scholarURL;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	public void setJournal(String journal) {
		this.journal = journal;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public void setCitationNum(String citationNum) {
		this.citationNum = citationNum;
	}
	
	public void setScholarURL(String scholarURL) {
		this.scholarURL = scholarURL;
	}
	
	public void setPublicationURL(String publicationURL) {
		this.publicationURL = publicationURL;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthors() {
		return authors;
	}
	
	public String getJournal() {
		return journal;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getCitationNum() {
		return citationNum;
	}
	
	public String getScholarURL() {
		return scholarURL;
	}
	
	public String getPublicationURL() {
		return publicationURL;
	}
	
	public String toString() {
		return ("Title: " + title + "\nAuthors: " + authors + "\nJournal: " + journal + "\nYear: " + year + "\nCitation Count: " + citationNum + "\nScholar URL: " + scholarURL + "\nPublication URL: " + publicationURL);
	}
	
}
