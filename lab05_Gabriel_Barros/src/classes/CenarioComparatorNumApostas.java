package classes;

import java.util.Comparator;

/**
 * Comparator de cenario baseado no numero de apostas em ordem decrescente, em caso de numeros iguais de aposta a ordem e defindia pela ordem de cadastro
 * @author Gabriel Barros
 *
 */
public class CenarioComparatorNumApostas implements Comparator<Cenario>{

	@Override
	public int compare(Cenario o1, Cenario o2) {
		if(o1.getNumApostas() > o2.getNumApostas()) {
			return -1;
		}else if(o1.getNumApostas() < o2.getNumApostas()) {
			return 1;
		}else {
			return o1.compareTo(o2);
		}
	}

}
