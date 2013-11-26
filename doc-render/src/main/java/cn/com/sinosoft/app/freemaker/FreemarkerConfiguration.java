package cn.com.sinosoft.app.freemaker;

import java.io.File;
import java.io.IOException;

import cn.com.sinosoft.app.utils.ResourceLoader;
import freemarker.template.Configuration;
/**
 * freemark����
 * @ClassName: FreemarkerConfiguration
 * @Description: freemark����
 * @author lihengjun
 * �޸�ʱ�䣺 2013��11��5�� ����3:25:17
 * �޸����ݣ��½�
 */
public class FreemarkerConfiguration {

	private static Configuration config = null;

	/**
	 * ��ȡ FreemarkerConfiguration
	 * 
	 * @Title: getConfiguation
	 * @Description:
	 * @return
	 * @author lihengjun �޸�ʱ�䣺 2013��11��11�� ����5:27:32 �޸����ݣ��½�
	 */
	public static synchronized Configuration getConfiguation() {
		if (config == null) {
			setConfiguation();
		}
		return config;
	}
	/**
	 * ���� ����
	 * @Title: setConfiguation
	 * @Description: 
	 * @author lihengjun
	 * �޸�ʱ�䣺 2013��11��5�� ����3:25:42
	 * �޸����ݣ��½�
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