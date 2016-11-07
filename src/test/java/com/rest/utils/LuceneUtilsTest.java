package com.rest.utils;

import com.google.gson.Gson;
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
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by bruce.ge on 2016/11/7.
 */
public class LuceneUtilsTest {

    private static Gson gson = new Gson();
    @Test
    public void testLucene() throws IOException {
        Analyzer standardAnalyzer = new StandardAnalyzer();
        Directory directory = FSDirectory.open(Paths.get("/tmp/index"));
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

        Directory directory1 = null;
        try {
            directory1 = FSDirectory.open(Paths.get("/tmp/index"));
        } catch (IOException e) {
            System.out.println("no file");
            return;
        }
        DirectoryReader reader = null;
        try {
            reader = DirectoryReader.open(directory1);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser("content", standardAnalyzer);
        //analyzer会对输入的元素进行分词 拿到或的结果
        Query query = null;
        try {
            query = parser.parse("text");
        } catch (ParseException e) {
            System.out.println("parse exception");
            return;
        }
        TopFieldDocs topFieldDocs = null;
        try {
            topFieldDocs = searcher.search(query, 1000, Sort.RELEVANCE);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            Document doc2 = null;
            try {
                doc2 = searcher.doc(doc1.doc);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String content = doc2.get("storeId");
            if(content!=null){
                TokenStream token = standardAnalyzer.tokenStream("storeId", new StringReader(content));
                try {
                    System.out.println(highlighter.getBestFragment(token,content));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidTokenOffsetsException e) {
                    e.printStackTrace();
                }
            }
        }


//        using with term query to find data.
//        针对每个被anlynazer解析后的元素来进行全匹配 下面用queryparser则可以找到
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            directory1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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


    @Test
    public void testMyUtils(){
        //来个正常的操作
        LuceneUtils.addSource("hello","nimei wode tian",1);
        LuceneUtils.addSource("ok hey you", "change your life guys",2);
        LuceneUtils.addSource("oh yeah boy", "hello your life guys",2);
        System.out.println(gson.toJson(LuceneUtils.query("hello")));
    }

}