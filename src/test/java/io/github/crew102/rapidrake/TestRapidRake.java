package io.github.crew102.rapidrake;

import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.Test;

import opennlp.tools.stemmer.snowball.SnowballStemmer;

import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.*;

public class TestRapidRake {

  private static String delims = "[-,.?():;\"!/]";
  private static String posUrl = "model-bin/en-pos-maxent.bin";
  private static String sentUrl = "model-bin/en-sent.bin";

  @Test
  public void testMinAlg() throws java.io.IOException {
    String[] stopWords = {""};
    String[] stopPOS = {""};
    RakeParams params = new RakeParams(stopWords, stopPOS, 0, false, delims);
    RakeAlgorithm minRakeAlg = new RakeAlgorithm(params, posUrl, sentUrl);
    Result aRes = minRakeAlg.rake("here is some text");
    String[] keys = aRes.getFullKeywords();
    float[] scores = aRes.getScores();

    assertEquals("here is some text", keys[0]);
    assertEquals("Incorrect scoring", 16, scores[0], 0);

    Result res2 = minRakeAlg.rake("also, here is some more. text.");
    String[] keys2 = res2.getFullKeywords();
    float[] scores2 = res2.getScores();

    assertEquals("also", keys2[0]);
    assertEquals("here is some more", keys2[1]);
    assertEquals("text", keys2[2]);
    assertEquals("Incorrect scoring", 1, scores2[0], 0);
  }
    
  @Test
  public void testStopWordRemoval() throws java.io.IOException {

    String[] stopWords = {"text"};
    String[] stopPOS = {""};
    RakeParams params = new RakeParams(stopWords, stopPOS, 0, true, delims);
    RakeAlgorithm rakeAlg = new RakeAlgorithm(params, posUrl, sentUrl);
    Result aRes = rakeAlg.rake("here is some. text");
    String[] keys = aRes.getFullKeywords();

    assertEquals("Stopword removal", 1, keys.length, 0);
  }
    
  @Test
  public void testStopPOSRemoval() throws java.io.IOException {
    String[] stopWords = {""};
    String[] stopPOS = {"VBD"};
    RakeParams params = new RakeParams(stopWords, stopPOS, 0, true, delims);
    RakeAlgorithm rakeAlg = new RakeAlgorithm(params, posUrl, sentUrl);
    Result aRes = rakeAlg.rake("I ran to the store");
    String[] keys = aRes.getFullKeywords();

    assertEquals("StopPOS removal", 2, keys.length, 0);
  }
    
  @Test
  public void testMinWordCharRemoval() throws java.io.IOException {

    String[] stopWords = {""};
    String[] stopPOS = {""};
    RakeParams params = new RakeParams(stopWords, stopPOS, 2, true, delims);
    RakeAlgorithm rakeAlg = new RakeAlgorithm(params, posUrl, sentUrl);
    Result aRes = rakeAlg.rake("I ran to the store");
    String[] keys = aRes.getFullKeywords();

    assertEquals("ran to the store", keys[0]);
  }
    
  @Test
  public void testStemScoring() throws java.io.IOException {

    String[] stopWords = {""};
    String[] stopPOS = {""};
    RakeParams params = new RakeParams(stopWords, stopPOS, 0, true, delims);
    RakeAlgorithm rakeAlg = new RakeAlgorithm(params, posUrl, sentUrl);
    Result aRes = rakeAlg.rake("good dogs. good. dog");
    float[] scores = aRes.getScores();

    RakeParams params2 = new RakeParams(stopWords, stopPOS, 0, false, delims);
    RakeAlgorithm rakeAlg2 = new RakeAlgorithm(params2, posUrl, sentUrl);
    Result aRes2 = rakeAlg2.rake("good dog. good. dog");
    float[] scores2 = aRes2.getScores();

    assertArrayEquals(scores, scores2, 0);
  }
    
  @Test
  public void testDelims() throws java.io.IOException {

    String[] stopWords = {""};
    String[] stopPOS = {""};
    RakeParams params = new RakeParams(stopWords, stopPOS, 0, true, "[.]"); // no comma
    RakeAlgorithm rakeAlg = new RakeAlgorithm(params, posUrl, sentUrl);
    Result aRes = rakeAlg.rake("good dogs, good. dog");
    String[] keys = aRes.getFullKeywords();

    assertEquals(2, keys.length, 0);
  }

  @Test
  public void testStemmerLang() throws java.io.IOException {

    String[] stopWords = new SmartWords().getSmartWords();
    String[] stopPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"};
    String txtEl = "dependent dogs. dependable dogs";

    RakeParams params = new RakeParams(stopWords, stopPOS, 0, true, delims);
    RakeAlgorithm alg = new RakeAlgorithm(params, posUrl, sentUrl);
    Result res = alg.rake(txtEl);

    RakeParams frenchStem = new RakeParams(stopWords, stopPOS, 0, true, delims,
                                           SnowballStemmer.ALGORITHM.FRENCH);
    RakeAlgorithm frenchAlg = new RakeAlgorithm(frenchStem, posUrl, sentUrl);
    Result frenchRes = frenchAlg.rake(txtEl);

    assertFalse(Arrays.equals(res.getStemmedKeywords(), frenchRes.getStemmedKeywords()));
  }

}
