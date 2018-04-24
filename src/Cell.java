public class Cell {
	public int up;    		// - stand for the up wall of the cell - //
	public int right; 		// - stand for the right wall of the cell - //
	public int down;  		// - stand for the down wall of the cell - //
	public int left;  		// - stand for the left wall of the cell - //
	public int checkStatus; 	// - check if the cell be visited - //
	public char isPath;	    // - check if the cell part of the Path to get out of the maze - //
	public Location parent; 	// - record the parent cell of the current cell - //
	
	public String toString() {
		return "(" + up + ", " + right + ", " + down + ", "+ left + ", " + checkStatus + ", " + isPath + ", " + parent + ")";
	}
}

