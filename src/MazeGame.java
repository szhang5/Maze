import javax.swing.*;

public class MazeGame extends JFrame {
    public MazeGame() {
		Maze a = new Maze();
        add(a);
    }
	public static void main(String[] args) {
		MazeGame f = new MazeGame();

	    JMenuBar menu = new JMenuBar();
        f.setJMenuBar(menu);
        JMenu game = new JMenu("Maze");
        JMenuItem about = game.add("About Maze");
        JMenuItem pause = game.add("Pause");
        JMenuItem goon = game.add("Continue");
        JMenuItem newgame = game.add("New Game");
        JMenuItem exit = game.add("Exit");
        JMenu help = new JMenu("Help");
        menu.add(game);
        menu.add(help);
        
//      f.setContentPane(new Maze());
        f.setSize(600, 644);
        f.setTitle("Maze Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
//        f.setResizable(false);
	}
}


