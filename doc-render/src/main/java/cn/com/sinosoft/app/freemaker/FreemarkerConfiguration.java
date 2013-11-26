package cn.com.sinosoft.app.freemaker;

import java.io.File;
import java.io.IOException;

import cn.com.sinosoft.app.utils.ResourceLoader;
import freemarker.template.Configuration;
/**
 * freemark配置
 * @ClassName: FreemarkerConfiguration
 * @Description: freemark配置
 * @author lihengjun
 * 修改时间： 2013年11月5日 下午3:25:17
 * 修改内容：新建
 */
public class FreemarkerConfiguration {

	private static Configuration config = null;

	/**
	 * 获取 FreemarkerConfiguration
	 * 
	 * @Title: getConfiguation
	 * @Description:
	 * @return
	 * @author lihengjun 修改时间： 2013年11月11日 下午5:27:32 修改内容：新建
	 */
	public static synchronized Configuration getConfiguation() {
		if (config == null) {
			setConfiguation();
		}
		return config;
	}
	/**
	 * 设置 配置
	 * @Title: setConfiguation
	 * @Description: 
	 * @author lihengjun
	 * 修改时间： 2013年11月5日 下午3:25:42
	 * 修改内容：新建
	 */
	private static void setConfiguation() {
		config = new Configuration();
		String path = ResourceLoader.getPath("");
		System.out.println("path="+path);
		try {
			config.setDirectoryForTemplateLoading(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}