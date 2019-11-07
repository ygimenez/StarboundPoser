package com.kuuhaku;

import javax.swing.*;
import java.awt.*;

import static com.kuuhaku.Poser.*;

public class Canvas extends JComponent {

	//298, 286
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.clearRect(0, 0, 512, 512);
		g2d.drawImage(BODY, 0, 0, null);

		g2d.translate(298, 286);
		g2d.rotate(Math.toRadians(armAngle));
		g2d.drawImage(ARM, -298, -286, null);
		g2d.rotate(-Math.toRadians(armAngle));
		g2d.translate(0, 0);
	}
}
