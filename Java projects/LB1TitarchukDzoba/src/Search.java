
/**
 * @authors Veronika Titarchuk && Anastasia Dzoba
 * @file Search.java
 */
import java.util.Arrays;

public class Search {

	/**
	 * outputs teachers with the last name, first name or по батькові that matches
	 * the input string
	 */
	public static void searchTeachersPIB() {
		String search = "";
		try {
			search = DataInput.getString("Введіть ім'я, прізвище або по батькові викладача: ");
		} catch (Exception e) {
			System.out.println("Помилка");
		}

		Teacher[] toSearch = collectAllTeachers();
		System.out.println("Результати пошуку: \n");
		for (int i = 0; i < toSearch.length; i++) {
			if (search.equals(toSearch[i].getFisrtName()) || search.equals(toSearch[i].getLastName())
					|| search.equals(toSearch[i].getPoBatkovi()))
				System.out.println(toSearch[i].toString());
		}
		System.out.println();
	}

	/**
	 * 
	 * @return array with all teachers in all faculties in all kafedras
	 */
	private static Teacher[] collectAllTeachers() {
		Teacher[] allTeachers = new Teacher[Tester.getFaculty().length * Tester.getFaculty()[0].getKafedras().length
				* Tester.getFaculty()[0].getKafedras()[0].getTeachers().length];
		Faculty[] toColect = Tester.getFaculty().clone();

		int number = 0;
		for (int i = 0; Tester.getFaculty().length > i; i++) {
			for (int j = 0; Tester.getFaculty()[i].getKafedras().length > j; j++) {
				for (int t = 0; t < Tester.getFaculty()[i].getKafedras()[j].getTeachers().length; t++) {
					allTeachers[number++] = toColect[i].getKafedras()[j].getTeachers()[t];
				}
			}
		}
		return allTeachers;
	}

	/**
	 * 
	 * @param faculty
	 *            faculty to collect
	 * @return array with all teachers in all kafedras
	 */
	private static Teacher[] collectAllTeachersFaculty(int faculty) {
		Teacher[] allTeachers = new Teacher[Tester.getFaculty()[0].getKafedras().length
				* Tester.getFaculty()[0].getKafedras()[0].getTeachers().length];
		Faculty[] toColect = Tester.getFaculty().clone();

		int number = 0;

		for (int j = 0; Tester.getFaculty()[faculty].getKafedras().length > j; j++) {
			for (int t = 0; t < Tester.getFaculty()[faculty].getKafedras()[j].getTeachers().length; t++) {
				allTeachers[number++] = toColect[faculty].getKafedras()[j].getTeachers()[t];
			}
		}

		return allTeachers;
	}

	/**
	 * 
	 * @param kafedra
	 *            kafedra to collect
	 * @return array with all teachers in kafedra
	 */
	private static Teacher[] collectAllTeachersKafedra(int kafedra) {
		Teacher[] allTeachers = new Teacher[Tester.getFaculty()[0].getKafedras()[0].getTeachers().length];
		Kafedra[] toColect = Tester.getFaculty()[Tester.cuttentFaculty].getKafedras().clone();

		int number = 0;

		for (int i = 0; Tester.getFaculty()[Tester.cuttentFaculty].getKafedras()[kafedra]
				.getTeachers().length > i; i++) {

			allTeachers[number++] = toColect[kafedra].getTeachers()[i];

		}

		return allTeachers;

	}

	/**
	 * 
	 * @param kafedra
	 *            kafedra to collect
	 * @return array with all students in kafedra
	 */
	private static Student[] collectAllStudentsKafedra(int kafedra) {

		Student[] allStudents = new Student[Tester.getFaculty()[0].getKafedras()[0].getStudents().length];

		allStudents = Tester.getFaculty()[Tester.cuttentFaculty].getKafedras()[Faculty.currentKafedra].getStudents()
				.clone();

		return allStudents;

	}

