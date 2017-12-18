package io.github.crew102.rapidrake.model;

/**
 * A data object containing the results of running RAKE on a single document. After instantiating a Result
 * based on the results of RAKE, we pass a reference to the object to R, which then calls the object's three getters 
 * (one at a time) to pull out the various pieces of data as R vectors.
 */
public class Result {
	
	private String[] fullKeywords;
	private String[] stemmedKeywords;
	private float[] scores;
	
    /**
     * Constructor.
     *
     * @param fullKeywords an array of keywords that RAKE found in a document 
     * @param stemmedKeywords the stemmed versions of <code>fullKeywords</code>
     * @param scores the scores assigned to the keywords
     */
	public Result(String[] fullKeywords, String[] stemmedKeywords, float[] scores) {
		this.fullKeywords = fullKeywords;
		this.stemmedKeywords = stemmedKeywords;
		this.scores = scores;
	}

	public String[] getFullKeywords() {
		return fullKeywords;
	}
	public String[] getStemmedKeywords() {
		return stemmedKeywords;
	}
	public float[] getScores() {
		return scores;
	}
	
}