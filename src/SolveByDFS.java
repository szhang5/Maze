import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class SolveByDFS {
	public static final int N = 20;
	private Stack<Location> s = new Stack<Location>();
	private int c = 0;
	private int r = 0;
	private Cell[][] maze;

	public SolveByDFS(Cell[][] maze) {
		this.maze = maze;
	}

	public Cell[][] getMaze() {
		findPath();
		return maze;
	}
	
	public void findPath() {
		Location tmp;

		if(c == 0 && r == 0)
			maze[c][r].isPath = 'S'; //startPoint
		ArrayList<Character> validDirction = findDirection();
		if (getDirection(validDirction) == null) {
			maze[c][r].isPath = 'W'; //wrong way
			if(s.isEmpty())
				return;
			else {
				tmp = s.pop();
				c = tmp.col;
				r = tmp.row;
				findPath();
			}		
		} else {
			s.push(new Location(c, r));
			Character direction = getDirection(validDirction);
			if (direction == 'U') {
				r--;
				if(c == N-1 && r == N-1)
					maze[c][r].isPath = 'E'; //endPoint
				else
					maze[c][r].isPath = 'T';
				s.push(new Location(c, r));
			}
			if (direction == 'L') {
				c--;					
				if(c == N-1 && r == N-1)
					maze[c][r].isPath = 'E'; //endPoint
				else
					maze[c][r].isPath = 'T';
				s.push(new Location(c, r));
			}
			if (direction == 'D') {
				r++;					
				if(c == N-1 && r == N-1)
					maze[c][r].isPath = 'E'; //endPoint
				else
					maze[c][r].isPath = 'T';
				s.push(new Location(c, r));
			}
			if (direction == 'R') {
				c++;
				if(c == N-1 && r == N-1)
					maze[c][r].isPath = 'E'; //endPoint
				else
					maze[c][r].isPath = 'T';
				s.push(new Location(c, r));
			}
		}
	
	}

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

	public Character getDirection(ArrayList<Character> direction) {
		if (direction.size() > 0) {
			Random random = new Random();
			int i = random.nextInt(direction.size());
			return direction.get(i);
		} 
		else
			return null;
	}
	
	public int getCol() {
		return this.c;
	}
	
	public int getRow() {
		return this.r;
	}
}
