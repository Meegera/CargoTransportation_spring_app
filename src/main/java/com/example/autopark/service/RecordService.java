package com.example.autopark.service;


import com.example.autopark.model.Record;
import com.example.autopark.repository.RecordRepository;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    List<Record> recordList = null;

    @Autowired
    private RecordRepository repoR;

    public List<Record> listAllRecords(String keyword) {/*коллекция, отвечающая за поиск и фильтр*/
        if(keyword != null){
            return repoR.search(keyword);
        }
        return repoR.findAll();
    }

    public void save(Record record){
        repoR.save(record);
    }

    public Record get(Long id){/*редактирование*/
        return repoR.findById(id).get();
    }

    public void delete(Long id){
        repoR.deleteById(id);
    }

    private String KeywordName; private String Keyword;
    public String getKeywordName() {return KeywordName;}
    public String getKeyword() {return Keyword;}
    public List<Record> listByRecordCriteria(String keywordRecord, String keywordPubDate, String keywordRecordPubDate, String keywordAuthor, String keywordText) {
        if (!StringUtil.isNullOrEmpty(keywordRecord)) {
            this.KeywordName = "keywordRecord";
            this.Keyword = keywordRecord;
            return repoR.findByRecord(keywordRecord);
        } else if (!StringUtil.isNullOrEmpty(keywordPubDate)) {
            this.KeywordName = "keywordPubDate";
            this.Keyword = keywordPubDate;
            return repoR.findByPublishDate(keywordPubDate);
        } else if (!StringUtil.isNullOrEmpty(keywordRecordPubDate)) {
            this.KeywordName = "keywordRecordPubDate";
            this.Keyword = keywordRecordPubDate;
            return repoR.findByDateRecord(keywordRecordPubDate);
        } else if (!StringUtil.isNullOrEmpty(keywordAuthor)) {
            this.KeywordName = "keywordAuthor";
            this.Keyword = keywordAuthor;
            return repoR.findByAuthor(keywordAuthor);
        } else if (!StringUtil.isNullOrEmpty(keywordText)) {
            this.KeywordName = "keywordText";
            this.Keyword = keywordText;
            return repoR.findByText(keywordText);
        } else
            return repoR.findAll();
    }

}
