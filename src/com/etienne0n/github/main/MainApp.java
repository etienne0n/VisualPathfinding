package com.etienne0n.github.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.etienne0n.github.astar.Playground;
import com.etienne0n.github.astar.Playgrounds;
import com.etienne0n.github.utils.BooleanFields;
import com.etienne0n.github.values.Direction;

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

public class MainApp extends Application {

	// PICTURE URIs

	private static final String WALL_URI = "file:pictures/wall_2_100x100.jpg";
	private static final String GRASS_URI = "file:pictures/grass_100x100.jpg";
	private static final String MENU_URI = "file:pictures/menu_texture_1600x120.jpg";
	private static final String ROBOT_URI = "file:pictures/robot.png";
	private static final String RED_CROSS_URI = "file:pictures/redcross_100x100.png";
	private static final String GREEN_CIRCLE_URI = "file:pictures/green_circle_100_100.png";

	// IMAGES

	private static final Image WALL_IMAGE = new Image(WALL_URI);
	private static final Image GRASS_IMAGE = new Image(GRASS_URI);
	private static final Image MENU_IMAGE = new Image(MENU_URI);
	private static final Image ROBOT_IMAGE = new Image(ROBOT_URI);
	private static final Image RED_CROSS_IMAGE = new Image(RED_CROSS_URI);
	private static final Image GREEN_CIRCLE_IMAGE = new Image(GREEN_CIRCLE_URI);
	// SPRITES
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

	private static final boolean[][] BOOLEAN_FIELD = BooleanFields.getRandomField_12x8();
	// CREATE A PLAYGROUND (needed for shortest paths)
	private static final Playground PLAYGROUND = Playgrounds.generatePlayground(BOOLEAN_FIELD);
	private static final int PLAYGROUND_WIDTH = PLAYGROUND.width();
	private static final int PLAYGROUND_HEIGHT = PLAYGROUND.height();

	// scene width and height

	private static final int SCENE_WIDTH = PLAYGROUND_WIDTH * PICTURE_WIDTH;
	private static final int SCENE_HEIGHT = PLAYGROUND_HEIGHT * PICTURE_HEIGHT + MENU_HEIGHT;

	// boolean variables

	private boolean startIsSet = false;
	private boolean targetIsSet = false;
	private boolean currentWaypointReached = false;

	// start and target coordinates
	private int bFieldStartX, bFieldStartY, bFieldTargetX, bFieldTargetY = 0;

	// Waypoints
	private List<Integer[]> waypoints = new ArrayList<>();
	private Integer[] currentWaypoint = new Integer[2];
	private Integer[] lastWaypoint = new Integer[2];

	// Robot's direction
	private Direction oldDirection = null;
	private Direction currentDirection = null;

	// Last System time in nanoseconds
	private Long lastNanoTime;

	// STRINGS
	private static final String STARTING_TEXT = "Select a starting grass field.";
	private static final String TARGET_TEXT = "Now select a different grass field as the target.";
	private static final String DRIVING_TEXT = "Robot is driving to the target...";
	private static final String TARGET_REACHED = "Robot reached the target.";

	public static void main(String... args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Visual Pathfinding");
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);

