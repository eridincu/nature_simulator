package project;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;
/**
 * 
 * @author M. Erdinc Oguz
 *
 */
public abstract class Creature implements Drawable{
	/*
	 * Creature's initial health when it's first created. 
	 */
	private double initialHealth;
	/*
	 * Creature's health during the game.
	 */
	private double currentHealth;
	/*
	 * Creature's location on the x axis.
	 */
	private int x;
	/**
	 * Creature's location on the y axis. 
	 */
	private int y;
	/**
	 * Constructor that creates creature with given health
	 * @param health of the creature when it's created.
	 */
	
	public Creature(double health) {
		this.initialHealth = health;
		this.currentHealth = health;
	}
	/**
	 * Returns the position of the creature at x axis.
	 * @return position at x
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * Returns the position of the creature at y axis.
	 * @return position at y
	 */
	 
	public int getY() {
		return this.y;
	}
	/**
	 * Sets the position of creature at x axis.
	 * @param x position on the grid
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Sets the position of the creature at y axis
	 * @param y position on the grid
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets the health of the creature after during actions. 
	 * @param health final health of the creature after action
	 */
	public void setHealth(double health) {
		this.currentHealth = health;
	}
	/**
	 * Returns the current health of the creature.
	 * @return health current health of creature.
	 */
	public double getHealth() {
		return currentHealth;
	}
	/**
	 * Chooses action for creature under certain conditions.
	 * @param localInfo gives necessary information to decide
	 * @return the action
	 */
	public abstract Action chooseAction(LocalInformation localInfo);
	/**
	 * Creature doesn't move due under given conditions
	 * Plant regenerates health
	 * Herbivore loses health and waits for a plant, if no one came, she dies
	 */
	public abstract void stay();
	/**
	 * Creates a new creature with valid health for both types at given direction
	 * @param direction 
	 * @return new creature
	 */
	public abstract Creature reproduce(Direction direction);
	/**
	 * Attacks the given creature and removes it from the game 
	 * @param attackedCreature 
	 */
	public void attack(Creature attackedCreature) {}
	/**
	 * Changes creature's location due to given direction
	 * Herbivore loses health during this action
	 * @param direction where to put
	 */
	public void move(Direction direction) {}

	@Override
	public void draw(GridPanel panel) {
		
		
	}
	
}
