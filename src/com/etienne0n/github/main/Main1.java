package com.etienne0n.github.main;

import com.etienne0n.github.astar.Playground;
import com.etienne0n.github.astar.Playgrounds;
import com.etienne0n.github.utils.BooleanFields;
import com.etienne0n.github.values.Value;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main1 extends Application {
	
	
	// PICTURE URIs
	
	private static final String WALL_FILE = "file:pictures/wall_2_100x100.jpg";
	private static final String GRASS_FILE = "file:pictures/grass_100x100.jpg";
	private static final String MENU_FILE = "file:pictures/menu_texture_1600x120.jpg";
	private static final String ROBOT_FILE = "file:pictures/robot.jpg";
	private static final String RED_CROSS_FILE = "file:pictures/redcross_100x100.png";
	private static final String GREEN_CIRCLE_FILE = "file:pictures/green_circle_100_100.png";
	
	// IMAGES
	
	private static final Image WALL_IMAGE = new Image(WALL_FILE);
	private static final Image GRASS_IMAGE = new Image(GRASS_FILE);
	private static final Image MENU_IMAGE = new Image(MENU_FILE);
	private static final Image ROBOT_IMAGE = new Image(ROBOT_FILE);
	private static final Image RED_CROSS_IMAGE = new Image(RED_CROSS_FILE);
	private static final Image GREEN_CIRCLE_IMAGE = new Image(GREEN_CIRCLE_FILE);
	//SPRITES
	private static final Sprite GRASS_SPRITE = new Sprite(GRASS_IMAGE);
	private static final Sprite WALL_SPRITE = new Sprite(WALL_IMAGE);
	private static final Sprite MENU_SPRITE = new Sprite(MENU_IMAGE);
	private static final Sprite ROBOT_SPRITE = new Sprite(ROBOT_IMAGE);
	private static final Sprite RED_CROSS_SPRITE = new Sprite(RED_CROSS_IMAGE);
	private static final Sprite GREEN_CIRCLE_SPRITE = new Sprite(GREEN_CIRCLE_IMAGE);
	
	// PICTURE SIZE
	private static final int PICTURE_WIDTH = 100;
	private static final int PICTURE_HEIGHT = 100;
	
	
	
	private static final int MENU_HEIGHT = 120;
	
	// Boolean Field
	private static final boolean[][] BOOLEAN_FIELD = BooleanFields.WIDE_FIELD_16x8;
	// CREATE A PLAYGROUND (needed for shortest paths)
	private static final Playground PLAYGROUND = Playgrounds.generatePlayground(BOOLEAN_FIELD);
	private static final int PLAYGROUND_WIDTH = PLAYGROUND.width();
	private static final int PLAYGROUND_HEIGHT = PLAYGROUND.height();
	
	// scene width and height
	
	private static final int WIDTH = PLAYGROUND_WIDTH * PICTURE_WIDTH;
	private static final int HEIGHT = PLAYGROUND_HEIGHT * PICTURE_HEIGHT + MENU_HEIGHT;
	
	//boolean variables
	
	private boolean startIsSet = false;
	private boolean targetIsSet = false;
	
	// start and target coordinates
	private int bFieldStartX, bFieldStartY, bFieldTargetX, bFieldTargetY = 0;
	
	// STRINGS
	private static final String STARTING_TEXT = "Select a starting field. (Must be grass)";
	
	
	
	public static void main(String...args) {launch(args);}
	@Override
	public void start(Stage stage) throws Exception {
	
		stage.setTitle("Visual Pathfinding");
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		Canvas backgroundCanvas = new Canvas(WIDTH, HEIGHT);
		Canvas robotCanvas = new Canvas(WIDTH, HEIGHT);
		Canvas textCanvas = new Canvas(WIDTH, HEIGHT);
		Canvas selectionCanvas = new Canvas(WIDTH, HEIGHT);
		
		root.getChildren().addAll(backgroundCanvas, robotCanvas, textCanvas, selectionCanvas);
		
		GraphicsContext backgroundGC = backgroundCanvas.getGraphicsContext2D();
		GraphicsContext robotGC = robotCanvas.getGraphicsContext2D();
		GraphicsContext textGC = textCanvas.getGraphicsContext2D();
		GraphicsContext selectionGC = selectionCanvas.getGraphicsContext2D();
		
		// Menu Font
		Font menuFont = Font.font("Consolas", FontWeight.BOLD, 28);
		textGC.setFill(Color.GREEN);
		textGC.setFont(menuFont);
		textGC.setStroke(Color.BLACK);
		textGC.setLineWidth(1.6);
		
		// Drawing the field into the canvas
		drawingBooleanField(BOOLEAN_FIELD, backgroundGC);
		
		// Drawing the menu

		MENU_SPRITE.setPosition(0, HEIGHT - MENU_HEIGHT);
		MENU_SPRITE.render(backgroundGC);
		
		
		// show stage and make it unresizable
		stage.setResizable(false);
		stage.show();
		
		
		// "SELECT STARTING FIELD
		
		write(textGC,STARTING_TEXT);
	
		//Animation for movement of robot
		Value<Long> lastNanoTime = new Value<>(System.nanoTime());
		
		AnimationTimer movementAnimationTimer = new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {
				// calculate time since last update
				double timeElapsedInSeconds = (currentNanoTime - lastNanoTime.getValue()) / 1e9;
				lastNanoTime.setValue(currentNanoTime);
				
				
				scene.setOnMouseMoved(m -> {
					// Clearing the selection graphics context 
					selectionGC.clearRect(0, 0, WIDTH, HEIGHT);
					
					double mouseX = m.getX();
					double mouseY = m.getY();
					
					int X_POS_IN_BFIELD = (int)mouseX/PICTURE_WIDTH;
					int Y_POS_IN_BFIELD = (int)mouseY/PICTURE_HEIGHT;
					
					// not out of bounds?
					
					boolean xOutOfBounds = X_POS_IN_BFIELD < 0 || X_POS_IN_BFIELD > PLAYGROUND_WIDTH - 1;
					boolean yOutOfBounds = Y_POS_IN_BFIELD < 0 || Y_POS_IN_BFIELD > PLAYGROUND_HEIGHT - 1;
					boolean outOfBounds = xOutOfBounds || yOutOfBounds;
					
					double drawX = X_POS_IN_BFIELD * PICTURE_WIDTH;
					double drawY = Y_POS_IN_BFIELD * PICTURE_HEIGHT;
					
					if(!outOfBounds) {
						if(BOOLEAN_FIELD[Y_POS_IN_BFIELD][X_POS_IN_BFIELD]) {
							GREEN_CIRCLE_SPRITE.setPosition(drawX, drawY);
							GREEN_CIRCLE_SPRITE.render(selectionGC);
						}else {
							RED_CROSS_SPRITE.setPosition(drawX, drawY);
							RED_CROSS_SPRITE.render(selectionGC);
						}
					}
					
					
					
				});
				
				
				
			}
			
		};
		movementAnimationTimer.start();
		

	}
	private void write(GraphicsContext gc, String text) {
		gc.clearRect(0, 0, WIDTH, HEIGHT);
		fillText(gc, text, WIDTH / 10, HEIGHT - MENU_HEIGHT + (MENU_HEIGHT / 5));
		
	}
	private void fillText(GraphicsContext gc, String text, int x, int y) {
		gc.fillText(text, x, y);
		gc.strokeText(text, x, y);
		
	}
	private void drawingBooleanField(boolean[][] bField, GraphicsContext graphicContext) {
		
		int height = bField.length;
		int width = bField[0].length;
		
		for(int r = 0; r < height; r++) {
			for(int c = 0; c < width; c++) {
				Sprite sprite;
				if(bField[r][c]) {
					
					sprite = GRASS_SPRITE;
				}else {
					
					sprite = WALL_SPRITE;
				}
				
				sprite.setPosition(c * PICTURE_HEIGHT, r * PICTURE_WIDTH);
				sprite.render(graphicContext);

			}
		}
		
	}



}
