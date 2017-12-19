package controllers;

import java.util.ArrayList;

import classes.*;
/**
 * Controller da classe Cenario
 * @author Gabriel Barros
 *
 */
public class CenarioController {
	private Caixa caixa;
	private ArrayList<Cenario> cenarios;
	
	/**
	 * Constroi o controller, inicializando o array de cenários
	 * @param caixa
	 * @param taxa
	 */
	public CenarioController(int caixa, double taxa) {
		this.cenarios = new ArrayList<>();
		this.caixa = new Caixa(caixa, taxa);
	}
	
	/**
	 * Retorna a quantidade de dinheiro atual no caixa
	 * @return
	 */
	public int getValorCaixa() {
		return caixa.getDinheiro();
	}
	
	/**
	 * Cadastra um cenário a partir de sua descrição
	 * @param descricao
	 */
	public void cadastrarCenario(String descricao) {
		cenarios.add(new Cenario((cenarios.size()+1), descricao));
	}
	
	/**
	 * Retorna uma representação String de um cenário específico
	 * @param cenario
	 * @return
	 */
	public String exibirCenario(int cenario) {
		verificaCenario(cenario, "Erro na consulta de cenario: ");
		return cenarios.get(cenario - 1).toString();
	}
	
	/**
	 * Retorna uma representação String de todos os cenários cadastrados
	 * @return
	 */
	public String exibirCenarios() {
		String result = "";
		for (int i = 0; i < cenarios.size(); i++) {
			result += cenarios.get(i).toString();
			if(i != cenarios.size() - 1) {
				result += System.lineSeparator();
			}
		}
		return result;
	}
	
	/**
	 * Cadastra uma aposta num cenário específico. Recebe o nome do apostador, o valor da aposta e a previsão da mesma.
	 * @param cenario
	 * @param apostador
	 * @param valor
	 * @param previsao
	 */
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		verificaCenario(cenario, "Erro no cadastro de aposta: ");
		if(previsao == null || previsao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
		if(!(previsao.equals("VAI ACONTECER") || previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		}
		
		cenarios.get(cenario - 1).cadastrarAposta(apostador, valor, previsao.equals("VAI ACONTECER"));
	}
	
	/**
	 * Retorna o número total de apostas cadastradas num cenário específico
	 * @param cenario
	 * @return
	 */
	public int totalDeApostas(int cenario) {
		verificaCenario(cenario, "Erro na consulta do total de apostas: ");
		return cenarios.get(cenario - 1).totalDeApostas();
	}
	
	/**
	 * Retorna o valor total apostado num cenário específico
	 * @param cenario
	 * @return
	 */
	public int valorTotalDeApostas(int cenario) {
		verificaCenario(cenario, "Erro na consulta do valor total de apostas: ");
		return cenarios.get(cenario - 1).valorTotalDeApostas();
	}
	
	/**
	 * Exibe todas as apostas de um cenário específico
	 * @param cenario
	 * @return
	 */
	public String exibeApostas(int cenario) {
		return cenarios.get(cenario - 1).exibeApostas();
	}
	
	/**
	 * Fecha um cenário, informando se o mesmo ocorreu ou não
	 * @param cenario
	 * @param ocorreu
	 */
	public void fecharCenario(int cenario, boolean ocorreu) {
		verificaCenario(cenario, "Erro ao fechar aposta: ");
		if(cenarios.get(cenario - 1).getEstado() == 1 || cenarios.get(cenario - 1).getEstado() == 2 ) {
			throw new IllegalArgumentException("Erro ao fechar aposta: Cenario ja esta fechado");
		}
		cenarios.get(cenario - 1).finalizarCenario(ocorreu, caixa.getTaxa());
		caixa.adicionarDinheiro(cenarios.get(cenario - 1).getCaixa());
	}
	
	/**
	 * Retorna a quantidade de dinheiro que deve ser destinada ao caixa do sistema
	 * @param cenario
	 * @return
	 */
	public int getCaixaCenario(int cenario) {
		verificaCenario(cenario, "Erro na consulta do caixa do cenario: ");
		if(cenarios.get(cenario - 1).getEstado() == 0) {
			throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}
		return cenarios.get(cenario - 1).getCaixa();
	}
	
	/**
	 * Retorna a quantidade de dinheiro que deve ser distribuida entre os vencedores da aposta
	 * @param cenario
	 * @return
	 */
	public int getTotalRateioCenario(int cenario) {
		verificaCenario(cenario, "Erro na consulta do total de rateio do cenario: ");
		if(cenarios.get(cenario - 1).getEstado() == 0) {
			throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		}
		return cenarios.get(cenario - 1).getRateio();	
	}
	
	private void verificaCenario(int cenario, String erro) {
		if(cenario < 1) {
			throw new IllegalArgumentException(erro + "Cenario invalido");
		}
		if(cenario > cenarios.size()) {
			throw new IllegalArgumentException(erro + "Cenario nao cadastrado");
		}
	}
}
