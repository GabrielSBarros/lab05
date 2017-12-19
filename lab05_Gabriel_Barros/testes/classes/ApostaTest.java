package classes;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.Aposta;

class ApostaTest {
	private Aposta aposta;
	
	@Test
	public void testApostadorVazio() {
		try{
			aposta = new Aposta("", 1, true);
			fail();
		}catch(IllegalArgumentException e) {	
		}
	}
	
	@Test
	public void testApostadorNulo() {
		try{
			aposta = new Aposta(null, 1, true);
			fail();
		}catch(IllegalArgumentException e) {	
		}
	}
	
	@Test
	public void testValorMenorQueUm() {
		try{
			aposta = new Aposta("joao", 0, true);
			fail();
		}catch(IllegalArgumentException e) {	
		}
	}
	
	@Test
	public void testToString() {
		aposta = new Aposta("joao", 12, true);
		assertEquals("joao - 12 - VAI ACONTECER", aposta.toString());
	}

}
