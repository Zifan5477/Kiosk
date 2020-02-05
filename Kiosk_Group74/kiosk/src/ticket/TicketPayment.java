package src.ticket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.film.Film;
import src.film.Schedule;

/**
 * This class is the GUI for ticket information and payment checking.
 * 
 * @author Ziqian Chen, Zifan Wan
 * @version 2.0
 * 
 */

public class TicketPayment extends JFrame {
	public static Film film;
	public static int showtimeNo;
	public static int screenNo = 1;
	public static ArrayList<String> selectedSeats;
	public static int[] ticketType = { 0, 0, 0, 0 }; // Child, Adult, Senior,
														// Student
	public static Ticket ticket = null;

	public TicketPayment() {
		init();
	}

	public TicketPayment(Film film, int showtimeNo, int screenNo, ArrayList<String> selectedSeats, int[] ticketType)
			throws HeadlessException {
		super();
		TicketPayment.film = film;
		TicketPayment.showtimeNo = showtimeNo;
		TicketPayment.screenNo = screenNo;
		TicketPayment.selectedSeats = selectedSeats;
		TicketPayment.ticketType = ticketType;
		init();
	}

	public void init() {
		JPanel pTitle = new JPanel();
		JPanel pButtons = new JPanel();
		JPanel pConfirm = new JPanel();

		// setup the top panel
		JLabel lTitle = new JLabel("TICKET PAYMENT");
		lTitle.setFont(new Font(null, Font.BOLD, 18));

		// setup the bottom panel
		JLabel lConfirm = new JLabel("Are you ready to pay?", SwingConstants.CENTER);
		JButton bPay = new JButton("Pay");
		JButton bBack = new JButton("Back");
		// bPay.addActionListener(new ContinueListener());
		bPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = { "Continue", "Cancel" };
				int m = JOptionPane.showOptionDialog(null,
						"The system will connect to your bank account \nand complete the payment. \nAre you sure to continue?",
						"Payment Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);
				if (m == 0) { // if confirmed, show the ticket info window
					dispose();
					showTicketInfo();
					postOperation();
				}
			}
		});
		bPay.setPreferredSize(new Dimension(100, 40));
		bBack.setPreferredSize(new Dimension(100, 40));
		bBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		pButtons.setLayout(new GridLayout(1, 2, 400, 0));
		pButtons.add(bBack);
		pButtons.add(bPay);
		pConfirm.setLayout(new BorderLayout());
		pConfirm.add(pButtons, BorderLayout.SOUTH);
		lConfirm.setFont(new Font("Georgia", Font.ITALIC, 16));
		lConfirm.setPreferredSize(new Dimension(0, 50));
		pConfirm.add(lConfirm, BorderLayout.NORTH);

		// setup the center panel
		TicketInfo pCenter = new TicketInfo(1);

		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(720, 320));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pTitle.add(lTitle);
		this.getContentPane().add(pTitle, BorderLayout.NORTH);
		this.getContentPane().add(pConfirm, BorderLayout.SOUTH);
		this.getContentPane().add(pCenter, BorderLayout.CENTER);
		JPanel pLeft = new JPanel();
		JPanel pRight = new JPanel();
		pLeft.setPreferredSize(new Dimension(80, 0));
		pRight.setPreferredSize(new Dimension(80, 0));
		this.getContentPane().add(pLeft, BorderLayout.WEST);
		this.getContentPane().add(pRight, BorderLayout.EAST);
		// this.getRootPane().setDefaultButton(bPay);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void showTicketInfo() {
		JFrame fr = new JFrame();
		fr.setPreferredSize(new Dimension(720, 420));
		fr.setLayout(new BorderLayout());
		JLabel lTitle = new JLabel("YOUR TICKET INFO", SwingConstants.CENTER);
		JLabel lNotice = new JLabel(
				"Payment Success! Please make sure to keep the information of your ticket as below.",
				SwingConstants.CENTER);
		lTitle.setFont(new Font(null, Font.BOLD, 20));
		lNotice.setFont(new Font("Georgia", Font.ITALIC, 14));
		JPanel pTop = new JPanel();
		pTop.setLayout(new GridLayout(0, 1));
		pTop.add(lTitle);
		pTop.add(lNotice);
		fr.getContentPane().add(pTop, BorderLayout.NORTH);
		JButton bFinish = new JButton("Finish");
		JButton bPrint = new JButton("Print the ticket.");
		bFinish.setPreferredSize(new Dimension(80, 40));
		bPrint.setPreferredSize(new Dimension(170, 40));
		bFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
