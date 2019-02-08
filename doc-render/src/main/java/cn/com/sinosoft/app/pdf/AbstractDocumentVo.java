package cn.com.sinosoft.app.pdf;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

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
	public Map fillDataMap(){
		Map<String, String> map = new HashMap<>();

		DocumentVo vo = this.getDocumentVo();
		try {
			map = BeanUtils.describe(vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return map;
	}

	private DocumentVo getDocumentVo() {
		return this;
	}

}
