package src.screen;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

/** This is the class for seat buttons.
 * @author Ziqian Chen
 * @version 3.1
 */

public class SeatButton extends JToggleButton {
	
	public SeatButton(){
//		this.setVisible(false);
//		this.setBackground(Color.RED);
//		if (this.isSelected()) {
//			this.setBackground(Color.YELLOW);
//		}
	}
	public SeatButton(String str){
		super(str);
		this.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent ev) {
				if(ev.getStateChange()==ItemEvent.SELECTED){
			        System.out.println("button is selected");
			        selected();
			      } else if(ev.getStateChange()==ItemEvent.DESELECTED){
			        System.out.println("button is not selected");
			      }
			}
		}
		);
		this.setBackground(Color.WHITE);
//		if (this.isSelected()) {
//			this.setBackground(Color.YELLOW);
//			System.out.println("laalalasdfaa");
//		}
	}
	
	public void selected(){
//		this.setVisible(false);
		System.out.println("button is selected2222");
    	this.setForeground(Color.CYAN);
		this.setBackground(Color.MAGENTA);
	}

}
