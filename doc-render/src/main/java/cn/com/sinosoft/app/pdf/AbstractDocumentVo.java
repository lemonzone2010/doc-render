package cn.com.sinosoft.app.pdf;

import java.util.HashMap;
import java.util.Map;

import cn.com.sinosoft.app.utils.JacksonBinder;
/**
 * 模板中需要的数据视图 抽象类
 * @ClassName: AbstractDocumentVo
 * @Description: 模板中需要的数据视图 抽象类
 * @author lihengjun
 * 修改时间： 2013年11月5日 下午3:22:35
 * 修改内容：新建
 */
public abstract class AbstractDocumentVo implements DocumentVo{
	/**
	 * ,填充模板中数据,获取模板数据map
	 * @Title: fillDataMap
	 * @Description:  获取模板数据map
	 * @return
	 * @author lihengjun
	 * 修改时间： 2013年11月5日 上午11:19:29
	 * 修改内容：新建
	 */
	public Map<String, Object> fillDataMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		DocumentVo vo = this.getDocumentVo();
		map = JacksonBinder.buildNonDefaultBinder().convertValue(vo, HashMap.class);
		
		return map;
	}
	
	private DocumentVo getDocumentVo() {
		return this;
	}

}
