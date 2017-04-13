import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Component;
import java.awt.Font;

public class MainActivity extends JFrame{
	
	private JTextField txtAuto_bmw;
	private JTextField txtAuto_audi;
	private JFrame frame;
	private JProgressBar pb1 = new JProgressBar(0, 100);
	private JProgressBar pb2 = new JProgressBar(0, 100);
	private Random random1 = new Random();
	private Random random2 = new Random();
	private int counter = 0;
	private JButton btnStart;
	private JLabel label_bmw;
	private JLabel label_audi;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainActivity ma = new MainActivity();
					ma.frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainActivity() {
		initialize();
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 500, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtAuto_bmw = new JTextField();
		txtAuto_bmw.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		txtAuto_bmw.setBounds(88, 175, 146, 29);
		txtAuto_bmw.setText("BMW");
		txtAuto_bmw.setEditable(false);
		txtAuto_bmw.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(txtAuto_bmw);
		txtAuto_bmw.setColumns(10);
		
		txtAuto_audi = new JTextField();
		txtAuto_audi.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		txtAuto_audi.setBounds(350, 175, 146, 29);
		txtAuto_audi.setText("Audi");
		txtAuto_audi.setEditable(false);
		txtAuto_audi.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(txtAuto_audi);
		txtAuto_audi.setColumns(10);
		
		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnStart.setBounds(230, 276, 130, 36);
		btnStart.setVisible(true);
		btnStart.addActionListener(new StartRace());
		frame.getContentPane().add(btnStart);
		pb1.setBounds(88, 215, 146, 31);
		
		pb1.setStringPainted(true);
		pb1.setForeground(Color.YELLOW);
		frame.getContentPane().add(pb1);
		pb2.setBounds(350, 215, 146, 31);
		
		pb2.setStringPainted(true);
		pb2.setForeground(Color.BLUE);
		frame.getContentPane().add(pb2);
		
		label_bmw = new JLabel("");
		label_bmw.setIcon(new ImageIcon(MainActivity.class.getResource("/Images/bmw.jpg")));
		label_bmw.setBounds(56, 11, 216, 162);
		frame.getContentPane().add(label_bmw);
		
		label_audi = new JLabel("");
		label_audi.setIcon(new ImageIcon(MainActivity.class.getResource("/Images/audi.jpg")));
		label_audi.setBounds(328, 11, 216, 162);
		frame.getContentPane().add(label_audi);
		
	}
	
	class StartRace implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg) {
			setDefaultValues();
			
			BMW bmw = new BMW();
			bmw.start();
			
			Audi audi = new Audi();
			Thread audi_a4 = new Thread(audi);
			audi_a4.start();
		}
	}
	
	class BMW extends Thread{
		public void run() {
			for(int i = 0; i < 101; i++) {
				pb2.setValue(i);
				pb2.repaint();
				pb2.setString(Integer.toString(i) + "%");
				
				try {
					Thread.sleep(random2.nextInt(50) + 10);
				}catch(InterruptedException err) {
					err.printStackTrace();
				}
				if(i == 100) {
					whoIsBetter("BMW");
				}
			}
		}
	}
	
	class Audi implements Runnable{
		public void run() {
			for(int i = 0; i < 101; i++) {
				pb1.setValue(i);
				pb1.repaint();
				pb1.setString(Integer.toString(i) + "%");
				
				try {
					Thread.sleep(random1.nextInt(50) + 10);
				}catch(InterruptedException err) {
					err.printStackTrace();
				}
				if(i == 100) {
					whoIsBetter("Audi");
				}
			}
		}
	}
	
	public void setDefaultValues() {
		txtAuto_bmw.setText("BMW");
		txtAuto_audi.setText("Audi");
		btnStart.setText("Racing...");
		btnStart.setBorderPainted(false);
		btnStart.setEnabled(false);
		counter = 0;
	}
	
	public void whoIsBetter(String str) {
		counter++;
		if(str.equals("Audi") && counter == 1){
			txtAuto_bmw.setText("WINNER (1st)");
		}else if(str.equals("BMW") && counter == 1){
			txtAuto_audi.setText("WINNER (1st)");
		}else if(str.equals("Audi") && counter == 2){
			txtAuto_bmw.setText("LOSER (2nd)");
			btnStart.setText("Start");
			btnStart.setBorderPainted(true);
			btnStart.setEnabled(true);
		}else{
			txtAuto_audi.setText("LOSER (2nd)");
			btnStart.setText("Start");
			btnStart.setBorderPainted(true);
			btnStart.setEnabled(true);
		}
	}
}