	/**
	 * outputs students with the last name, first name or по батькові that matches
	 * the input string
	 */
	public static void searchStudentsPIB() {
		String search = "";
		try {
			search = DataInput.getString("Введіть ім'я, прізвище або по батькові студента: ");
		} catch (Exception e) {
			System.out.println("Помилка");
		}

		Student[] toSearch = collectAllStudents();
		System.out.println("Результати пошуку: \n");
		for (int i = 0; i < toSearch.length; i++) {
			if (search.equals(toSearch[i].getFirstName()) || search.equals(toSearch[i].getLastName())
					|| search.equals(toSearch[i].getPoBatkovi()))
				System.out.println(toSearch[i].toString());
		}
		System.out.println();
	}

	/**
	 * outputs students with the course that matches the input int
	 */
	public static void searchStudentsCourse() {
		int searchCourse = -1;

		try {
			searchCourse = DataInput.getInt("Введіть курс студента: ");
		} catch (Exception e) {
			System.out.println("Помилка");
		}

		if (searchCourse < 1 || searchCourse > 4)
			System.out.println("Помилка");
		else {
			Student[] toSearch = collectAllStudents();
			System.out.println("Результати пошуку: \n");
			for (int i = 0; i < toSearch.length; i++) {
				if (searchCourse == toSearch[i].getCourse())
					System.out.println(toSearch[i].toString());
			}
			System.out.println();
		}
	}

	/**
	 * outputs students with the group that matches the input int
	 */
	public static void searchStudentsGroupe() {
		int searchGroup = -1;

		try {
			searchGroup = DataInput.getInt("Введіть групу студента: ");
		} catch (Exception e) {
			System.out.println("Помилка");
		}
		if (searchGroup < 1 || searchGroup > 6)
			System.out.println("Помилка");
		else {
			Student[] toSearch = collectAllStudents();
			System.out.println("Результати пошуку: \n");
			for (int i = 0; i < toSearch.length; i++) {
				if (searchGroup == toSearch[i].getGroup())
					System.out.println(toSearch[i].toString());
			}
			System.out.println();
		}
	}

	/**
	 * outputs teachers of the current faculty in alphabetic order
	 * 
	 * @param currentFaculty
	 *            faculty to take teachers from
	 */
	public static void TeachersAlphabeticOrder(int currentFaculty) {

		Teacher[] toSortAlphabet = collectAllTeachersFaculty(currentFaculty);

		boolean uncomplete = true;

		while (uncomplete) {
			uncomplete = false;
			for (int i = 0; i < toSortAlphabet.length - 1; i++) {

				for (int j = 0; j < Math.min(toSortAlphabet[i].getLastName().length(), // довжина
						toSortAlphabet[i + 1].getLastName().length()); j++) {

					if (toSortAlphabet[i].getLastName().charAt(j) > toSortAlphabet[i].getLastName().charAt(j)) {

						Teacher temp = toSortAlphabet[i];
						toSortAlphabet[i] = toSortAlphabet[i + 1];
						toSortAlphabet[i + 1] = temp;
						uncomplete = true;
					}
				}
			}
		}
		for (int i = 0; i < toSortAlphabet.length; i++)
			System.out.println(toSortAlphabet[i].toString());

	}

	/**
	 * 
	 * @param currentFaculty
	 *            faculty to work with
	 * @return array with all the students of the all kafedras in the current
	 *         faculty
	 */
	private static Student[] collectAllStudentsFaculty(int currentFaculty) {
		Student[] allStudents = new Student[Tester.getFaculty()[0].getKafedras().length
				* Tester.getFaculty()[0].getKafedras()[0].getStudents().length];
		Faculty[] toColect = Tester.getFaculty().clone();

		int number = 0;

		for (int j = 0; Tester.getFaculty()[currentFaculty].getKafedras().length > j; j++) {
			for (int t = 0; t < Tester.getFaculty()[currentFaculty].getKafedras()[j].getStudents().length; t++) {
				allStudents[number++] = toColect[currentFaculty].getKafedras()[j].getStudents()[t];
			}
		}

		return allStudents;
	}

	/**
	 * 
	 * @param currentFaculty
	 *            faculty to work with
	 * prints array with all the students of the all kafedras in the current
	 *         faculty in alphabetic order
	 */

	public static void studentsAlphabeticOrder(int currentFaculty) {
		Student[] toSortAlphabet = collectAllStudentsFaculty(currentFaculty);

		ComparatorStudentsAlphabet compStudAlph = new ComparatorStudentsAlphabet();
		Arrays.sort(toSortAlphabet, compStudAlph);

		for (int i = 0; i < toSortAlphabet.length; i++)
			System.out.println(toSortAlphabet[i].toString());
	}

