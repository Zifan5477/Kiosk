package src.screen;

//package cinema;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.*;
import java.io.*;

public class Screen2 extends Screen {

	// public static void main(String[] args){
	// JFrame fr = new JFrame();
	// fr.setSize(720, 480);
	// fr.setLayout(null);
	// fr.add(new Screen1());
	// fr.setVisible(true);
	// }
	
	int seat_totalAmount = 26;

	public Screen2() {
		this.setSize(720, 480); // set the size of the panel.
		this.setLayout(new BorderLayout(30, 15));
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);
	}

	public void init() throws IOException {

		JPanel a = new JPanel(new BorderLayout(10, 10));
		JPanel p = new JPanel(new GridLayout(4, 4, 9, 15));
		JPanel q = new JPanel(new GridLayout(4, 4, 9, 15));
		JPanel r = new JPanel(new GridLayout(4, 1, 9, 15));
		JPanel r1 = new JPanel(new GridLayout(4, 1, 9, 15));
		JPanel b = new JPanel(new BorderLayout(15, 0));

		JPanel space = new JPanel();

		JTextField screen = new JTextField("SCREEN", JTextField.CENTER);
		screen.setFont(new Font(null, Font.BOLD, 16));
		screen.setForeground(Color.white);
		screen.setBackground(Color.gray);
		screen.setEditable(false);
		screen.setHorizontalAlignment(JTextField.CENTER);
		b.add(screen, BorderLayout.NORTH);
		b.add(space, BorderLayout.SOUTH);

		String str[] = { "8", "7", "6", "5", "8", "6", "5", "4", "8", "6", "5", "4", "8", "6", "5", "4" };

		String str_1[] = { "4", "3", "2", "1", "3", "2", "1", "1", "3", "2", "1", "1", "3", "2", "1", "1" };

		String str_2[] = { "    D", "     C", "     B", "     A" };

		String str_3[] = { "D    ", "C     ", "B     ", "A     " };

		JPressButton seat_1[];

		seat_1 = new JPressButton[str.length];

		JPressButton seat_2[];

		seat_2 = new JPressButton[str_1.length];
		ImageIcon imageIcon = new ImageIcon(filepackage + "occupied.png");
		Image image = imageIcon.getImage();
		Image smallImage = image.getScaledInstance(55, 87, Image.SCALE_FAST);
		ImageIcon smallIcon = new ImageIcon(smallImage);
		JLabel row[];
		row = new JLabel[str_2.length];
		JLabel row1[];
		row1 = new JLabel[str_3.length];
		// batch allocating the numbers of the seats.
		for (int i = 0; i < str.length; i++) {
			seat_1[i] = new JPressButton();
			seat_2[i] = new JPressButton();
			seat_1[i].setText(str[i]);
			seat_2[i].setText(str_1[i]);
			seat_1[i].setBackground(Color.white);
			seat_1[i].setSelectBackground(new Color(50, 220, 255));
			seat_2[i].setBackground(Color.white);
			seat_2[i].setSelectBackground(new Color(50, 220, 255));
			seat_1[i].setOpaque(true);
			seat_1[i].setBorderPainted(false);
			seat_2[i].setOpaque(true);
			seat_2[i].setBorderPainted(false);
			seat_1[i].setPreferredSize(new Dimension(55, 87));
			seat_2[i].setPreferredSize(new Dimension(55, 87));
			seat_1[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateSelectedSeats(seat_1,seat_2);
				}
			});
			seat_2[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateSelectedSeats(seat_1,seat_2);
				}
			});
			p.add(seat_1[i]);
			q.add(seat_2[i]);
		}
		// these commands let the choosen seats disappear to satisfy the type of
		// the cinema.
		seat_1[4].setVisible(false);
		seat_1[8].setVisible(false);
		seat_1[12].setVisible(false);
		seat_2[7].setVisible(false);
		seat_2[11].setVisible(false);
		seat_2[15].setVisible(false);
		for (int i = 0; i < str_3.length; i++) {
			row[i] = new JLabel(str_3[i]);
			r.add(row[i]);
		}
		for (int i = 0; i < str_2.length; i++) {
			row1[i] = new JLabel(str_2[i]);
			r1.add(row1[i]);
		}
		// set the style of the seats.
		a.add(p, BorderLayout.WEST);
		a.add(q, BorderLayout.EAST);
		a.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

		this.add(a, BorderLayout.CENTER);
		this.add(r, BorderLayout.EAST);
		this.add(r1, BorderLayout.WEST);
		this.add(b, BorderLayout.SOUTH);
		String seatinfo[];
		seatinfo = new rwfile().readFl(filepackage + filename);
		System.out.println(Arrays.toString(seatinfo));
		Integer seat_id[] = getSeatNobyID(seatinfo);
		int n = 0;
		for (int i = 0; i < seat_id.length; i++) {
			if (null != seat_id[i]) {
				n = seat_id[i];
				if (n > 200)
					seat_2[n - 200].setIcon(smallIcon);
				else
					seat_1[n - 100].setIcon(smallIcon);
			}
		}
		setVisible(true);

	}
	
	public ArrayList<String> updateSelectedSeats(JPressButton[] seat_1, JPressButton[] seat_2) {
		ArrayList<String> arrStr = new ArrayList<String>();
		ArrayList<Integer> intSelectedSeats = new ArrayList<Integer>();
		for (int i = 0; i < seat_1.length; i++) {
			if (seat_1[i].isSelected())
				intSelectedSeats.add(100 + i);
			if (seat_2[i].isSelected())
				intSelectedSeats.add(200 + i);
		}
		arrStr = getSeatIDbyNo(intSelectedSeats);
		Screen.selectedSeats = arrStr;
		System.out.println(arrStr);
		return arrStr;
	}

	/**
	 * This method is to convert the number of the seat into its ID.
	 * e.g., 100 into 8D, 101 into 7D.
	 */
	public ArrayList<String> getSeatIDbyNo(ArrayList<Integer> seat) {
		Integer[] seats = new Integer[seat.size()];
		seats = (Integer[]) seat.toArray(seats);
		String[] seatss = new String[seats.length];

		int i;
		for (i = 0; i < seats.length; i++) {
			if (null != seats[i]) {

				if (seats[i] < 200) {
					switch ((seats[i] - 100) / 4) {
					case (0):
						seatss[i] = "D" + Integer.toString(8 - (seats[i] - 100) % 4);
						break;
					case (1):
						seatss[i] = "C" + Integer.toString(7 - (seats[i] - 100) % 4);
						break;
					case (2):
						seatss[i] = "B" + Integer.toString(7 - (seats[i] - 100) % 4);
						break;
					case (3):
						seatss[i] = "A" + Integer.toString(7 - (seats[i] - 100) % 4);
						break;
					}
				}

				else if (seats[i] >= 200) {
					switch ((seats[i] - 200) / 4) {
					case (0):
						seatss[i] = "D" + Integer.toString(4 - (seats[i] - 200) % 4);
						break;
					case (1):
						seatss[i] = "C" + Integer.toString(3 - (seats[i] - 200) % 4);
						break;
					case (2):
						seatss[i] = "B" + Integer.toString(3 - (seats[i] - 200) % 4);
						break;
					case (3):
						seatss[i] = "A" + Integer.toString(3 - (seats[i] - 200) % 4);
						break;
					}
				}
			}
		}
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(seatss));
		return list;
	}

	/**
	 * This method is to convert the Name of the seat into its ID number. e.g.,
	 * 8D into 100, 7D into 101.
	 */
	public Integer[] getSeatNobyID(String id[]) {
		Integer[] Seatid = new Integer[id.length];
		int i;
		for (i = 0; i < id.length; i++) {
			if (null != id[i]) {
				String id_1 = id[i].substring(0, 1);
				Integer id_2 = Integer.parseInt(id[i].substring(1));
				System.out.println(id_1);
				if (id_1.equals("D")) {
					if (id_2 > 4)
						Seatid[i] = 100 + 8 - id_2;
					else if (id_2 <= 4)
						Seatid[i] = 200 + 4 - id_2;
				} else if (id_2 > 3) {
					switch (id_1) {

					case "C":
						Seatid[i] = 100 + (11 - id_2);
						break;
					case "B":
						Seatid[i] = 100 + (11 - id_2) + 4;
						break;
					case "A":
						Seatid[i] = 100 + (11 - id_2) + 8;
						break;
					}
				} else if (id_2 <= 3) {
					switch (id_1) {
					case "C":
						Seatid[i] = 200 + (7 - id_2);
						break;
					case "B":
						Seatid[i] = 200 + (7 - id_2) + 4;
						break;
					case "A":
						Seatid[i] = 200 + (7 - id_2) + 8;
						break;
					}
				}
			}

		}

		return Seatid;
	}

}
