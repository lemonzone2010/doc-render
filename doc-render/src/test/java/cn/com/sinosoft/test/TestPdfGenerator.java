package cn.com.sinosoft.test;


import java.io.File;

import junit.framework.TestCase;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import cn.com.sinosoft.app.freemaker.HtmlGenerator;
import cn.com.sinosoft.app.pdf.PdfDocumentGenerator;
import cn.com.sinosoft.app.pdf.bean.OverseaVo;
import cn.com.sinosoft.app.pdf.exception.DocumentGeneratingException;
import cn.com.sinosoft.app.utils.ResourceLoader;

public class TestPdfGenerator extends TestCase {
	private static HtmlGenerator htmlGenerator = new HtmlGenerator();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String logConfigPath = ResourceLoader.getPath("log4j.properties");
		PropertyConfigurator.configure(logConfigPath);
	}

	public void test(String outputFile) throws DocumentGeneratingException {

		long start = System.currentTimeMillis();

		// 模板数据
		OverseaVo overseaVo = new OverseaVo();

		overseaVo.setPolicyNo("1234567890123456");
		overseaVo.setHolderName("丽丽张123丽丽张123");
		overseaVo.setInsuredName("丽丽张123丽丽张123丽丽张123丽丽张123");
		overseaVo.setBeneficiaryName("测试受益人姓名");
		overseaVo.setBranchName("北京");
		overseaVo.setCompanyName("科索沃公司");
		overseaVo.setDestination("英国,俄罗斯,冰岛,日内瓦,威尼斯小镇");
		overseaVo.setHolderAdress("北京市屋顶后街金融大街14号中国人寿广场xxx曾x101室");
		overseaVo.setHolderPostCode("123456");
		overseaVo.setInsuredBirthday("2013-11-10");
		overseaVo.setInsuredIDNo("123456789012345678");
		overseaVo.setInsuredName("爱新觉罗启星");
		overseaVo.setInsuredPassportNo("测试护照号码123456789");
		overseaVo.setInsuredPhone("13112345678");
		overseaVo.setInsuredPingyinName("aixinjuluoqixing");
		overseaVo.setInsuredSex("女");
		overseaVo.setIssueDate("2013-11-12");
		overseaVo.setPeriod("十一年");
		overseaVo.setPremium("1009.00");
		overseaVo.setRelation("子女");
		overseaVo.setRemarks("这是一张测试保单,仅为测试,学习所用,请勿转载");
		overseaVo.setAccidentalSumInsured("150000");
		overseaVo.setEmergencySumInsured("500000");
		overseaVo.setMedicalSumInsured("220000");
		overseaVo.setImagePath(ResourceLoader.getPath("config/images"));

		// classpath 中模板路径
		String template = "config/templates/overseaAssistance.html";
		// classpath 路径
		String outputFileClass = ResourceLoader.getPath("");
		// 生成pdf路径
		outputFile = outputFile == null ? new File(outputFileClass)
				.getParentFile().getParent()
				+ "/tmp/"
				+ System.currentTimeMillis() + ".pdf" : outputFile;

		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, overseaVo, outputFile);

		System.err.println(" \n pdf生成成功  IS OK path=\n" + outputFile);
		System.err.println("耗时time=" + (System.currentTimeMillis() - start)
				/ 1000);

	}

	@Test
	public void testGenerate() {
		String outputFileClass = ResourceLoader.getPath("");
		String outputFile = new File(outputFileClass)
		.getParentFile().getParent()
		+ "/tmp/"
		+ System.currentTimeMillis() + ".pdf" ;
		try {
			test(outputFile);
		} catch (Exception ex) {
			System.err.println(" \n pdf生成失败");
			ex.printStackTrace();
		}
		
		File file = new File(outputFile);
		
		assertNotNull("生成pdf文件为空", file);
		assertTrue("pdf文件不存在", file.exists());
//		assertTrue("pdf生成文件大小错误", file.getFreeSpace()>178000);
		
	}
}