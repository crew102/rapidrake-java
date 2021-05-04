package io.github.crew102.rapidrake.opennlpUtils;

import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

/**
 * A wrapper around opennlp.tools.postag.POSTaggerME.
 */
public class Tagger {
  
  private String taggerModelUrl;
  private InputStream taggerStream;

  /**
   * Constructor.
   * 
   * @param taggerModelUrl the URL of a POS tagging model
   */
  public Tagger(String taggerModelUrl) {
    this.taggerModelUrl = taggerModelUrl;
  }

   /**
   * Constructor.
   * 
   * @param taggerStream the input stream of the POS tagging model
   */
  public Tagger(InputStream taggerStream) {
    this.taggerStream = taggerStream;
  }

  /**
   * Get the POS tagger.
   * 
   * @throws java.io.IOException if <code>inputString</code> or <code>taggerStream</code> is invalid
   * @return a <code>opennlp.tools.postag.POSTaggerME</code>
   */
  public POSTaggerME getPosTagger() throws java.io.IOException {
    
    InputStream inStream = taggerStream;
    POSModel modelIn;
    
    try {
      if(taggerModelUrl != null) {
        inStream = new FileInputStream(taggerModelUrl);
      }
      modelIn = new POSModel(inStream);
    } catch(java.io.IOException ex) {
      throw new java.io.IOException("Couldn't find POS model based on URL", ex);
    } finally {
      if (inStream != null) {
        try {
          inStream.close();
        } catch(java.io.IOException ex2) {
          throw new java.io.IOException(ex2);
        }
      }
    }
    return new POSTaggerME(modelIn);
  }
    
}
