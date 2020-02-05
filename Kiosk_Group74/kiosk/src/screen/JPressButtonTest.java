package src.screen;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JFrame;

import org.junit.Test;

public class JPressButtonTest {

//	@Test
//	public void test() {
//		JFrame frame = new JFrame();
//		JPressButton button = new JPressButton();
//		button.setText("123");
//		button.setBackground(Color.green);
//		button.setSelectBackground(Color.yellow);
//		frame.setContentPane(button);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
//	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPressButton button = new JPressButton();
		button.setText("123");
		button.setBackground(Color.green);
		button.setSelectBackground(Color.yellow);
		frame.setContentPane(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
