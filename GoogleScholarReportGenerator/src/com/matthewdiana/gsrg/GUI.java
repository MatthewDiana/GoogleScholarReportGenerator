package com.matthewdiana.gsrg;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.CCombo;
import org.jsoup.Jsoup;
import org.eclipse.wb.swt.SWTResourceManager;

public class GUI {

	protected Shell frame;
	private ArrayList<Professor> professors;
	private List list;
	private ArrayList<Button> checkboxes;
	private Button generateButton;
	private Button combineCB;
	private CCombo format;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUI window = new GUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		
		Display display = Display.getDefault();
		
		createContents();
		
		frame.open();
		
		// Attempt to read Google Scholar URLs from text file. If unable to, inform the user.
		try {
			professors = FileManager.readProfessorsFromFile();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to find urls.txt", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			e.printStackTrace();
		}
		
		// Attempt to connect to Google Scholar before starting threads.
		try {
			Jsoup.connect("http://scholar.google.com/").get();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Unable to connect to Google Scholar. Please check your internet connection.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		display.syncExec(
				new Runnable() {
					public void run(){
						ArrayList<Thread> threads = new ArrayList<>();
						for (Professor p : professors) {
							threads.add(new Thread(p));
						}
						for (Thread t : threads) {
							t.start();
						}
						
						// Check if all threads are terminated. If not, wait 2000ms before checking again.
						boolean finished = false;
						while (!finished) {
							finished = true;
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							for (Thread t : threads) {
								if(t.getState() != Thread.State.TERMINATED) {
									finished = false;
								}
							}
						}
						
						
					}
				}
			);

		list.removeAll();
		for (Professor p : professors) {
			list.add(p.getFullName());
		}		
		
		frame.layout();
	
		while (!frame.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		frame = new Shell();
		frame.setSize(424, 392);
		frame.setText("Google Scholar Report Generator");
		
		Label titleLabel = new Label(frame, SWT.NONE);
		titleLabel.setFont(SWTResourceManager.getFont("Trebuchet MS", 16, SWT.BOLD));
		titleLabel.setAlignment(SWT.CENTER);
		titleLabel.setBounds(0, 0, 408, 29);
		titleLabel.setText("Google Scholar Report Generator");
		
		Label label = new Label(frame, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		label.setBounds(10, 30, 388, 2);
		
		generateButton = new Button(frame, SWT.NONE);
		generateButton.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		
		generateButton.setEnabled(false);
		generateButton.setBounds(10, 317, 388, 35);
		generateButton.setText("GENERATE");
		
		list = new List(frame, SWT.BORDER | SWT.V_SCROLL);
		list.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		list.setBounds(10, 56, 200, 255);
		list.add("Scraping Google Scholar...");
		list.add("This could take a minute...");
		
		combineCB = new Button(frame, SWT.CHECK);
		combineCB.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		combineCB.setBounds(226, 202, 137, 16);
		combineCB.setText("Combine Professors");
		
		Label professorListLabel = new Label(frame, SWT.NONE);
		professorListLabel.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		professorListLabel.setAlignment(SWT.CENTER);
		professorListLabel.setBounds(10, 35, 200, 15);
		professorListLabel.setText("Professor List");
		
		Label includeLabel = new Label(frame, SWT.NONE);
		includeLabel.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		includeLabel.setAlignment(SWT.CENTER);
		includeLabel.setBounds(226, 35, 172, 19);
		includeLabel.setText("Include");
		
		Button nameCB = new Button(frame, SWT.CHECK);
		nameCB.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		nameCB.setSelection(true);
		nameCB.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		nameCB.setBounds(226, 59, 172, 16);
		nameCB.setText("Publication Name");
		
		Button authorsCB = new Button(frame, SWT.CHECK);
		authorsCB.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		authorsCB.setSelection(true);
		authorsCB.setBounds(226, 81, 172, 16);
		authorsCB.setText("Authors");
		
		Button journalCB = new Button(frame, SWT.CHECK);
		journalCB.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		journalCB.setSelection(true);
		journalCB.setBounds(226, 103, 172, 16);
		journalCB.setText("Journal");
		
		Button yearCB = new Button(frame, SWT.CHECK);
		yearCB.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		yearCB.setSelection(true);
		yearCB.setBounds(226, 125, 173, 16);
		yearCB.setText("Year Published");
		
		Button citationCountCB = new Button(frame, SWT.CHECK);
		citationCountCB.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		citationCountCB.setSelection(true);
		citationCountCB.setBounds(226, 147, 172, 16);
		citationCountCB.setText("Citation Count");
		
		Label optionsLabel = new Label(frame, SWT.NONE);
		optionsLabel.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		optionsLabel.setText("Options");
		optionsLabel.setAlignment(SWT.CENTER);
		optionsLabel.setBounds(226, 177, 172, 19);
		
		Label label_1 = new Label(frame, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		label_1.setBounds(226, 169, 172, 2);
		
		checkboxes = new ArrayList<>();
		checkboxes.add(nameCB);
		checkboxes.add(authorsCB);
		checkboxes.add(journalCB);
		checkboxes.add(yearCB);
		checkboxes.add(citationCountCB);
		
		Label label_2 = new Label(frame, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		label_2.setBounds(226, 257, 172, 2);
		
		Label formatLabel = new Label(frame, SWT.NONE);
		formatLabel.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		formatLabel.setText("Format");
		formatLabel.setAlignment(SWT.CENTER);
		formatLabel.setBounds(226, 265, 172, 19);
		
		format = new CCombo(frame, SWT.BORDER);
		format.setFont(SWTResourceManager.getFont("Trebuchet MS", 9, SWT.NORMAL));
		format.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				generateButton.setEnabled(true);
			}
		});
		format.setItems(new String[] {"HTML", "CSV"});
		format.setText("Select Report Format...");
		format.setEditable(false);
		format.setBounds(226, 290, 172, 21);
		
		generateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Report report = new Report(professors, checkboxes);
				try {
					if (combineCB.getSelection()) {
						if (format.getText().equals("HTML")) {
							report.generateHTMLReportCombined();
						} else if (format.getText().equals("CSV")) {
							report.generateCVSReportCombined();
						}
					} else {
						if (format.getText().equals("HTML")) {
							report.generateHTMLReport();
						} else if (format.getText().equals("CSV")) {
							report.generateCSVReport();
						}
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
