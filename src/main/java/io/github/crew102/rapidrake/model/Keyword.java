package io.github.crew102.rapidrake.model;

import java.util.Map;

/**
 * An n-gram that doesn't contain stop words or phrase delimiters.
 */
public class Keyword {
    
  private String keyString; 
  private String[] keyStringAry; 
  private String[] keyStemmedStringAry; 
  private String keyStemmedString; 
  private float score;
  
  public String[] getKeyStringAry() {
    return keyStringAry;
  }
  public String[] getKeyStemmedAry() {
    return keyStemmedStringAry;
  }
  public String getKeyString() {
    return keyString;
  }
  public String getStemmedString() {
    return keyStemmedString;
  }
  public float getScore() {
    return score;
  }
  
  /**
   * Constructor.
   * 
   * @param keyString the full form (i.e., not tokenized) of the keyword (e.g., "good dogs")
   * @param keyStringAry the tokenized version of the keyword (e.g., {"good", "dogs"})
   * @param keyStemmedString the stemmed version of <code>keyString</code> (e.g., "good dogs")
   * @param keyStemmedStringAry the stemmed version of <code>keyStringAry</code> (e.g., {"good", "dog"})
   */
  public Keyword(String keyString, String[] keyStringAry, String keyStemmedString, String[] keyStemmedStringAry) {
    this.keyString = keyString;
    this.keyStringAry = keyStringAry;
    this.keyStemmedString = keyStemmedString;
    this.keyStemmedStringAry = keyStemmedStringAry;
  }
  public Keyword(String keyString, String[] keyStringAry) {
    this.keyString = keyString;
    this.keyStringAry = keyStringAry;
  }
  
  /**
   * Sum the scores of each token belonging to a given keyword.
   * 
   * @param scoreVec a document-level collection of key-value pairs, where the keys are the distinct tokens across all 
   *        keywords and the values are the document-level scores associated with them
   * @param rakeParams the parameters that RAKE will use
   * @see RakeParams
   */
  public void sumScore(Map<String, Float> scoreVec, RakeParams rakeParams) {
    
    String[] ary;
    if (rakeParams.shouldStem()) {
      ary = keyStemmedStringAry;
    } else {
      ary = keyStringAry;
    }
    
    float sum = 0;
    for (int i = 0; i < ary.length; i++) {
      String oneToken = ary[i];
      float val = scoreVec.get(oneToken);
      sum = val + sum;
    }
    score = sum;
  }
  
}