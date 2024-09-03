package Game;
/*
 * File: Breakout.java
 * -------------------
 * Name: Veronika Titarchuk
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (APPLICATION_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 2;
	
	private double vx, vy;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int NumOfTheTurn;
	private boolean isFinished;
	private int counterOfBricks;

	private static final char c = 32;

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		game();
	}

	public void init() {
		NumOfTheTurn = 0;
		isFinished = false;
		counterOfBricks = NBRICK_ROWS * NBRICKS_PER_ROW;

		background();
		paddle((APPLICATION_WIDTH - PADDLE_WIDTH) / 2, APPLICATION_HEIGHT - PADDLE_Y_OFFSET);
		brickets();
		addMouseListeners();
		addKeyListeners();
	}
	
	/** 
	 * Body of the game
	 */
	public void game() {
		while (!isFinished) {
			if (ball != null) {
				moveBall();
				detector();
				checkForBallCollision();
			} else {
				pause(500);
				if (vy < 0)
					vy = -vy;
				ball();
			}
		}
		if (ball != null) {
			remove(ball);
		}
		remove(paddle);

		if (counterOfBricks == 0) {
			win = new GImage("D:\\Eclipse workspace\\Laboratory2\\src\\Game\\win.jpg");
			win.setSize(win.getWidth() / 4, win.getHeight() / 4);
			add(win, (APPLICATION_WIDTH - win.getWidth()) / 2, (APPLICATION_HEIGHT - win.getHeight()) / 2);
		} else {
			lose = new GImage("D:\\Eclipse workspace\\Laboratory2\\src\\Game\\lose.jpg");
			lose.setSize(lose.getWidth() / 4, lose.getHeight() / 4);
			add(lose, (APPLICATION_WIDTH - lose.getWidth()) / 2, (APPLICATION_HEIGHT - lose.getHeight()) / 2);
		}
	}

	/**
	 * Background of the App
	 */
	private void background() {
		background = new GImage("D:\\Eclipse workspace\\Laboratory2\\src\\Game\\background1.gif");
		background.setSize(background.getWidth(), APPLICATION_HEIGHT);
		add(background, 0, 0);
	}

	/**
	 * 
	 * @param x - Location on Ox
	 * @param y - Location on Oy
	 * 
	 * Shape, size, color of the paddle
	 */
	public void paddle(int x, int y) {
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.white);
		add(paddle);
	}

	/**
	 * Creating brickets, their size, shape, color and location
	 */
	public void brickets() {
		for (int i = 0; i < NBRICKS_PER_ROW; i++) {
			for (int j = 0; j < NBRICK_ROWS; j++) {
				bricket = new GRect(
						(APPLICATION_WIDTH - NBRICKS_PER_ROW * BRICK_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / 2
								+ i * (BRICK_SEP + BRICK_WIDTH),
						BRICK_Y_OFFSET + j * (BRICK_HEIGHT + BRICK_SEP), BRICK_WIDTH, BRICK_HEIGHT);
				bricket.setFilled(true);
				if (j == 0 || j == 1) {
					bricket.setFillColor(Color.RED);
					bricket.setColor(Color.RED);
				}
				if (j == 2 || j == 3) {
					bricket.setFillColor(Color.ORANGE);
					bricket.setColor(Color.ORANGE);
				}
				if (j == 4 || j == 5) {
					bricket.setFillColor(Color.YELLOW);
					bricket.setColor(Color.YELLOW);
				}
				if (j == 6 || j == 7) {
					bricket.setFillColor(Color.GREEN);
					bricket.setColor(Color.GREEN);
				}
				if (j == 8 || j == 9) {
					bricket.setFillColor(Color.CYAN);
					bricket.setColor(Color.CYAN);
				}
				add(bricket);
			}
		}
	}

	/**
	 * Creating ball in the App. Size, shape, color and speed
	 */
	public void ball() {
		if (!(NumOfTheTurn == NTURNS)) {
			ball = new GOval((APPLICATION_WIDTH - BALL_RADIUS) / 2, (APPLICATION_HEIGHT - BALL_RADIUS) / 2, BALL_RADIUS,
					BALL_RADIUS);
			ball.setFilled(true);
			ball.setColor(Color.white);
			ball.setFillColor(Color.WHITE);
			add(ball);

			vx = rgen.nextDouble(1.0, 3.0);
			if (rgen.nextBoolean(0.5))
				vx = -vx;
			vy = rgen.nextDouble(1.0, 3.0);
			NumOfTheTurn++;
		}
	}

	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
	}

	public void mouseDragged(MouseEvent e) {
		if (paddle.getX() <= APPLICATION_WIDTH - PADDLE_WIDTH && paddle.getX() >= 0) {
			paddle.move(e.getX() - last.getX(), 0);
			last = new GPoint(e.getPoint());
		} else {
			if (paddle.getX() > APPLICATION_WIDTH - PADDLE_WIDTH)
				paddle.move(APPLICATION_WIDTH - PADDLE_WIDTH - paddle.getX(), 0);
			if (paddle.getX() < 0)
				paddle.move(-paddle.getX(), 0);
		}
	}

	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == c) {
			vx *= 1.1;
			vy *= 1.1;
		}
	}

	/**
	 * Ball moving and finding dots of the ball 
	 */
	private void moveBall() {
		if (ball != null) {
			ball.move(vx, vy);
			pause(10);
			ballUpperLeft = new GPoint(ball.getX(), ball.getY());
			ballUpperRight = new GPoint(ball.getX() + BALL_RADIUS, ball.getY());
			ballLowerLeft = new GPoint(ball.getX(), ball.getY() + BALL_RADIUS);
			ballLowerRight = new GPoint(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS);
		}
	}

	

	private boolean collide(GPoint point) {
		return (getElementAt(point) != null
				&& getElementAt(point) != background);
	}

