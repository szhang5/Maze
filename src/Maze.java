import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.swing.JPanel;

class Maze extends JPanel {
	private static final int N = 30;
	private static char[][] map = new char[N][N];
	private static Cell entrance;
	private static Cell exit;
	private static Stack<Cell> s = new Stack<Cell>();
	Random random = new Random(); 

	public Maze() {
		newMap();
		newEntrance();
		newExit();
		drawPath(entrance);
	}

	public void newMap() {
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				map[c][r] = 'W';
			}
		}
	}

	public void newEntrance() {
		int r = random.nextInt(N);
		int c;
		int k; 

		if (r == 0 || r == N-1) {
			c = random.nextInt(N); // create random number from 0 to N (not inclusive);
			map[c][r] = 'S';
			entrance = new Cell(c, r);
		}
		else {
			k = (random.nextInt() % 2 == 0) ? 0 : N-1; // choose between 0 and N-1
			map[k][r] = 'S';
			entrance = new Cell(k, r);
		}
		s.push(entrance);
		System.out.println(entrance);
	}
	
	public void newExit() {
		int r = random.nextInt(N);
		int c;
		int k; 

		if (r == 0 || r == N-1) {
			c = random.nextInt(N); // create random number from 0 to N (not inclusive);
			if(map[c][r] != 'S') {
				map[c][r] = 'D';		
			}else {
				newExit();
			}
			exit = new Cell(c, r);
		}else {
			k = (random.nextInt() % 2 == 0) ? 0 : N-1; // choose between 0 and N-1
			if(map[k][r] != 'S') {
				map[k][r] = 'D';		
			}else {
				newExit();
			}
			exit = new Cell(k, r);
		}
		System.out.println(exit);
	}
	
	public void drawPath(Cell curLocation) {
//		System.out.println(curLocation);	
		while(!s.isEmpty()) {
			curLocation = findDirection(curLocation);
//			System.out.println("in while loop: " + curLocation);
			if(curLocation == null) {
				if(s.isEmpty())
					break;
				curLocation = s.pop();
			}
			else {
				int x = curLocation.x;
				int y = curLocation.y;
				s.push(curLocation);
				if( map[x][y] == 'W' || map[x][y] == 'D') {				
					if(map[x][y] == 'W')
						map[x][y] = 'p';
				}
			}
//			System.out.println(s);
//			System.out.println(s.isEmpty());
		}
	}
	
	public Cell findDirection(Cell location) {
		ArrayList<Cell> direction = new ArrayList<Cell>();
		Cell newlocation; 
		int[] x = {0, 1, 0, -1};
		int[] y = {-1, 0, 1, 0};
		for(int i = 0; i < x.length; i++) {
			int c = location.x+x[i];
			int r = location.y+y[i];
			if((c >= 0 && c <= N-1) && (r >= 0 && r <= N-1) && map[c][r] != 'S' && map[c][r] != 'p') {
				newlocation = new Cell(c, r);
//				System.out.println("findDirection"+newlocation);
				if(isValidMove(newlocation)) 
					direction.add(newlocation);
			}			
		}
//		for(int i = 0; i < direction.size(); i++) {
//			System.out.println("ValidDirection"+direction.get(i));
//		}
		if(direction.size() > 0) {
			int j = random.nextInt(direction.size());
			return direction.get(j);	
		}
		else
			return null;
	} 
	
	public boolean isValidMove(Cell location) {
		int count = 0;
		int x = location.x;
		int y = location.y;
		
		if((x >= 0 && x <= N-1) && (y-1 >= 0 && y-1 <= N-1) && (map[x][y-1] == 'p' || map[x][y-1] == 'S'))
			count++;
		if((x+1 >= 0 && x+1 <= N-1) && (y >= 0 && y <= N-1) && (map[x+1][y] == 'p' || map[x+1][y] == 'S'))
			count++;
		if((x >= 0 && x <= N-1) && (y+1 >= 0 && y+1 <= N-1) && (map[x][y+1] == 'p' || map[x][y+1] == 'S'))
			count++;
		if((x-1 >= 0 && x-1 <= N-1) && (y >= 0 && y <= N-1) && (map[x-1][y] == 'p' || map[x-1][y] == 'S'))
			count++;
		if(count >= 2)
			return false;
		else 
			return true;	
		
	}
	
	
	public void paintComponent(Graphics g) {
		int size = 20;
		super.paintComponent(g);
		g.setColor(Color.black);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'W') {
					g.fillRect(i * size, j * size, size, size);
				}
				if(map[i][j] == 'S') {
					g.setColor(Color.red);
					g.fillRect(i * size, j * size, size, size);
					g.setColor(Color.black);
				}
				if(map[i][j] == 'D') {
					g.setColor(Color.yellow);
					g.fillRect(i * size, j * size, size, size);
					g.setColor(Color.black);
				}
				if(map[i][j] == 'p') {	
					g.setColor(Color.white);
					g.fillRect(i * size, j * size, size, size);
					g.setColor(Color.black);
				}	
			}
		}
	}
}
