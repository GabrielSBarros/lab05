package classes;

import java.util.Comparator;

/**
 * Comparator de cenario baseado na descricao do cenario em ordem alfabetica, em caso de descricoes iguais a ordem e definida pelo maior numero de apostas
 * @author Gabriel Barros
 *
 */
public class CenarioComparatorNome implements Comparator<Cenario> {

	@Override
	public int compare(Cenario o1, Cenario o2) {
		int result = o1.getDescricao().compareTo(o2.getDescricao());
		if(result == 0) {
			if(o1.getNumApostas() < o2.getNumApostas()) {
				result = -1;
			}else {
				result = 1;
			}
		}
		return result;
	}

}
