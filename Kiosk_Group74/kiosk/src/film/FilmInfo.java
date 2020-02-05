package src.film;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FilmInfo extends JFrame implements MouseListener {

	private JLabel labCoverPic = new JLabel();
	private JLabel labTitle = new JLabel("FILM INFORMATION");
	private JLabel labLength = new JLabel("FILM LENGTH");
	private JButton btnBack = new JButton("BACK");
	private JButton jf;
	private String name;
	private int duration;
	private static int coverWidth = 400;
	private static int coverHeight = 550;

	public FilmInfo(String name, int duration) {
//		setLocationRelativeTo(null);
		ArrayList<Film> arrFilm = setFilms(this.readFile("kiosk/data/films/Text.txt"));
		int re = 0;
		this.name = name;
		this.duration = duration;
		// Find out which film is clicked
		for (int i = 0; i < arrFilm.size(); i++) {
			if (name.equals(arrFilm.get(i).getName())) {
				re = i;
				break;
			}
		}

		// Merging the info texts.
		String strInfo = "";
//		strInfo += "<html>";
		Iterator<String> it = arrFilm.get(re).getInfo().iterator();
		while (it.hasNext()) {
			strInfo += it.next();
//			strInfo += "<br/>";
			strInfo += "\n";
		}

//		strInfo += "</html>";

		JTextArea txt = new JTextArea(strInfo);
		txt.setFont(new Font("Calibri", Font.ITALIC, 15));
		txt.setEditable(false);
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		
		JScrollPane scrollText = new JScrollPane(txt);		

		jf = new JButton(strInfo);
		jf.setBorderPainted(false);
		jf.setFont(new Font(null, Font.ITALIC, 15));
		labTitle.setText(name);
		labTitle.setFont(new Font("Cambria", Font.BOLD, 30));
		labLength.setText("Length: "+duration+" min");
		labLength.setFont(new Font("Cambria", Font.BOLD, 16));
//		jlbtitle.setForeground(Color.BLUE);
		this.setSize(960, 640);
//		this.setLocation(150, 80);
		setLocationRelativeTo(null);
		setTitle("FILM INFORMATION");
		setLayout(null);
		ImageIcon icon = new ImageIcon(arrFilm.get(re).getPicPath());
		icon.setImage(icon.getImage().getScaledInstance(FilmInfo.coverWidth,FilmInfo.coverHeight,Image.SCALE_DEFAULT));
		labCoverPic.setIcon(icon);
		
		add(labTitle);
		labTitle.setBounds(500, 50, 600, 40);
		add(labLength);
		labLength.setBounds(500, 80, 600, 40);
		add(scrollText);
		scrollText.setBounds(500, 130, 400, 360);
		add(labCoverPic);
		labCoverPic.setBounds(50, 25, 400, 550);
		add(btnBack);
		btnBack.setBounds(800, 500, 100, 40);
		btnBack.setPreferredSize(new Dimension(100,80));
		btnBack.addMouseListener(this);
//		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

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

	public void mouseClicked(MouseEvent e) {
		this.dispose();
		Schedule sch = new Schedule();

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
