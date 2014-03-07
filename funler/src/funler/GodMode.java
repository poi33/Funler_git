package funler;

public class GodMode extends Funler {	  
	void showAll(int moveX, int moveY) {
		float mapX = numMap.length;
		float mapY = numMap[0].length; 

		for (int i=(int)mapX-1; i>0; i--) {
			for (int j=(int)mapY-1; j>0; j--) {
				String w = i+" | "+(j);
				fill(255);
				text(w, 50*(i-1)+moveX, 50*(j-1)+moveY+20);
				if (numMap[i][j] == 1) {
					if (50*(i+1)+moveX > 0 && 50*(j+1)+moveY >=0 && 50*i+moveX <width && 50*j + moveY <height) {
						float dx = mapX*50-width/2;
						float dy = mapY*50-height/2;
						if ((mapX-i)*50-dx-49 <= (int)moveX && (mapX-i)*50+49-dx >= (int)moveX) {
							fill(50, 50, 200);
							rect(i*50+moveX, j*50+moveY, 30, 30);
						}
						if (mapY*50-j*50-dy-49 <= (int)moveY && mapY*50-j*50+49-dy >= (int) moveY) {
							fill(200, 50, 50);
							rect(i*50+moveX, j*50+moveY, 40, 40);
						}
					}
				}
			}
		}
		//For viewing where enemys can move (not path finding)
		//enemy[0].canHit();
	}

}
