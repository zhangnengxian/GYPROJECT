package cc.dfsoft.uexpress.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 查找数组位置
 * @Auther: zhangnx
 * @Date: 2019/8/22 13:40
 * @Version:1.0
 */
public class ArraysUtil {

    public static int searchPosition(Object[] o, Object k) {
        if (o == null || k == null) return -1;
        for (int i = 0; i < o.length; i++) {
            if (k.equals(o[i])) {
                return i;
            }
        }
        return -1;
    }



    /**
    * @Description: 平移搜素指定位置值
    * @author zhangnx
    * @date 2019/8/23 9:55
    */
    public static String translateSearch(List<String> l, String v, int n){
        if (l==null || l.size()<1) return null;
        if (StringUtils.isBlank(v)) return null;

        Object r=null;
        for (int i = 0; i <l.size() ; i++) {
            if (v.equals(l.get(i))){
                if (n>0 && i+n<l.size()){
                    r=l.get(i+n);
                }else if (n<0 && i+n>=0){
                    r=l.get(i+n);
                }
            }
        }
        return r!=null?r.toString():"";
    }


    /**
     * @Description: 获取开始到结束之间的code
     * @author zhangnx
     * @date 2019/8/23 10:22
     */
    public static List<String> splitStartToEndCodeTranslateList(String code,String startCode,String endCode){
        if (StringUtils.isNotBlank(code)){
            String [] codeArr=code.split(",");
            int startp = ArraysUtil.searchPosition(codeArr, startCode);
            int endp = ArraysUtil.searchPosition(codeArr, endCode);

            if (startp==-1 ||endp==-1) return null;
            if (startp>endp) return null;

            //获取开始状态到结束状态之间的工作流
            codeArr= Arrays.copyOfRange(codeArr, startp,endp+1);

            return Arrays.asList(codeArr);
        }
        return null;
    }
}
