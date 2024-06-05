rapidrake
================

> A fast version of the Rapid Automatic Keyword Extraction (RAKE) algorithm

[![CI build](https://github.com/crew102/rapidrake-java/actions/workflows/ci-build.yml/badge.svg)](https://github.com/crew102/rapidrake-java/actions/workflows/ci-build.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.crew102/rapidrake/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.crew102/rapidrake)

Installation
------------

Assuming you're using Maven, follow these two steps to use `rakidrake` in your Java project:

1. Include a dependency on `rapidrake` in your POM:

```xml
<dependency>
    <groupId>io.github.crew102</groupId>
    <artifactId>rapidrake</artifactId>
    <version>0.1.4</version>
</dependency>
```

2. Download the `opennlp` trained models for sentence detection and part-of-speech tagging. You can find these two models (trained on various languages) on [opennlp's model page](http://opennlp.sourceforge.net/models-1.5/). For example, you could use the English versions of the [sentence detection](http://opennlp.sourceforge.net/models-1.5/en-sent.bin) and [POS-tagger](http://opennlp.sourceforge.net/models-1.5/en-pos-maxent.bin) models. You'll specify the file paths to these models when you instantiate a `RakeAlgorithm` object (see below for example).

Basic usage
------------

```java
import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;

public class Example {

  public static void main(String[] args) throws java.io.IOException {
    
    // Create an object to hold algorithm parameters
    String[] stopWords = new SmartWords().getSmartWords(); 
    String[] stopPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"}; 
    int minWordChar = 1;
    boolean shouldStem = true;
    String phraseDelims = "[-,.?():;\"!/]"; 
    RakeParams params = new RakeParams(stopWords, stopPOS, minWordChar, shouldStem, phraseDelims);
    
    // Create a RakeAlgorithm object
    String POStaggerURL = "model-bin/en-pos-maxent.bin"; // The path to your POS tagging model
    String SentDetectURL = "model-bin/en-sent.bin"; // The path to your sentence detection model
    RakeAlgorithm rakeAlg = new RakeAlgorithm(params, POStaggerURL, SentDetectURL);
    
    // Call the rake method
    String txt = "dogs are great, don't you agree? I love dogs, especially big dogs";
    Result result = rakeAlg.rake(txt);
    
    // Print the resulting keywords (not stemmed)
    System.out.println(result.distinct());
    
  }
}

// [dogs (1.33), great (1), big dogs (3.33)]
```

Advanced Usage
--------------
The process of initializing the OpenNLP models is time consuming, because of this creating instances of `RakeAlgorithm`
can be expensive when you pass in the file paths to the constructor. 

If you plan on creating many `RakeAlgorithm` instances, you can speed up the construction by instances of the POS and
Sentence Detection objects. The following shows how you can create instances of the require model objects, so that you
can cache the instances and re-use them.


```java
import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;
import io.github.crew102.rapidrake.opennlpUtils.Tagger;
import io.github.crew102.rapidrake.opennlpUtils.SentDetector;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;

public class Example {

  public static void main(String[] args) throws java.io.IOException {
    
    // Create an object to hold algorithm parameters
    String[] stopWords = new SmartWords().getSmartWords(); 
    String[] stopPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"}; 
    int minWordChar = 1;
    boolean shouldStem = true;
    String phraseDelims = "[-,.?():;\"!/]"; 
    RakeParams params = new RakeParams(stopWords, stopPOS, minWordChar, shouldStem, phraseDelims);
    
    // Create a RakeAlgorithm object
    String POStaggerURL = "model-bin/en-pos-maxent.bin"; // The path to your POS tagging model
    String SentDetectURL = "model-bin/en-sent.bin"; // The path to your sentence detection model

    // create the required model classes, you can cache these instances in a singleton
    POSTaggerME tagger = new Tagger(POStaggerURL).getPosTagger();
    SentenceDetectorME sentDetect = new SentDetector(SentDetectURL).getSentDetector();

    // now creating an instance of the RakeAlgorithm is fast
    RakeAlgorithm rakeAlg = new RakeAlgorithm(params, tagger, sentDetect);
    
    // Call the rake method
    String txt = "dogs are great, don't you agree? I love dogs, especially big dogs";
    Result result = rakeAlg.rake(txt);
    
    // Print the resulting keywords (not stemmed)
    System.out.println(result.distinct());
    
  }
}

// [dogs (1.33), great (1), big dogs (3.33)]
```

Learning more
------------

You can learn more about how RAKE works and the various parameters you can set by visiting `slowraker`'s [website](https://crew102.github.io/slowraker/index.html).