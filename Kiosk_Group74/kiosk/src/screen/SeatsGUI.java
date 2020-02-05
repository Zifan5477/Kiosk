package src.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import src.film.Film;
import src.film.FilmInfo;
import src.film.Schedule;
import src.ticket.TicketTypeSelect;

import java.util.*;
import java.io.*;

/**
 * This is the GUI for seat selection.
 * 
 * @author Ziqian Chen
 * @version 3.1
 */
public class SeatsGUI extends JFrame {
	
	public static Film film;
	public static int showtimeNo;
	public static int screenNo = 1;

	public SeatsGUI(){
		this.setSize(960, 640);
		this.setTitle("SEAT SELECTION");
		this.setLayout(null);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLocationRelativeTo(null); // Place the window at the screen
											// center.
		this.setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public SeatsGUI(Film film, int showtimeNo, int screenNo){
		SeatsGUI.film = film;
		this.showtimeNo = showtimeNo;
		this.screenNo = screenNo;
		this.setSize(960, 640);
		this.setTitle("SEAT SELECTION");
		this.setLayout(null);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLocationRelativeTo(null); // Place the window at the screen
											// center.
		this.setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/** Here goes the main. */
	public static void main(String[] args) throws IOException {

		new SeatsGUI();

	}

	public void init() throws IOException {
		JPanel panLeft = new JPanel();
		JPanel panRight = new JPanel();
		JPanel panTop = new JPanel();

		/* Setting up the top panel. */
		JPanel panSubTop = new JPanel();
		panSubTop.setLayout(new GridLayout(0, 5));
		JLabel labTitle = new JLabel("<html><font size=18> SCREEN " + screenNo + "</font></html>",
				SwingConstants.CENTER);
		JLabel labSubtitle = new JLabel("<html><font size=4>" + film.getName() + ",   " + film.getShowtime().get(showtimeNo) + "</font></html>",
				SwingConstants.CENTER);
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font(null, Font.BOLD, 20));
		btnBack.setForeground(Color.GRAY);
		ImageIcon iconBack = new ImageIcon("kiosk/data/seats/back.png");
		iconBack.setImage(iconBack.getImage().getScaledInstance(40, 34, Image.SCALE_DEFAULT));
		btnBack.setIcon(iconBack);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Schedule();
			}
		});
		panSubTop.add(btnBack);
		panSubTop.add(new JLabel(""));
		panSubTop.add(labTitle);
		panTop.setLayout(new BorderLayout());
		panTop.add(labSubtitle, BorderLayout.CENTER);
		panTop.add(panSubTop, BorderLayout.NORTH);

		panTop.setSize(this.getWidth(), 80);
		panTop.setLocation(0, 20);

		/* Setting up the left panel. */
		panLeft.setSize(720, 480);
		panLeft.setLocation(30, 100);
		panLeft.setBackground(Color.WHITE);
		panLeft.setLayout(null);
		// Decide which screen.
		if (screenNo == 1) {
			panLeft.add(new Screen1());
		} else if (screenNo == 2) {
			panLeft.add(new Screen2());
		} else {
			panLeft.add(new Screen3());
		}

		/* Setting up the right panel. */

		panRight.setSize(150, 480);
		panRight.setLocation(780, 100);
		panRight.setBackground(Color.WHITE);
		panRight.setLayout(new BorderLayout());
		JPanel panText = new JPanel(); // Texts of notice at the top.
		panText.setBackground(Color.WHITE);
		panText.setSize(130, 380);
		JPanel panButton = new JPanel(); // Button of confirm at the bottom.
		JButton btnConfirm = new JButton("Confirm");
		// if (flagSelected) {
		// btnConfirm.setVisible(false);
		// }
		panButton.add(btnConfirm);
		btnConfirm.setPreferredSize(new Dimension(150, 50));
		btnConfirm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (Screen.selectedSeats == null || Screen.selectedSeats.size()==0)
					JOptionPane.showMessageDialog(null, "You haven't selected any seat yet.");
				else{
//					dispose();
					System.out.println(Screen.selectedSeats);
					new TicketTypeSelect(film, showtimeNo, screenNo, Screen.selectedSeats);
				}
			}
		});
		JTextArea txt = new JTextArea("Please select the seat(s) from the left panel by simply clicking them."
				+ "\n\nClick again to unselect.");
		txt.setFont(new Font(null, Font.ITALIC, 14));
		txt.setEditable(false);
		txt.setForeground(new Color(240, 110, 100));
		txt.setPreferredSize(new Dimension(130, 200));
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		panText.add(txt);
		panRight.add(panText, BorderLayout.CENTER);
		panRight.add(panButton, BorderLayout.SOUTH);

		this.getContentPane().add(panLeft);
		this.getContentPane().add(panRight);
		this.getContentPane().add(panTop);

		System.out.println(panLeft.getHeight());

	}
}