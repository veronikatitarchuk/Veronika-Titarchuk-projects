/**
 * 
 * @authors Veronika Titarchuk && Anastasia Dzoba
 * @file Teacher.java
 * class to work with students
 */
public class Student {

	Student(){
		setFirstName("");
		setLastName("");
		setPoBatkovi("");
	}
	
	private String firstName;
	private String lastName;
	private String poBatkovi;
	private int course;
	private int group;
	
	/**
	 * edits student fields
	 */
	public void edit() {

		while (true) { // menu

			System.out.println("Редагувати ім'я 1");
			System.out.println("Редагувати прізвище 2");
			System.out.println("Редагувати по батькові 3");
			System.out.println("Редагувати курс 4");
			System.out.println("Редагувати групу 5");
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

			if(menu==4) {					
				int course = 0;

				while (course < 1 || course > 4) {
					course = DataInput.getInt("Введіть курс студента [1-4] : ");
					if (course < 1 || course > 4)
						System.out.println("Помилка, введіть цифру 1-4");
				}
				
				setCourse(course);

		}
			
			if(menu==5) {					
				int group = 0;

				while (group < 1 || group > 6) {
					group = DataInput.getInt("Введіть групу студента [1-6] : ");
					if (group < 1 || group > 6)
						System.out.println("Помилка, введіть цифру 1-6");
				}
				setGroup(group);

		}
			
		}
	}
	
	@Override
	public String toString() {
		return "Student " + lastName + " " + firstName + " " + poBatkovi + " Course " + course + " Group " + group;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
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



	public int getCourse() {
		return course;
	}



	public void setCourse(int course) {
		this.course = course;
	}



	public int getGroup() {
		return group;
	}



	public void setGroup(int group) {
		this.group = group;
	}
	
	
	
}
