import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class SolveByBFS {
	private static final int N = 20;
	private Queue<Location> q = new LinkedList<Location>();
	private int c = 0;
	private int r = 0;
	private Cell[][] maze;

	public SolveByBFS(Cell[][] maze) {
		this.maze = maze;
		Cell tmp = findPath();
		getPath(tmp);
	}

	public Cell[][] getMaze() {
		return maze;
	}

	public Cell findPath() {
		Location tmp;
		do {
			System.out.println("(" + c + " ," + r + ")");
			if(c == 0 && r == 0) {
				maze[c][r].isPath = 'S';
				maze[c][r].parent = null;
			}
			System.out.println(maze[c][r]);
			
			ArrayList<Character> validDirction = findDirection();
			System.out.println(Arrays.toString(validDirction.toArray()));
			
			if(validDirction.size() > 0) {
				for(int i = 0; i < validDirction.size(); i++) {
					Character direction = validDirction.get(i);
					System.out.println(direction);
					int tmpC = c;
					int tmpR = r;
					if (direction == 'U') {
						tmpR--;
						if(tmpC == N-1 && tmpC == N-1)
							maze[tmpC][tmpR].isPath = 'E'; //endPoint
						else
							maze[tmpC][tmpR].isPath = 't';
						maze[tmpC][tmpR].parent = new Location(c, r);
						q.add(new Location(tmpC, tmpR));
					}
					if (direction == 'L') {
						tmpC--;					
						if(tmpC == N-1 && tmpR == N-1)
							maze[tmpC][tmpR].isPath = 'E'; //endPoint
						else
							maze[tmpC][tmpR].isPath = 't';
						maze[tmpC][tmpR].parent = new Location(c, r);
						q.add(new Location(tmpC, tmpR));
					}
					if (direction == 'D') {
						tmpR++;					
						if(tmpC == N-1 && tmpR == N-1)
							maze[tmpC][tmpR].isPath = 'E'; //endPoint
						else
							maze[tmpC][tmpR].isPath = 't';
						maze[tmpC][tmpR].parent = new Location(c, r);
						q.add(new Location(tmpC, tmpR));
					}
					if (direction == 'R') {
						tmpC++;
						if(tmpC == N-1 && tmpR == N-1)
							maze[tmpC][tmpR].isPath = 'E'; //endPoint
						else
							maze[tmpC][tmpR].isPath = 't';
						maze[tmpC][tmpR].parent = new Location(c, r);
						q.add(new Location(tmpC, tmpR));
					}
				}
			}
			System.out.println(q);
			tmp = q.poll();
			c = tmp.col;
			r = tmp.row;
			System.out.println("(" + c + " ," + r + ")");
		}while(!(c == N-1 && r == N-1));
		return maze[N-1][N-1];
	}
	
	public void getPath(Cell cell) {
		Location path;
		path = cell.parent;
		do {
			c = path.col;
			r = path.row;
			if(c == 0 && r == 0)
				maze[c][r].isPath = 'S';
			else
				maze[c][r].isPath = 'T';
			path = maze[c][r].parent;
		}while(path != null);
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

}
