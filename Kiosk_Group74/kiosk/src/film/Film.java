package src.film;
import java.util.ArrayList;

public class Film {
	
	private String name;
	private int duration;
	private String pic;
	private ArrayList<Integer> screen;
	private ArrayList<String> showtime;
	private ArrayList<String> info;

	public Film(String name, int duration, String pic, ArrayList<Integer> screen, ArrayList<String> showtime,
			ArrayList<String> info) {
		super();
		this.name = name;
		this.duration = duration;
		this.pic = pic;
		this.screen = screen;
		this.showtime = showtime;
		this.info = info;
	}

	
	public String getPic() {
		return pic;
	}
	
	public String getPicPath() {
		return "kiosk/data/films/" + pic;
	}

	public int getDuration() {
		return duration;
	}
	public String getName() {
		return name;
	}
	public ArrayList<Integer> getScreen() {
		return screen;
	}
	public ArrayList<String> getShowtime() {
		return showtime;
	}
	public ArrayList<String> getInfo() {
		return info;
	}
	

}