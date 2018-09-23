package top.jfunc.json.strategy;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import top.jfunc.json.annotation.JsonField;

/**
 * @author xiongshiyan at 2018/9/20 , contact me with email yanshixiong@126.com or phone 15208384257
 */
public class FieldPropertyNamingStrategy extends PropertyNamingStrategy {
    /**
     * 擦,只针对public
     */
    @Override
    public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
        JsonField annotation = field.getAnnotation(JsonField.class);
        if(null == annotation || "".equals(annotation.value())){
            return defaultName;
        }
        return "".equals(annotation.value()) ?  defaultName : annotation.value();
    }

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        JsonField annotation = method.getAnnotation(JsonField.class);
        if(null == annotation){
            return super.nameForGetterMethod(config, method, defaultName);
        }
        return "".equals(annotation.value()) ?  defaultName : annotation.value();
    }
}
