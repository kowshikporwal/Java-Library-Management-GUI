package com.kowshik061195.library.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class LoadScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JButton jbNew, jbLoad, jbExit;
	private JLabel jlMessage;

	public LoadScreen(String title){
		super(title);
		initWidgets();
		addWidgets();
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(320,115);
		setResizable(false);
		
		
		
	}

	private void initWidgets() {
		panel = new JPanel(new FlowLayout());

		jlMessage = new JLabel(
				"Would ypu like to start a new library? or laod one up?");
		
	    jbNew = new JButton("Create a new library");
	    jbLoad = new JButton("Load a saved library");
	    jbExit = new JButton("Exit");
	    

	}

	private void addWidgets() {
      panel.add(jlMessage);
      panel.add(jbLoad);
      panel.add(jbNew);
      panel.add(jbExit);
      
      panel.setBackground(new Color(194,230,248));
      setContentPane(panel);
      
		
	}
	
	public void addActionListener(ActionListener l){
		jbNew.addActionListener(l);
		jbLoad.addActionListener(l);
		jbExit.addActionListener(l);
		
	}
	
	public JButton getButtonNew(){
		return jbNew;
	}
	public JButton getButtonLoad(){
		return jbLoad;
	}
	public JButton getButtonExit(){
		return jbExit;
	}
	

}
