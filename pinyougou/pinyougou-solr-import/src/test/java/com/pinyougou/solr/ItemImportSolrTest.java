package com.pinyougou.solr;

import com.alibaba.fastjson.JSON;
import com.pinyougou.mapper.ItemMapper;
import com.pinyougou.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext*.xml")
public class ItemImportSolrTest {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SolrTemplate solrTemplate;
    @Test
    public void importItem(){
        TbItem param = new TbItem();
        param.setStatus("1");
        List<TbItem> itemList = itemMapper.select(param);
        //将字符串格式的商品规格转为json
        if(itemList != null && itemList.size()>0){
            for (TbItem item : itemList) {
                item.setSpecMap(JSON.parseObject(item.getSpec(), Map.class));
            }
        }
        solrTemplate.saveBeans(itemList);
        solrTemplate.commit();
    }

    @Test
    public void deleteItem() {
        SimpleQuery simpleQuery = new SimpleQuery("*:*");
        solrTemplate.delete(simpleQuery);
        solrTemplate.commit();
    }
}
