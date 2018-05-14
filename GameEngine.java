package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        private ArrayList<Enemy2> enemies2 = new ArrayList<Enemy2>();
	private SpaceShip v1;
        private SpaceShip2 v2;
	
	private Timer timer;
        private boolean d_v1 = true;
        private boolean d_v2 = true;
	
	private long score = 0;
        private long level = 0;
	private double difficulty = 0.1;
        private int count = 0;
	
	public GameEngine(GamePanel gp, SpaceShip v1, SpaceShip2 v2) {
		this.gp = gp;
		this.v1 = v1;
                this.v2 = v2;
		
		gp.sprites.add(v1);
                gp.sprites.add(v2);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
                                count++;
                                if(count >= 300){
                                    count =0;
                                    difficulty += 0.1;
                                    level++;
                                }
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
                Enemy2 e2 = new Enemy2((int)(Math.random()*390), 30);
		gp.sprites.add(e);
                gp.sprites.add(e2);
		enemies.add(e);
                enemies2.add(e2);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
                Iterator<Enemy2> e2_iter = enemies2.iterator();
		while(e_iter.hasNext()){
                    Enemy e = e_iter.next();
                    e.proceed();
                    if(!e.isAlive()){
			e_iter.remove();
			gp.sprites.remove(e);
			score += 50;
                    }
                }
                while(e2_iter.hasNext()){
                    Enemy2 e2 = e2_iter.next();
                    e2.proceed();
                    if(!e2.isAlive()){
			e2_iter.remove();
			gp.sprites.remove(e2);
			score += 10;
                    }
		}
		
		gp.updateGameUI(this, getLevel());
		
		Rectangle2D.Double vr = v1.getRectangle();
                Rectangle2D.Double vv = v2.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				d_v1 = false;
                                die();
                                return;
			}
                        if(er.intersects(vv)){
				d_v2 = false;
                                die();
                                return;
			}
		}
                for(Enemy2 e2 : enemies2){
			er = e2.getRectangle();
			if(er.intersects(vr)){
				d_v1 = false;
                                die();
                                return;
			}
                        if(er.intersects(vv)){
				d_v2 = false;
                                die();
                                return;
			}
		}
	}
	
	public void die(){

            if(!d_v1 && !d_v2){
                timer.stop();
                GOver over = new GOver();
            }

	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
                    if(d_v1)
                        v1.move(-1);
                    break;
		case KeyEvent.VK_RIGHT:
                    if(d_v1)
			v1.move(1);
                    break;
                case KeyEvent.VK_A:
                    if(d_v2)
			v2.move(-1);
                    break;
		case KeyEvent.VK_D:
                    if(d_v2)
			v2.move(1);
                    break;
		case KeyEvent.VK_J:
			difficulty += 0.1;
                        level++;
			break;
		}
	}

	public long getScore(){
            if(d_v1 && d_v2)
		return score*2;
            else
                return score;
	}
        
        public long getLevel(){
		return level;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}