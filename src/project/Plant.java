package project;


import java.awt.Color;
import java.util.List;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import naturesimulator.Action.Type;
import ui.GridPanel;
/**
 * 
 * @author M. Erdinc Oguz
 *
 */
public class Plant extends Creature{

	public final double MAX_HP = 1.0;
	/**
	 * Creates new plant at given location and with given health using parent's constructor
	 * @param x position of the location
	 * @param y position of the location
	 */
	public Plant(int x, int y) {
		super(0.5);
		setX(x);
		setY(y);
	}
	public Plant(double m) {
		super(m);
	}
	@Override
	public Action chooseAction(LocalInformation localInfo) {
		//checking health and around the creature for free directions to produce 
		if(getHealth()>=0.75&&!localInfo.getFreeDirections().isEmpty()) {
			return new Action(Type.REPRODUCE,LocalInformation.getRandomDirection(localInfo.getFreeDirections()));
		}
		//last option
		else {
			return new Action(Type.STAY);
		}
	}
	@Override
	public void stay() {
		
		double temp = getHealth() + 0.05;
		
		if(temp>MAX_HP) {
			setHealth(MAX_HP);
		}
		
		else {
			setHealth(temp);
		}
	}
	@Override
	public Plant reproduce(Direction direction) {
		Plant a = new Plant(getHealth()*0.1);
		if(direction==Direction.UP) {
			a.setY(getY()-1);
			a.setX(getX());
		}
		if(direction==Direction.DOWN) {
			a.setY(getY()+1);
			a.setX(getX());
		}
		if(direction==Direction.RIGHT) {
			a.setX(getX()+1);
			a.setY(getY());
		}
		if(direction==Direction.LEFT) {
			a.setX(getX()-1);
			a.setY(getY());
		}
		
		setHealth(getHealth()*0.7);
		return a;
	}
	@Override
	public void draw(GridPanel panel) {
		Color dark = new Color(0, 120, 0);
		Color normal = new Color(0,160,0);
		Color bright = new Color(20,200,0);
		Color veryBright = new Color(30,240,0);
		if(getHealth()>=0.75) {
			panel.drawSquare(getX(),getY(), dark);
		}
		else if(getHealth()<0.75&&getHealth()>=0.50) {
			panel.drawSquare(getX(), getY(), normal);
		}
		else if(getHealth()<0.50&&getHealth()>=0.25){
			panel.drawSquare(getX(), getY(), bright);
		}
		else {
			panel.drawSquare(getX(), getY(), veryBright);
		}
		
	}

}