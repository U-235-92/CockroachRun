package com.cr.core;

import java.util.concurrent.TimeUnit;

import javax.swing.JButton;

import com.cr.swing.GUIConstructror;

public class Referee extends Thread {
	
	private GUIConstructror gui_constructor;
	
	private JButton button;
	
	private int countdown = 3;
	
	public Referee(GUIConstructror gui_constructor, JButton button) {
		
		this.gui_constructor = gui_constructor;
		
		this.button = button;
	}
	
	@Override
	public void run() {
		
		while(countdown != 0) {
			
			button.setEnabled(false);
			
			System.out.println(countdown);
			
			countdown--;
			
			try {
				
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch(InterruptedException exc) {
				
				exc.printStackTrace();
			}
		}
		
		System.out.println("Start!");
		
		button.setEnabled(true);
		
		for(Cockroach c : gui_constructor.getCockroach_set()) {
			
			Thread th = new Thread(c);
			th.start();
		}
		
		int max = 0;
		
		while(gui_constructor.is_start_game()) {
			
			for(Cockroach c : gui_constructor.getCockroach_set()) {
				
				if(c.getX() > max) {
					
					max = c.getX();
					c.setLeader(true);
					
					if(c.isLeader()) {
						
						gui_constructor.getjLeaderLBL().setText("Current leader is: " + c.getName());
					}
				}
			}
		}
	}
}
