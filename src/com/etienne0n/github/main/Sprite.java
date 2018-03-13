package com.etienne0n.github.main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A sprite is a term for a single visual entity.<br>
 * 
 * @author Etienne Onasch
 *
 */
public class Sprite {

	private final Image image;
	private double xPos;
	private double yPos;
	private double xVel;
	private double yVel;
	private double width;
	private double height;
	private boolean mobile = true;
	
	// CONSTRUCTION
	/**
	 * Constructs a new sprite
	 * @param image the image of the sprite
	 * @param xPos the x-position of the sprite
	 * @param yPos the y-position of the sprite
	 * @param xVel the x-velocity of the sprite
	 * @param yVel the y-velocity of the sprite
	 */
	public Sprite(Image image, double xPos, double yPos, double xVel, double yVel) {
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
	}
	/**
	 * Construct a new sprite.
	 * @param image the image of the sprite
	 * @param xPos the x-position of the sprite
	 * @param yPos the y-position of the sprite
	 */
	public Sprite(Image image, double xPos, double yPos) {
		this(image, xPos, yPos, 0.0, 0.0);
	}
	/**
	 * Construct a new sprite.
	 * @param image the image of the sprite
	 */
	public Sprite(Image image) {
		this(image, 0.0, 0.0);
	}
	
	// SETTERS
	/**
	 * sets new coordinates for this sprite.<br>
	 * @param xPos the new x-position
	 * @param yPos the new y-position
	 */
	public void setPosition(double xPos, double yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	/**
	 * sets new velocity for this sprite.<br>
	 * @param xVel the new x-velocity
	 * @param yVel the new y-velocity
	 */
	public void setVelocity(double xVel, double yVel) {
		if(mobile) {
			this.xVel = xVel;
			this.yPos = yVel;
		}
		
	}
	/**
	 * accelerates (or decelerates) this sprite.
	 * @param xVel delta-x-velocity
	 * @param yVel delta-y-velocity
	 */
	public void accelerate(double xVel, double yVel) {
		if(mobile) {
			this.xVel += xVel;
			this.yVel += yVel;
		}
		
	}
	
	/**
	 * updates the position of the sprite<br>
	 * @param time the elapsed time in seconds.
	 */
	public void update(double seconds) {
		if(mobile) {
			xPos += xVel * seconds;
			yPos += yVel * seconds;
		}
		
	}
	
	/**
	 * renders the sprite at its current position.
	 * @param gc the GraphicsContext object from a Canvas object where
	 * the sprite will be rendered.
	 */
	public void render(GraphicsContext gc) {
		gc.drawImage(image, xPos, yPos);
		
	}
	/**
	 * returns an instance of javafx.geometry.Rectangle2D with the size
	 * of the image of this sprite.<br>
	 * If the sprite is not an rectangle (the image has transparent areas),
	 * then this Rectangle2D object is just an approximation of the 
	 * visible size of the sprite.<br>
	 * In other words, it's the smallest rectangle the sprite can fit in.
	 * @return a new <b>javafx.geometry.Rectangle2D</b> object with the size of the image of this sprite
	 */
	public Rectangle2D getBoundary() {
		return new Rectangle2D(xPos, yPos, width, height);
	}
	/**
	 * determines if the boundaries of this sprite intersect with the boundaries of 
	 * another sprite.
	 * @param other another sprite
	 * @return <b>{@code true}</b> if the boundaries of this sprite intersects
	 * with the boundaries of the other sprite.<br>
	 * if there is no intersection between the two sprites this method
	 * will return <b>{@code false}</b> instead.
	 */
	public boolean intersects(Sprite other) {
		return getBoundary().intersects(other.getBoundary());
	}
	/**
	 * makes this sprite imobile.<br>
	 * Its position can still be changed via setter methods <br>,
	 * but its velocity is set to 0 and the methods: <br>
	 * <b>{@code update(double)},</b><br>
	 * <b>{@code setVelocity(double, double)}</b> and<br>
	 * <b>{@code accelerate(double, double)}</b><br>
	 * won't do anything anymore.
	 */
	public void immobilize() {
		xVel = 0;
		yVel = 0;
		mobile = false;
	}
	/**
	 * mobilizes this sprite again.<br>
	 * the following methods will work again:<br>
	 * <b>{@code update(double)},</b><br>
	 * <b>{@code setVelocity(double, double)}</b> and<br>
	 * <b>{@code accelerate(double, double)}</b><br>
	 */
	public void mobilize() {
		mobile = true;
	}
	
	@Override
	public String toString() {
		return " Position: [" + xPos + "," + yPos + "]" + 
				" Velocity: [" + xVel + "," + yVel + "]";
	}
	
	
	

}
