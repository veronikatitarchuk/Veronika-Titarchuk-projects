/**
 * 
 * @authors Veronika Titarchuk && Anastasia Dzoba
 * @file Tester.java
 *
 */
public class Tester {
	public static int cuttentFaculty;
	private static Faculty[] faculty = new Faculty[6];
	public static int facultyNumber;

	public static void main(String[] args) {
		init(); // Creates faculties in the array

		FillDataBase.fillDataBase();
		menu(); // main menu
	}

	/**
	 * main menu in the project
	 */
	private static void menu() {
		boolean go = true;
		while (go) { // menu
			System.out.println("Створити факультет введіть 1");
			System.out.println("Переіменувати факультету введіть 2");
			System.out.println("Редагувати факультет введіть 3");
			System.out.println("Видалити факультет введіть 4");
			System.out.println("Переглянути факультети введіть 5");
			System.out.println("Для пошуку викладача за ПІБ введіть 6");
			System.out.println("Для пошуку студента за ПІБ введіть 7");
			System.out.println("Для пошуку стедента за курсом введіть 8");
			System.out.println("Для пошуку студента за групою введіть 9");
			System.out.println("Для виходу введіть 0");
			int menu = DataInput.getInt();

			switch (menu) {
			case 0:
				go = false;
				break;
			case 1:
				createFaculty();
				break;
			case 2:
				changeFacultyName();
				break;
			case 3:
				editFaculty();
				break;
			case 4:
				deleteFaculty();
				break;
			case 5:
				System.out.println(showFaculties());
				break;
			case 6:
				Search.searchTeachersPIB();
				break;
			case 7:
				Search.searchStudentsPIB();
				break;
			case 8:
				Search.searchStudentsCourse();
			case 9:
				Search.searchStudentsGroupe();
				break;
			}
		}
	}

	/**
	 * changes faculty name
	 */
	private static void changeFacultyName() {
		while (true) {
			int facultyNumber = DataInput.getInt("Введіть номер факультету:\nНазад - введіть 0");
			if (facultyNumber == 0)
				break;
			else if (facultyNumber > 0 && facultyNumber < faculty.length + 1) {
				String newName = "";
				try {
					newName = DataInput.getString("Введіть нову назву: ");
				} catch (Exception e) {
					System.out.println("Помилка");
				}
				faculty[facultyNumber].setName(newName);
				System.out.println("Факультет успішно переіменовано!");
			} else
				System.out.println("Помилка, факультету з таким номером немає");
		}
	}

	/**
	 * Deletes faculty with input number;
	 */
	private static void deleteFaculty() {
		while (true) {
			int facultyNumber = DataInput.getInt("Введіть номер факультету:\nНазад - введіть 0");
			if (facultyNumber == 0)
				break;
			else if (facultyNumber > 0 && facultyNumber < faculty.length + 1) {
				faculty[facultyNumber - 1] = new Faculty();
				faculty[facultyNumber - 1].setName("Новий факультет");
				System.out.println("Факультет успішно видалено!");
			} else
				System.out.println("Помилка, факультету з таким номером немає");
		}
	}

	/**
	 * edits faculty
	 */
	private static void editFaculty() {
		while (true) {
			facultyNumber = DataInput.getInt("Введіть номер факультету для редагування\nНазад 0");
			if (facultyNumber == 0)
				break;
			else if (facultyNumber > 0 && facultyNumber < faculty.length + 1) {
				cuttentFaculty = facultyNumber-1;
				faculty[facultyNumber - 1].edit();

			} else
				System.out.println("Помилка, факультету з таким номером немає");
		}
	}

	/**
	 * creates faculty
	 */
	private static void createFaculty() {
		while (true) {
			int facultyNumber = DataInput.getInt("Введіть номер нового факультету (поточний видаляється)\nНазад 0");
			if (facultyNumber == 0)
				break;
			else if (facultyNumber > 0 && facultyNumber < faculty.length + 1) {

				faculty[facultyNumber - 1] = new Faculty();

				String facultyName = "";

				while (true) { // Для того, щоб не можна було зробити факультет із пустим ім'ям
					try {
						facultyName = DataInput.getString("Введіть назву факультету");
					} catch (Exception e) {
						System.out.println("Помилка");
					}
					if (facultyName.equals(""))
						System.out.println("Помилка");
					else
						break;
				}

				faculty[facultyNumber - 1].setName(facultyName);

			} else
				System.out.println("Помилка, факультету з таким номером немає");
		}
	}

	/**
	 * @return String with faculties` names
	 */
	private static String showFaculties() {
		String res = "";
		for (int i = 0; i < faculty.length; i++) {
			res += (i + 1) + ". " + faculty[i].getName() + "\n";
		}
		return res;
	}

	/**
	 * Creates faculties in the array
	 */
	private static void init() {
		for (int i = 0; i < faculty.length; i++) {
			faculty[i] = new Faculty();
			faculty[i].init();
			faculty[i].setName("Новий факультет");
		}
		for (int i = 0; i < faculty.length; i++) {
			for (int j = 0; Tester.getFaculty()[0].getKafedras().length > j; j++) {
				Tester.getFaculty()[i].getKafedras()[j].init();
			}
		}
		faculty[1].setName("sfg");
		faculty[2].setName("FI");
		faculty[3].setName("sfgdfhh");
	}

	public static Faculty[] getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty[] faculty) {
		Tester.faculty = faculty;
	}

}
