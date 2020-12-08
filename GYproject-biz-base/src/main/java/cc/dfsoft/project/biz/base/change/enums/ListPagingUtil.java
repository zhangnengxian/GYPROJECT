package cc.dfsoft.project.biz.base.change.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassDes: list分页
 * @Author zhangnx
 * @Date 2018/12/26 15:46
 * Version:1.0
 */
public class ListPagingUtil {
    public static  <T> List<T> listPaging(List<T> list, Integer pageStart, Integer pageSize) {
        list=list!=null?list:new ArrayList<T>();
        pageStart=(pageStart==null||pageStart<=0)?1:pageStart;
        pageSize=pageSize!=null?pageSize:10;

        List<T> pagingList = new ArrayList<T>();

        int totalNum =(pageStart-1)+pageSize>list.size()?list.size():(pageStart-1)+pageSize;
        for (int i =pageStart-1;i<totalNum;i++) {
            pagingList.add(list.get(i));
        }
        return pagingList;
    }

}
