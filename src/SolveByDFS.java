import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class SolveByDFS {
	private static final int N = 20;
	private static Stack<Location> s = new Stack<Location>();
	private static int c = 0;
	private static int r = 0;
	private static Cell[][] maze;

	public SolveByDFS(Cell[][] maze) {
		this.maze = maze;
		findPath();
	}

	public Cell[][] getMaze() {
		return maze;
	}
	
	public void findPath() {
		Location check;
		Location tmp;
		do {
			Location l = new Location(c, r);
			System.out.println(l);
			if(c == 0 && r == 0)
				maze[c][r].isPath = 'S'; //startPoint
			ArrayList<Character> validDirction = findDirection();
			System.out.println(Arrays.toString(validDirction.toArray()));
			
			if (getDirection(validDirction) == null) {
				maze[c][r].isPath = 'W'; //wrong way
				if(s.isEmpty())
					break;
				else {
					tmp = s.pop();
					c = tmp.col;
					r = tmp.row;
					System.out.println("after pop: c = " + c + ", r = " + r);
					findPath();
				}		
			} else {
				s.push(new Location(c, r));
				Character direction = getDirection(validDirction);
				System.out.println(direction);
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
			System.out.println("c = " + c + ", r = " + r + ";" + maze[c][r]);
			System.out.println(s);
			check = s.peek();
		}while (!(check.col == N-1 && check.row == N-1));
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
}
