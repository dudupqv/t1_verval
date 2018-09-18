import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameEntryTest {
	
	String name = "Eduardo";
	int score = 0;
	String outTest = "("+name+", "+score+")";
	GameEntry ge1 = new GameEntry(name, score);

	@Test
	void testGetName() {
		assertFalse("String é nula", ge1.getName().equals(null));
		assertEquals(name, ge1.getName());
	}
	
	@Test
	void testGetScore() {
		//Limite Inferior
		
		//Teste limite inferior -1
		assertFalse(-1 == ge1.getScore());
		
		//Teste limite inferior 0, 1
		assertTrue(ge1.getScore() >= 0);		
		
		
		//Limite Superior
		
		//Teste limite superior Interger.MAX_VALUE
		assertFalse(Integer.MAX_VALUE+1 == ge1.getScore());
		
		//Teste limite superior Interger.MAX_VALUE -1, Interger.MAX_VALUE
		assertTrue(Integer.MAX_VALUE >= ge1.getScore());
	}

	@Test
	void testToString() {
		assertEquals(outTest, ge1.toString());
	}

}