//				Window[] children = getOwnedWindows();
//                for (Window win : children) {
//                    if (win instanceof JFrame)
//                        win.dispose();
//                }
				new Schedule();
				
			}
		});
		bPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < TicketPayment.ticket.getSeats().size(); i++) {
					writeFile(Ticket.packagePath + TicketPayment.ticket.getTicketID()[i] + ".txt",
							TicketPayment.ticket.ticketToString());
				}
				JOptionPane.showMessageDialog(null, "Your ticket(s) has/have been printed."
						+ "\n(Check /kiosk/data/tickets/ and the ticket is named after its ID)");
			}
		});
		JPanel pBottom = new JPanel();
		pBottom.add(bPrint);
		pBottom.add(bFinish);
		fr.getContentPane().add(pBottom, BorderLayout.SOUTH);
		TicketInfo pCenter = new TicketInfo(0);
		pCenter.setLayout(new GridLayout(0, 4));
		fr.getContentPane().add(pCenter, BorderLayout.CENTER);
		fr.pack();
		fr.setLocationRelativeTo(null);
		fr.setVisible(true);
	}

	/** To perform the follow-up actions including recording the purchase, the seats and the ticket ID.  */
	protected void postOperation() {
		// Record occupied seats.
		writeFile("kiosk/data/seats/" + ticket.getFilmName() + "_" + ticket.getShowtime() + ".txt", ticket.printSeats("\n"));
		// Record ticket IDs into the ticket pool.
		writeFile("kiosk/data/tickets/TicketIDPool.txt", ticket.recordTicketID());
		// Record full information.
		String strDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		writeFile("kiosk/data/records/" + strDate + "record.txt", ticket.recordPurchase());
	}

	/** Write input into a file at pth. */
	public void writeFile(String pth, String input) {
		File f = new File(pth);
		FileWriter fw;
		try {
			fw = new FileWriter(f.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(input);
//			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		TicketPayment t = new TicketPayment();
//
//	}

}

class TicketInfo extends JPanel {

	public TicketInfo(int flag) { // flag == 1, payment interface;
									// flag == 0, ticket info interface
		if (TicketPayment.film == null) {	// For JUnit Test
			TicketPayment.ticket = new Ticket();
		} else {
			TicketPayment.ticket = new Ticket(TicketPayment.film, TicketPayment.showtimeNo, TicketPayment.screenNo,
					TicketPayment.selectedSeats, TicketPayment.ticketType);
			init(flag);
		}
	}

	public void init(int flag) {
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(0, 2, 0, 10));
		String strLeft[] = { "Film Title: ", "Length: ", "Showtime", "Screen No.: ", "Seats: ", "Ticket Type: ",
				// ""
		};
		String strRight[] = { TicketPayment.ticket.getFilmName(),
				Integer.toString(TicketPayment.ticket.getFilmLength()), TicketPayment.ticket.getShowtime(),
				Integer.toString(TicketPayment.ticket.getScreen()), TicketPayment.ticket.printSeats(" "),
				"<html><body>" + "Children Ticket: " + TicketPayment.ticket.getTicketType()[0] + "<br>"
						+ "Adult Ticket: " + TicketPayment.ticket.getTicketType()[1] + "<br>" + "Senior Ticket: "
						+ TicketPayment.ticket.getTicketType()[2] + "<br>" + "Student Ticket: "
						+ TicketPayment.ticket.getTicketType()[3] + "<body></html>",
				// ""
		};
		for (int i = 0; i < strLeft.length; i++) {
			JLabel labLeft = new JLabel("         " + strLeft[i]);
			labLeft.setFont(new Font(null, Font.BOLD, 14));
			this.add(labLeft);
			this.add(new JLabel(strRight[i]));
		}
		if (flag == 1) { // flag == 1, payment interface
			JLabel lab1 = new JLabel("TOTAL PRICE:           ", SwingConstants.RIGHT);
			lab1.setFont(new Font(null, Font.BOLD, 14));
			lab1.setForeground(Color.BLUE);
			this.add(lab1);
			JLabel lab2 = new JLabel("£" + TicketPayment.ticket.calculatePrice());
			lab2.setFont(new Font(null, Font.BOLD, 14));
			lab2.setForeground(Color.BLUE);
			this.add(lab2);
		} else if (flag == 0) { // flag == 0, ticket info interface
			// this.add(new JLabel(""));
			// JLabel lab1 = new JLabel("TICKET ID: ", SwingConstants.RIGHT);
			// lab1.setFont(new Font(null, Font.BOLD, 14));
			// lab1.setForeground(new Color(0, 120, 80));
			// this.add(lab1);
			// JLabel lab2 = new JLabel(Integer.toString(ticket.getTicketID()));
			// lab1.setFont(new Font(null, Font.BOLD, 14));
			// lab2.setForeground(new Color(0, 120, 80));
			// this.add(lab2);
		}
	}
}
