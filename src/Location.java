public class Location { 
	public int col; // - coordinate of x-axis - //
	public int row; // - coordinate of y-axis - //
	
	public Location(int col, int row) {
		this.col = col;
		this.row = row;
	}
	
	public String toString() {
		return "(" + col + "," + row + ")"; 
	}
}

