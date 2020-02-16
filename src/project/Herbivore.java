package project;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;
import naturesimulator.Action.Type;
/**
 * 
 * @author M. Erdinc Oguz
 *
 */
public class Herbivore extends Creature{
	/**
	 * Max health of an herbivore
	 */
	public final double MAX_HP = 20.0;
	/**
	 * Creates new Herbivore and sets its health and position using set methods and parent's constructor
	 * @param x new creature's position on x axis
	 * @param y new creature's position on y axis
	 */
	public Herbivore(int x, int y) {
		super(10.0);
		setX(x);
		setY(y);
	}
	/**
	 * Constructor for reproducing new herbivore
	 * @param health for new herbivore
	 */
	public Herbivore(double health) {
		super(health*0.2);
	}
	@Override
	public Action chooseAction(LocalInformation localInfo) {
		//checking health and around the creature for free directions to produce 
		if(getHealth()==this.MAX_HP&&!localInfo.getFreeDirections().isEmpty()) {
			return new Action(Type.REPRODUCE,LocalInformation.getRandomDirection(localInfo.getFreeDirections()));
		}
		//is there any creature
		else if(!creatureDirections(localInfo).isEmpty()) {
			return new Action(Type.ATTACK,LocalInformation.getRandomDirection(creatureDirections(localInfo)));
		}
		//checks health and direction to move
		else if(creatureDirections(localInfo).isEmpty()&&getHealth()>=1.0) {
			return new Action(Type.MOVE,LocalInformation.getRandomDirection(localInfo.getFreeDirections()));
		}
		//last option
		else {
			return new Action(Type.STAY);
		}
		
	}
	/**
	 * Returns an arraylist of directions if there is any creature at that direction
	 * @param localInfo gives necessary info about creature.
	 * @return list of directions
	 */
	private ArrayList<Direction> creatureDirections(LocalInformation localInfo) {
		ArrayList<Direction> creatureDirections = new ArrayList<Direction>();
		//checking around the herbivore for creatures
		if(localInfo.getCreatureUp() instanceof Plant) {
			creatureDirections.add(Direction.UP);
		}
		if(localInfo.getCreatureDown() instanceof Plant) {
			creatureDirections.add(Direction.DOWN);
		}
		if(localInfo.getCreatureLeft() instanceof Plant) {
			creatureDirections.add(Direction.LEFT);
		}
		if(localInfo.getCreatureRight() instanceof Plant) {
			creatureDirections.add(Direction.RIGHT);
		}
		
		return creatureDirections;
	}
	@Override
	public void stay() {
		setHealth(getHealth()-0.1);
	}
	@Override
	public Creature reproduce(Direction direction) {
		Herbivore a = new Herbivore(getHealth()*0.2);
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
		setHealth(getHealth()*0.4);

		return a;
	}
	@Override
	public void attack(Creature attackedCreature) {
		double temp = getHealth() + attackedCreature.getHealth();
		if(temp>=this.MAX_HP) {
			setHealth(this.MAX_HP);
		}
		else {
			setHealth(temp);
		}
		attackedCreature.setHealth(0.0);
		setX(attackedCreature.getX());
		setY(attackedCreature.getY());
	}
	@Override
	public void move(Direction direction) {
		setHealth(getHealth()-1.0);
		if(direction==Direction.DOWN) {
			setY(getY()+1);
		}
		else if(direction==Direction.UP) {
			setY(getY()-1);
		}
		else if(direction==Direction.RIGHT) {
			setX(getX()+1);
		}
		else if(direction==Direction.LEFT) {
			setX(getX()-1);
		}
	}
	@Override
	public void draw(GridPanel panel) {
		
		Color dark = new Color(120, 0,0);
		Color normal = new Color(160,0,0);
		Color bright = new Color(200,20,0);
		Color veryBright = new Color(240,30,0);
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
