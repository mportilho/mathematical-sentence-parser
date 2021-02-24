package io.github.mportilho.mathsentenceparser.syntaxtree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestLogicOperationCaches {

	private static CacheCheckVisitor cacheVisitor;

	@BeforeAll
	public static void beforeClass() {
		cacheVisitor = new CacheCheckVisitor();
	}

	@Test
	public void testAdditionOperation() {
		throw new IllegalArgumentException();
	}

}
