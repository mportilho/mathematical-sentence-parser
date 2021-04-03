package io.github.mportilho.mathsentenceparser.sentence.text;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.mportilho.mathsentenceparser.MathSentence;

public class TestDateTimeTextalRepresentation {

	@Test
	public void testDateTextualRepresentation() {
		assertThat(new MathSentence("2000-03-05 = 2000-03-05").toString()).isEqualTo("2000-03-05 = 2000-03-05");
		assertThat(new MathSentence("(2000-03-05) = 2000-03-05").toString()).isEqualTo("(2000-03-05) = 2000-03-05");
		assertThat(new MathSentence("2000-03-05 setDays 3 = 2000-03-05").toString()).isEqualTo("2000-03-05 setDays 3 = 2000-03-05");
		assertThat(new MathSentence("2000-03-05 plusDays 3 setYears 2010 = 2010-03-03").toString())
				.isEqualTo("2000-03-05 plusDays 3 setYears 2010 = 2010-03-03");
		assertThat(new MathSentence("(2000-03-05 minusDays 3 setYears 2010) = 2010-03-03").toString())
				.isEqualTo("(2000-03-05 minusDays 3 setYears 2010) = 2010-03-03");
	}

	@Test
	public void testTimeTextualRepresentation() {
		assertThat(new MathSentence("13:14:15 = 13:14:15").toString()).isEqualTo("13:14:15 = 13:14:15");
		assertThat(new MathSentence("(13:14:15) = 13:14:15").toString()).isEqualTo("(13:14:15) = 13:14:15");
		assertThat(new MathSentence("13:14:15 setHours 3 plusMinutes 1 = 13:14:15").toString())
				.isEqualTo("13:14:15 setHours 3 plusMinutes 1 = 13:14:15");
		assertThat(new MathSentence("13:14:15 minusHours 3 setSeconds 20 = 03:14:20").toString())
				.isEqualTo("13:14:15 minusHours 3 setSeconds 20 = 03:14:20");
		assertThat(new MathSentence("(13:14:15 setHours 3 setSeconds 20) = 03:14:20").toString())
				.isEqualTo("(13:14:15 setHours 3 setSeconds 20) = 03:14:20");
	}

	@Test
	public void testDateTimeTextualRepresentation() {
		assertThat(new MathSentence("2000-03-05T13:14:15 = 2000-03-05T13:14:15").toString()).isEqualTo("2000-03-05T13:14:15 = 2000-03-05T13:14:15");
		assertThat(new MathSentence("(2000-03-05T13:14:15) = 2000-03-05T13:14:15").toString())
				.isEqualTo("(2000-03-05T13:14:15) = 2000-03-05T13:14:15");
		assertThat(new MathSentence("2000-03-05T13:14:15 plusDays 3 = 2000-03-05T13:14:15").toString())
				.isEqualTo("2000-03-05T13:14:15 plusDays 3 = 2000-03-05T13:14:15");
		assertThat(new MathSentence("2000-03-05T13:14:15 setDays 3 setYears 2010 plusMinutes 4 = 2010-03-03T13:18:15").toString())
				.isEqualTo("2000-03-05T13:14:15 setDays 3 setYears 2010 plusMinutes 4 = 2010-03-03T13:18:15");
		assertThat(new MathSentence("(2000-03-05T13:14:15 minusDays 3 setYears 2010 plusMinutes 4) = 2010-03-03T13:18:15").toString())
				.isEqualTo("(2000-03-05T13:14:15 minusDays 3 setYears 2010 plusMinutes 4) = 2010-03-03T13:18:15");
	}

}