	/**
	 * 
	 * @param currentKafedra
	 *            kafedra to work with
	 * prints all the teachers of the all kafedras in the current
	 *         faculty in alphabetic order
	 */
	public static void teachersKafedraAlphabeticOrder(int currentKafedra) {
		Teacher[] toSortAlphabet = collectAllTeachersKafedra(currentKafedra);

		ComparatorTeachersAlphabet compTeachAlph = new ComparatorTeachersAlphabet();
		Arrays.sort(toSortAlphabet, compTeachAlph);

		for (int i = 0; i < toSortAlphabet.length; i++)
			System.out.println(toSortAlphabet[i].toString());

	}

	/**
	 * 
	 * @param currentKafedra
	 *            kafedra to work with
	 * prints all the students of the current kafedras in the current
	 *         faculty in alphabetic order
	 */
	public static void studentsKafedraAlphabeticOrder(int currentKafedra) {
		Student[] toSortAlphabet = collectAllStudentsKafedra(currentKafedra);

		ComparatorStudentsAlphabet compStudAlph = new ComparatorStudentsAlphabet();
		Arrays.sort(toSortAlphabet, compStudAlph);

		for (int i = 0; i < toSortAlphabet.length; i++)
			System.out.println(toSortAlphabet[i].toString());

	}

	/**
	 * 
	 * @param currentFaculty
	 *            faculty to work with
	 * prints all the students of the all kafedras in the current
	 *         faculty in course order
	 */
	public static void studentsKafedraCourseOrder(int currentKafedra) {
		Student[] toSortAlphabet = collectAllStudentsKafedra(currentKafedra);

		ComparatorStudentsCourse compStudCourse = new ComparatorStudentsCourse();
		Arrays.sort(toSortAlphabet, compStudCourse);

		for (int i = 0; i < toSortAlphabet.length; i++)
			System.out.println(toSortAlphabet[i].toString());

	}

	/**
	 * 
	 * @param currentFaculty
	 *            faculty to work with
	 *            @param course course that you want to print students of
	 * prints all the students of the all kafedras in the current
	 *         faculty of the input course
	 */
	public static void kafedraStudentsCourse(int currentKafedra, int course) {

		Student[] kafedraStudents = collectAllStudentsKafedra(currentKafedra);

		for (int i = 0; i < kafedraStudents.length; i++)
			if (kafedraStudents[i].getCourse() == course)
				System.out.println(kafedraStudents[i].toString());

	}

	/**
	 * @return all the students of all faculties of all kafedras
	 */
	private static Student[] collectAllStudents() {
		Student[] allStudents = new Student[Tester.getFaculty().length * Tester.getFaculty()[0].getKafedras().length
				* Tester.getFaculty()[0].getKafedras()[0].getStudents().length];
		Faculty[] toColect = Tester.getFaculty().clone();

		int number = 0;
		for (int i = 0; Tester.getFaculty().length > i; i++) {
			for (int j = 0; Tester.getFaculty()[i].getKafedras().length > j; j++) {
				for (int t = 0; t < Tester.getFaculty()[i].getKafedras()[j].getStudents().length; t++) {
					allStudents[number++] = toColect[i].getKafedras()[j].getStudents()[t];
				}
			}
		}
		return allStudents;
	}

	/**
	 * 
	 * @param currentFaculty
	 *            faculty to work with
	 *            @param course course that you want to print students of
	 * prints all the students of the all kafedras in the current
	 *         faculty of the input course in alphabetic order
	 */
	public static void studentsKafedraExectCourseAlphabeticOrder(int currentKafedra, int course) {
		Student[] kafedraStudents = collectAllStudentsKafedra(currentKafedra);

		ComparatorStudentsAlphabet compStudAlph = new ComparatorStudentsAlphabet();
		Arrays.sort(kafedraStudents, compStudAlph);

		for (int i = 0; i < kafedraStudents.length; i++)
			if (kafedraStudents[i].getCourse() == course)
				System.out.println(kafedraStudents[i].toString());

	}

}
