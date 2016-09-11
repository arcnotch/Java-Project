package io;


import java.io.IOException;
import java.io.InputStream;


public class MyDecompressorInputStream extends InputStream{
	InputStream instream;
	
	public MyDecompressorInputStream(InputStream sin) {
		super();
		this.instream = sin;
	}
	
	/**
	 * read method is reading from a file and input into the program the content
	 * @return int
	 */
	@Override
	public int read() throws IOException 
	{		
		return (int)instream.read();
		
	}

	/**
	 * read method is reading from a file array of bytes and input into the program the content
	 * @param b is the array we want to read
	 * @return int
	 */
	@Override
	public int read(byte[] byt) throws IOException
	{
		int temp;
		byte b;
		int counter = 0;
		int i = 0;
		
		while( (temp = instream.read()) != -1) {
			b =  new Integer(temp).byteValue();
			counter = instream.read();
			while(counter > 0)
			{
				byt[i] = b;
				counter=counter-1;
				i++;
			}
		}
		
		return 1;
	}

}
