package io.github.crew102.rapidrake.model;

import java.util.Arrays;
import java.util.List;

/**
 * A parameter object for RAKE settings.
 */
public class RakeParams {
	
	private final List<String> stopWords;
	private final List<String> stopPOS;
	private final int wordMinChar;
	private final boolean stem;
	private final String phraseDelims;
	
    /**
     * Constructor.
     *
     * @param stopWords an array of stopwords, which will will be treated like phrase delimiters when RAKE is identifying
     * 		  candidate keywords 
     * @param stopPOS an array of part-of-speech (POS) tags that should be considered stopwords. Words that are tagged 
     *        with any of the parts-of-speech listed in <code>stopPOS</code> will be treated like delimiters. 
     *        See <a href="http://martinschweinberger.de/docs/articles/PosTagR.pdf">Part-Of-Speech Tagging with R</a> 
     *        for a list of acceptable POS tags and their meanings. 
     * @param wordMinChar the minimum number of characters that a token/word must have. Words below this threshold are 
     * 		  treated like phrase delimiters.
     * @param stem an indicator for whether you want to stem the tokens in each keyword
     * @param phraseDelims a character set containing the punctuation characters which will be used to identify phrases
     */
	public RakeParams(String[] stopWords, String[] stopPOS, int wordMinChar, boolean stem, String phraseDelims) {
		this.stopWords = Arrays.asList(stopWords);
		this.stopPOS = Arrays.asList(stopPOS);
		this.wordMinChar = wordMinChar;
		this.stem = stem;
		this.phraseDelims = phraseDelims;
	}
	
	public List<String> getStopWords() {
		return stopWords;
	}
	public List<String> getStopPOS() {
		return stopPOS;
	}
	public int getWordMinChar() {
		return wordMinChar;
	}
	public boolean shouldStem() {
		return stem;
	}
	public String getPhraseDelmins() {
		return phraseDelims;
	}

}