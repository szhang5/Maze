import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;

class Maze extends JPanel {
	private static final int N = 20;
	public static Cell[][] maze = new Cell[N][N];
	private static Stack<Location> s = new Stack<Location>();
	private static int c = 0;
	private static int r = 0;

	// *****************//
	// - Constructor - //
	// *****************//
	public Maze() {
		newMap();
		// drawPath();
		maze[0][0].left = 1;
		maze[N - 1][N - 1].right = 1;

	}

	public Cell[][] getNewMaze() {
		newMap();
		// drawPath();
		maze[0][0].left = 1;
		maze[N - 1][N - 1].right = 1;
		return maze;
	}

	public Cell[][] getPath() {
		drawPath();
		return maze;
	}
	
	public Cell[][] getMaze(){
		return maze;
	}
	
	public void setMaze(Cell[][] maze) {
		this.maze = maze;
	}

	// *************//
	// - New Map - //
	// *************//
	public void newMap() {
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				maze[c][r] = new Cell();
				maze[c][r].checkStatus = 0; // 0 stands for unvisited
				maze[c][r].isPath = 'F';
			}
		}
	}

	// *****************//
	// - Refresh Map - //
	// *****************//
	public void refreshMaze() {
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				maze[c][r].isPath = 'F';
			}
		}
	}

	// *************************//
	// - Find unvisited Cell - //
	// *************************//
	public ArrayList<Character> findUnvisitedNeighbor() {
		ArrayList<Character> unvisitied = new ArrayList<Character>();
		if (r > 0 && maze[c][r - 1].checkStatus == 0) {
			unvisitied.add('U');
		}
		if (c > 0 && maze[c - 1][r].checkStatus == 0) {
			unvisitied.add('L');
		}
		if (r < N - 1 && maze[c][r + 1].checkStatus == 0) {
			unvisitied.add('D');
		}
		if (c < N - 1 && maze[c + 1][r].checkStatus == 0) {
			unvisitied.add('R');
		}
		return unvisitied;
	}

	// *******************************************//
	// - Choose one of the valid walls to open - //
	// *******************************************//
	public Character getDirection(ArrayList<Character> direction) {
		if (direction.size() > 0) {
			Random random = new Random();
			int i = random.nextInt(direction.size());
			return direction.get(i);
		} else
			return null;
	}

	// ******************************************//
	// - Use DFS to draw the path of the maze - //
	// ******************************************//
	public void drawPath() {
		// - 1 stands for visited cell - //
		maze[c][r].checkStatus = 1;

		// - Use ArrayList to store unvisited neighbor cell - //
		ArrayList<Character> validNeighbor = findUnvisitedNeighbor();

		if (getDirection(validNeighbor) == null) {
				Location tmp = s.pop();
				c = tmp.col;
				r = tmp.row;
				drawPath();
		} else {
			s.push(new Location(c, r));
			Character direction = getDirection(validNeighbor);
			// - Go up - //
			if (direction == 'U') {
				maze[c][r].up = 1;
				r--;
				maze[c][r].down = 1;
			}
			// - Go left - //
			if (direction == 'L') {
				maze[c][r].left = 1;
				c--;
				maze[c][r].right = 1;
			}
			// - Go down - //
			if (direction == 'D') {
				maze[c][r].down = 1;
				r++;
				maze[c][r].up = 1;
			}
			// - Go right - //
			if (direction == 'R') {
				maze[c][r].right = 1;
				c++;
				maze[c][r].left = 1;
			}
		}
	}

	// *********************//
	// - Paint Component - //
	// *********************//
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == 0 && j == 0) {
					g.setColor(Color.red);
					g.fillRect(i * 20, j * 20, 20, 20);
					g.setColor(Color.black);
				}
				if (i == N - 1 && j == N - 1) {
					g.setColor(Color.blue);
					g.fillRect(i * 20, j * 20, 20, 20);
					g.setColor(Color.black);
				}
				if (maze[i][j].up == 0) {
					g.fillRect(i * 20, j * 20, 20, 2);
				}
				if (maze[i][j].left == 0) {
					g.fillRect(i * 20, j * 20, 2, 20);
				}
				if (maze[i][j].down == 0) {
					g.fillRect(i * 20, (j * 20) + 18, 20, 2);
				}
				if (maze[i][j].right == 0) {
					g.fillRect((i * 20) + 18, j * 20, 2, 20);
				}
				// - Paint the path yellow - //
				if (maze[i][j].isPath == 'T') {
					g.setColor(Color.yellow);
					g.fillRect((i * 20 + 2), (j * 20 + 2), 16, 16);
					if (maze[i][j].up == 1)
						g.fillRect((i * 20 + 2), j * 20, 16, 2);
					if (maze[i][j].left == 1)
						g.fillRect(i * 20, (j * 20 + 2), 2, 16);
					if (maze[i][j].down == 1)
						g.fillRect((i * 20 + 2), (j * 20) + 18, 16, 2);
					if (maze[i][j].right == 1)
						g.fillRect((i * 20) + 18, (j * 20 + 2), 2, 16);
					g.setColor(Color.black);
				}
				// - Paint the BFS process green - //
				if (maze[i][j].isPath == 't') {
					g.setColor(Color.green);
					g.fillRect((i * 20 + 2), (j * 20 + 2), 16, 16);
					if (maze[i][j].up == 1)
						g.fillRect((i * 20 + 2), j * 20, 16, 2);
					if (maze[i][j].left == 1)
						g.fillRect(i * 20, (j * 20 + 2), 2, 16);
					if (maze[i][j].down == 1)
						g.fillRect((i * 20 + 2), (j * 20) + 18, 16, 2);
					if (maze[i][j].right == 1)
						g.fillRect((i * 20) + 18, (j * 20 + 2), 2, 16);
					g.setColor(Color.black);
				}
			}
		}
	}

	public static Stack<Location> getS() {
		return s;
	}

}
