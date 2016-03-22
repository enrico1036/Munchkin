package Prova;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements MouseListener, MouseMotionListener,ActionListener {

	
	private JButton myCard;
	private JPanel a,b,c,d;
	public MyPanel(){
		
		
		a= new JPanel();
		a.setName("a");
		a.setBounds(0, 0, 250, 250);
		a.setVisible(true);
		a.setOpaque(false);
		
		add(a);
		
		b= new JPanel();
		b.setName("b");
		b.setBounds(250, 0,250, 250);
		b.setVisible(true);
		b.setOpaque(false);
		add(b);
		
		c= new JPanel();
		c.setName("c");
		c.setBounds(0, 250,250,250);
		c.setVisible(true);
		c.setOpaque(false);
		add(c);
		
		d= new JPanel();
		d.setName("d");
		d.setBounds(250, 250, 250, 250);
		d.setVisible(true);
		d.setOpaque(false);
		add(d);
		
		
		myCard = new JButton("MARCO CARTA");
		myCard.setBounds(260,260,100,100);
		myCard.setActionCommand("CLICK");
		myCard.addActionListener(this);
		myCard.addMouseMotionListener(this);
		myCard.addMouseListener(this);
		add(myCard);
		addMouseMotionListener(this);
		addMouseListener(this);
		
		
	}

	int globalX, globalY = 0;
	boolean inButton = false;

	@Override
	public void mouseDragged(MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		globalX = e.getX() + c.getX();
		globalY = e.getY() + c.getY();

		if (inButton)
			myCard.setLocation(globalX - myCard.getWidth() / 2, globalY - myCard.getHeight() / 2);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("CLICK")) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					int cycles = 20;
					int velX = (getWidth()/2 - globalX) / cycles;
					int velY = (getHeight()/2 - globalY) / cycles;
					
					for(int i=0; i<cycles; i++){
						myCard.setLocation(myCard.getX()+velX, myCard.getY()+velY);
						
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(inButton){
			//System.out.println(e.getSource().getClass().getName());
			
			
			/* Raga allora ho visto che con i metodi getComponent e GgetSource
			 * non funzia perche restituisce il jbutton
			 * allora ho provato con la posizione che invece funziona
			 * 
			 * solo che secondo me è un po' una porcata
			 * infatti volevo fa in modo che i singoli pannelli implementassero il
			 * metodo mouseListener e non il pannellone MyPanel come è adesso.
			 */
			
			
		
			if(myCard.getX()<=250){
				if(myCard.getY()<=250){
					Action.mouseUp(a);
				}
				else
					Action.mouseUp(c);
			}else{
				if(myCard.getY()<=250){
					Action.mouseUp(b);
			}
				else
					Action.mouseUp(d);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		inButton = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		inButton = false;
	}

}

