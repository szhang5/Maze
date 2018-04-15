public class Location {
	public int col;
	public int row;
	
	public Location(int col, int row) {
		this.col = col;
		this.row = row;
	}
	
	public String toString() {
		return "(" + col + "," + row + ")";
	}
}
