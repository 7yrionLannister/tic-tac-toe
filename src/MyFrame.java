import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame implements ActionListener {
	private JButton square[][] = new JButton[3][3];
	private Icon X, O, white;
	private int alterna = 0;
	private int table = 0;

	public MyFrame() {
		setLayout(new GridLayout(3,3));

		X = new ImageIcon("X.PNG");
		O = new ImageIcon("O.PNG");
		white = new ImageIcon("white.JPG");

		for(int i = 0; i < square.length; i++) {
			for(int o = 0; o < square[i].length; o++) {
				square[i][o] = new JButton(white);
				square[i][o].addActionListener(this);
				add(square[i][o]);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		alterna++;
		((AbstractButton) e.getSource()).setEnabled(false);
		if(alterna % 2 == 0) {
			((AbstractButton) e.getSource()).setIcon(O);
			((AbstractButton) e.getSource()).setToolTipText("O");
		}
		else {
			((AbstractButton) e.getSource()).setIcon(X);
			((AbstractButton) e.getSource()).setToolTipText("X");
		}

		for(int u = 0; u < square.length; u++) {
			for(int c = 0; c < square[u].length; c++) {
				if(square[u][c].isEnabled() == false) {
					table++;
				}
			}
		}

		//Tres en linea
		if(alterna >= 5) {
			for(int b = 0; b < square.length; b++) {

				//Filas
				if(square[b][0].getIcon() == square[b][1].getIcon() && square[b][1].getIcon() == square[b][2].getIcon() && 
						square[b][0].getIcon() != white) {
					JOptionPane.showMessageDialog(this, String.format("The winner is %s", square[b][0].getToolTipText()));
					cleanTable();
				}
				//Columnas
				if(square[0][b].getIcon() == square[1][b].getIcon() && square[1][b].getIcon() == square[2][b].getIcon() && 
						square[0][b].getIcon() != white) {
					JOptionPane.showMessageDialog(this, String.format("The winner is %s", square[0][b].getToolTipText()));
					cleanTable();
				}
			}
			//Diagonal1
			if(square[0][0].getIcon() == square[1][1].getIcon() && square[1][1].getIcon() == square[2][2].getIcon() && 
					square[1][1].getIcon() == ((AbstractButton) e.getSource()).getIcon()) {
				JOptionPane.showMessageDialog(this, String.format("The winner is %s", square[1][1].getToolTipText()));
				cleanTable();
			}
			//Diagonal2
			if(square[0][2].getIcon() == square[1][1].getIcon() && square[1][1].getIcon() == square[2][0].getIcon() && 
					square[0][0].getIcon() == ((AbstractButton) e.getSource()).getIcon()) {
				JOptionPane.showMessageDialog(this, String.format("The winner is %s", square[1][1].getToolTipText()));
				cleanTable();
			}
		}

		if(table == 9) {
			JOptionPane.showMessageDialog(this, "It's a draw");
			cleanTable();
		}
		else
			table = 0;
	}

	private void cleanTable() {
		alterna = 0;
		table = 0;
		for(int i = 0; i < square.length; i++) {
			for(int o = 0; o < square[i].length; o++) {
				square[i][o].setEnabled(true);
				square[i][o].setIcon(white);
			}
		}
	}
}
