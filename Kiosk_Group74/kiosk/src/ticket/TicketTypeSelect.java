package src.ticket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import src.film.Film;

/**
 * This class is the GUI for ticket type selection
 * 
 * @author Zifan Wan, Ziqian Chen
 * @version 4.2
 * 
 */

public class TicketTypeSelect extends JFrame {
	private Film film;
	private int showtimeNo;
	private int screenNo = 1;
	private ArrayList<String> selectedSeats;
	private int[] ticketType = { 0, 0, 0, 0 }; // Child, Adult, Senior, Student

	public TicketTypeSelect() throws HeadlessException {
		super();
		selectedSeats = new ArrayList<String>();
		this.selectedSeats.add("A1");
		this.selectedSeats.add("A2");
	}

	public TicketTypeSelect(Film film, int showtimeNo, int screenNo, ArrayList<String> selectedSeats)
			throws HeadlessException {
		super();
		this.film = film;
		this.showtimeNo = showtimeNo;
		this.screenNo = screenNo;
		this.selectedSeats = selectedSeats;
		this.init();
	}

	final void init() {
		JPanel pTitle = new JPanel();
		JPanel pInput = new JPanel();
		JPanel pTop = new JPanel();
		JPanel pConfirm = new JPanel();
		JLabel lTitle = new JLabel("TICKET TYPE SELECTION");
		JButton bConfirm = new JButton("Confirm");
		JButton bCancel = new JButton("Cancel");
		lTitle.setFont(new Font(null, Font.BOLD, 18));

		/* Setting up the table */
		String[] columnNames = { "    Type", "Age & Requirements", "ID Required", "Discount" };
		Object[][] obj = new Object[4][5];
		obj[0][0] = "    Child";
		obj[1][0] = "    Adult";
		obj[2][0] = "    Senior";
		obj[3][0] = "    Student";
		obj[0][1] = "2-17 ";
		obj[1][1] = "18+";
		obj[2][1] = "55+";
		obj[3][1] = "18+ (full time education)";
		obj[0][2] = "None";
		obj[1][2] = "None";
		obj[2][2] = "None";
		obj[3][2] = "Student ID";
		obj[0][3] = "50%";
		obj[1][3] = "None";
		obj[2][3] = "20%";
		obj[3][3] = "15%";
		DefaultTableModel model = new DefaultTableModel(obj, columnNames);
		JTable table = new JTable(model);
		table.setEnabled(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setPreferredWidth(240);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(new Font(null, Font.PLAIN, 15));

		/* use JScrollPanes so that the page can scroll when exceeds */
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setPreferredSize(new Dimension(725, 170));
		JScrollPane scrollOption = new JScrollPane(pInput);

		/**
		 * i is the number of seats read from files
		 */

		bConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				System.out.println(ticketType[0]);
				System.out.println(ticketType[1]);
				System.out.println(ticketType[2]);
				System.out.println(ticketType[3]);

				// Check if the user has input the equivalent amout to how many
				// seats he/she has chosen.
				int sum = ticketType[0] + ticketType[1] + ticketType[2] + ticketType[3];
				if (sum == selectedSeats.size()) {
					if (ticketType[3] > 0)		// ticketType[3] refers to the number of student tickets.
						JOptionPane.showMessageDialog(null,
								"Reminder:" + "\nFor student tickets, please make sure to show the student ID"
										+ "\nwhen entering the screen.");
					dispose();
					new TicketPayment(film, showtimeNo, screenNo, selectedSeats, ticketType); // Jump to the payment interface.
				
				} else if (sum > selectedSeats.size()) {
					JOptionPane.showMessageDialog(null,
							"OOPS! " + "\nYou chose more than the total count of your ticket(s)."
									+ "\nThe sum of these 4 types should be " + selectedSeats.size() + ".");
				} else {
					JOptionPane.showMessageDialog(null,
							"OOPS! " + "\nYou have not chosen enough count for your ticket(s)."
									+ "\nThe sum of these 4 types should be " + selectedSeats.size() + ".");
				}
			}
		});
		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		pConfirm.add(bCancel);
		pConfirm.add(bConfirm);
		pTop.setLayout(new BorderLayout());
		pTop.add(BorderLayout.NORTH, pTitle);
		addOptions(pInput);

		// bConfirm.addActionListener(new ConfirmListener1());
		pTitle.add(lTitle);

		pInput.setLayout(new GridLayout(0, 4, 0, 8));
		pTop.add(BorderLayout.CENTER, scrollTable);
		this.setLayout(new BorderLayout());

		this.getContentPane().add(BorderLayout.CENTER, scrollOption);
		this.getContentPane().add(BorderLayout.NORTH, pTop);
		this.getContentPane().add(BorderLayout.SOUTH, pConfirm);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void addOptions(JPanel p) {
		JSpinner jsp[];
		jsp = new JSpinner[4];
		String types[] = { "Child", "Adult", "Senior", "Student" };

		for (int i = 0; i < 4; i++) {
			JLabel l = new JLabel(types[i] + " Ticket(s): ", SwingConstants.RIGHT);
			jsp[i] = new JSpinner(new SpinnerNumberModel(0, // initial value
					0, // min
					100, // max
					1)); // step

			p.add(l, BorderLayout.NORTH);
			p.add(jsp[i], BorderLayout.NORTH);
		}
		jsp[0].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ticketType[0] = (int) ((JSpinner) e.getSource()).getValue();
			}
		});
		jsp[1].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ticketType[1] = (int) ((JSpinner) e.getSource()).getValue();
			}
		});
		jsp[2].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ticketType[2] = (int) ((JSpinner) e.getSource()).getValue();
			}
		});
		jsp[3].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ticketType[3] = (int) ((JSpinner) e.getSource()).getValue();
			}
		});
	}

	public void addOptions(JPanel p, int num) {
		JComboBox jcb[];
		jcb = new JComboBox[num];
		for (int i = 0; i < num; i++) {
			JLabel l = new JLabel("Type of ticket " + (i + 1) + ":    ", SwingConstants.RIGHT);
			String strOptions[] = { "-Please select-", "Child", "Adult", "Senior", "Student" };

			jcb[i] = new JComboBox<String>(strOptions);
			jcb[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox) e.getSource();
					String choice = (String) cb.getSelectedItem();
					System.out.println(choice);

					switch (choice) {
					case "Child":
						ticketType[0]++;
					}
				}

			});

			p.add(l, BorderLayout.NORTH);
			p.add(jcb[i], BorderLayout.NORTH);

		}
	}

	public static void main(String[] args) {
		TicketTypeSelect choose = new TicketTypeSelect();

		choose.init();

	}
}

// class ConfirmListener1 implements ActionListener{
//
// @Override
// public void actionPerformed(ActionEvent arg0) {
//// Component jPanel = null;
// // TODO Auto-generated method stub
// Object[] options ={ "Confirm", "Cancel" };
// int m = JOptionPane.showOptionDialog(null, "Are you sure to choose that type
// of ticket(s)?", "Reminder",JOptionPane.YES_NO_OPTION,
// JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//
// }
//
// }