/**
 * @authors Veronika Titarchuk && Anastasia Dzoba
 * @file ComparatorStudentsAlphabet.java
 * sorts Students in course order
 */
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class ComparatorStudentsCourse implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		Student st1 = (Student) o1;
		Student st2 = (Student) o2;
		int res = st1.getCourse() - st2.getCourse();
				return res;
	}
}
