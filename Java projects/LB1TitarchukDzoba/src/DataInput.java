
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class DataInput {

	/**
	 * 
	 * @return long value of the input string;
	 * @throws IOException
	 */

	public static Long getLong(String st) throws Exception {

		String s = getString(st);
		Long value = Long.valueOf(s);
		return value;

	}

	/**
	 * 
	 * @return long value of the input string;
	 * @throws IOException
	 */
	public static Long getLong() throws Exception {
		String s = getString();
		Long value = Long.valueOf(s);
		return value;
	}

	/**
	 * 
	 * @return long value of the input string;
	 * @throws IOException
	 */
	public static char getChar(String st) throws Exception {
		String s = getString(st);
		return s.charAt(0);
	}

	/**
	 * 
	 * @return long value of the input string;
	 * @throws IOException
	 */
	public static char getChar() throws Exception {
		String s = getString();
		return s.charAt(0);
	}

	public static double getDouble(String st) {
		String s = "";
		while (true) {
			try {
				s = getString(st);
				double value = Double.valueOf(s);
				return value;
			} catch (Exception e) {
				System.out.println("Помилка");
			}
		}
	}
	
	public static double getDouble(){
			String s = "";
		while (true) {
			try {
				s = getString();
				double value = Double.valueOf(s);
				return value;
			} catch (Exception e) {
				System.out.println("Помилка");
			}
		}
	}

	/**
	 * 
	 * @return long value of the input string;
	 * @throws IOException
	 */
	public static Integer getInt(String st) {
		String s = "";
		while (true) {
			try {
				s = getString(st);
				Integer value = Integer.valueOf(s);
				return value;
			} catch (Exception e) {
				System.out.println("Помилка");
			}
		}
	}

	/**
	 * 
	 * @return long value of the input string;
	 * @throws IOException
	 */
	public static Integer getInt() {
		String s = "";
		while (true) {
			try {
				s = getString();
				Integer value = Integer.valueOf(s);
				return value;
			} catch (Exception e) {
				System.out.println("Помилка");
			}
		}
	}

	/**
	 * 
	 * @return input string;
	 * @throws IOException
	 */
	public static String readLine(String st) throws Exception {
		System.out.println(st);

		return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

	/**
	 * 
	 * @return input string;
	 * @throws IOException
	 */
	public static String readLine() throws Exception {
		return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

	/**
	 * 
	 * @return input string;
	 * @throws IOException
	 */
	public static String getString(String st) throws Exception {
		System.out.println(st);
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	/**
	 * 
	 * @return input string;
	 * @throws IOException
	 */
	public static String getString() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

}