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

		// ģ������
		OverseaVo overseaVo = new OverseaVo();

		overseaVo.setPolicyNo("1234567890123456");
		overseaVo.setHolderName("������123������123");
		overseaVo.setInsuredName("������123������123������123������123");
		overseaVo.setBeneficiaryName("��������������");
		overseaVo.setBranchName("����");
		overseaVo.setCompanyName("�����ֹ�˾");
		overseaVo.setDestination("Ӣ��,����˹,����,������,����˹С��");
		overseaVo.setHolderAdress("�������ݶ���ֽ��ڴ��14���й����ٹ㳡xxx��x101��");
		overseaVo.setHolderPostCode("123456");
		overseaVo.setInsuredBirthday("2013-11-10");
		overseaVo.setInsuredIDNo("123456789012345678");
		overseaVo.setInsuredName("���¾�������");
		overseaVo.setInsuredPassportNo("���Ի��պ���123456789");
		overseaVo.setInsuredPhone("13112345678");
		overseaVo.setInsuredPingyinName("aixinjuluoqixing");
		overseaVo.setInsuredSex("Ů");
		overseaVo.setIssueDate("2013-11-12");
		overseaVo.setPeriod("ʮһ��");
		overseaVo.setPremium("1009.00");
		overseaVo.setRelation("��Ů");
		overseaVo.setRemarks("����һ�Ų��Ա���,��Ϊ����,ѧϰ����,����ת��");
		overseaVo.setAccidentalSumInsured("150000");
		overseaVo.setEmergencySumInsured("500000");
		overseaVo.setMedicalSumInsured("220000");
		overseaVo.setImagePath(ResourceLoader.getPath("config/images"));

		// classpath ��ģ��·��
		String template = "config/templates/overseaAssistance.html";
		// classpath ·��
		String outputFileClass = ResourceLoader.getPath("");
		// ����pdf·��
		outputFile = outputFile == null ? new File(outputFileClass)
				.getParentFile().getParent()
				+ "/tmp/"
				+ System.currentTimeMillis() + ".pdf" : outputFile;

		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// ����pdf
		pdfGenerator.generate(template, overseaVo, outputFile);

		System.err.println(" \n pdf���ɳɹ�  IS OK path=\n" + outputFile);
		System.err.println("��ʱtime=" + (System.currentTimeMillis() - start)
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
			System.err.println(" \n pdf����ʧ��");
			ex.printStackTrace();
		}
		
		File file = new File(outputFile);
		
		assertNotNull("����pdf�ļ�Ϊ��", file);
		assertTrue("pdf�ļ�������", file.exists());
//		assertTrue("pdf�����ļ���С����", file.getFreeSpace()>178000);
		
	}
}