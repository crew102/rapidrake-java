//package io.github.crew102.rapidrake;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import io.github.crew102.rapidrake.RakeAlgorithm;
//import io.github.crew102.rapidrake.model.*;
//
//public class TestRapidRake {
//	
//	RakeAlgorithm myAlgd;
//	
//    @Test
//    public void tesInitTokens() throws java.io.IOException {
    	
//		String[] stopWords = {"shirt"};
//		String[] stopPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"};
//		RakeParams params = new RakeParams(stopWords, stopPOS, 3, true, "[-,.?():;\"!/]");
//				
//		RakeAlgorithm myAlgd = new RakeAlgorithm(params, "model-bin/en-pos-maxent.bin", "model-bin/en-sent.bin");
//    	Result aRes = myAlgd.rake("here is some text. I love dogs. i'm a dog lover.");
    	
//		String[] stopWords1 = {""};
//		String[] stopPOS1 = {""};
//		RakeParams params1 = new RakeParams(stopWords1, stopPOS1, 0, true, "[-,.?():;\"!/]");
//		RakeAlgorithm myAlgd = new RakeAlgorithm(params1, "model-bin/en-pos-maxent.bin", "model-bin/en-sent.bin");
//    	Result aRes1 = myAlgd.rake("dogs are great, arn't they? at least i think they are.");

//    	assertEquals("here", aRes1.getFullKeywords()[0]);
//     }
//   
//
//}

