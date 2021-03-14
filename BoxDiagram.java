package week5.boxdiagram;

import acm.graphics.*;
import java.util.*;
import acm.program.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BoxDiagram extends GraphicsProgram {
	public void init() {
		lb=new JLabel("Name");
		tf = new JTextField(20);
	    addButton=new JButton("Add");
		removeButton=new JButton("Remove");
	    clearButton=new JButton("Clear");
		add(lb,SOUTH);
		add(tf,SOUTH);
		add(addButton,SOUTH);
		add(removeButton,SOUTH);
		add(clearButton,SOUTH);
		
		tf.addActionListener(this);
		addActionListeners();
		addMouseListeners();
		
		items= new HashMap<String,GObject>();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object actionSource = e.getSource();
		if((actionSource==tf) || (actionSource== addButton)){
			addLabelBox(tf.getText());
		} else if (actionSource == removeButton) {
			removeLabelBox(tf.getText());
		} else if (actionSource == clearButton) {
			clearAllLabelBox(tf.getText());
		}
	}
	
	public void addLabelBox(String text) {
	    labelBox = new GCompound();
		GRect rect = new GRect(BOX_WIDTH,BOX_HEIGHT);
		GLabel label = new GLabel(text);
		labelBox.add(rect, -BOX_WIDTH / 2, -BOX_HEIGHT / 2);
		labelBox.add(label,-label.getWidth() / 2, label.getHeight() / 2);
		add(labelBox,(getWidth() / 2), (getHeight() / 2));
		items.put(text,labelBox);
		
	}	
	
	public void removeLabelBox(String text) {
		gobj = items.get(text);
		if (gobj != null) {
			remove(gobj);
		}
	}
	
	public void clearAllLabelBox(String text) {
		Iterator <String> it = items.keySet().iterator();
	    while(it.hasNext()) {
	    	removeLabelBox(it.next());
	    } 
	}

	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last); 
	}
	
	public void mouseDragged(MouseEvent e) {
		if (gobj != null) {
			gobj.move(e.getX()-last.getX(),e.getY()-last.getY());	
			last = new GPoint(e.getPoint());		 
		}
	}

	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	private HashMap<String,GObject> items;
	private JTextField tf;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private JLabel lb;
	private GPoint last;

	private GObject gobj;
	GCompound labelBox ; 
}

	

