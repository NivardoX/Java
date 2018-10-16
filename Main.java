package pong;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {

		double dif = 1.5;
		int count_enemy = 0;
		int count_player = 0;

		UIManager UI = new UIManager();
		UI.put("OptionPane.background", Color.GRAY);
		UI.put("Panel.background", Color.GRAY);
		
		

		// Start Screen
		String inputString = JOptionPane.showInputDialog(null, "Escolha a dificuldade", "Dificuldade",
				JOptionPane.INFORMATION_MESSAGE);
		int input = Integer.parseInt(inputString);
		if (input == 0)
			input = 1;
		System.out.println("Dificuldade escolhida: " + input);

		// Game Screen
		Game pong = new Game(count_player, count_enemy, input);
	
		JFrame frame = new JFrame("Pong");
		frame.setFocusable(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pong);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.NORMAL);
		frame.toFront();
		frame.setAlwaysOnTop(true);
	
		JOptionPane.showMessageDialog(frame, "O jogo irá iniciar!",
				"Iniciar o jogo.", JOptionPane.WARNING_MESSAGE);
		pong.requestFocus();
		new Thread(pong).start();
	}
}
