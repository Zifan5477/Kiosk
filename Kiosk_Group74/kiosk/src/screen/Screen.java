package src.screen;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Screen extends JPanel {
	public static ArrayList<String> selectedSeats;
	public static String filepackage = "kiosk/data/seats/";
	public static String filename = SeatsGUI.film.getName() + "_" + SeatsGUI.film.getShowtime().get(SeatsGUI.showtimeNo) + ".txt";
	public Screen() {
		
	}
}
