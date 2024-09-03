/**
 * @authors Veronika Titarchuk && Anastasia Dzoba
 * @file ComparatorStudentsAlphabet.java
 * sorts Students in alphabetic order
 * priarities: last name - first name - по батькові
 */
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class ComparatorStudentsAlphabet implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		Student st1 = (Student) o1;
		Student st2 = (Student) o2;
		Locale locale = new Locale("uk","UA");
		Collator collator = Collator.getInstance(locale);
		int res = collator.compare(st1.getLastName(),st2.getLastName());
		if(res==0)
			res = collator.compare(st1.getFirstName(),st2.getFirstName());
		if(res==0)
			res = collator.compare(st1.getPoBatkovi(),st2.getPoBatkovi());
				return res;
	}
}
