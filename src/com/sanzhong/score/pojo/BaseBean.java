package com.sanzhong.score.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

public abstract class BaseBean {

	protected static Logger logger = Logger.getLogger(BaseBean.class);
	@SuppressWarnings("rawtypes")
	public static RowMapper mapRow(final Class clazz) throws Exception{
		return new RowMapper(){
			@SuppressWarnings("unchecked")
			public Object mapRow(ResultSet resultset, int rowNum)
					throws SQLException {
				Field[] fields = clazz.getDeclaredFields();
				Object o = null;
				try {
					o = clazz.newInstance();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
				for(Field field:fields){
					Method m;
					try {
						m = clazz.getMethod("set"+toUpp(field.getName()), new Class[]{field.getType()});
						m.invoke(o, new Object[]{resultset.getObject(field.getName())});
					} catch (Exception e) {
						logger.error("field:"+field+"---"+e.getMessage(),e);
					}
				}
				return o;
			}
			private String toUpp(String str) {
				if (str != null && str != "") {
					str = str.substring(0, 1).toUpperCase() + str.substring(1);
				}
				return str;
			}
		};
	}
}
