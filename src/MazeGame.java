import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

public class MazeGame extends JFrame implements ActionListener {

	static JMenuItem newgame;
	static JMenuItem exit; 
	static JMenuItem dfs;
	static JMenuItem bfs;
	static Maze m;
	private Timer t;
	
	public MazeGame() {
		newgame.addActionListener(this);
		exit.addActionListener(this);
		dfs.addActionListener(this);
		bfs.addActionListener(this);
		m = new Maze();
		add(m);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(newgame)) {
			Maze.cell = m.getNewMaze();
			repaint();
		}
		if(e.getSource().equals(exit)) {
			System.exit(0);
		}
		if(e.getSource().equals(dfs)) {
			SolveByDFS dfs = new SolveByDFS(Maze.cell);
			t = new Timer(50, new TimerListener(dfs));
	        t.start();
		}
		if(e.getSource().equals(bfs)) {
			SolveByBFS bfs = new SolveByBFS(m.maze);	
			m.maze = bfs.getMaze();
			System.out.println(Arrays.deepToString(m.maze));
			repaint();
		}
		
	}

	public static void main(String[] args) {
		JMenuBar menu = new JMenuBar();
		JMenu game = new JMenu("Maze");
		newgame = new JMenuItem("New Game");
		game.add(newgame);
		exit = new JMenuItem("Exit");
        game.add(exit);
		JMenu solution = new JMenu("Solution");
		dfs = new JMenuItem("DFS Solution");
		bfs = new JMenuItem("BFS Solution");
		solution.add(dfs);
		solution.add(bfs);

		MazeGame f = new MazeGame();
		f.setJMenuBar(menu);

		menu.add(game);
		menu.add(solution);

		// f.setContentPane(new Maze());
		f.setSize(400, 444);
		f.setTitle("Maze Test");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(false);
	}

	class TimerListener implements ActionListener {
		private SolveByDFS dfs;
		
		public TimerListener(SolveByDFS dfs) {
			this.dfs = dfs;
		}
        public void actionPerformed(ActionEvent e) {	
			Maze.cell = dfs.getMaze();
//			System.out.println(Arrays.deepToString(Maze.cell));
			if ((dfs.getCol() == dfs.N-1 && dfs.getRow() == dfs.N-1)){
				t.stop();
			}
			repaint();
        }
    }

}
