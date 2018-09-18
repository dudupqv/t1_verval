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
	boolean testScoreValue(int value) {
		if(value >= 0 && value <= Integer.MAX_VALUE){
			return true;
		}
		return false;
	}

	@Test
	void testGetScore() {
		assertTrue("Score nao está entre 0 e Integer(MAX_VALUE)", testScoreValue((ge1.getScore())));
		assertFalse("Teste limite inferior -1",-1 == ge1.getScore());
		assertTrue("Teste limite inferior 0, 1",ge1.getScore() <= 0);		
		assertFalse("Teste limite superior Interger.MAX_VALUE", Integer.MAX_VALUE+1 == ge1.getScore());
		assertTrue("Teste limite superior Interger.MAX_VALUE -1, Interger.MAX_VALUE",Integer.MAX_VALUE >= ge1.getScore());
	}

	@Test
	void testToString() {
		assertEquals(outTest, ge1.toString());
	}

}
