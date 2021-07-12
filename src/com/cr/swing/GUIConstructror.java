package com.cr.swing;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.cr.core.Cockroach;
import com.cr.core.Referee;

public class GUIConstructror {

	private volatile boolean is_start_game = false;
	
	private Set<Cockroach> cockroach_set = new HashSet<>();
	
	private JFrame jMainFRM;
	
	private JButtonPanel jButtonPNL;
	
	private JScrollPanel jScrollPNL;
	
	private JPanel jEnterPNL;
	private JPanel jCardPNL;
	
	private JScrollPane jScrollP;
	
	private JTextField jEnterTF;
	
	private JLabel jEnterLBL;
	private JLabel jLeaderLBL;
	
	private JButton jEnterBTN;
	private JButton jStartBTN;
	
	private int count_roads;

	public void runGUI() {
		
		SwingUtilities.invokeLater(() -> {
			
			CardLayout cardLayout = new CardLayout();
			
			jMainFRM = new JFrame("Cockroach_Run");
			
			jScrollPNL = new JScrollPanel();
			jCardPNL = new JPanel(cardLayout);
			jEnterPNL = new JPanel();
			jButtonPNL = new JButtonPanel();
			
			jScrollP = new JScrollPane(jScrollPNL);
			
			jEnterTF = new JTextField(10);
			
			jEnterLBL = new JLabel("Enter count of roads:");
			jLeaderLBL = new JLabel();
			
			jEnterBTN = new JButton("Enter");
			jStartBTN = new JButton("Start");
			
			jEnterPNL.setName("Enter");
			jScrollPNL.setName("Main");
			jScrollP.setName("Scroll");
			
			setFrame(jMainFRM);
			
			jEnterPNL.add(jEnterLBL, BorderLayout.CENTER);
			jEnterPNL.add(jEnterTF, BorderLayout.CENTER);
			jEnterPNL.add(jEnterBTN, BorderLayout.CENTER);
			
			jCardPNL.add(jEnterPNL.getName(), jEnterPNL);
			jCardPNL.add(jScrollP.getName(), jScrollP);
			
			jMainFRM.add(jCardPNL);
			
			jEnterBTN.addActionListener((ae) -> {
				
//				int count_roads = 0;
				
				boolean is_enter = false;
				
				try {
					
					count_roads = Integer.valueOf(jEnterTF.getText());
					
					is_enter = true;
					
				} catch(NumberFormatException exc) {
					
					System.err.println("¬ведены некорректные данные!");
				}
				
				jScrollPNL.setCount_road(count_roads);
				
				for(int i = 0; i < jScrollPNL.getCount_road(); i++) {
					
					JRoadPanel roadPanel = new JRoadPanel(i + 1);
					
					Cockroach cockroach = new Cockroach(101, JScrollPanel.HEIGHT_ROAD / 2, roadPanel, this);
					
					cockroach_set.add(cockroach);
					
					roadPanel.setCockroach(cockroach);
					
					jScrollPNL.add(roadPanel);
				}
				
				jButtonPNL.add(jStartBTN);
				
				jScrollPNL.add(jButtonPNL);
				
				if(is_enter) {
					
					cardLayout.show(jCardPNL, jScrollP.getName());
				}
			});
			
			jStartBTN.addActionListener((ae) -> {
				
				if(!is_start_game) {
					
					is_start_game = true;
					
					Referee referee = new Referee(this, jStartBTN);
					referee.start();
					
					jButtonPNL.add(jLeaderLBL);
					
					jStartBTN.setText("Restart");
					
				} else {
					
					is_start_game = false;
					
					jStartBTN.setText("Start");
					
					for(Cockroach c : cockroach_set) {
						
						c.setX(Cockroach.START_POINT);
						
						c.getjRoadPNL().repaint();
					}
				}
			});	
		});
	}

	public int getCount_roads() {
		return count_roads;
	}

	public Set<Cockroach> getCockroach_set() {
		return cockroach_set;
	}

	public boolean is_start_game() {
		return is_start_game;
	}

	public void set_start_game(boolean is_start_game) {
		this.is_start_game = is_start_game;
	}

	public JLabel getjLeaderLBL() {
		return jLeaderLBL;
	}

	private void setFrame(JFrame jFRM) {
		
		jFRM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFRM.setSize(new Dimension(600, 400));
		jFRM.setResizable(false);
		jFRM.setLocationRelativeTo(null);
		jFRM.setVisible(true);
	}
}
