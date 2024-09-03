/**
 * @authors Veronika Titarchuk && Anastasia Dzoba
 * @file Faculty
 * class for work with faculties
 */
import java.util.Arrays;

public class Faculty {

	private String name;

	private Kafedra[] kafedras = new Kafedra[4];
	public static int currentKafedra;

	public void edit() {

		boolean go = true;
		while (go) { // menu
			System.out.println("Створити кафедру введіть 1");
			System.out.println("Переіменувати факультету введіть 2");
			System.out.println("Редагувати кафедру введіть 3");
			System.out.println("Видалити кафедрут введіть 4");
			System.out.println("Переглянути кафедри введіть 5");
			System.out.println("Вивести всіх викладачів факультета впорядкованих за алфавітом введіть 6");
			System.out.println("Вивести всіх студентів факультета впорядкованих за алфавітом введіть 7");
			System.out.println("Для виходу введіть 0");
			int menu = DataInput.getInt();

			switch (menu) {
			case 0:
				go = false;
				break;
			case 1:
				createKafedra();
				break;
			case 2:
				changeKafedraName();
				break;
			case 3:
				editKafedra();
				break;
			case 4:
				deleteKafedra();
				break;
			case 5:
				System.out.println(showKafedras());
				break;
			case 6:
				Search.TeachersAlphabeticOrder(Tester.cuttentFaculty);// 3.3. Вивести всіх викладачів факультета
																		// впорядкованих за алфавітом.
				break;
			case 7:
				Search.studentsAlphabeticOrder(Tester.cuttentFaculty); // 3.3. Вивести всіх студентів факультета
																		// впорядкованих за алфавітом.
				break;

			}
		}
	}

	/**
	 * changes name of the kafedra 
	 * number of kafedra is input from the keyboard
	 */
	private void changeKafedraName() {
		while (true) {
			int kafedraNumber = DataInput.getInt("Введіть номер кафедри:\nНазад - введіть 0");
			if (kafedraNumber == 0)
				break;
			else if (kafedraNumber > 0 && kafedraNumber < kafedras.length + 1) {
				String newName = "";
				try {
					newName = DataInput.getString("Введіть нову назву: ");
				} catch (Exception e) {
					System.out.println("Помилка");
				}
				kafedras[kafedraNumber].setName(newName);
				System.out.println("Кафедру успішно переіменовано!");
			} else
				System.out.println("Помилка, кафедри з таким номером немає");
		}
	}

	/**
	 * Creates faculties in the array
	 */
	void init() {
		for (int i = 0; i < kafedras.length; i++)
			kafedras[i] = new Kafedra();
		kafedras[0].setName("Математика");
		kafedras[1].setName("Мультимедія");
		kafedras[2].setName("sfgdfhh");
	}

	/**
	 * Deletes kafedra with input number;
	 */
	private void deleteKafedra() {
		while (true) {
			int kafedraNumber = DataInput.getInt("Введіть номер кафедри:\nНазад - введіть 0");
			if (kafedraNumber == 0)
				break;
			else if (kafedraNumber > 0 && kafedraNumber < kafedras.length + 1) {
				kafedras[kafedraNumber - 1] = new Kafedra();
				System.out.println("Кафедру успішно видалено!");
			} else
				System.out.println("Помилка, кафедри з таким номером немає");
		}
	}

	/**
	 * goes to the kafedra menu
	 * number of kafedra is input from the keyboard
	 */
	private void editKafedra() {
		while (true) {
			int kafedraNumber = DataInput.getInt("Введіть номер кафедри для редагування\nНазад 0");
			if (kafedraNumber == 0)
				break;
			else if (kafedraNumber > 0 && kafedraNumber < kafedras.length + 1) {
				currentKafedra = kafedraNumber;
				kafedras[kafedraNumber - 1].edit();

			} else
				System.out.println("Помилка, кафедри з таким номером немає");
		}
	}

	/**
	 * create kafedra on the input cell in an array
	 */
	private void createKafedra() {
		while (true) {
			int kafedraNumber = DataInput.getInt("Введіть номер нової кафедри (поточна видаляється)\nНазад 0");
			if (kafedraNumber == 0)
				break;
			else if (kafedraNumber > 0 && kafedraNumber < kafedras.length + 1) {

				kafedras[kafedraNumber - 1] = new Kafedra();

				String kafedraName = "";

				while (true) { // Для того, щоб не можна було зробити кафедру із пустим ім'ям
					try {
						kafedraName = DataInput.getString("Введіть назву кафедри");
					} catch (Exception e) {
						System.out.println("Помилка");
					}
					if (kafedraName.equals(""))
						System.out.println("Помилка");
					else
						break;
				}

				kafedras[kafedraNumber - 1].setName(kafedraName);

			} else
				System.out.println("Помилка, кафедри з таким номером немає");
		}
	}

	/**
	 * @return String with faculties` names
	 */
	private String showKafedras() {
		String res = "";
		for (int i = 0; i < kafedras.length; i++) {
			res += (i + 1) + ". " + kafedras[i].getName() + "\n";
		}
		return res;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Kafedra[] getKafedras() {
		return kafedras;
	}

	public void setKafedras(Kafedra[] kafedras) {
		this.kafedras = kafedras;
	}

	@Override
	public String toString() {
		return "Faculty " + name + "Kafedras:\n" + Arrays.toString(kafedras);
	}

}
