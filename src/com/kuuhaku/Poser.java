package com.kuuhaku;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Poser extends JFrame {
	private Canvas canvas;
	private JPanel container;
	private JSpinner wpnController;
	private JSpinner armController;
	private Graphics2D g2d = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB).createGraphics();
	static BufferedImage ARM;
	static BufferedImage BODY;
	static int armAngle;
	static int wpnAngle;

	Poser() {
		try {
			ARM = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("com/kuuhaku/assets/arm.png")));
			BODY = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("com/kuuhaku/assets/body.png")));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Oops, an error has ocurred: " + e);
			ARM = null;
			BODY = null;
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

		wpnController.addChangeListener(e -> {
			wpnAngle = (int) wpnController.getValue();
			canvas.repaint();
		});

		setSize(700, 540);
		setResizable(false);
		setVisible(true);
		canvas.repaint();
	}

	private void createUIComponents() {
		canvas = new Canvas();
	}
}
