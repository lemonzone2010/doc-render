package cn.com.sinosoft.app.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/**
 * Jackson 简单封装类
 * @ClassName: JacksonBinder
 * @Description:Jackson 简单封装类
 * @author lihengjun
 * 修改时间： 2013年11月8日 上午9:56:18
 * 修改内容：新建
 */
public class JacksonBinder {

	private static Logger logger = Logger.getLogger(JacksonBinder.class);

	private ObjectMapper mapper;
//	private XmlMapper xmlMapper;

	public JacksonBinder(Inclusion inclusion) {
		this.mapper = new ObjectMapper();
		// 设置输出包含的属性
		this.mapper.getSerializationConfig().setSerializationInclusion(inclusion);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		this.mapper.getDeserializationConfig()
				.set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);

//		xmlMapper = new XmlMapper();
//		xmlMapper.getSerializationConfig().setSerializationInclusion(inclusion);
//		xmlMapper.getDeserializationConfig().set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
//						false);
		
	}

	/**
	 * 创建输出全部属性到Json字符串的Binder.
	 */
	public static JacksonBinder buildNormalBinder() {
		return new JacksonBinder(Inclusion.ALWAYS);
	}

	/**
	 * 创建只输出非空属性到Json字符串的Binder.
	 */
	public static JacksonBinder buildNonNullBinder() {
		return new JacksonBinder(Inclusion.NON_NULL);
	}

	/**
	 * 创建只输出初始值被改变的属性到Json字符串的Binder.
	 */
	public static JacksonBinder buildNonDefaultBinder() {
		return new JacksonBinder(Inclusion.NON_DEFAULT);
	}

	/**
	 * 如果JSON字符串为Null或"null"字符串,返回Null. 如果JSON字符串为"[]",返回空集合.
	 * 
	 * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: List<MyBean> beanList =
	 * binder.getMapper().readValue(listString, new
	 * TypeReference<List<MyBean>>() {});
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (ObjectUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return this.mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}


	/**
	 * 源数据对象 转换 成目标对象
	 * @Title: convertValue
	 * @Description: 
	 * @param fromValue 源数据对象
	 * @param toValueType 目标对象类型
	 * @return
	 * @author lihengjun
	 * 修改时间： 2013年11月5日 上午11:10:00
	 * 修改内容：新建
	 */
	public <T> T convertValue(Object fromValue, Class<T> toValueType) {
		if (ObjectUtils.isEmpty(fromValue)) {
			return null;
		}
		return this.mapper.convertValue(fromValue, toValueType);
	}
	/**
	 * 
	 * @Title: fromJsonFile
	 * @Description: 根据json文件转换成对象
	 * @param file
	 *            json 文件
	 * @param clazz
	 * @return
	 * @author lihengjun 修改时间： 2013年11月7日 上午11:22:01 修改内容：新建
	 */
	public <T> T fromJsonFile(File file, Class<T> clazz) {
		if (ObjectUtils.isEmpty(file)) {
			return null;
		}

		try {
			return this.mapper.readValue(file, clazz);
		} catch (IOException e) {
			logger.error("parse json string error:" + file, e);
			return null;
		}
	}

	/**
	 * 如果对象为Null,返回"null". 如果集合为空集合,返回"[]".
	 */
	public String toJson(Object object) {

		try {
			return this.mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.error("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数.
	 */
	public void setDateFormat(String pattern) {
		if (ObjectUtils.isNotBlank(pattern)) {
			DateFormat df = new SimpleDateFormat(pattern);
			this.mapper.getSerializationConfig().setDateFormat(df);
			this.mapper.getDeserializationConfig().setDateFormat(df);
		}
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return this.mapper;
	}

//	/**
//	 * Object to XML, default root name is 'root'
//	 * 
//	 * @param nameValuePair
//	 * @return
//	 */
//	public String toXML(Object object) {
//		if (ObjectUtils.isEmpty(object)) {
//			return null;
//		}
//		try {
//			return getXmlMapper().writeValueAsString(object);
//		} catch (JsonGenerationException e) {
//			logger.error("write to xml string error:" + object, e);
//		} catch (JsonMappingException e) {
//			logger.error("write to xml string error:" + object, e);
//		} catch (IOException e) {
//			logger.error("write to xml string error:" + object, e);
//		}
//		return null;
//	}
//	
//	/**
//	 * 
//	 * @Title: fromXML
//	 * @Description: 
//	 * @param xml
//	 * @param clazz
//	 * @return
//	 * @author lihengjun
//	 * 修改时间： 2013年11月7日 下午12:51:10
//	 * 修改内容：新建
//	 */
//	public <T> T fromXML(String xml, Class<T> clazz) {
//		if (ObjectUtils.isEmpty(xml)) {
//			return null;
//		}
//
//		try {
//			return getXmlMapper().readValue(xml, clazz);
//		} catch (IOException e) {
//			logger.error("parse json string error:" + xml, e);
//			return null;
//		}
//	}
//	public XmlMapper getXmlMapper() {
//		return xmlMapper;
//	}

}