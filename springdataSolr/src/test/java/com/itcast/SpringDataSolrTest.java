package com.itcast;

import com.pinyougou.pojo.TbItem;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SolrDataQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath*:applicationContext-solr.xml")
public class SpringDataSolrTest {
    @Autowired
    private SolrTemplate solrTemplate;
    @Test
    public void test(){
        SimpleQuery query = new SimpleQuery("*:*");
        Criteria criteria = new Criteria("item_title");
        criteria.contains("手机");
        query.setRows(20);
        query.addCriteria(criteria);
        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(query, TbItem.class);
        System.out.println("总页数："+tbItems.getTotalPages());
        System.out.println("总记录数："+tbItems.getTotalElements());
        System.out.println("getMaxScore："+tbItems.getMaxScore());
        System.out.println("getNumber："+tbItems.getNumber());
        System.out.println("getSize："+tbItems.getSize());
        System.out.println("getSort："+tbItems.getSort());
        System.out.println("getContent："+tbItems.getContent());
        /*for (TbItem tbItem : tbItems) {
            System.out.println(tbItem);
        }*/
    }

    @Test
    public void test2(){
        SimpleQuery simpleQuery = new SimpleQuery("*:*");
        /*Criteria criteria = new Criteria("item_title");
        criteria.contains("华为");
        simpleQuery.addCriteria(criteria);*/
        solrTemplate.delete(simpleQuery);
        solrTemplate.commit();
    }
}
