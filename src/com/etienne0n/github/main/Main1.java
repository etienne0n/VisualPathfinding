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
	
	private static final String WALL = "file:wall_2_100x100.jpg";
	private static final String GRASS = "file:grass_100x100.jpg";
	private static final String MENU = "file:menu_texture_1600x120.jpg";
	// IMAGES
	
	private static final Image WALL_IMAGE = new Image(WALL);
	private static final Image GRASS_IMAGE = new Image(GRASS);
	private static final Image MENU_IMAGE = new Image(MENU);
	// PICTURE SIZE
	private static final int PICTURE_WIDTH = 100;
	private static final int PICTURE_HEIGHT = 100;
	
	private static final int MENU_HEIGHT = 120;
	
	//boolean variables
	
	private boolean startIsSet = false;
	private boolean targetIsSet = false;
	
	// start and target coordinates
	private int bFieldStartX, bFieldStartY, bFieldTargetX, bFieldTargetY = 0;
	
	
	
	public static void main(String...args) {launch(args);}
	@Override
	public void start(Stage stage) throws Exception {
		// CREATE A PLAYGROUND (needed for shortest paths)
		
		final boolean [][] bField = BooleanFields.WIDE_FIELD_2_16x8;
		
		Playground playground = Playgrounds.generatePlayground(bField);
		
		
		int width = playground.width() * PICTURE_WIDTH;
		int height = playground.height() * PICTURE_HEIGHT + MENU_HEIGHT;
		//--------------------------------------------------------------------------------------
		
		stage.setTitle("Visual Pathfinding");
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		Canvas backgroundCanvas = new Canvas(width, height);
		Canvas robotCanvas = new Canvas(width, height);
		Canvas textCanvas = new Canvas(width, height);
		Canvas selectionCanvas = new Canvas(width, height);
		
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
		drawingBooleanField(bField, backgroundGC);
		
		// Drawing the menu
		Sprite menu = new Sprite(MENU_IMAGE, 0, height - MENU_HEIGHT);
		menu.render(backgroundGC);
		
		
		// show stage and make it unresizable
		stage.setResizable(false);
		stage.show();
		
		
		// "SELECT STARTING FIELD"
		String startText = "Select a starting field. (Must be grass)";
		

		
		fillText(textGC, startText, width / 10, height - MENU_HEIGHT + (MENU_HEIGHT / 5));
		
		
		
		
		
		
		
		
		
		
		
		//Animation for movement of robot
		Value<Long> lastNanoTime = new Value<>(System.nanoTime());
		
		AnimationTimer movementAnimationTimer = new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {
				// calculate time since last update
				double timeElapsedInSeconds = (currentNanoTime - lastNanoTime.getValue()) / 1e9;
				lastNanoTime.setValue(currentNanoTime);
				
			}
			
		};
		

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
				Image image;
				if(bField[r][c]) {
					
					image = GRASS_IMAGE;
				}else {
					
					image = WALL_IMAGE;
				}
				
				Sprite s = new Sprite(image, c * PICTURE_HEIGHT, r * PICTURE_WIDTH);
				s.render(graphicContext);
				s = null;
			}
		}
		
	}



}
