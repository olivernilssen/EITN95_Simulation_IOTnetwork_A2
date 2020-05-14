package Task_1.src;

import java.io.*;
import java.util.Properties;

/*
 * configFile.java
 *
 * Mikael Andersson, Lund Institute of Technology, 2002-2005
 *
 */

public class configFile {
	FileWriter writer;
	FileReader reader;
	Properties props;

	String aNewLine = "\r\n";

	/*
	 * Creates a new reader of properties
	 *
	 * @param pFileName The filename to use
	 * 
	 * @param pAppend Whether to append to existing data in file
	 *
	 */
	public configFile(String path, boolean pAppend) {

		try {
			reader = new FileReader(path);
			writer = new FileWriter(path, pAppend);
			props = new Properties();
			props.load(reader);

		} catch (FileNotFoundException ex) {
			System.out.println("file not found " + path);
		} catch (IOException ex) {
			// I/O error
		}
	}

	/*
	 * Closes the fileprinter
	 *
	 */
	public void close() {
		try {
			reader.close();
		} catch (IOException pIOE) {
			System.out.println(pIOE.toString());
		}
	}

	/*
	 * Appends a new line to the file
	 *
	 * @param pString The string to append to the file
	 *
	 */
	public void println(String pString) {
		try {
			writer.write(pString, 0, pString.length());
			writer.write(aNewLine, 0, aNewLine.length());
		} catch (IOException pIOE) {
			System.out.println(pIOE.toString());
		}
	}

	public void storeProps(String prop, String value) {
		props.setProperty(prop, value);

		try {
			props.store(writer, prop + " settings");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Appends a text to the file
	 *
	 * @param	pString		The string to append to the file
	 *
	 */
	public void print(String pString)
	{		
		try
		{
			writer.write(pString, 0, pString.length());
        }
        catch(IOException pIOE)
        {
        	System.out.println(pIOE.toString());
        }       
	}	
}