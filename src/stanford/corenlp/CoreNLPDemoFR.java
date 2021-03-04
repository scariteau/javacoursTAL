package stanford.corenlp;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

public class CoreNLPDemoFR {
	public static void main(String[] args) {
//
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos,parse");
		props.setProperty("parse.model", "edu/stanford/nlp/models/srparser/frenchSR.beam.ser.gz");
		props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/french-ud.tagger");
		// (ROOT (SENT (NNP je) (FW suis) (FW belle)))
		props.setProperty("tokenize.language", "fr");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		String text = "je suis une fille du sud de la France";
		Annotation document = new Annotation(text);

		pipeline.annotate(document);

		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			Tree parseTree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
			System.out.println(parseTree);
		}

	}
}
