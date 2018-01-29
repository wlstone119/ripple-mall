
package com.stone.ripple.mvc.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class Searcher {
	
	private static Logger logger = Logger.getLogger(Searcher.class);
	
	@Autowired
	private Analyzer analyzer;
	@Autowired
	private Version luceneVersion;
	@Autowired
	private Directory directory;
	
	public List<String> search(String keyWords) throws Exception {
		QueryParser parser = new QueryParser(luceneVersion, "contents", analyzer);
		parser.setDefaultOperator(QueryParser.AND_OPERATOR);
		Query query = parser.parse(keyWords);
		
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(reader);
		TopDocs topDocs = indexSearcher.search(query, 100);
		
		logger.info("命中 " + topDocs.totalHits + " For Query: " + query);
		
		List<String> result = new ArrayList<String>(topDocs.totalHits);
		for (int i = 0; i < topDocs.totalHits; i++) {
			Document targetDoc = indexSearcher.doc(topDocs.scoreDocs[i].doc);
			result.add(targetDoc.get("title"));
		}
		reader.close();
		return result;
	}
}
