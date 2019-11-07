package com.kuuhaku;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Poser extends JFrame {
	private Canvas canvas;
	private JPanel container;
	private JSpinner wpnAngController;
	private JSpinner armController;
	private JSpinner wpnXOffset;
	private JCheckBox centerWeapon;
	private JSpinner wpnYOffset;
	private JButton chooseWpn;
	static BufferedImage ARM;
	static BufferedImage BODY;
	static BufferedImage WEAPON;
	static int armAngle = -90;
	static int wpnAngle = -10;
	static float[] wpnOffset = new float[]{0, -1.8f};
	static boolean centerWpn = true;

	Poser() {
		try {
			ARM = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("com/kuuhaku/assets/arm.png")));
			BODY = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("com/kuuhaku/assets/body.png")));
			WEAPON = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("com/kuuhaku/assets/weapon.png")));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Oops, an error has ocurred: " + e);
			ARM = null;
			BODY = null;
			WEAPON = null;
		}

		add(container);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		armController.addChangeListener(e -> {
			armAngle = (int) armController.getValue();
			canvas.repaint();
		});

		wpnAngController.addChangeListener(e -> {
			wpnAngle = (int) wpnAngController.getValue();
			canvas.repaint();
		});

		wpnXOffset.addChangeListener(e -> {
			wpnOffset[0] = (Float) wpnXOffset.getValue();
			canvas.repaint();
		});

		wpnYOffset.addChangeListener(e -> {
			wpnOffset[1] = (Float) wpnYOffset.getValue();
			canvas.repaint();
		});

		centerWeapon.addActionListener(e -> {
			centerWpn = centerWeapon.isSelected();
			canvas.repaint();
		});

		chooseWpn.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setFileFilter(new FileNameExtensionFilter("Image file", "jpeg", "jpg", "png"));
			if (chooser.showSaveDialog(null) == 	JFileChooser.APPROVE_OPTION) {
				try {
					Image chosenFile = ImageIO.read(chooser.getSelectedFile());
					chosenFile = chosenFile.getScaledInstance(Math.round(chosenFile.getWidth(null) * 11.8f), Math.round(chosenFile.getHeight(null) * 11.8f), Image.SCALE_SMOOTH);
					WEAPON = new BufferedImage(chosenFile.getWidth(null), chosenFile.getHeight(null), BufferedImage.TYPE_INT_ARGB);

					Graphics2D g2d = WEAPON.createGraphics();
					g2d.drawImage(chosenFile, 0, 0, null);
					g2d.dispose();
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, "Oops, an error has ocurred: " + ex);
				}
			}
			canvas.repaint();
		});

		setTitle("Starbound Poser");
		setSize(1006, 540);
		setLocationRelativeTo(null);
		setVisible(true);
		canvas.repaint();
	}

	private void createUIComponents() {
		armController = new JSpinner(new SpinnerNumberModel(-90, -360, 360, 1));
		wpnAngController = new JSpinner(new SpinnerNumberModel(-10, -360, 360, 1));
		wpnXOffset = new JSpinner(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(-256), Float.valueOf(256), Float.valueOf(0.1f)));
		wpnYOffset = new JSpinner(new SpinnerNumberModel(Float.valueOf(-1.8f), Float.valueOf(-256), Float.valueOf(256), Float.valueOf(0.1f)));
	}
}
