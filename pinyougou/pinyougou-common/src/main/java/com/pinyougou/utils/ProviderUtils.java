package com.pinyougou.utils;

import java.io.Serializable;

public class ProviderUtils <T> {
    //根据id批量删除
    public void batchDelete(Serializable [] ids,T t){
       /* StringBuilder sb=new StringBuilder("delete from "+t+"where id in (");
        if(ids != null && ids.length >0) {
            for (Serializable id : ids) {
                sb.append(id+",");
            }
        }
        String str = sb.substring(0, sb.length() - 1);*/

    }
}
