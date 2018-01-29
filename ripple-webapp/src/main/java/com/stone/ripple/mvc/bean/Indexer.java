
package com.stone.ripple.mvc.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class Indexer {
	
	private static Logger logger = Logger.getLogger(Indexer.class);
	
	@Autowired
	private IndexWriter indexWriter;
	@Autowired
	private String newsPath;
	
	public void index(boolean create) throws IOException {
		logger.info("索引任务开始...");
		long t0 = System.currentTimeMillis();
		final File docDir = new File(newsPath);
		if (!docDir.exists() || !docDir.canRead()) {
			throw new RuntimeException("Document directory '"
							+ docDir.getAbsolutePath()
							+ "' does not exist or is not readable, please check the path");
		}
		indexDocs(docDir);
		indexWriter.commit();
		logger.info("索引任务结束... 耗时：" + (System.currentTimeMillis() - t0) / 1000 + "秒");
	}

	private void indexDocs(File file) throws IOException {
		// do not try to index files that cannot be read
		if (file.canRead()) {
			if (file.isDirectory()) {
				String[] files = file.list();
				// an IO error could occur
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						indexDocs(new File(file, files[i]));
					}
				}
			} else {

				FileInputStream fis;
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException fnfe) {
					// at least on windows, some temporary files raise this
					// exception with an "access denied" message
					// checking if the file can be read doesn't help
					return;
				}

				try {

					// make a new, empty document
					Document doc = new Document();

					// Add the path of the file as a field named "path". Use a
					// field that is indexed (i.e. searchable), but don't
					// tokenize
					// the field into separate words and don't index term
					// frequency
					// or positional information:
					String filePath = file.getPath();
					Field pathField = new StringField("path", filePath, Field.Store.YES);
					doc.add(pathField);
					Field titleField = new StringField("title", 
							filePath.substring(filePath.lastIndexOf("\\") + 1), Field.Store.YES);
					doc.add(titleField);
					// Add the contents of the file to a field named "contents".
					// Specify a Reader,
					// so that the text of the file is tokenized and indexed,
					// but not stored.
					// Note that FileReader expects the file to be in UTF-8
					// encoding.
					// If that's not the case searching for special characters
					// will fail.
					doc.add(new TextField("contents", new BufferedReader(
							new InputStreamReader(fis, "UTF-8"))));
					indexWriter.updateDocument(new Term("path", filePath), doc);
				} finally {
					fis.close();
				}
			}
		}
	}
}
