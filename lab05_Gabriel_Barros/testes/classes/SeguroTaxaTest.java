package classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class SeguroTaxaTest {
	private SeguroTaxa seguro;
	
	@Test
	public void testToString() {
		seguro = new SeguroTaxa(0.3);
		assertEquals("30.0%", seguro.toString());
	}
	
	@Test
	public void testTaxaMenorOuIgualAZero() {
		try {
		seguro = new SeguroTaxa(0);
		fail();
		}catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void testTaxaMaiorQueUm() {
		try {
		seguro = new SeguroTaxa(1.1);
		fail();
		}catch(IllegalArgumentException e) {
			
		}
	}

}
