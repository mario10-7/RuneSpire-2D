package com.daybreak.Util;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.daybreak.Screens.GameScreen;


public class Log {

	ArrayList<String> entries;
	JFrame frame;
	JTextPane area;
	JScrollPane scroll;
	StyledDocument doc;
	Style style;
	
	public Log() {
		entries = new ArrayList<String>();
		frame = new JFrame();
		area = new JTextPane();
		doc = area.getStyledDocument();
		
		style = area.addStyle("Style",null);

		frame.setSize(400,400);
		frame.setVisible(true);
		frame.setTitle("RSCL Log");
		area.setEditable(false);
		
		JScrollPane areaScrollPane = new JScrollPane(area);
		areaScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		areaScrollPane.setPreferredSize(new Dimension(250,250));
		
		frame.add(areaScrollPane);
		
		
		
		for(String entry : entries){
			area.setText(area.getText()+entry+"\n");
		}
		
	}
	
	public void getCommand(){
		 Thread t = new Thread(new Runnable(){
		        public void run(){
		        	String option = JOptionPane.showInputDialog(null,"Enter an item ID");
		    		GameScreen.player.playerInventory.addItem(GameScreen.iManager.getItem(Integer.parseInt(option)));
		        }
		    });
		  t.start();
	}
	
	
	public void addEntry(String entry){
		try {
			StyleConstants.setForeground(style, Color.BLACK);
			doc.insertString(doc.getLength(),entry+"\n",style);
			entries.add(entry);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public void addEntry(String entry,Color color){
		try {
			StyleConstants.setForeground(style, color);
			doc.insertString(doc.getLength(), entry+"\n", style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public void addCoords(int x, int y){
		String entry = x+","+y;
		entries.add(entry);
		area.setText(area.getText()+entry+"\n");
	}
	
}
