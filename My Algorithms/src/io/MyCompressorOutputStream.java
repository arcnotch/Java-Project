package io;


import java.io.IOException;
import java.io.OutputStream;


public class MyCompressorOutputStream extends OutputStream {

	private OutputStream stringout;
	private int MAX=255;
	
	public MyCompressorOutputStream(OutputStream sout) {
		super();
		this.stringout = sout;
	}
	
	/**
	 * write method is writing to a file the maze
	 */
	@Override
	public void write(byte[] byteArray){
		byte last = 0;
		int counter = 0;
		
		
		for (int i = 0; i < byteArray.length; i++){
			if(byteArray[i] != last){
				try {
					if(counter > 0){
						stringout.write(last);
						stringout.write(counter);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				last = byteArray[i];
				counter = 1;
				
			}else{
				counter++;
				if(counter == MAX){
					try {
						stringout.write(last);
						stringout.write(counter);
					} catch (IOException e) {
						e.printStackTrace();
					}
					counter = 0;
				}
			}
		}
		
		if(counter > 0){
			try {
				stringout.write(last);
				stringout.write(counter);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * write method is writing to a file as bytes
	 */
	@Override
	public void write(int byt) throws IOException {
		stringout.write((byte) byt);
	}
}