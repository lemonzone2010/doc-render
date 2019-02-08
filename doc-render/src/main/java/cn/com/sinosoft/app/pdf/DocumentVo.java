package cn.com.sinosoft.app.pdf;

import java.util.Map;
/**
 * 模板中需要的数据视图
 * @ClassName: DocumentVo
 * @Description:模板中需要的数据视图
 * @author lihengjun
 * 修改时间： 2013年11月5日 下午3:21:54
 * 修改内容：新建
 */
public interface DocumentVo {
	/**
	 * 获取主键,用于记录日志
	 * @Title: findPrimaryKey
	 * @Description: 获取主键,用于记录日志
	 * @return
	 * @author lihengjun
	 * 修改时间： 2013年11月5日 下午3:00:10
	 * 修改内容：新建
	 */
	public String findPrimaryKey();
	/**
	 * 获取数据map
	 * @Title: fillDataMap
	 * @Description: 
	 * @return
	 * @author lihengjun
	 * 修改时间： 2013年11月5日 上午11:19:29
	 * 修改内容：新建
	 */
	public Map<String, Object> fillDataMap();
}
