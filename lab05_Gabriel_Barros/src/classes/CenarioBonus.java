package classes;

/**
 * Reopresentacao de um cenario de aposta com bonus
 * @author Gabriel Barros
 *
 */
public class CenarioBonus extends Cenario{
	private int bonus;
	
	/**
	 * Constroi um cenario com bonus a partir do numero que o identifica, sua descricao e o bonus a ele associado.
	 * @param num
	 * @param descricao
	 * @param bonus
	 */
	public CenarioBonus(int num, String descricao, int bonus) {
		super(num, descricao);
		if(bonus <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Bonus invalido");
		}
		this.bonus = bonus;
	}


	/**
	 * Fecha um cenario. Recebe um estado informando se o cenario ocorreu ou nao e a taxa que deve ser retirada de cada aposta perdida no sistema.
	 * Define o estado do cenario, a quantidade de dinheiro que deve ser encaminhada ao caixa e a quantidade de dinheiro que deve ser repartida entre os vencedores adicionando o bonus
	 * Retorna quanto dinheiro deve ser retirado do caixa 
	 * @param estado
	 * @param taxa
	 */
	@Override
	public int finalizarCenario(boolean estado, double taxa) {
		int retirarCaixa = 0;
		retirarCaixa = super.finalizarCenario(estado, taxa);
		super.setRateio(super.getRateio() + this.bonus);
		return retirarCaixa;
	}
	
	/**
	 * Retorna uma representacao String de um Cenario com bonus:
	 * NUMERACAO - DESCRICAO - ESTADO - BONUS
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(" - R$ %,.2f", (double)this.bonus / 100);
	}
	
}
