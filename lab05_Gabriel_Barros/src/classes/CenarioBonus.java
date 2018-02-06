package classes;

import java.util.ArrayList;

public class CenarioBonus extends Cenario{
	private int bonus;
	
	public CenarioBonus(int num, String descricao, int bonus) {
		super(num, descricao);
		this.bonus = bonus;
	}
	
	private void finalizarApostas(double taxa, boolean estado, int bonus) {
		int erradas = 0;
		ArrayList<Aposta> apostas = getApostas();
		for (int i = 0; i < apostas.size(); i++) {
			if(!apostas.get(i).getPrevisao() == estado) {
				erradas += apostas.get(i).getValor();
			}
		}
		setCaixa((int)(erradas * taxa));
		setRateio(erradas - getCaixa() + this.bonus);
	}

	@Override
	public void finalizarCenario(boolean estado, double taxa) {
		int i = 2;
		if(estado) {
			i = 1;
		}
		super.setEstado(i);
		this.finalizarApostas(taxa, estado, this.bonus);
	}
	
	@Override
	public String toString() {
		return super.toString() + " - " + this.bonus;
	}
	
}
