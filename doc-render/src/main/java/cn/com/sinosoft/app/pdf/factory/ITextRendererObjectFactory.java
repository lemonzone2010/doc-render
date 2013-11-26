package cn.com.sinosoft.app.pdf.factory;

import java.io.File;
import java.io.IOException;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import cn.com.sinosoft.app.utils.ResourceLoader;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
/**
 * ITextRenderer���󹤳�,�ṩ����,�����������弯(��С20M),�����Ӷ����
 * @ClassName: ITextRendererObjectFactory
 * @Description:
 * @author lihengjun
 * �޸�ʱ�䣺 2013��11��13�� ����3:00:20
 * �޸����ݣ��½�
 */
public class ITextRendererObjectFactory extends
		BasePoolableObjectFactory {
	private static GenericObjectPool itextRendererObjectPool = null;

	@Override
	public Object makeObject() throws Exception {
		ITextRenderer renderer = createTextRenderer();
		return renderer;
	}
	/**
	 * ��ȡ�����,ʹ�ö��󹤳� ���ṩ����,�ܹ�֧�� 500�߳� ����10
	 * @Title: getObjectPool
	 * @Description: ��ȡ�����
	 * @return GenericObjectPool
	 * @author lihengjun
	 * �޸�ʱ�䣺 2013��11��13�� ����2:15:15
	 * �޸����ݣ��½�
	 */
	public static GenericObjectPool getObjectPool(){
		synchronized (ITextRendererObjectFactory.class) {
			if(itextRendererObjectPool==null){
				itextRendererObjectPool = new GenericObjectPool(
						new ITextRendererObjectFactory());
				GenericObjectPool.Config config = new GenericObjectPool.Config();
				config.lifo = false;
				config.maxActive = 15;
				config.maxIdle = 5;
				config.minIdle = 1;
				config.maxWait = 5 * 1000;
				itextRendererObjectPool.setConfig(config);
			}
		}
		
		return itextRendererObjectPool;
	}

	/**
	 * ��ʼ��
	 * 
	 * @Title: initTextRenderer
	 * @Description:
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 * @author lihengjun �޸�ʱ�䣺 2013��11��13�� ����10:39:39 �޸����ݣ��½�
	 */
	public static synchronized ITextRenderer createTextRenderer()
			throws DocumentException, IOException {
		ITextRenderer renderer = new ITextRenderer();
		ITextFontResolver fontResolver = renderer.getFontResolver();
		addFonts(fontResolver);
		return renderer;
	}

	/**
	 * �������
	 * 
	 * @Title: addFonts
	 * @Description:
	 * @param fontResolver
	 * @throws DocumentException
	 * @throws IOException
	 * @author lihengjun �޸�ʱ�䣺 2013��11��5�� ����1:37:57 �޸����ݣ��½�
	 */
	public static ITextFontResolver addFonts(ITextFontResolver fontResolver)
			throws DocumentException, IOException {
		// Font fontChinese = null;
		// BaseFont bfChinese = BaseFont.createFont("STSong-Light",
		// "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		// fontChinese = new Font(bfChinese, 12, Font.NORMAL);

		File fontsDir = new File(ResourceLoader.getPath("config/fonts"));
		if (fontsDir != null && fontsDir.isDirectory()) {
			File[] files = fontsDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f == null || f.isDirectory()) {
					break;
				}
				fontResolver.addFont(f.getAbsolutePath(), BaseFont.IDENTITY_H,
						BaseFont.NOT_EMBEDDED);
			}
		}
		return fontResolver;
	}
}
