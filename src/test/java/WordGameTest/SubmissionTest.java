package WordGameTest;

import WordGame.ValidWords;
import WordGame.ValidWordsImpl;
import WordGame.WordGame;
import WordGame.WordGameImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (C) 2015 Codesse. All rights reserved.
 * ••••••••••••••••••••••••••••••••••••••••••••••••
 */
public class SubmissionTest {

	static ValidWords validWords;
	WordGame service;

	@BeforeClass
	public static void oneTimeSetUp() {
		validWords = new ValidWordsImpl();
	}

	@Before
	public void setUp() throws Exception {
		service = new WordGameImpl("areallylongword", validWords);
	}

	@Test
	public void testSubmission() throws Exception {
		assertEquals(3, service.submitWord("player1", "all"));
		assertEquals(4, service.submitWord("player2", "word"));
		assertEquals(0, service.submitWord("player3", "tale"));
		assertEquals(0, service.submitWord("player4", "glly"));
		assertEquals(6, service.submitWord("player5", "woolly"));
		assertEquals(0, service.submitWord("player6", "adder"));
		assertEquals(3, service.submitWord("player1", "all"));
	}

	@Test
	public void testHighestScoredPlayername() throws Exception {
		service.submitWord("player2", "word");
		service.submitWord("player3", "all");
		service.submitWord("player1", "woolly");
		assertEquals("player1",service.getPlayerNameAtPosition(0));
	}

	@Test
	public void testHighestScoredPlayerWord() throws Exception {
		service.submitWord("player2", "word");
		service.submitWord("player3", "all");
		service.submitWord("player1", "woolly");
		assertEquals("woolly",service.getWordEntryAtPosition(0));
	}

	@Test
	public void testHighestScoredPlayerScore() throws Exception {
		service.submitWord("player2", "word");
		service.submitWord("player3", "all");
		service.submitWord("player1", "woolly");
		assertEquals(java.util.Optional.ofNullable(6),java.util.Optional.ofNullable(service.getScoreAtPosition(0)));
	}

	@Test
	public void testSameExactWordReturnFalse() throws Exception {
		WordGameImpl service1 = new WordGameImpl("areallylongword", validWords);
		service1.submitWord("player2", "word");
		service1.submitWord("player3", "all");
		service1.submitWord("player1", "woolly");
		service1.submitWord("player4", "woolly");
		//System.out.println(WordGameImpl.leaderList.size());
		assertEquals(3, service1.getLeaderList().size());
	}

	@Test
	public void testIfAddedMoreWordsWhenBoardIsFull() throws Exception {
		WordGameImpl service1 = new WordGameImpl("areallylongword", validWords);
		service1.submitWord("player1", "word");
		service1.submitWord("player2", "all");
		service1.submitWord("player3", "woolly");
		service1.submitWord("player4", "lower");
		service1.submitWord("player5", "loyal");
		service1.submitWord("player6", "orange");
		service1.submitWord("player7", "lawn");
		service1.submitWord("player8", "woolly");
		service1.submitWord("player9", "grey");
		service1.submitWord("player10", "raw");
		service1.submitWord("player11", "laryngeal");
		service1.submitWord("player12", "legally");


		//System.out.println(WordGameImpl.leaderList.size());
		assertEquals(10, service1.getLeaderList().size());
		assertEquals("player11",service1.getPlayerNameAtPosition(0));
		assertEquals("laryngeal",service1.getWordEntryAtPosition(0));
		assertEquals(java.util.Optional.ofNullable(9),java.util.Optional.ofNullable(service1.getScoreAtPosition(0)));

	}
}
