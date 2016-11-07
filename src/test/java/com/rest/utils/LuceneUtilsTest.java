package com.rest.utils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

/**
 * Created by bruce.ge on 2016/11/7.
 */
public class LuceneUtilsTest {
    @Test
    public void testLucene() throws IOException, ParseException, InvalidTokenOffsetsException {
        Analyzer standardAnalyzer = new StandardAnalyzer();
        Directory directory = new RAMDirectory();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
        IndexWriter writer = new IndexWriter(directory,indexWriterConfig);
        Document doc = new Document();
        String text = "this is the text to be indexed my text ok boy";
        //content不需要store.需要索引
        //id 需要store 不需要索引
        doc.add(new Field("content",text, TextField.TYPE_STORED));
        //存两遍即可
        IntPoint id = new IntPoint("id",123);
        StoredField storeId = new StoredField("storeId", 123);
        doc.add(id);
        doc.add(storeId);
        writer.addDocument(doc);

        Document otherDoc = new Document();

        writer.close();

        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser("content", standardAnalyzer);
        //analyzer会对输入的元素进行分词 拿到或的结果
        Query query = parser.parse("text");
        TopFieldDocs topFieldDocs = searcher.search(query, 1000, Sort.RELEVANCE);
        System.out.println(topFieldDocs.totalHits);
        ScoreDoc[] scoreDocs = topFieldDocs.scoreDocs;
//        for (int i = 0; i < scoreDocs.length; i++) {
//            Document doc1 = searcher.doc(scoreDocs[i].doc);
//            System.out.println(doc1.get("content"));
//        }
        QueryScorer scorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<B>","</B>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter,scorer);
        highlighter.setTextFragmenter(fragmenter);
        for(ScoreDoc doc1: scoreDocs){
            Document doc2 = searcher.doc(doc1.doc);
            String content = doc2.get("content");
            if(content!=null){
                TokenStream token = standardAnalyzer.tokenStream("content", new StringReader(content));
                System.out.println(highlighter.getBestFragment(token,content));
            }
        }


//        using with term query to find data.
//        针对每个被anlynazer解析后的元素来进行全匹配 下面用queryparser则可以找到
        reader.close();
        directory.close();

    }

    public void intQuery() throws IOException {
        Analyzer standardAnalyzer = new StandardAnalyzer();
        Directory directory = new RAMDirectory();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
        IndexWriter writer = new IndexWriter(directory,indexWriterConfig);
        Document doc = new Document();
        String text = "this is the text to be indexed my text ok boy";
        //content不需要store.需要索引
        //id 需要store 不需要索引
        doc.add(new Field("content",text, TextField.TYPE_STORED));
        //存两遍即可
        IntPoint id = new IntPoint("id",123);
        StoredField storeId = new StoredField("storeId", 123);
        doc.add(id);
        doc.add(storeId);
        writer.addDocument(doc);
        writer.close();
        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        Query query = IntPoint.newRangeQuery("id", 112, 124);
        ScoreDoc[] scoreDocs = searcher.search(query, 1000, Sort.RELEVANCE).scoreDocs;
        for (int i = 0; i < scoreDocs.length; i++) {
            Document doc1 = searcher.doc(scoreDocs[i].doc);
            System.out.println(doc1.get("storeId"));
        }
    }

}