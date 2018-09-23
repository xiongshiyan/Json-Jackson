package top.jfunc.json.strategy;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import top.jfunc.json.annotation.JsonField;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExcludeFilter extends SimpleBeanPropertyFilter {
    public static final String FILTER_NAME = "field-exclude";

    /**
     * Set of property names to filter out.
     */
    private final Set<String> _propertiesToExclude = new HashSet<>();

    public ExcludeFilter(String... ignoreFields){
        _propertiesToExclude.addAll(Arrays.asList(ignoreFields));
    }


    @Override
    protected boolean include(BeanPropertyWriter writer) {
        return  !_propertiesToExclude.contains(writer.getName());
    }

    @Override
    protected boolean include(PropertyWriter writer) {
        boolean b = _propertiesToExclude.contains(writer.getName());
        if(b){
            return false;
        }
        JsonField annotation = writer.findAnnotation(JsonField.class);
        return null == annotation || annotation.serialize();
    }
}
