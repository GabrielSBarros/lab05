package classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class CenarioBonusTest {
	private CenarioBonus cenarioBonus;
	
	@Test
	public void testToString() {
		cenarioBonus = new CenarioBonus(1, "cenario", 100);
		assertEquals("1 - cenario - Nao finalizado - R$ 1,00", cenarioBonus.toString());
	}
	
	@Test
	public void testBonusMenorQueUm() {
		try {
			cenarioBonus = new CenarioBonus(1, "cenario", 0);
			fail();
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void finalizarCenarioAconteceu() {
		SeguroValor seguroValor = new SeguroValor(100);
		SeguroTaxa seguroTaxa = new SeguroTaxa(0.3);
		cenarioBonus = new CenarioBonus(1, "cenario", 100);
		cenarioBonus.cadastrarAposta("apostador", 10, true);
		cenarioBonus.cadastrarAposta("apostador2", 10, false);
		cenarioBonus.cadastrarAposta("apostador3", 10, true, seguroValor);
		cenarioBonus.cadastrarAposta("apostador4", 10, true, seguroTaxa);
		cenarioBonus.finalizarCenario(true, 0.1);
		assertEquals(1, cenarioBonus.getEstado());
		assertEquals(109, cenarioBonus.getRateio());
		assertEquals(1, cenarioBonus.getCaixa());
	}
	
	@Test
	public void finalizarCenarioNAconteceu() {
		SeguroValor seguroValor = new SeguroValor(100);
		SeguroTaxa seguroTaxa = new SeguroTaxa(0.3);
		cenarioBonus = new CenarioBonus(1, "cenario", 100);
		cenarioBonus.cadastrarAposta("apostador", 10, true);
		cenarioBonus.cadastrarAposta("apostador2", 10, false);
		cenarioBonus.cadastrarAposta("apostador3", 10, true, seguroValor);
		cenarioBonus.cadastrarAposta("apostador4", 10, true, seguroTaxa);
		cenarioBonus.finalizarCenario(false, 0.1);
		assertEquals(2, cenarioBonus.getEstado());
		assertEquals(127, cenarioBonus.getRateio());
		assertEquals(3, cenarioBonus.getCaixa());
	}

}
