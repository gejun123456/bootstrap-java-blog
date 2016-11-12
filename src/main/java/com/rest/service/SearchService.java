package com.rest.service;


import com.google.common.collect.Lists;
import com.rest.constant.LuceneFieldConstant;
import com.rest.dto.QueryResult;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.parboiled.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rest.constant.LuceneFieldConstant.*;

/**
 * Created by bruce.ge on 2016/11/7.
 */
@Service
public class SearchService {
    private static Logger logger = LoggerFactory.getLogger(SearchService.class);
    private Analyzer analyzer;

    private Directory directory;

    private IndexWriterConfig indexWriterConfig;

    private IndexWriter indexWriter;

    private DirectoryReader reader;

    private DirectoryReader indexReader;

    private IndexSearcher searcher;
    //fix with static when start.

    @PostConstruct
    public void init() throws IOException {
        logger.info("start init lucene service");
        analyzer = new StandardAnalyzer();
        //没问题 生成一个directory
        directory = FSDirectory.open(Paths.get("/tmp/testindex"));
        reader = DirectoryReader.open(directory);
        indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriter = new IndexWriter(directory, indexWriterConfig);
        indexReader = DirectoryReader.open(indexWriter);
        searcher = new IndexSearcher(reader);
    }

    @PreDestroy
    public void destroy() throws IOException {
        logger.info("start destroy lucene service");
        indexWriter.close();
        indexReader.close();
        reader.close();
        logger.info("successfully closed");
    }

    public void addSource(String title, String content, int id) {
        //there must time to show.
        Document doc = new Document();
        //add field title,id, and content.
        Field idf = new IntPoint(ID, id);
        Field storedId = new StoredField(STOREDID, id);
        Field titlef = new TextField(TITLE, title, Field.Store.YES);
        Field contentf = new TextField(CONTENT, content, Field.Store.YES);
        doc.add(idf);
        doc.add(storedId);
        doc.add(titlef);
        doc.add(contentf);
        try {
            indexWriter.addDocument(doc);
            indexWriter.commit();
        } catch (IOException e) {
            logger.error("doc add failed,", e);
        }
    }

    public List<QueryResult> query(String qs) {
        //根据变量来控制
        List<QueryResult> queryResults = Lists.newArrayList();
        // then build with parser.
        //search by title or content.
        String[] fields = {LuceneFieldConstant.TITLE, LuceneFieldConstant.CONTENT};
        Map<String, Float> boost = new HashMap<String, Float>() {{
            put(LuceneFieldConstant.TITLE, 2.0f);
            put(LuceneFieldConstant.CONTENT, 1.0f);
        }};
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer, boost);
        Query query = null;
        try {
            query = parser.parse(qs);
        } catch (ParseException e) {
            logger.error("create query by string:" + qs + "failed", e);
            return queryResults;
        }
        TopFieldDocs search = null;
        try {
            search = searcher.search(query, 1000, Sort.RELEVANCE);
        } catch (IOException e) {
            logger.error("search catch exception, the string is:" + qs, e);
            return queryResults;
        }
        int totalHits = search.totalHits;
        if (search.scoreDocs.length == 0) {
            return queryResults;
        }
        QueryScorer scorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<B>", "</B>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        for (ScoreDoc scoreDoc : search.scoreDocs) {
            Document doc = null;
            try {
                doc = searcher.doc(scoreDoc.doc);
            } catch (IOException e) {
                logger.error("search doc catch exception", e);
                return queryResults;
            }
            QueryResult result = new QueryResult();
            String sId = doc.get(LuceneFieldConstant.STOREDID);
            result.setId(Integer.valueOf(sId));
            String title = doc.get(LuceneFieldConstant.TITLE);
            result.setTitle(title);
            try {
                if (title != null) {
                    TokenStream token = analyzer.tokenStream(LuceneFieldConstant.TITLE, new StringReader(title));
                    String bestFragment = highlighter.getBestFragment(token, title);
                    if (StringUtils.isNotEmpty(bestFragment)) {
                        result.setTitle(bestFragment);
                    }
                }
                String content = doc.get(LuceneFieldConstant.CONTENT);
                if (content != null) {
                    TokenStream token = analyzer.tokenStream(LuceneFieldConstant.CONTENT, new StringReader(content));
                    String bestFragment = highlighter.getBestFragment(token, content);
                    if (StringUtils.isNotEmpty(bestFragment)) {
                        result.setMarkText(bestFragment);
                    }
                }
            } catch (Exception e) {
                logger.error("get best fragement catch exception", e);
                return queryResults;
            }
            queryResults.add(result);
        }
        return queryResults;
    }

}
