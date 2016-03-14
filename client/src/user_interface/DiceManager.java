package user_interface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DiceManager extends JPanel implements MouseListener {

	
	 //TODO SISTEMARLO NEL SERVER
	 private JLabel diceLabel;
	 private int diceValue;
	 
	 
	 public DiceManager(GameWindow wnd){
		 
		 int ww=wnd.getWidth();
		 int wh=wnd.getHeight();
		 
		 this.setOpaque(false);
		 this.setBounds(ww*2/5, wh*2/3, ww*3/5, wh/3);
		 this.setVisible(false);
		 this.addMouseListener(this);
		 this.setLayout(null);
		 
		 diceLabel = new JLabel();
		 diceLabel.setVisible(true);
		 diceLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		 add(diceLabel);
		 
	 }
	 
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
			this.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
	 
	public void rollDice(){

		 diceValue=(int)(Math.random()*6+1);
		 diceLabel.setText("Il tuo numero e' "+diceValue);
		 this.setVisible(true);
	}
	
	public int getDiceValue(){
		return diceValue;
	}
}
