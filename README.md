项目主页:http://git.oschina.net/lemonzone2010/doc-render

最近公司需要生成PDF,基于这个需求简单学习了下IText 和 flying saucer,对于这两个技术.我先简单介绍下:

Flying Saucer和iText介绍:

   A.  iText是一个生成PDF文档的开源java库，能够动态从XML或者数据库生成PDF，同时它具备PDF文档的绝大多数属性(比如加密……)，支持java，C#等。官网:http://www.itextpdf.com/

   B.  Flying Saucer(或者叫xhtmlrender project on java.net)是一个基于iText的开源java库，能够轻松的将html(带css2.1)生成pdf。 网站:http://code.google.com/p/flying-saucer/

基于这个两个技术,大致就有了以下思路方便的生成pdf:

编写HTML模板--->通过Flying Saucer和IText--->生成pdf

于是这里需要用到一个java模板工具freemarker

   C. FreeMarker是一个模版引擎，一个基于文本的模板输出工具（生成任意的HTML表单代码）。官网:http://freemarker.org/

范例说明:

项目主页:http://git.oschina.net/lemonzone2010/doc-render

1. 支持中文

    a.目录doc-render/src/test/resources/config/fonts中如果没有字体,需要手动复制系统中字体文件ARIALUNI.TTF到目录

    b.window系统字体路径:C:/Windows/Fonts/ARIALUNI.TTF

    c. html模板文档css字体设置:   font-family: Arial Unicode MS;

1. 能够加载图片,设置的默认图片路径 classpath:config/images/

1. 运行Junit测试类 TestPdfGenerator.testGenerate() 即可生成pdf,pdf生成路径见日志(doc-render/tmp/).

1. 由于生成pdf需要加载中文字体文件(一般字体文件>10M),本例中增加了资源池(最大资源数15),相关详细见ITextRendererObjectFactory.getObjectPool();经过简单测试能够支持:150个用户迭代10次

1. 模板html:[点击查看](doc-render/src/test/resources/config/templates/overseaAssistance.html)

1. pdf生成文件[点击查看](doc-render/tmp/1549647421978.pdf)

1. 基于maven构建,如果没有maven


项目主页:http://git.oschina.net/lemonzone2010/doc-render