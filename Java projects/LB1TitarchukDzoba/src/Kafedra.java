/**
 * @authors Veronika Titarchuk && Anastasia Dzoba
 * @file Kafedra.java
 * main class to work with kafedra
 */
import java.util.Arrays;

public class Kafedra {

	private String name;

	private Teacher teachers[] = new Teacher[5];

	private Student students[] = new Student[15];

	/**
	 * main menu in the kafedra
	 */
	public void edit() {

		boolean goMainMenu = true;
		while (goMainMenu) { // menu

			System.out.println("Меню студентів 1");
			System.out.println("Меню викладачів 2");
			System.out.println("Для виходу введіть 0");
			int menu = DataInput.getInt();

			switch (menu) {
			case 0:
				goMainMenu = false;
				break;
			case 1:
				editStudents();
				break;
			case 2:
				editTeachers();
				break;
			}

		}
	}

	/**
	 * main menu for working with students in the kafedra
	 */
	private void editTeachers() {
		boolean go = true;
		while (go) { // menu
			System.out.println("Створити викладача введіть 1");
			System.out.println("Редагувати викладача введіть 2");
			System.out.println("Видалити викладачат введіть 3");
			System.out.println("Переглянути викладачів введіть 4");
			System.out.println("Вивести всіх викладачів кафедри впорядкованих за алфавітом введіть 5");
			System.out.println("Для виходу введіть 0");
			int menu = DataInput.getInt();
			switch (menu) {
			case 0:
				go = false;
				break;
			case 1:
				createTeacher();
				break;
			case 2:
				editTeacher();
				break;
			case 3:
				deleteTeacher();
				break;
			case 4:
				showTeachers();
				break;
			case 5:
			Search.teachersKafedraAlphabeticOrder(Faculty.currentKafedra);
			}
		}
	}

	/**
	 * Deletes Teacher with input number;
	 */
	private void deleteTeacher() {
		while (true) {
			int TeacherNumber = DataInput.getInt("Введіть номер викладача:\nНазад - введіть 0");
			if (TeacherNumber == 0)
				break;
			else if (TeacherNumber > 0 && TeacherNumber < teachers.length + 1) {
				teachers[TeacherNumber - 1] = new Teacher();
				System.out.println("викладача успішно видалено!");
			} else
				System.out.println("Помилка, викладача з таким номером немає");
		}
	}

	/**
	 * edits teacher
	 */
	private void editTeacher() {
		while (true) {

			int TeacherNumber = DataInput.getInt("Введіть номер викладача для редагування\nНазад 0");
			if (TeacherNumber == 0)
				break;
			else if (TeacherNumber > 0 && TeacherNumber < teachers.length + 1) {

				teachers[TeacherNumber - 1].edit();

			} else
				System.out.println("Помилка, викладача з таким номером немає");
		}
	}

	/**
	 * creates teacher with the input information
	 */
	private void createTeacher() {
		while (true) {
			int TeacherNumber = DataInput.getInt("Введіть номер нового викладача (поточна видаляється)\nНазад 0");
			if (TeacherNumber == 0)
				break;
			else if (TeacherNumber > 0 && TeacherNumber < teachers.length + 1) {

				teachers[TeacherNumber - 1] = new Teacher();

				///////////////////////

				String firstName = "";

				while (true) { // Для того, щоб не можна було зробити кафедру із пустим ім'ям
					try {
						firstName = DataInput.getString("Введіть ім'я викладача");
					} catch (Exception e) {
						System.out.println("Помилка");
					}
					if (firstName.equals(""))
						System.out.println("Помилка");
					else
						break;
				}

				teachers[TeacherNumber - 1].setFirstName(firstName);

				//////////////////////

				String lastName = "";

				while (true) { // Для того, щоб не можна було зробити кафедру із пустим ім'ям
					try {
						lastName = DataInput.getString("Введіть прізвище викладача");
					} catch (Exception e) {
						System.out.println("Помилка");
					}
					if (lastName.equals(""))
						System.out.println("Помилка");
					else
						break;
				}

				teachers[TeacherNumber - 1].setLastName(lastName);

				/////////////////////////

				String poBatkovi = "";

				while (true) { // Для того, щоб не можна було зробити кафедру із пустим ім'ям
					try {
						poBatkovi = DataInput.getString("Введіть по батькі викладача");
					} catch (Exception e) {
						System.out.println("Помилка");
					}
					if (poBatkovi.equals(""))
						System.out.println("Помилка");
					else
						break;
				}

				teachers[TeacherNumber - 1].setPoBatkovi(poBatkovi);

				System.out.println("Викладач успішно створений!");

			} else
				System.out.println("Помилка, викладача з таким номером немає");
		}
	}

	/**
	 * @return String with faculties` names
	 */
	private void showTeachers() {
		for (int i = 0; i < teachers.length; i++) {
			System.out.println(teachers[i].toString());
		}

	}

