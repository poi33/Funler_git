package funler;

import processing.core.PApplet;

public class GodMode {
	PApplet parent;
	GodMode(PApplet parent) {
		this.parent = parent;
	}
	
	void showAll(int moveX, int moveY, Tile[][] tileMap) {
		float mapX = tileMap.length;
		float mapY = tileMap[0].length; 

		for (int i=(int)mapX-1; i>0; i--) {
			for (int j=(int)mapY-1; j>0; j--) {
				String we = i+"|"+j;
				parent.fill(255);
				parent.pushStyle();
				parent.textSize(10);
				parent.text(we, 50*(i-1)+moveX, 50*(j-1)+moveY+50);
				parent.popStyle();
				if (tileMap[i][j].getType() == 1) {
					if (50*(i+1)+moveX > 0 && 50*(j+1)+moveY >=0 && 50*i+moveX <parent.width && 50*j + moveY <parent.height) {
						float dx = mapX*50-parent.width/2;
						float dy = mapY*50-parent.height/2;
						if ((mapX-i)*50-dx-49 <= (int)moveX && (mapX-i)*50+49-dx >= (int)moveX) {
							parent.fill(50, 50, 200);
							parent.rect(i*50+moveX, j*50+moveY, 30, 30);
						}
						if (mapY*50-j*50-dy-49 <= (int)moveY && mapY*50-j*50+49-dy >= (int) moveY) {
							parent.fill(200, 50, 50);
							parent.rect(i*50+moveX, j*50+moveY, 40, 40);
						}
					}
				}
			}
		}
	}

}
