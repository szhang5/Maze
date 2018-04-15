public class Cell {
	public int up;
	public int right;
	public int down;
	public int left;
	public int checkStatus;
	public char isPath;
	public Location parent;
	
	public String toString() {
		return "(" + up + ", " + right + ", " + down + ", "+ left + ", " + checkStatus + ", " + isPath + ", " + parent + ")";
	}
}
