package stanford.corenlp;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

public class CoreNLPDemo {
	public static void main(String[] args) {

		// 1. Set up a CoreNLP pipeline. This should be done once per type of
		// annotation, as it's fairly slow to initialize.
		// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER,
		// parsing, and coreference resolution
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// 2. Run the pipeline on some text.
		// read some text in the text variable
		String text = "the quick brown fox jumped over the lazy dog"; // Add your text here!
		// create an empty Annotation just with the given text
		Annotation document = new Annotation(text);
		// run all Annotators on this text
		pipeline.annotate(document);

		// 3. Read off the result
		// Get the list of sentences in the document
		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			// Get the parse tree for each sentence
			Tree parseTree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
			// Do something interesting with the parse tree!
			System.out.println(parseTree);
		}

	}
}
