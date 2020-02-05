package src.screen;

import java.util.*;
import java.io.*;

public class rwfile {
	public String[] readFl(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line = "";
		List<String> list = new ArrayList<String>();
		while ((line = reader.readLine()) != null) {
			list.add(line);
		}
		reader.close();

		System.out.println(list);
		// delete blank elements.
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals("")) {
				list.remove(i);
				i--;
			}
		}
		System.out.println(list);

		String[] num = list.toArray(new String[list.size()]);

		return num;
	}
}