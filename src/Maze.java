import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;

class Maze extends JPanel {
	private static final int N = 20;
	private static Cell[][] cell = new Cell[N][N];
	private static Stack<Location> s = new Stack<Location>();
	private static int c = 0;
	private static int r = 0;

	public Maze() {
		newMap();
		drawPath();
		cell[0][0].left = 1;  // 1 stands for open the wall; left stands for the left wall of the cell
		cell[N - 1][N - 1].right = 1;
		SolveByDFS dfs = new SolveByDFS(cell);	
		cell = dfs.getMaze();
	}

	public void newMap() {
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				cell[c][r] = new Cell();
				cell[c][r].checkStatus = 0; // 0 stands for unvisited
				cell[c][r].isPath = 'F'; 
			}
		}
	}

	// find unvisited neighbor
	public ArrayList<Character> findUnvisitedNeighbor() {
		ArrayList<Character> unvisitied = new ArrayList<Character>();
		if (r > 0 && cell[c][r - 1].checkStatus == 0) {
			unvisitied.add('U');
		}
		if (c > 0 && cell[c - 1][r].checkStatus == 0) {
			unvisitied.add('L');
		}
		if (r < N - 1 && cell[c][r + 1].checkStatus == 0) {
			unvisitied.add('D');
		}
		if (c < N - 1 && cell[c + 1][r].checkStatus == 0) {
			unvisitied.add('R');
		}
		return unvisitied;
	}

	public Character getDirection(ArrayList<Character> direction) {
		if (direction.size() > 0) {
			Random random = new Random();
			int i = random.nextInt(direction.size());
			return direction.get(i);
		} else
			return null;
	}

	public void drawPath() {
		do {
			cell[c][r].checkStatus = 1; // 1 stands for visited cell;
			ArrayList<Character> validNeighbor = findUnvisitedNeighbor();
			if (getDirection(validNeighbor) == null) {
				Location tmp = s.pop();
				if (s.isEmpty())
					break;
				else {
					c = tmp.col;
					r = tmp.row;
					drawPath();
				}
			} else {
				s.push(new Location(c, r));
				Character direction = getDirection(validNeighbor);
				if (direction == 'U') {
					cell[c][r].up = 1;
					r--;
					cell[c][r].down = 1;
				}
				if (direction == 'L') {
					cell[c][r].left = 1;
					c--;
					cell[c][r].right = 1;
				}
				if (direction == 'D') {
					cell[c][r].down = 1;
					r++;
					cell[c][r].up = 1;
				}
				if (direction == 'R') {
					cell[c][r].right = 1;
					c++;
					cell[c][r].left = 1;
				}
			}
			
		} while (!s.isEmpty());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i==0 && j == 0) {
					g.setColor(Color.red);
					g.fillRect(i * 20, j * 20, 20, 20);
					g.setColor(Color.black);
				}
				if(i== N-1 && j == N-1) {
					g.setColor(Color.green);
					g.fillRect(i * 20, j * 20, 20, 20);
					g.setColor(Color.black);
				}
				if (cell[i][j].up == 0) {
						g.fillRect(i * 20, j * 20, 20, 2);
				}
				if (cell[i][j].left == 0) {
					g.fillRect(i * 20, j * 20, 2, 20);
				}
				if (cell[i][j].down == 0) {
						g.fillRect(i * 20, (j * 20)+18, 20, 2);
				}
				if (cell[i][j].right == 0) {
					g.fillRect((i * 20)+18, j * 20, 2, 20);
				}
				if(cell[i][j].isPath == 'T'){
					g.setColor(Color.yellow);
					g.fillRect((i * 20 + 2) , (j * 20 + 2), 18, 18);
					g.setColor(Color.black);
				}
			}
		}
	}

}
