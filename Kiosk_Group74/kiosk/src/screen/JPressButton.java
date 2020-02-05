package src.screen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.plaf.metal.MetalToggleButtonUI;

/**
 * This is a generic class for seat buttons.
 * 
 * @author Zhouliang Zhang
 * @version 2.0
 */
public class JPressButton extends JToggleButton {

	private Color selectBackground;
	public boolean flagSelected = false;

	public JPressButton() {
		super();
//		this.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				flagSelected = !flagSelected;
//			}
//		});
		this.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
			        System.out.println("button is selected");
			      } else if(e.getStateChange()==ItemEvent.DESELECTED){
			        System.out.println("button is not selected");
			      }
			}
		});
	}

	@Override
	public void updateUI() {
		setUI(new PressButtonUI());
	}

	public Color getSelectBackground() {
		return selectBackground;
	}

	public void setSelectBackground(Color selectBackground) {
		this.selectBackground = selectBackground;
		updateUI();
	}

	public boolean isFlagSelected() {
		return flagSelected;
	}

	public void setFlagSelected(boolean flagSelected) {
		this.flagSelected = flagSelected;
	}

	class PressButtonUI extends MetalToggleButtonUI {
		@Override
		public void installDefaults(AbstractButton b) {
			super.installDefaults(b);
			JPressButton pressButton = (JPressButton) b;
			selectColor = pressButton.getSelectBackground();
		}
	}

}