package cn.com.sinosoft.app.pdf;

import java.util.HashMap;
import java.util.Map;

import cn.com.sinosoft.app.utils.JacksonBinder;
/**
 * ģ������Ҫ��������ͼ ������
 * @ClassName: AbstractDocumentVo
 * @Description: ģ������Ҫ��������ͼ ������
 * @author lihengjun
 * �޸�ʱ�䣺 2013��11��5�� ����3:22:35
 * �޸����ݣ��½�
 */
public abstract class AbstractDocumentVo implements DocumentVo{
	/**
	 * ,���ģ��������,��ȡģ������map
	 * @Title: fillDataMap
	 * @Description:  ��ȡģ������map
	 * @return
	 * @author lihengjun
	 * �޸�ʱ�䣺 2013��11��5�� ����11:19:29
	 * �޸����ݣ��½�
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
