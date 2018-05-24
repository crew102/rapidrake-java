package io.github.crew102.rapidrake.opennlpUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceDetectorME;

/**
 * A wrapper around opennlp.tools.sentdetect.SentenceDetectorME.
 */
public class SentDetector {
	
	private String sentDectModelUrl;
	private InputStream sentDectStream;

	/**
	 * Constructor.
	 * @param sentDectModelUrl the URL of a sentence detection model
	 */
	public SentDetector(String sentDectModelUrl) {
		this.sentDectModelUrl = sentDectModelUrl;
	}

	 /**
   * Constructor.
   * @param sentDectStream the input stream of the sentence detection model
   */
	public SentDetector(InputStream sentDectStream) {
		this.sentDectStream = sentDectStream;
	}
	
	/**
	 * Get the sentence detector.
	 * @throws java.io.IOException if <code>sentDectModelUrl</code> or <code>sentDectStream</code> is invalid
	 * @return a <code>opennlp.tools.sentdetect.SentenceDetectorME</code>
	 */
	public SentenceDetectorME getSentDetector() throws java.io.IOException {
	  
		InputStream inStream = sentDectStream;
		SentenceModel modelIn = null;
		
		try {
			if (sentDectModelUrl != null) {
			  inStream = new FileInputStream(sentDectModelUrl);
			}
			modelIn = new SentenceModel(inStream);
		} catch(java.io.IOException ex) {
			throw new java.io.IOException("Couldn't find sentence detector model based on URL", ex);
		} finally {
			if (inStream != null) {
				try {
				  inStream.close();
				} catch(java.io.IOException ex2) {
					throw new java.io.IOException(ex2);
				}
			}
		}
		return new SentenceDetectorME(modelIn);
	}
		
}