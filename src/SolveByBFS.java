import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SolveByBFS {
	public static final int N = 20;
	private Queue<Location> q = new LinkedList<Location>();
	private int c = 0;
	private int r = 0;
	private Cell[][] maze;
	private int pathC = N-1;
	private int pathR = N-1;

	public SolveByBFS(Cell[][] maze) {
		this.maze = maze;
	}

	public Cell[][] getMaze() {
		findPath();
		return maze;
	}

	public Cell[][] getMaze2() {
		getPath();
		return maze;
	}

	//*******************//
	// - BFS Find Path - //
	//*******************//
	public void findPath() {
		Location tmp;
		if (c == 0 && r == 0) {
			maze[c][r].isPath = 'S';
			maze[c][r].parent = null;
		}

		ArrayList<Character> validDirction = findDirection();

		if (validDirction.size() > 0) {
			for (int i = 0; i < validDirction.size(); i++) {
				Character direction = validDirction.get(i);
				int tmpC = c;
				int tmpR = r;
				// - If GO UP - //
				if (direction == 'U') {
					tmpR--;
					if ((tmpC == N - 1 && tmpR == N - 1))
						maze[tmpC][tmpR].isPath = 'E';
					else
						maze[tmpC][tmpR].isPath = 't';
					maze[tmpC][tmpR].parent = new Location(c, r);
					q.add(new Location(tmpC, tmpR));
				}
				// - If GO LEFT - //
				if (direction == 'L') {
					tmpC--;
					if ((tmpC == N - 1 && tmpR == N - 1))
						maze[tmpC][tmpR].isPath = 'E'; 
					else
						maze[tmpC][tmpR].isPath = 't';
					maze[tmpC][tmpR].parent = new Location(c, r);
					q.add(new Location(tmpC, tmpR));
				}
				// - If GO DOWN - //
				if (direction == 'D') {
					tmpR++;
					if ((tmpC == N - 1 && tmpR == N - 1))
						maze[tmpC][tmpR].isPath = 'E'; 
					else
						maze[tmpC][tmpR].isPath = 't';
					maze[tmpC][tmpR].parent = new Location(c, r);
					q.add(new Location(tmpC, tmpR));
				}
				// - If GO RIGHT - //
				if (direction == 'R') {
					tmpC++;
					if ((tmpC == N - 1 && tmpR == N - 1))
						maze[tmpC][tmpR].isPath = 'E'; 
					else
						maze[tmpC][tmpR].isPath = 't';
					maze[tmpC][tmpR].parent = new Location(c, r);
					q.add(new Location(tmpC, tmpR));
				}
			}
		}
		tmp = q.poll();
		c = tmp.col;
		r = tmp.row;
	}

	//******************//
	// - BFS Get Path - //
	//******************//
	public void getPath() {
		int temC = pathC;
		int temR = pathR;
		if (temC == 0 && temR == 0)
			maze[temC][temR].isPath = 'S';
		else if(temC == N-1 && temR == N-1)
			maze[temC][temR].isPath = 'E';
		else
			maze[temC][temR].isPath = 'T';
		pathC = maze[temC][temR].parent.col;
		pathR = maze[temC][temR].parent.row;
	}

	//**************************//
	// - Find Valid Direction - //
	//**************************//
	public ArrayList<Character> findDirection() {
		ArrayList<Character> openWall = new ArrayList<Character>();
		if (r > 0 && maze[c][r - 1].isPath == 'F' && maze[c][r - 1].down == 1)
			openWall.add('U');
		if (c > 0 && maze[c - 1][r].isPath == 'F' && maze[c - 1][r].right == 1)
			openWall.add('L');
		if (r < N - 1 && maze[c][r + 1].isPath == 'F' && maze[c][r + 1].up == 1)
			openWall.add('D');
		if (c < N - 1 && maze[c + 1][r].isPath == 'F' && maze[c + 1][r].left == 1)
			openWall.add('R');
		return openWall;
	}

	public int getCol() {
		return this.c;
	}

	public int getRow() {
		return this.r;
	}

}
