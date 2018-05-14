
package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class End {
    private int x ;
    private int y;
    public End(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics2D g){
        g.setColor(Color.ORANGE);
	g.fillRect(x, y, 400, 5);
    }
}
