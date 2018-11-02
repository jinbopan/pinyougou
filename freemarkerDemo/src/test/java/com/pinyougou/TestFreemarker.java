package com.pinyougou;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class TestFreemarker {
    @Test
    public void test() throws Exception {
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_0);
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(TestFreemarker.class,"/ftl");
        Template template = configuration.getTemplate("test.ftl");
        Map dataModel = new HashMap();
        dataModel.put("name","张三" );
        dataModel.put("message",true );
        List<Map<String,Object>> list=new ArrayList<>();
        Map map = new HashMap();
        map.put("name","李四" );
        map.put("message",true );
        list.add(map);
        Map map1 = new HashMap();
        map1.put("name","李霸天" );
        map1.put("message",false );
        list.add(map1);
        dataModel.put("list",list );

        dataModel.put("datetime",new Date());
        dataModel.put("number",123488 );
        Writer out=new FileWriter("D:\\setup\\test.html");
        template.process(dataModel, out);
        out.close();
    }
}