		Canvas backgroundCanvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
		Canvas robotCanvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
		Canvas textCanvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
		Canvas selectionCanvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);

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

		MENU_SPRITE.setPosition(0, SCENE_HEIGHT - MENU_HEIGHT);
		MENU_SPRITE.render(backgroundGC);

		// show stage and make it unresizable
		stage.setResizable(false);
		stage.show();

		// "SELECT STARTING FIELD

		write(textGC, STARTING_TEXT);

		// Animation for movement of robot
		// Value<Long> lastNanoTime = new Value<>(System.nanoTime());

		AnimationTimer movementAnimationTimer = new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {
				// calculate time since last update
				double timeElapsedInSeconds = (currentNanoTime - lastNanoTime) / 1e9;
				lastNanoTime = currentNanoTime;

				// Clear Robot's graphic context
				robotGC.clearRect(0.0, 0.0, SCENE_WIDTH, SCENE_HEIGHT);

				// Robot's current position

				double currentRobotXPos = ROBOT_SPRITE.getXPosition();
				double currentRobotYPos = ROBOT_SPRITE.getYPosition();

				// current waypoint's playground coordinates

				double currentWaypointXPos = currentWaypoint[0] * PICTURE_WIDTH;
				double currentWaypointYPos = currentWaypoint[1] * PICTURE_HEIGHT;
				
				//Current waypoint reached?
				
				// Measure method
				currentWaypointReached = currentDirection == Direction.NORTH ? currentRobotYPos <= currentWaypointYPos
						: currentDirection == Direction.SOUTH ? currentRobotYPos >= currentWaypointYPos
								: currentDirection == Direction.EAST ? currentRobotXPos >= currentWaypointXPos
										: currentRobotXPos <= currentWaypointXPos;
				
				
				if (currentWaypointReached) {
					
					
					if (waypoints.size() > 0) {
						lastWaypoint = currentWaypoint;
						currentWaypoint = waypoints.remove(0);
						oldDirection = currentDirection;
						currentDirection = direction(lastWaypoint, currentWaypoint);
						
						if(oldDirection != currentDirection) {
							robotPositionCorrection(currentWaypointXPos, currentWaypointYPos);
						}
						
						
						ROBOT_SPRITE.setVelocity(currentDirection.getXVel(), currentDirection.getYVel());
						currentWaypointReached = false;
					} else {
						robotPositionCorrection(currentWaypointXPos, currentWaypointYPos);
						write(textGC, TARGET_REACHED);
//						targetIsSet = false;
//						bFieldStartX = (int) currentWaypointXPos / PICTURE_WIDTH;
//						bFieldStartY = (int) currentWaypointYPos / PICTURE_HEIGHT;
	
						stop();
					}

				}
				
				ROBOT_SPRITE.update(timeElapsedInSeconds);
				ROBOT_SPRITE.render(robotGC);

				

			}

			private void robotPositionCorrection(double currentWaypointXPos, double currentWaypointYPos) {
				robotGC.clearRect(0.0, 0.0, SCENE_WIDTH, SCENE_HEIGHT);
				ROBOT_SPRITE.setPosition(currentWaypointXPos, currentWaypointYPos);
				ROBOT_SPRITE.setVelocity(0.0, 0.0);
				ROBOT_SPRITE.render(robotGC);
			}

		};

		// Mouse cursor
		scene.setOnMouseMoved(m -> {
			// Clearing the selection graphics context
			selectionGC.clearRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);

			double mouseX = m.getSceneX();
			double mouseY = m.getSceneY();

			int bFieldXPos = (int) mouseX / PICTURE_WIDTH;
			int bFieldYPos = (int) mouseY / PICTURE_HEIGHT;

			// out of bounds?
			boolean outOfBounds = bFieldXPos < 0 || bFieldXPos > PLAYGROUND_WIDTH - 1 || bFieldYPos < 0
					|| bFieldYPos > PLAYGROUND_HEIGHT - 1;

			double drawX = bFieldXPos * PICTURE_WIDTH;
			double drawY = bFieldYPos * PICTURE_HEIGHT;

			if (!outOfBounds) {
				if (BOOLEAN_FIELD[bFieldYPos][bFieldXPos]) {
					GREEN_CIRCLE_SPRITE.setPosition(drawX, drawY);
					GREEN_CIRCLE_SPRITE.render(selectionGC);
				} else {
					RED_CROSS_SPRITE.setPosition(drawX, drawY);
					RED_CROSS_SPRITE.render(selectionGC);
				}
			}

		});

		scene.setOnMouseClicked(e -> {
			double mouseXPos = e.getX();
			double mouseYPos = e.getY();

			int bFieldXPos = (int) (mouseXPos / PICTURE_WIDTH);
			int bFieldYPos = (int) (mouseYPos / PICTURE_HEIGHT);

			if (BOOLEAN_FIELD[bFieldYPos][bFieldXPos]) {
				if (!startIsSet) {
					ROBOT_SPRITE.setPosition(bFieldXPos * PICTURE_WIDTH, bFieldYPos * PICTURE_HEIGHT);
					ROBOT_SPRITE.render(robotGC);
					bFieldStartX = bFieldXPos;
					bFieldStartY = bFieldYPos;
					
					lastWaypoint[0] = bFieldXPos;
					lastWaypoint[1] = bFieldYPos;
					
					write(textGC, TARGET_TEXT);
					startIsSet = true;
				} else if (!targetIsSet && ((bFieldXPos != bFieldStartX) || (bFieldYPos != bFieldStartY))) {
					targetIsSet = true;
					bFieldTargetY = bFieldYPos;
					bFieldTargetX = bFieldXPos;
					write(textGC, DRIVING_TEXT);
					System.out.println("Calculated Path:");
					waypoints = PLAYGROUND.shortestPath(bFieldStartY, bFieldStartX, bFieldTargetY, bFieldTargetX);
					for (Integer[] ary : waypoints) {
						System.out.println(Arrays.toString(ary));
					}
					currentWaypoint = waypoints.remove(0);

					currentDirection = direction(lastWaypoint, currentWaypoint);
					ROBOT_SPRITE.setVelocity(currentDirection.getXVel(), currentDirection.getYVel());
					lastNanoTime = System.nanoTime();
					movementAnimationTimer.start();
				}
			}

		});

	}

	/**
	 * This method just works with no diagonal movement!!!
	 * 
	 * @param lastWaypoint
	 *            the last waypoint
	 * @param nextWaypoint
	 *            the next waypoint
	 * @return the current direction
	 */
	private Direction direction(Integer[] lastWaypoint, Integer[] nextWaypoint) {
		int lastWpX = lastWaypoint[0];
		int lastWpY = lastWaypoint[1];
		int nextWpX = nextWaypoint[0];
		int nextWpY = nextWaypoint[1];

		if (lastWpX != nextWpX) {
			return lastWpX < nextWpX ? Direction.EAST : Direction.WEST;
		} else {
			return lastWpY < nextWpY ? Direction.SOUTH : Direction.NORTH;
		}

	}

	private void write(GraphicsContext gc, String text) {
		gc.clearRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
		fillText(gc, text, SCENE_WIDTH / 10, SCENE_HEIGHT - MENU_HEIGHT + (MENU_HEIGHT / 5));

	}

	private void fillText(GraphicsContext gc, String text, int x, int y) {
		gc.fillText(text, x, y);
		gc.strokeText(text, x, y);

	}

	private void drawingBooleanField(boolean[][] bField, GraphicsContext graphicContext) {

		int height = bField.length;
		int width = bField[0].length;

		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				Sprite sprite;
				if (bField[r][c]) {
					sprite = GRASS_SPRITE;
				} else {
					sprite = WALL_SPRITE;
				}

				sprite.setPosition(c * PICTURE_HEIGHT, r * PICTURE_WIDTH);
				sprite.render(graphicContext);

			}
		}

	}

}
