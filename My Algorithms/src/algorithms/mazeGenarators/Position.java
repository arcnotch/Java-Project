package algorithms.mazeGenarators;

public class Position {
	private int level;
	private int row;
	private int colum;
	
	public Position(int l, int r, int c) {
		this.level = l;
		this.row = r;
		this.colum = c;
	}
	public Position(Position posi) {
		this.row = posi.getRow();
		this.level = posi.getLevel();
		this.colum = posi.getColumn();
	}

	public int getLevel() {
		return level;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return colum;
	}

	
	public void setLevel(int l) {
		this.level = l;
	}
	public void setRow(int r) {
		this.row = r;
	}
	public void setColumn(int c) {
		this.colum = c;
	}
	
	@Override
	public String toString(){
		return "{"+this.level+","+this.row+","+this.colum+"}";
	}
	
	//by level,row and column
	@Override
	public boolean equals(Object object) {
		if(object.getClass() == this.getClass()){
			Position casted = (Position)object;
			
			if(casted.getRow() == row && casted.getColumn() == colum && casted.getLevel() == level){
				return true;
			}
		}
		return false;
	}
}