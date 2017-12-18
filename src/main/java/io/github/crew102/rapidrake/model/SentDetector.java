package io.github.crew102.rapidrake.model;

import java.io.FileInputStream;
import java.io.InputStream;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceDetectorME;

/**
 * A wrapper around opennlp.tools.sentdetect.SentenceDetectorME.
 */
public class SentDetector {
	
	private String inputString;
	
	/**
	 * Constructor.
	 * @param inputString the URL of a sentence detection model
	 */
	public SentDetector(String inputString) {
		this.inputString = inputString;
	}
	
	public SentenceDetectorME getSentDetector() throws java.io.IOException {
		InputStream modelIn = null;
		SentenceModel modelIn2 = null;
		try {
			modelIn = new FileInputStream(inputString);
			modelIn2 = new SentenceModel(modelIn);
		} catch(java.io.IOException ex) {
			throw new java.io.IOException("Couldn't find sentence detector model based on URL", ex);
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch(java.io.IOException ex2) {
					throw new java.io.IOException(ex2);
				}
			}
		}
		return new SentenceDetectorME(modelIn2);
	}
		
}