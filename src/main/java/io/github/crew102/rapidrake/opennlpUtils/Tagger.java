package io.github.crew102.rapidrake.opennlpUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

/**
 * A wrapper around opennlp.tools.postag.POSTaggerME.
 */
public class Tagger {
	
	private String inputString = null;
	private InputStream input = null;

	/**
	 * Constructor.
	 * @param inputString the URL of a POS tagging model
	 */
	public Tagger(String inputString) {
		this.inputString = inputString;
	}

	public Tagger(InputStream inputString) {
		this.input = inputString;
	}

	/**
	 * Get the POS tagger.
	 * @throws java.io.IOException if <code>inputString</code> is invalid
	 * @return a <code>opennlp.tools.postag.POSTaggerME</code>
	 */
	public POSTaggerME getPosTagger() throws java.io.IOException {
		InputStream modelIn = null;
		POSModel modelIn2 = null;
		try {
			if(input == null) {
				modelIn = new FileInputStream(inputString);
			}else
				modelIn = input;

			modelIn2 = new POSModel(modelIn);
		} catch(java.io.IOException ex) {
			throw new java.io.IOException("Couldn't find POS model based on URL", ex);
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch(java.io.IOException ex2) {
					throw new java.io.IOException(ex2);
				}
			}
		}
		return new POSTaggerME(modelIn2);
	}
		
}