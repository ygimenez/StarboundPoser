package com.kuuhaku;

import javax.swing.*;
import java.awt.*;

import static com.kuuhaku.Poser.*;

public class Canvas extends JComponent {

	//298, 286
	//12, 83
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.clearRect(0, 0, 512, 512);
		g2d.drawImage(BODY, 0, 0, null);
		//START ARM ALIGNMENT
		g2d.translate(298, 286);
		g2d.rotate(Math.toRadians(armAngle + 90));
		//START WEAPON ALIGNMENT
		g2d.translate(12, 83);
		g2d.rotate(Math.toRadians(wpnAngle - 90));
		g2d.drawImage(WEAPON, (centerWpn ? -WEAPON.getWidth() / 2 : -WEAPON.getWidth()) + (Math.round(wpnOffset[0] * 10) * 11), (centerWpn ? -WEAPON.getHeight() / 2 : -WEAPON.getHeight()) + (Math.round(wpnOffset[1] * 10) * 11), null);
		//END WEAPON ALIGNMENT
		g2d.rotate(-Math.toRadians(wpnAngle + 270));
		g2d.translate(-12, -83);
		//END ARM ALIGNMENT
		g2d.drawImage(ARM, -298, -286, null);
		g2d.rotate(-Math.toRadians(armAngle - 90));
	}
}
