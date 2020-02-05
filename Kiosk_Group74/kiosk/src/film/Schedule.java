package src.film;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import src.screen.SeatsGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Title : Schedule.java Description : This class is the GUI for film selection.
 * 
 * @author Zhuoqun Yuan, Ziqian Chen
 * @version 4.1
 */

public class Schedule extends JFrame implements MouseListener {
	JLabel moviep[] = new JLabel[20];
	ArrayList<Film> arrFilm = setFilms(this.readFile("kiosk/data/films/Text.txt"));
	private Date date;

	public Schedule() {

		super.setTitle("WELCOME TO THE KIOSK SYSTEM!");
		this.setLayout(null);
		this.setSize(960, 640);
		// this.setResizable(true);
		setLocationRelativeTo(null);
		JPanel theall = new JPanel();
		theall.setPreferredSize(new Dimension(130 + 170*arrFilm.size(), 1000)); // 这里这里这里这里这里这里这里
		theall.setLayout(null);

		JPanel title = new JPanel();
		title.setBounds(20, 0, 900, 100);
		JLabel ti = new JLabel("K I O S K");
		ti.setFont(new Font("Cambria", Font.BOLD, 100));
		title.add(ti);
		theall.add(title);
		JPanel pictures[] = new JPanel[arrFilm.size()];

		JPanel names[] = new JPanel[arrFilm.size()];
		JPanel times[] = new JPanel[arrFilm.size()];
		for (int i = 0; i < arrFilm.size(); i++) {
			pictures[i] = new JPanel();
			pictures[i].setBounds(18 + 188 * i, 40 + 100, 170, 280);
			moviep[i] = new JLabel(new ImageIcon(arrFilm.get(i).getPicPath()));
			moviep[i].addMouseListener(this);
			moviep[i].setBounds(18 + 188 * i, 40 + 100, 170, 280);
			moviep[i].setSize(170, 280);
			pictures[i].add(moviep[i]);
			theall.add(pictures[i]);
			names[i] = new JPanel();
			names[i].add(new JLabel(arrFilm.get(i).getName()));
			names[i].setBounds(18 + 188 * i, 10 + 100, 170, 20);
			theall.add(names[i]);
			times[i] = new JPanel();
			times[i].add(new JLabel("Length:" + String.valueOf(arrFilm.get(i).getDuration()) + "min"));
			times[i].setBounds(18 + 188 * i, 320 + 100, 170, 20);
			theall.add(times[i]);
		}

		JButton btnShowtime[][] = new JButton[arrFilm.size()][20]; // It can be
																	// assumed
																	// that
																	// there
																	// won't be
																	// as much
																	// as 20
																	// shows for
																	// a film.
		for (int i = 0; i < arrFilm.size(); i++) {
			// btnShowtime[i][] = new
			// JButton[i][arrFilm.get(i).getShowtime().size()];
			for (int j = 0; j < arrFilm.get(i).getShowtime().size(); j++) {
				int this_filmNo = i;
				int this_showtimeNo = j;
				int this_screenNo = arrFilm.get(i).getScreen().get(j);
				String this_showtime = arrFilm.get(i).getShowtime().get(j);

				// btnShowtime[i][] = new
				// JButton()[arrFilm.get(i).getShowtime().size()][];
				btnShowtime[i][j] = new JButton();
				btnShowtime[i][j].setText("     " + this_showtime + "          Screen " + this_screenNo);

				btnShowtime[i][j].setBounds(18 + 188 * i, 340 + 100 + 55 * j, 170, 40);
				// Check the if showtime has passed.
				if (compareTime(arrFilm.get(i).getShowtime().get(j)) >= 0) {
					btnShowtime[i][j].setForeground(Color.LIGHT_GRAY);
					btnShowtime[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(null,
									"Sorry, the showtime has passed." + "\nYou may choose another one.");
						}
					});
				} else {
					btnShowtime[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
							new SeatsGUI(arrFilm.get(this_filmNo), this_showtimeNo, this_screenNo);
						}
					});
				}
				theall.add(btnShowtime[i][j]);
			}
		}

		JScrollPane jsp = new JScrollPane(theall);
		jsp.setBounds(0, 0, 960, 620);
		this.getContentPane().add(jsp);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Read the file contents into a string Arraylist
	 * 
	 * @param pth
	 * @return list
	 */
	public ArrayList<String> readFile(String pth) {

		try {
			String encoding = "UTF-8"; // Coding format
			File file = new File(pth);
			ArrayList<String> list = new ArrayList<String>();
			if (file.isFile() && file.exists()) { // Check if the file exists
				InputStreamReader in = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bfr = new BufferedReader(in);
				String tempString = null;

				// Putting values into Arraylist line by line, until null
				while ((tempString = bfr.readLine()) != null) {
					list.add(tempString);
				}
				bfr.close();
				return list;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Read the file contents into a string Arraylist
	 * 
	 * @param list
	 * @return listFilm
	 */
	public ArrayList<Film> setFilms(ArrayList<String> list) {
		ArrayList<Film> listFilm = new ArrayList<Film>();
		String name;
		int duration;
		String pic;
		int i = 0;
		while (i < list.size()) {
			ArrayList<String> showtime = new ArrayList<String>();
			ArrayList<Integer> screen = new ArrayList<Integer>();
			ArrayList<String> info = new ArrayList<String>();
			name = list.get(i++); // The first value is the film title.
			duration = Integer.parseInt(list.get(i++)); // The second is the
														// film length.
			pic = list.get(i++); // The third is the file name of the film
									// cover.
			// Record each showtime and its corresponding screen.
			while (list.get(i).equals("INFO_START") == false) { // Identifier
																// "INFO_START"
																// is used to
																// specify the
																// start of
																// info.
				screen.add(Integer.parseInt(list.get(i++)));
				showtime.add(list.get(i++));
			}
			i++; // Skip the "INFO_START".
			// Record the information of each film.
			while (list.get(i).equals("INFO_END") == false) { // Identifier
																// "INFO_END" is
																// used to
																// specify the
																// end of info.
				info.add(list.get(i++));
			}
			listFilm.add(new Film(name, duration, pic, screen, showtime, info));
			i++; // Skip the "INFO_END".
			i++; // Skip the blank character.
		}
		return listFilm;
	}

	/**
	 * Check if the given time is previous to current. If yes, return 1; if
	 * passed, return -1; if concurrent, return 0.
	 */
	public int compareTime(String str) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		String nowtime = formatter.format(currentTime);

		Calendar now = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		String t1 = str;
		try {
			now.setTime(formatter.parse(nowtime));
			c1.setTime(formatter.parse(t1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result1 = now.compareTo(c1);
		// System.out.println(String.valueOf(result1));
		return result1;
	}

	public static void main(String[] args) {
		Schedule sch = new Schedule();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int o = 0; o < 20; o++) {
			if (e.getSource() == this.moviep[o]) {
				this.dispose();
				FilmInfo j = new FilmInfo(arrFilm.get(o).getName(), arrFilm.get(o).getDuration());
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}