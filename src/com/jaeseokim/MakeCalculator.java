package com.jaeseokim;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MakeCalculator extends JFrame implements ActionListener{
	final String[] btn_text = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
	JTextField tf = new JTextField("");
	JButton[] btn = new JButton[btn_text.length];
	
	private void MakeLayout() {
		add(tf,BorderLayout.NORTH);
		tf.setFont(new Font("고딕",Font.PLAIN,50));
		JPanel pBtn = new JPanel(new GridLayout(4,0));
		for(int i=0; i<btn_text.length;i++) {
			pBtn.add(btn[i] = new JButton(btn_text[i]));
			btn[i].setFont(new Font("고딕",Font.PLAIN,25));
			btn[i].addActionListener(this);
		}
		add(pBtn);
	}
	
	public MakeCalculator() {
		MakeLayout();
		ImageIcon icon = new ImageIcon("icon.png");
		setIconImage(icon.getImage());
		setTitle("JCalculator");
		setSize(400 ,700);
		setLocation(400,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sum = null,symbol;
		switch (e.getActionCommand()) {
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
			sum = tf.getText();
			sum+=e.getActionCommand();
			tf.setText(sum);
			break;
		
		case ".":	
		case "+":
		case "-":
		case "*":
		case "/":
			symbol = Character.toString(tf.getText().charAt(tf.getText().length()-1));
			switch (symbol) {
			case ".":
			case "+":
			case "-":
			case "*":
			case "/":
				String tmp = "";
				sum = tf.getText();
				for(int i=0; i<sum.length()-1;i++) {
					tmp+=sum.charAt(i);
				}
				tmp+=e.getActionCommand();
				tf.setText(tmp);
				break;

			default:
				sum = tf.getText();
				sum+=e.getActionCommand();
				tf.setText(sum);
				break;
			}
			break;
		case "=":
			sum = tf.getText();
			String nums[] = sum.split("\\s*[+|-|*|/]+");
			sum = sum.replaceAll("[0-9]", "");
			String symbols[] = sum.split(" ");
			double answer = Double.parseDouble(nums[0]);
			for(int i =0; i<symbols.length;i++) {
				switch (symbols[i]) {
				case "+":
					answer += Double.parseDouble(nums[i+1]);
					break;
				case "-":
					answer -= Double.parseDouble(nums[i+1]);
					break;
				case "*":
					answer *= Double.parseDouble(nums[i+1]);					
					break;
				case "/":
					answer /= Double.parseDouble(nums[i+1]);
					break;

				default:
					break;
				}
			}
			tf.setText(Double.toString(answer));
			break;
		default:
			break;
		}
		
	}

}
 