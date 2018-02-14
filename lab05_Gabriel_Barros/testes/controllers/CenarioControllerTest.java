package controllers;

import static org.junit.Assert.*;
import org.junit.Test;


public class CenarioControllerTest {
	private CenarioController controller;
	
	@Test
	public void testCadastrarCenario() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		assertEquals("1 - cenario - Nao finalizado", controller.exibirCenario(1));
		controller.cadastrarCenario("cenario");
		assertEquals("2 - cenario - Nao finalizado", controller.exibirCenario(2));
	}
	
	@Test
	public void testCadastrarCenarioBonus() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario", 100);
		assertEquals("1 - cenario - Nao finalizado - R$ 1,00", controller.exibirCenario(1));
		controller.cadastrarCenario("cenario", 200);
		assertEquals("2 - cenario - Nao finalizado - R$ 2,00", controller.exibirCenario(2));
	}
	
	@Test
	public void testExibirCenarios() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarCenario("cenario");
		String expected = "1 - cenario - Nao finalizado" + System.lineSeparator() 
						+ "2 - cenario - Nao finalizado";
		
		assertEquals(expected, controller.exibirCenarios());
	}
	
	@Test
	public void testCadastrarAposta() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarAposta(1, "apostador", 2, "VAI ACONTECER");
		assertEquals("1 - apostador - 2 - VAI ACONTECER", controller.exibeApostas(1));
	}
	
	@Test
	public void testCadastrarApostaAsseguradaValor() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarApostaSeguraValor(1, "apostador", 2, "VAI ACONTECER", 100, 50);
		assertEquals("1 - apostador - 2 - VAI ACONTECER - ASSEGURADA(valor) - R$ 1,00", controller.exibeApostas(1));
		assertEquals(50050, controller.getValorCaixa());
	}
	
	@Test
	public void testCadastrarApostaAsseguradaTaxa() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarApostaSeguraTaxa(1, "apostador", 2, "VAI ACONTECER", 0.3, 50);
		assertEquals("1 - apostador - 2 - VAI ACONTECER - ASSEGURADA(taxa) - 30.0%", controller.exibeApostas(1));
		assertEquals(50050, controller.getValorCaixa());
	}
	
	@Test
	public void testAlterarSeguroValor() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarApostaSeguraTaxa(1, "apostador", 2, "VAI ACONTECER", 0.3, 50);
		assertEquals("1 - apostador - 2 - VAI ACONTECER - ASSEGURADA(taxa) - 30.0%", controller.exibeApostas(1));
		controller.alterarSeguroValor(1, 1, 100);
		assertEquals("1 - apostador - 2 - VAI ACONTECER - ASSEGURADA(valor) - R$ 1,00", controller.exibeApostas(1));
	}
	
	@Test
	public void testAlterarSeguroTaxa() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarApostaSeguraValor(1, "apostador", 2, "VAI ACONTECER", 100, 50);
		assertEquals("1 - apostador - 2 - VAI ACONTECER - ASSEGURADA(valor) - R$ 1,00", controller.exibeApostas(1));
		controller.alterarSeguroTaxa(1, 1, 0.3);
		assertEquals("1 - apostador - 2 - VAI ACONTECER - ASSEGURADA(taxa) - 30.0%", controller.exibeApostas(1));
	}
		
	@Test
	public void testCadastrarApostaApostadorVazio() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		try {
		controller.cadastrarAposta(1, "", 2, "VAI ACONTECER");
		fail();
		}catch(IllegalArgumentException e) {}
	}
	
	@Test
	public void testCadastrarApostaApostadorNulo() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		try {
		controller.cadastrarAposta(1, null, 2, "VAI ACONTECER");
		fail();
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testCadastrarApostaPrevisaoVazia() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		try {
			controller.cadastrarAposta(1, "apostador", 2, "");
		fail();
		}catch(IllegalArgumentException e) {}
	}
	
	@Test
	public void testCadastrarApostaPrevisaoNula() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		try {
			controller.cadastrarAposta(1, "apostador", 2, null);
		fail();
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testTotalDeApostas() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarAposta(1, "apostador", 2, "VAI ACONTECER");
		assertEquals(1, controller.totalDeApostas(1));
		
		controller.cadastrarAposta(1, "apostador", 2, "VAI ACONTECER");
		assertEquals(2, controller.totalDeApostas(1));
	}
	
	@Test
	public void testTotalDeApostasCenarioInvalido() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarAposta(1, "apostador", 2, "VAI ACONTECER");
		try {
			assertEquals(1, controller.totalDeApostas(-1));
			fail();
		}catch(IllegalArgumentException e) {			
		}

	}
	
	@Test
	public void testTotalDeApostasCenarioNCadastrado() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarAposta(1, "apostador", 2, "VAI ACONTECER");
		try {
			assertEquals(1, controller.totalDeApostas(2));
			fail();
		}catch(IllegalArgumentException e) {			
		}

	}
	
	@Test
	public void testValorTotalDeApostas() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarAposta(1, "apostador", 2, "VAI ACONTECER");
		assertEquals(2, controller.valorTotalDeApostas(1));
		
		controller.cadastrarAposta(1, "apostador", 2, "VAI ACONTECER");
		assertEquals(4, controller.valorTotalDeApostas(1));
	}
	
	@Test
	public void testValorTotalDeApostasCenarioInvalido() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarAposta(1, "apostador", 2, "VAI ACONTECER");
		try {
			assertEquals(2, controller.valorTotalDeApostas(-1));
			fail();
		}catch(IllegalArgumentException e) {			
		}

	}
	
	@Test
	public void testValorTotalDeApostasCenarioNCadastrado() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarAposta(1, "apostador", 2, "VAI ACONTECER");
		try {
			assertEquals(2, controller.valorTotalDeApostas(2));
			fail();
		}catch(IllegalArgumentException e) {			
		}

	}
	
	@Test
	public void testFecharCenarioOcorreu() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarAposta(1, "apostador", 10, "VAI ACONTECER");
		controller.cadastrarAposta(1, "apostador", 20, "N VAI ACONTECER");
		controller.fecharCenario(1, true);
		assertEquals(2, controller.getCaixaCenario(1));
		assertEquals(18, controller.getTotalRateioCenario(1));
		assertEquals("1 - cenario - Finalizado (ocorreu)", controller.exibirCenario(1));
	}
	
	@Test
	public void testFecharCenarioNOcorreu() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		controller.cadastrarAposta(1, "apostador", 10, "VAI ACONTECER");
		controller.cadastrarAposta(1, "apostador", 20, "N VAI ACONTECER");
		controller.fecharCenario(1, false);
		assertEquals(1, controller.getCaixaCenario(1));
		assertEquals(9, controller.getTotalRateioCenario(1));
		assertEquals("1 - cenario - Finalizado (n ocorreu)", controller.exibirCenario(1));
	}
	
	@Test
	public void testGetCaixaCenarioInvalido() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		try {
			controller.getCaixaCenario(-1);
			fail();
		}catch(IllegalArgumentException e) {}
	}
	
	@Test
	public void testGetCaixaCenarioNCadastrado() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		try {
			controller.getCaixaCenario(2);
			fail();
		}catch(IllegalArgumentException e) {}
	}
	
	@Test
	public void testGetTotalRateioCenarioInvalido() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		try {
			controller.getTotalRateioCenario(-1);
			fail();
		}catch(IllegalArgumentException e) {}
	}
	
	@Test
	public void testGetTotalRateioCenarioNCadastrado() {
		controller = new CenarioController(50000, 0.1);
		controller.cadastrarCenario("cenario");
		try {
			controller.getTotalRateioCenario(2);
			fail();
		}catch(IllegalArgumentException e) {}
	}

}
