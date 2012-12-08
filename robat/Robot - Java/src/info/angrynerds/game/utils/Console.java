package info.angrynerds.game.utils;

import info.angrynerds.game.framework.State;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public abstract class Console {
	private static JFrame frame;
	private static JTextArea area;
	
	private static boolean showing = true;
	
	public static void setVisible(boolean visible) {
		if(frame == null) {
			frame = new JFrame("Robot Tycoon: Console");
			area = new JTextArea(10, 20);
			area.setFont(new Font(Font.MONOSPACED,
					Font.PLAIN, 12));
			area.setBackground(new Color(72, 209, 204));
			area.setForeground(Color.BLACK);
			area.setLineWrap(true);
			area.setWrapStyleWord(true);
			area.setEditable(false);
			area.addKeyListener(new AreaListener());
			frame.getContentPane().add(BorderLayout.CENTER,
					new JScrollPane(area));
			frame.setBounds(500, 50, 500, 500);
			println("//////////////////////////////////////");
			println("// rõbä† Console                    //");
			println("// Authors: Daniel Glus, John Lhota //");
			println("//     Miles Shebar                 //");
			println("//////////////////////////////////////");
			println("Keyboard commands:");
			println("c\tClears the window.");
			println("q\tQuits the game!");
			println("v\tToggles whether to show output.");
			println("0\tWelcome.");
			println("1\tFactory.");
			println("2\tMap.");
			println("3\tStore.");
			println("===STARTING APPLICATION===");
		}
		frame.setVisible(visible);
	}
	
	public static void println(Object o) {
		if(showing) {
		String str = o.toString();
		if(!str.endsWith("\n")) 		// If a string doesn't have a newline at the end...
			str = str + "\n";			// Add a newline
		area.append(str);
		}
	}
	
	private static class AreaListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_C) {
				area.setText("");
			} else if(e.getKeyCode() == KeyEvent.VK_Q) {
				System.exit(0);
			} else if(e.getKeyCode() == KeyEvent.VK_V) {
				showing = !showing;
				println("We are now showing output? " + showing + ".");
			}
			else if(e.getKeyCode() == KeyEvent.VK_0) {
				State.switchState("welcome");
			}
			else if(e.getKeyCode() == KeyEvent.VK_1) {
				State.switchState("factory");
			}
			else if(e.getKeyCode() == KeyEvent.VK_2) {
				State.switchState("map");
			}
			else if(e.getKeyCode() == KeyEvent.VK_3) {
				State.switchState("store");
			}
		}

		public void keyReleased(KeyEvent e) {
			
		}

		public void keyTyped(KeyEvent e) {
			
		}
	}
}