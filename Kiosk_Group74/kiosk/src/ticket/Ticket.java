package src.ticket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import src.film.Film;

public class Ticket {
	public static String packagePath = "kiosk/data/tickets/";
	private String filmName = "-FILM NAME-";
	private int filmLength = 0;
	private String showtime = "-SHOWTIME-";
	private int screen = 1;
	private ArrayList<String> seatID = null;
	private ArrayList<String> ticketTypeString = null;
	private int[] ticketType = new int[] { 0, 1, 1, 1 };
	private int[] ticketID;

	/* arbitrary example */
	public Ticket() {
		seatID = new ArrayList<String>();
		seatID.add("8D");
		seatID.add("7D");
		seatID.add("6D");
		// Initialisations.
		ticketID = issueIDs();
		ticketTypeString = new ArrayList<String>();
		ticketTypeString = setupTypes();
	}

	public Ticket(Film film, int showtimeNo, int screenNo, ArrayList<String> selectedSeats, int[] ticketType) {
		this.filmName = film.getName();
		this.filmLength = film.getDuration();
		this.showtime = film.getShowtime().get(showtimeNo);
		this.screen = screenNo;
		this.seatID = selectedSeats;
		this.ticketType = ticketType;
		// Initialisations.
		this.ticketID = issueIDs();
		this.ticketTypeString = new ArrayList<String>();
		this.ticketTypeString = setupTypes();
	}

