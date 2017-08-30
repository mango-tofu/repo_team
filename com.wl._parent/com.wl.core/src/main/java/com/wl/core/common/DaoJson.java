package com.wl.core.common;

import java.io.IOException;
import java.io.StringWriter;


import java.lang.reflect.AnnotatedElement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @date 
 */
public class DaoJson {

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * @Title: 返回JSON
	 * @author 
	 * @date 
	 * @time 
	 * @Description: 将对象返回JSON字符
	 */
	public static String getJson(Object o) {  
		StringWriter sw = new StringWriter();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					DEFAULT_DATE_FORMAT);
			ObjectMapper om = new ObjectMapper();
			om.setDateFormat(dateFormat);
			om.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
				@Override
				public Object findSerializer(
						org.codehaus.jackson.map.introspect.Annotated a) {
					if (a instanceof AnnotatedMethod) {
						AnnotatedElement m = a.getAnnotated();
						DateTimeFormat an = m
								.getAnnotation(DateTimeFormat.class);
						if (an != null) {
							if (!DEFAULT_DATE_FORMAT.equals(an.pattern())) {
								return new JsonDateSerializer(an.pattern());
							}
						}
					}
					return super.findSerializer(a);
				}
			});
			JsonGenerator jg = new JsonFactory().createJsonGenerator(sw);
			om.writeValue(jg, o);
			jg.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
        
    } 
	
	public static class JsonDateSerializer extends JsonSerializer<Date> {
		private SimpleDateFormat dateFormat;
		public JsonDateSerializer(String format) {
			dateFormat = new SimpleDateFormat(format);
		}

		@Override
		public void serialize(Date date, JsonGenerator gen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			String value = dateFormat.format(date);
			gen.writeString(value);
		}
	}
}
