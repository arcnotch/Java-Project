package boot;

import algorithms.mazeGenarators.*;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Run {

	public static void main1(String a, String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Levels of Maze= ");
		int levels = in.nextInt();
		
		System.out.print("Rows of Maze= ");
		int rows = in.nextInt();
		
		System.out.print("Columns of Maze= ");
		int coloums = in.nextInt();
		
		System.out.print("Type of Maze(Growing = 1, Simple = 2) = ");
		int type = in.nextInt();
		
		in.close();
		
		Maze3dAlgorithms [] algo = new Maze3dAlgorithms[3];
		algo[0] = new SimpleMaze3dGenerator();
		algo[1] = new GrowingTreeGenerator();
		
		Maze3d my_maze3d;
		
		if(type == 1){
			my_maze3d = algo[1].generate(levels, rows, coloums);
		}else{
			my_maze3d = algo[0].generate(levels, rows, coloums);
		}

		System.out.println("num of levels in the maze: "+my_maze3d.gettLevels());
		System.out.println("num of rows in the maze: "+my_maze3d.gettRows());
		System.out.println("num of coloums in the maze: "+my_maze3d.gettColumns());
		System.out.println(my_maze3d.toString());
	
	}

	public static void main(String[] args) {
		Maze3dGenerator mg = new GrowingTreeGenerator();
		Maze3d maze = mg.generate(10,10,10);
		
		System.out.println(maze.toString());

		OutputStream out = null;
		
		try {
			out = new MyCompressorOutputStream( new FileOutputStream("1.maz") );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			out.write(maze.toByteArray());
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		InputStream in = null;
		try {
			in = new MyDecompressorInputStream( new FileInputStream("1.maz") );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		byte b[] = new byte[maze.toByteArray().length];
		
		try {
			in.read(b);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Maze3d loaded=new Maze3d(b);
//		System.out.println("----------------------------------------");
//		System.out.println(loaded.toString());
		System.out.println(loaded.equals(maze));

	}

}
