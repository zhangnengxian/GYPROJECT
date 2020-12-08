package cc.dfsoft.uexpress.common.dto;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * @ClassDesc: 对象转Json日期处理器
 * @author: zhangnx
 * @date: 14:24 2018/9/20
 * @version: V1.0
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
    //private String format = "yyyy-MM-dd HH:mm:ss";
    private String format = "yyyy-MM-dd";

    public JsonDateValueProcessor() {
        super();
    }

    public JsonDateValueProcessor(String format) { // 自己需要的格式
        super();
        this.format = format;
    }

    @Override
    public Object processArrayValue(Object value, JsonConfig paramJsonConfig) {
        return process(value);
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig paramJsonConfig) {
        return process(value);
    }

    private Object process(Object value) {
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }

}