/**
 * Changing direction depending on an angle of collision.
 */
	private void detector() {
		boolean collisionTop = collide(ballUpperLeft) && collide(ballUpperRight);
		boolean collisionBottom = collide(ballLowerLeft) && collide(ballLowerRight);
		boolean collisionLeft = collide(ballUpperLeft) && collide(ballLowerLeft);
		boolean collisionRight = collide(ballUpperRight) && collide(ballLowerRight);
		boolean collisionAngle = collide(ballUpperRight) || collide(ballUpperLeft)
				|| collide(ballLowerLeft) || collide(ballLowerRight);

		// remove bricks
		removeElement(ballUpperLeft);
		removeElement(ballUpperRight);
		removeElement(ballLowerLeft);
		removeElement(ballLowerRight);

		// frame
		if (ball.getX() <= 0
				|| ball.getX() + ball.getWidth() >= APPLICATION_WIDTH){
			vx = -vx;
		}
		else if (ball.getY() <= 0) {
			vy = -vy;
		}
		//
		else if (collisionTop) {
			vy = -vy;
			removeElement(ballUpperLeft);

		} else if (collisionBottom) {
			vy = -vy;
			removeElement(ballLowerLeft);
		} else if (collisionLeft) {
			vx = -vx;
			removeElement(ballLowerLeft);

		} else if (collisionRight) {
			vx = -vx;
			removeElement(ballLowerRight);

		} else if (collisionAngle) {
			vx = -vx;
			vy = -vy;
		}

		/*
		 * paddle fix
		 */
		if (ball.getY() + ball.getHeight() >= paddle.getY()
				&& ball.getY() <= paddle.getY() + paddle.getHeight()
				&& ball.getX() + ball.getWidth() >= paddle.getX()
				&& ball.getX() <= paddle.getX() + paddle.getWidth())
			ball.setLocation(ball.getX(), paddle.getY() - ball.getHeight());
	}

	/**
	 * Method for deleting brick after collision.
	 * 
	 * @param point
	 *            of collision
	 */
	private void removeElement(GPoint point) {
		// defining colliding object.
		GObject collide = getElementAt(point);

		// collisions with bricks
		if (collide != null && collide != paddle && collide!=background) {
			remove(collide);
			counterOfBricks--;
			if(counterOfBricks==0)
				isFinished = true;
		}
	}
	private void checkForBallCollision() {
		if (ball.getY() >= APPLICATION_HEIGHT && ball != null) {
			remove(ball);
			ball = null;
			if (NumOfTheTurn == NTURNS) {
				isFinished = true;
			}
		}
	}

	GObject collide;
	GImage lose;
	GImage win;
	GImage background;
	GPoint ballUpperLeft;
	GPoint ballUpperRight;
	GPoint ballLowerLeft;
	GPoint ballLowerRight;
	GPoint last;
	GOval ball;
	GRect bricket;
	GRect paddle;
}
