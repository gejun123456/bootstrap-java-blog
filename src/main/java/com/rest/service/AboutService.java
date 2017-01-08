package com.rest.service;

import com.rest.domain.AboutPo;
import com.rest.mapper.AboutPoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bruce.ge on 2017/1/8.
 */
@Service
public class AboutService {
    @Autowired
    private AboutPoDao aboutPoDao;

    @Transactional
    public AboutPo getAbout() {
        return aboutPoDao.findFirst();
    }
}
