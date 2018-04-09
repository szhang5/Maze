public class Cell {
	private int row;
	private int col;
	public int up;
	public int right;
	public int down;
	public int left;
	public int checkStatus;
	
	public Cell(){
		
	}
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}
