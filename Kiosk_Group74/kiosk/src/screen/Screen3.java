package src.screen;

//package cinema;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.*;
import java.io.*;

public class Screen3 extends Screen {

	// public static void main(String[] args){
	// JFrame fr = new JFrame();
	// fr.setSize(720, 480);
	// fr.setLayout(null);
	// fr.add(new Screen1());
	// fr.setVisible(true);
	// }
	
	int seat_totalAmount = 32;

	public Screen3() {
		this.setSize(720, 480); // set the size of the panel.
		this.setLayout(new BorderLayout(20, 10));
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);
	}

	public void init() throws IOException {

		JPanel a = new JPanel(new BorderLayout(120, 0));
		JPanel p = new JPanel(new GridLayout(4, 2, 9, 15));
		JPanel q = new JPanel(new GridLayout(4, 2, 9, 15));
		JPanel t = new JPanel(new GridLayout(4, 2, 9, 15));
		JPanel s = new JPanel(new FlowLayout(1, 20, 0));
		JPanel r = new JPanel(new GridLayout(4, 1, 20, 30));
		JPanel r1 = new JPanel(new GridLayout(4, 1, 20, 30));
		JPanel b = new JPanel(new BorderLayout(15, 0));

		Font f = new Font("Calibri", Font.PLAIN, 14);

		JPanel space = new JPanel();
		JTextField screen = new JTextField("SCREEN");
		screen.setHorizontalAlignment(JTextField.CENTER);
		screen.setBackground(Color.gray);
		screen.setFont(new Font(null, Font.BOLD, 16));
		screen.setForeground(Color.white);
		screen.setEditable(false);
		b.add(screen, BorderLayout.NORTH);
		b.add(space, BorderLayout.SOUTH);

		String str[] = { "6", "5", "6", "5", "6", "5", "6", "5" };
		String str_1[] = { "4", "3", "4", "3", "4", "3", "4", "3" };
		String str_4[] = { "2", "1", "2", "1", "2", "1", "2", "1" };
		String str_3[] = { "8", "7", "6", "5", "4", "3", "2", "1" };
		String str_2[] = { "       D", "       C", "       B", "       A" };
		String str_5[] = { "D       ", "C       ", "B       ", "A       " };
		
		JPressButton seat_1[];
		seat_1 = new JPressButton[str.length];
		JPressButton seat_2[];
		seat_2 = new JPressButton[str_1.length];
		JPressButton seat_4[];
		seat_4 = new JPressButton[str_4.length];
		JPressButton seat_3[];
		seat_3 = new JPressButton[str_3.length];
		ImageIcon imageIcon = new ImageIcon(filepackage + filename);
		Image image = imageIcon.getImage();
		Image smallImage = image.getScaledInstance(73, 80, Image.SCALE_FAST);
		ImageIcon smallIcon = new ImageIcon(smallImage);
		JLabel row[];
		row = new JLabel[str_2.length];
		JLabel row3[];
		row3 = new JLabel[str_5.length];
		JLabel row1;
		row1 = new JLabel("E");
		JLabel row2;
		row2 = new JLabel("E");
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
			seat_4[i] = new JPressButton();
			seat_4[i].setText(str_4[i]);
			seat_4[i].setBackground(Color.white);
			seat_4[i].setSelectBackground(new Color(50, 220, 255));
			seat_4[i].setOpaque(true);
			seat_4[i].setBorderPainted(false);
			seat_1[i].setPreferredSize(new Dimension(55, 68));
			seat_4[i].setPreferredSize(new Dimension(55, 68));
			seat_2[i].setPreferredSize(new Dimension(55, 68));
			seat_1[i].setFont(f);
			seat_2[i].setFont(f);
			seat_4[i].setFont(f);
			seat_1[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateSelectedSeats(seat_1,seat_2,seat_3,seat_4);
				}
			});
			seat_2[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateSelectedSeats(seat_1,seat_2,seat_3,seat_4);
				}
			});
			seat_4[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateSelectedSeats(seat_1,seat_2,seat_3,seat_4);
				}
			});
			p.add(seat_1[i]);
			q.add(seat_2[i]);
			t.add(seat_4[i]);
		}

		s.add(row1);
		for (int i = 0; i < str_3.length; i++) {
			seat_3[i] = new JPressButton();
			seat_3[i].setText(str_3[i]);
			seat_3[i].setBackground(Color.white);
			seat_3[i].setSelectBackground(new Color(50, 220, 255));
			seat_3[i].setOpaque(true);
			seat_3[i].setBorderPainted(false);
			seat_3[i].setPreferredSize(new Dimension(55, 71));
			seat_3[i].setFont(f);
			seat_3[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateSelectedSeats(seat_1,seat_2,seat_3,seat_4);
				}
			});
			s.add(seat_3[i]);
		}
		s.add(row2);

		for (int i = 0; i < str_5.length; i++) {
			row[i] = new JLabel(str_5[i]);
			row[i].setFont(f);
			r.add(row[i]);
		}

		for (int i = 0; i < str_2.length; i++) {
			row3[i] = new JLabel(str_2[i]);
			row3[i].setFont(f);
			r1.add(row3[i]);
		}
		// set the style of the seats.
		a.add(p, BorderLayout.WEST);
		a.add(q, BorderLayout.CENTER);
		a.add(t, BorderLayout.EAST);

		this.add(a, BorderLayout.CENTER);
		this.add(r, BorderLayout.EAST);
		this.add(r1, BorderLayout.WEST);
		this.add(s, BorderLayout.NORTH);
		this.add(b, BorderLayout.SOUTH);
		String seatinfo[];
		seatinfo = new rwfile().readFl(filepackage + "Screen3.txt");
		Integer seat_id[] = getSeatbyID(seatinfo);

		int n = 0;
		for (int i = 0; i < seat_id.length; i++) {
			if (null != seat_id[i]) {
				n = seat_id[i];
				if (n >= 400)
					seat_4[n - 400].setIcon(smallIcon);
				else if (n >= 300 && n < 400)
					seat_3[n - 300].setIcon(smallIcon);
				else if (n >= 200 && n < 300)
					seat_2[n - 200].setIcon(smallIcon);
				else
					seat_1[n - 100].setIcon(smallIcon);
			}
		}
		setVisible(true);

	}
	
	public ArrayList<String> updateSelectedSeats(JPressButton[] seat_1, JPressButton[] seat_2, JPressButton[] seat_3, JPressButton[] seat_4) {
		ArrayList<String> arrStr = new ArrayList<String>();
		ArrayList<Integer> intSelectedSeats = new ArrayList<Integer>();
		for (int i = 0; i < seat_1.length; i++) {
			if (seat_1[i].isSelected())
				intSelectedSeats.add(100 + i);
			if (seat_2[i].isSelected())
				intSelectedSeats.add(200 + i);
			if (seat_3[i].isSelected())
				intSelectedSeats.add(300 + i);
			if (seat_4[i].isSelected())
				intSelectedSeats.add(400 + i);
		}
		arrStr = getSeatIDbyNo(intSelectedSeats);
		Screen.selectedSeats = arrStr;
		System.out.println(arrStr);
		return arrStr;
	}

	/**
	 * This method is to convert the number of the seat into its ID. e.g., 100
	 * into 8D, 101 into 7D.
	 * 
	 * @param seat
	 */
	public ArrayList<String> getSeatIDbyNo(ArrayList<Integer> seat) {
		Integer[] seats = new Integer[seat.size()];
		seats = (Integer[]) seat.toArray(seats);
		String[] seatss = new String[seats.length];

		int i;
		for (i = 0; i < seats.length; i++) {
			if (null != seats[i]) {

				if (seats[i] < 200) {
					switch ((seats[i] - 100) / 2) {
					case (0):
						seatss[i] = "D" + Integer.toString(6 - (seats[i] - 100) % 2);
						break;
					case (1):
						seatss[i] = "C" + Integer.toString(6 - (seats[i] - 100) % 2);
						break;
					case (2):
						seatss[i] = "B" + Integer.toString(6 - (seats[i] - 100) % 2);
						break;
					case (3):
						seatss[i] = "A" + Integer.toString(6 - (seats[i] - 100) % 2);
						break;
					}
				}

				else if (seats[i] >= 200 && seats[i] < 300) {
					switch ((seats[i] - 200) / 2) {
					case (0):
						seatss[i] = "D" + Integer.toString(4 - (seats[i] - 200) % 2);
						break;
					case (1):
						seatss[i] = "C" + Integer.toString(4 - (seats[i] - 200) % 2);
						break;
					case (2):
						seatss[i] = "B" + Integer.toString(4 - (seats[i] - 200) % 2);
						break;
					case (3):
						seatss[i] = "A" + Integer.toString(4 - (seats[i] - 200) % 2);
						break;
					}
				} else if (seats[i] >= 400) {
					switch ((seats[i] - 400) / 2) {
					case (0):
						seatss[i] = "D" + Integer.toString(2 - (seats[i] - 400) % 2);
						break;
					case (1):
						seatss[i] = "C" + Integer.toString(2 - (seats[i] - 400) % 2);
						break;
					case (2):
						seatss[i] = "B" + Integer.toString(2 - (seats[i] - 400) % 2);
						break;
					case (3):
						seatss[i] = "A" + Integer.toString(2 - (seats[i] - 400) % 2);
						break;
					}
				} else if (seats[i] < 400 && seats[i] >= 300) {
					seatss[i] = "E" + Integer.toString(308 - seats[i]);
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
	public Integer[] getSeatbyID(String id[]) {
		Integer[] Seatid = new Integer[id.length];
		int i;
		for (i = 0; i < id.length; i++) {
			if (null != id[i]) {
				String id_1 = id[i].substring(0, 1);
				Integer id_2 = Integer.parseInt(id[i].substring(1));
				System.out.println(id_1);
				if (id_1.equals("E")) {
					Seatid[i] = 300 + 8 - id_2;
				} else if (id_2 > 4) {
					switch (id_1) {
					case "D":
						Seatid[i] = 100 + 6 - id_2;
						break;
					case "C":
						Seatid[i] = 100 + (6 - id_2) + 2;
						break;
					case "B":
						Seatid[i] = 100 + (6 - id_2) + 4;
						break;
					case "A":
						Seatid[i] = 100 + (6 - id_2) + 6;
						break;
					}
				} else if (id_2 <= 4 && id_2 > 2) {
					switch (id_1) {
					case ("D"):
						Seatid[i] = 200 + 4 - id_2;
						break;
					case ("C"):
						Seatid[i] = 200 + (4 - id_2) + 2;
						break;
					case ("B"):
						Seatid[i] = 200 + (4 - id_2) + 4;
						break;
					case ("A"):
						Seatid[i] = 200 + (4 - id_2) + 6;
						break;
					}
				} else if (id_2 <= 2) {
					switch (id_1) {
					case ("D"):
						Seatid[i] = 400 + 2 - id_2;
						break;
					case ("C"):
						Seatid[i] = 400 + (2 - id_2) + 2;
						break;
					case ("B"):
						Seatid[i] = 400 + (2 - id_2) + 4;
						break;
					case ("A"):
						Seatid[i] = 400 + (2 - id_2) + 6;
						break;
					}
				}
			}
		}
		return Seatid;
	}
}
