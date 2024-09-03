/**
 * 
 * @authors Veronika Titarchuk && Anastasia Dzoba
 * @file Teacher.java
 * class to work with teachers
 */
public class Teacher {

	Teacher(){
		setFirstName("");
		setLastName("");
		setPoBatkovi("");
	}
	
	private String firstName;
	private String lastName;
	private String poBatkovi;
	
	/**
	 * edits teacher fields
	 */
	public void edit() {

		while (true) { // menu

			System.out.println("Редагувати ім'я 1");
			System.out.println("Редагувати прізвище 2");
			System.out.println("Редагувати по батькові 3");
			System.out.println("Для виходу введіть 0");
			int menu = DataInput.getInt();

			if (menu == 0)
				break;

			if (menu == 1) {
				String firstName = "";

				while (true) { // Для того, щоб не можна було зробити кафедру із пустим ім'ям
					try {
						firstName = DataInput.getString("Введіть ім'я студента");
					} catch (Exception e) {
						System.out.println("Помилка");
					}
					if (firstName.equals(""))
						System.out.println("Помилка");
					else
						break;
				}
				setFirstName(firstName);
			}
				

			if (menu == 2) {
				
				String lastName = "";

				while (true) { // Для того, щоб не можна було зробити кафедру із пустим ім'ям
					try {
						lastName = DataInput.getString("Введіть прізвище студента");
					} catch (Exception e) {
						System.out.println("Помилка");
					}
					if (lastName.equals(""))
						System.out.println("Помилка");
					else
						break;
				}
				setLastName(lastName);
			}
			
			if(menu==3) {					
					String poBatkovi = "";

					while (true) { // Для того, щоб не можна було зробити кафедру із пустим ім'ям
						try {
							poBatkovi = DataInput.getString("Введіть по батькові студента");
						} catch (Exception e) {
							System.out.println("Помилка");
						}
						if (poBatkovi.equals(""))
							System.out.println("Помилка");
						else
							break;
					}
					setPoBatkovi(poBatkovi);
			}
			
		}
	}
	
	@Override
	public String toString() {
		return "Teacher " + lastName + " " + firstName + " " + poBatkovi;
	}
	public String getFisrtName() {
		return firstName;
	}
	public void setFirstName(String fisrtName) {
		this.firstName = fisrtName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPoBatkovi() {
		return poBatkovi;
	}
	public void setPoBatkovi(String poBatkovi) {
		this.poBatkovi = poBatkovi;
	}
	
}
