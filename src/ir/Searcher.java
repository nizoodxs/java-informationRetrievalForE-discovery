/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp;

import java.io.IOException;
import java.nio.file.Paths;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author Admin
 */
public class Searcher {
    
    private static final String INDEX_DIRECTORY = "C:\\testFiles\\indexedDocs";
    private static final String FIELD ="contents";
    
    public static void search(String queryString) throws IOException, ParseException{
        
        try (IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(INDEX_DIRECTORY)))) {
            IndexSearcher searcher = new IndexSearcher(reader);
            
            StandardAnalyzer analyzer = new StandardAnalyzer();
            
            QueryParser queryParser = new QueryParser(FIELD,analyzer);
            queryParser.setDefaultOperator(QueryParser.Operator.OR);
            Query query = queryParser.parse(queryString.trim());
            
// Collect enough docs to show pages
//TopDocs results = searcher.search(query,5000);
ScoreDoc[] hits = searcher.search(query, 10000).scoreDocs;
//int numTotalHits = results.totalHits;


//hits = searcher.search(query,numTotalHits).scoreDocs;

for(ScoreDoc hit : hits){
    int i = 0;
    Document doc = searcher.doc(hit.doc);
    i++;
    String path = doc.get("path");
    
    if (path != null) {
        System.out.println((i+1) + ". " + path);
        String title = doc.get("title");
        if (title != null) {
            System.out.println("   Title: " + doc.get("title"));
        }
    } else {
        System.out.println((i+1) + ". " + "No path for this document");
    }
}
        }
    }
    
}
