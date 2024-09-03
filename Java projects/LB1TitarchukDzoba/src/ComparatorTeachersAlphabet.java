/**
 * @authors Veronika Titarchuk && Anastasia Dzoba
 * @file ComparatorStudentsAlphabet.java
 * sorts Teachers in alphabetic order
 * priarities: last name - first name - по батькові
 */
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class ComparatorTeachersAlphabet implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		Teacher tch1 = (Teacher) o1;
		Teacher st2 = (Teacher) o2;
		Locale locale = new Locale("uk","UA");
		Collator collator = Collator.getInstance(locale);
		int res = collator.compare(tch1.getLastName(),st2.getLastName());
		if(res==0)
			res = collator.compare(tch1.getFisrtName(),st2.getFisrtName());
		if(res==0)
			res = collator.compare(tch1.getPoBatkovi(),st2.getPoBatkovi());
				return res;
	}
}
