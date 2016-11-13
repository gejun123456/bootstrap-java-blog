package com.rest.service;


import com.google.common.collect.Lists;
import com.rest.constant.LuceneFieldConstant;
import com.rest.dto.SearchResult;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
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

    //fix with static when start.

    @PostConstruct
    public void init() throws IOException {
        logger.info("start init lucene service");
        analyzer = new StandardAnalyzer();
        //没问题 生成一个directory
        directory = FSDirectory.open(Paths.get("/tmp/testindex"));
        indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriter = new IndexWriter(directory, indexWriterConfig);
    }

    @PreDestroy
    public void destroy() throws IOException {
        logger.info("start destroy lucene service");
        indexWriter.close();
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

    public List<SearchResult> query(String qs) {
        List<SearchResult> searchResults = Lists.newArrayList();
        DirectoryReader indexReader = null;
        try {
            //根据变量来控制
            // then build with parser.
            //search by title or content.
            String[] fields = {LuceneFieldConstant.TITLE, LuceneFieldConstant.CONTENT};
            Map<String, Float> boost = new HashMap<String, Float>() {{
                put(LuceneFieldConstant.TITLE, 2.0f);
                put(LuceneFieldConstant.CONTENT, 1.0f);
            }};
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer, boost);
            Query query = null;
            query = parser.parse(qs);
            // TODO: 2016/11/12 figure out why it need to create every time.
            indexReader = DirectoryReader.open(indexWriter);
            IndexSearcher searcher = new IndexSearcher(indexReader);
            TopFieldDocs search = null;
            search = searcher.search(query, 1000, Sort.RELEVANCE);
            if (search.scoreDocs.length == 0) {
                return searchResults;
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
                    SearchResult result = new SearchResult();
                    String sId = doc.get(LuceneFieldConstant.STOREDID);
                    result.setId(Integer.valueOf(sId));
                    String title = doc.get(LuceneFieldConstant.TITLE);
                    result.setTitle(title);

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

                    searchResults.add(result);
                } catch (Exception e) {
                    logger.error("doc catch exception, the docId is {} the title is:{}", doc.get(LuceneFieldConstant.STOREDID), doc.get(LuceneFieldConstant.TITLE), e);
                }
            }

            return searchResults;
        } catch (Exception e) {
            logger.error("query with {} catch exception", qs, e);
            return searchResults;
        }finally {
            if(indexReader!=null){
                try {
                    indexReader.close();
                } catch (IOException e) {
                    logger.error("indexReaderClose catch exception",e);
                }
            }
        }
    }

    public void update(String title, String content, int id) {
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
            delete(id);
            indexWriter.addDocument(doc);
            indexWriter.commit();
        } catch (IOException e) {
            logger.error("doc add failed,", e);
        }
    }


    public void delete(int id){
        Query query = IntPoint.newExactQuery(ID, id);
        try {
            indexWriter.deleteDocuments(query);
            indexWriter.commit();
        } catch (IOException e) {
            logger.error("delete failed, the id is {}",id,e);
        }
    }
}
