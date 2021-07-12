package com.cr.swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

public class JScrollPanel extends JPanel implements Scrollable {
	
	private static final long serialVersionUID = -3641569911142573633L;
	
	private int count_road;
	public static final int HEIGHT_ROAD = 100;
	public static final int WIDTH_ROAD = 600;
	
	public JScrollPanel() {
		
		count_road = 0; 
		
		setLayout(new GridLayout(count_road + 1, 1));
	}
	
//	public JScrollPanel(int count_road) {
//		
//		this.count_road = count_road; 
//		
//		setLayout(new GridLayout(count_road + 1, 1));
//	}
	
	public int getCount_road() {
		
		return count_road;
	}
	
	public void setCount_road(int count_road) {
		
		this.count_road = count_road; 
		
		setLayout(new GridLayout(count_road + 1, 1));
	}

	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(WIDTH_ROAD, (count_road + 1) * HEIGHT_ROAD);
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return getPreferredSize();
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return HEIGHT_ROAD;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return HEIGHT_ROAD;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}
}