	/**
	 * This method is to translate the message that, e.g., {0, 1, 1, 2} stands
	 * for 0 Children ticket, 1 Adult ticket, 1 Senior ticket, and 2 Student
	 * tickets.
	 */
	public ArrayList<String> setupTypes() {
		String strTypes[] = new String[] { "Children", "Adult", "Senior", "Student" };
		ArrayList<String> arrType = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < ticketType[i]; j++) {
				arrType.add(strTypes[i]);
			}
		}
		return arrType;
	}

	/** To issue IDs for each ticket/seat, while checking uniqueness. */
	public int[] issueIDs() {
		int[] randomID = new int[seatID.size()];
		if (seatID.size() == 1) {
			randomID[0] = createID();
		} else {
			boolean flagUnique = true;
			do {
				for (int i = 0; i < seatID.size(); i++) {
					randomID[i] = createID();
				}
				// Check if the new IDs coincide with each other - although it
				// is very unlikely.
				for (int j = 0; j < seatID.size(); j++) {
					for (int k = j + 1; k < seatID.size(); k++) {
						if (randomID[j] == randomID[k])
							flagUnique = false;
					}
				}
			} while (!flagUnique);
		}
		return randomID;
	}

	/** To create a unique 8-digit ticket ID. */
	public int createID() {
		// get all the used IDs to avoid coincidence
		ArrayList<Integer> listIDs = readUsedIDs(packagePath + "TicketIDPool.txt");
		boolean valid = true;
		int newID = 0;
		do {
			int randomID = 0;
			// generate an 8-bit code with number 1,2,3,4
			for (int j = 0; j < 8; j++) {
				int ran = (int) (1 + 4 * Math.random());
				randomID += ran * ((int) (Math.pow(10, j)));
			}
			newID = randomID;
			// Check uniqueness
			for (int i = 0; i < listIDs.size(); i++) {
				if (listIDs.get(i) == newID) {
					valid = false;
					break;
				}
			}
		} while (!valid);
		return newID;
	}

	/** To read all used IDs form the recorded ID pool. */
	public ArrayList<Integer> readUsedIDs(String filepath) {
		try {
			String encoding = "UTF-8"; // Coding format
			File file = new File(filepath);
			ArrayList<Integer> listIDs = new ArrayList<Integer>();
			if (file.isFile() && file.exists()) { // Check if the file exists
				InputStreamReader in = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bfr = new BufferedReader(in);
				String tempString = null;

				// Putting values into Arraylist line by line, until null
				while ((tempString = bfr.readLine()) != null) {
					listIDs.add(Integer.parseInt(tempString));
				}
				bfr.close();
				return listIDs;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** To list out all the seats that have been chosen by the user. */
	public String printSeats(String gap) {
		String str = "";
		for (int i = 0; i < seatID.size(); i++) {
			str += seatID.get(i);
			str += gap;
		}
		return str;
	}

	/** To calculate how much the user should pay. */
	public double calculatePrice() {
		double sum = ticketType[0] * 0.5 + ticketType[1] * 1 + ticketType[2] * 0.2 + ticketType[3] * 0.15;
		sum *= 16;
		return sum;
	}

	public String ticketToString() {
		String str = "";
		for (int i = 0; i < ticketID.length; i++) {
			str += "Ticket " + (i + 1) + "\n";
			str += "Ticket ID: " + this.ticketID[i] + "\n";
			str += "Title: " + this.filmName + "\n";
			str += "Time: " + this.showtime + "\n";
			str += "Screen: " + this.screen + "\n";
			str += "Ticket Type: " + ticketTypeString.get(i) + "\n";
			if (ticketTypeString.get(i).equals("Student"))
				str += "Note: Student ID is required for admission at the gate.\n";
			str += "\n";
		}
		return str;
	}

	public String seatsToString() {
		String str = "";
		for (int i = 0; i < seatID.size(); i++) {
			str += seatID.get(i);
			str += "\n";
		}
		return str;
	}

	public String recordPurchase() {
		String str = "";
		SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
		String strDate = dfDate.format(new Date());
		String strTime = dfTime.format(new Date());
		for (int i = 0; i < ticketID.length; i++) {
			str += strDate + "\t";
			str += strTime + "\t";
			str += filmName + "\t";
			str += showtime + "\t";
			str += screen + "\t";
			str += seatID.get(i) + "\t";
			str += ticketTypeString.get(i) + "\t";
			str += ticketID[i] + "\n";
		}
		return str;
	}

	public String recordTicketID() {
		String str = "";
		for (int i = 0; i < ticketID.length; i++) {
			str += ticketID[i] + "\n";
		}
		return str;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public int getFilmLength() {
		return filmLength;
	}

	public void setFilmLength(int filmLength) {
		this.filmLength = filmLength;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public int[] getTicketType() {
		return ticketType;
	}

	public void setTicketType(int[] ticketType) {
		this.ticketType = ticketType;
	}

	public int[] getTicketID() {
		return ticketID;
	}

	public void setTicketID(int[] ticketID) {
		this.ticketID = ticketID;
	}

	public int getScreen() {
		return screen;
	}

	public void setScreen(int screen) {
		this.screen = screen;
	}

	public ArrayList<String> getSeats() {
		return seatID;
	}

	public void setSeats(ArrayList<String> seats) {
		this.seatID = seats;
	}

	// /** To check if this id has been used */
	// public boolean checkUnused(int newid){
	//
	// // read all the used IDs from the record file.
	// try {
	// String encoding = "GBK"; // Coding format
	// File file = new File("kiosk/data/tickets/TicketIDPool.txt");
	// ArrayList<Integer> listIDs = new ArrayList<Integer>();
	// if (file.isFile() && file.exists()) { // Check if the file exists
	// InputStreamReader in = new InputStreamReader(new FileInputStream(file),
	// encoding);
	// BufferedReader bfr = new BufferedReader(in);
	// String tempString = null;
	//
	// // Putting values into Arraylist line by line, until null
	// while ((tempString = bfr.readLine()) != null) {
	// listIDs.add(Integer.parseInt(tempString));
	// }
	// bfr.close();
	// }
	//
	// // Check identicality
	// for (int i=0; i<listIDs.size(); i++){
	// if (listIDs.get(i) == newid){
	// return false;
	// }
	// }
	//
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	//
	// return true;
	// }

}