	/**
	 * main menu for working with students in the kafedra
	 */
	private void editStudents() {
		while (true) { // menu
			System.out.println("Створити студента введіть 1");
			System.out.println("Редагувати студента введіть 2");
			System.out.println("Видалити студентат введіть 3");
			System.out.println("Переглянути студентів введіть 4");
			System.out.println("Вивести всіх студентів кафедри впорядкованих за алфавітом введіть 5");
			System.out.println("Вивести всіх студентів кафедри впорядкованих за курсами введіть 6");
			System.out.println("Вивести всіх студентів кафедри вказаного курсу введіть 7");
			System.out.println("Вивести всіх студентів кафедри вказаного курсу впорядкованих за алфавітом введіть 8");
			System.out.println("Для виходу введіть 0");
			int menu = DataInput.getInt();

			if (menu == 0)
				break;

			else if (menu == 1)
				createStudent();

			else if (menu == 2)
				editStudent();

			else if (menu == 3)
				deleteStudent();
			
			else if (menu == 4)
				showStudents();
			
			else if (menu == 5)
				Search.studentsKafedraAlphabeticOrder(Faculty.currentKafedra);
			
			else if (menu == 6)
				Search.studentsKafedraCourseOrder(Faculty.currentKafedra);
			else if (menu == 7)
				studentsKafedraExectCourse(Faculty.currentKafedra);
			else if (menu == 8)
				studentsKafedraExectCourseAlphabeticOrder(Faculty.currentKafedra);
		}
	}

	/**
	 * outputs students of the current kafedra in alphabetic order
	 * @param currentKafedra kafedra you are working with
	 */
	private void studentsKafedraExectCourseAlphabeticOrder(int currentKafedra) {
		int course =0;
		while(course<1||course>6) {
		course = DataInput.getInt("Введіть курс, студентів, якого хочете вивести");
		if(course<1||course>6)
			System.out.println("Помилка, введіть значення [1-6]");
		}
		Search.studentsKafedraExectCourseAlphabeticOrder(currentKafedra, course);
	}
	
	/**
	 * outputs students of the current kafedra of the input course
	 * @param currentKafedra kafedra you are working with
	 */
	private void studentsKafedraExectCourse(int currentKafedra) {
		int course =0;
		while(course<1||course>6) {
		course = DataInput.getInt("Введіть курс, студентів, якого хочете вивести");
		if(course<1||course>6)
			System.out.println("Помилка, введіть значення [1-6]");
		}
		Search.kafedraStudentsCourse(currentKafedra, course);
	}

	/**
	 * Deletes student with input number;
	 */
	private void deleteStudent() {
		while (true) {
			int studentNumber = DataInput.getInt("Введіть номер студента:\nНазад - введіть 0");
			if (studentNumber == 0)
				break;
			else if (studentNumber > 0 && studentNumber < students.length + 1) {
				students[studentNumber - 1] = new Student();
				System.out.println("Студента успішно видалено!");
			} else
				System.out.println("Помилка, студента з таким номером немає");
		}
	}

	/**
	 * edits student
	 */
	private void editStudent() {
		while (true) {

			int studentNumber = DataInput.getInt("Введіть номер студента для редагування\nНазад 0");
			if (studentNumber == 0)
				break;
			else if (studentNumber > 0 && studentNumber < students.length + 1) {

				students[studentNumber - 1].edit();

			} else
				System.out.println("Помилка, студента з таким номером немає");
		}
	}

	/**
	 * creates student with the input information
	 */
	private void createStudent() {
		while (true) {
			int studentNumber = DataInput.getInt("Введіть номер нового студента (поточна видаляється)\nНазад 0");
			if (studentNumber == 0)
				break;
			else if (studentNumber > 0 && studentNumber < students.length + 1) {

				students[studentNumber - 1] = new Student();

				///////////////////////

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

				students[studentNumber - 1].setFirstName(firstName);

				//////////////////////

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

				students[studentNumber - 1].setLastName(lastName);

				/////////////////////////

				String poBatkovi = "";

				while (true) { // Для того, щоб не можна було зробити кафедру із пустим ім'ям
					try {
						poBatkovi = DataInput.getString("Введіть по батькі студента");
					} catch (Exception e) {
						System.out.println("Помилка");
					}
					if (poBatkovi.equals(""))
						System.out.println("Помилка");
					else
						break;
				}

				students[studentNumber - 1].setPoBatkovi(poBatkovi);

				//////////////////////////////

				int course = 0;

				while (course < 1 || course > 4) {
					course = DataInput.getInt("Введіть курс студента [1-4] : ");
					if (course < 1 || course > 4)
						System.out.println("Помилка, введіть цифру 1-4");
				}

				students[studentNumber - 1].setCourse(course);

				////////////////////////////////

				int group = 0;

				while (group < 1 || group > 6) {
					group = DataInput.getInt("Введіть групу студента [1-6] : ");
					if (group < 1 || group > 6)
						System.out.println("Помилка, введіть цифру 1-6");
				}

				students[studentNumber - 1].setGroup(group);

				System.out.println("Студент успішно створений!");

			} else
				System.out.println("Помилка, студента з таким номером немає");
		}
	}

	/**
	 * @return String with faculties` names
	 */
	private void showStudents() {
		for (int i = 0; i < students.length; i++) {
			System.out.println(students[i].toString());
		}

	}
/**
 * initializes teachers and students
 */
	public void init() {
		for (int i = 0; i < teachers.length; i++)
			teachers[i] = new Teacher();
		for (int i = 0; i < students.length; i++)
			students[i] = new Student();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher[] getTeachers() {
		return teachers;
	}

	public void setTeachers(Teacher[] teachers) {
		this.teachers = teachers;
	}

	public Student[] getStudents() {
		return students;
	}

	public void setStudents(Student[] students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Kafedra " + name + "\nTeachers:\n" + Arrays.toString(teachers) + "\nStudents:\n"
				+ Arrays.toString(students);
	}

}
