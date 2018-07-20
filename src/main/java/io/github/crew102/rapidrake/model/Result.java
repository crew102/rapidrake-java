package io.github.crew102.rapidrake.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A data object containing the results of running RAKE on a single document. After instantiating a Result, we pass a 
 * reference to the object to R, which then calls the object's three getters to pull out the various pieces of data as 
 * R vectors.
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
  

  /**
  * Return a description of the Result. The Result is shown in the following format:
  * "[keyword1 (score of keyword1), keyword2 (score of keyword2)].
  */
  @Override 
  public String toString() {
    
    DecimalFormat dFormat = new DecimalFormat("###.##");
    String[] keyScore = new String[fullKeywords.length];
    
    for (int i = 0; i < fullKeywords.length; i++) {
      keyScore[i] = fullKeywords[i] + " (" + dFormat.format(scores[i]) + ")";
    }
    
    return Arrays.toString(keyScore);
  }
  
  /**
  * Remove duplicate keywords.
  * @return A Result object with duplicate keywords removed 
  */
  public Result distinct() {
        
    ArrayList<String> fullKeywordsListOut = new ArrayList<String>();
    ArrayList<String> stemmedKeyListOut = new ArrayList<String>();
    ArrayList<Float> scoresListOut = new ArrayList<Float>();
        
    for (int i = 0; i < fullKeywords.length; i++) {
      String oneKey = fullKeywords[i];
      if (!fullKeywordsListOut.contains(oneKey)) {
        fullKeywordsListOut.add(oneKey);
        stemmedKeyListOut.add(stemmedKeywords[i]);
        scoresListOut.add(scores[i]);
      }   
    }

    this.fullKeywords = fullKeywordsListOut.toArray(new String[fullKeywordsListOut.size()]);
    this.stemmedKeywords = stemmedKeyListOut.toArray(new String[stemmedKeyListOut.size()]);
    
    float[] scores = new float[scoresListOut.size()];
    
    for (int i = 0; i < scores.length; i ++) {
      scores[i] = scoresListOut.get(i);
    }
    this.scores = scores;
    
    return this;
  }

}