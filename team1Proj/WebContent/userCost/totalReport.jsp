

<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.jfree.chart.title.LegendTitle"%>
<%@page import="org.jfree.chart.title.TextTitle"%>
<%@page import="org.jfree.chart.axis.ValueAxis"%>
<%@page import="org.jfree.chart.axis.CategoryAxis"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.jfree.chart.*,org.jfree.chart.plot.*,org.jfree.chart.labels.*,
org.jfree.data.category.*,java.awt.*,org.jfree.ui.*,org.jfree.chart.renderer.category.BarRenderer3D,
org.jfree.chart.servlet.*,org.jfree.chart.plot.PlotOrientation,org.jfree.data.general.DatasetUtilities"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>3D柱状图查询</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
</head>

<body>
<table width="96%" border="0" cellpadding="4" cellspacing="1"  bgcolor="#464646" class="newfont03" align="center">
  <caption class="tablestyle_title">业主缴费管理-->缴费查询</caption>
  <tr>
  <td colspan="9">
   <div style="margin-top:2px; margin-left:auto; margin-right:auto; width:100%; height:auto;">
  <fieldset style="border:1px solid #476D90; width:90%">
     <legend>缴费金额统计-->按收费项目统计金额</legend>
      <div class="newfont03">
      
         <form action="${pageContext.request.contextPath}/fee?method=getFee"  method="post">
    <span style="float:left; margin-left:28px">
             缴费日期：
             从<input type="text" name="beginDate" class="text" style="width:154px" value="${requestScope.startDate }"}" onClick="WdatePicker()" />&nbsp;到&nbsp;<input type="text" name="endDate" class="text" style="width:154px" value="${requestScope.endDate }"}" onClick="WdatePicker()" />
            </span>
            <span  style="float:left; margin-left:30px">
                 <input type="submit" style="width:123px; height:20px; border:none; background-image:url(${pageContext.request.contextPath}/images/btn_bg.gif)" value="3D统计图"/>
            </span>
          </form>
      </div>
</fieldset>
</div>
<div style="margin-top:2px; margin-left:auto; margin-right:auto; width:90%; height:auto;">
    <!-- 显示jfreeChart统计图 -->
	<%-- <img src="${pageContext.request.contextPath}/${imgPath}" width="48%" />
  <img src="${pageContext.request.contextPath}/${imgPath1}" width="48%"  /> --%>

	
<%-- <% 
DefaultCategoryDataset dataset = new DefaultCategoryDataset();
 List<Object[]> ob= (List<Object[]>)request.getAttribute("obj");
 out.println(ob.size());
//ob=;
/* List<Object>   feeitems=new ArrayList<Object>(); 
List<String>   feeitems=new ArrayList<String>(); */
 //feeitems=(List)request.getAttribute("feeitems");
//out.print(obj.get(0)[0]); 
/* for(int i=0;i<ob.size();i++){
	//dataset.addValue(obj.get(i)[i], "深圳", feeitems[i]);
	Object[] o=ob.get(i);
	for(int a=0;a<o.length;a++){
	out.println(o[a]);
	}
} */


JFreeChart chart = ChartFactory.createBarChart3D("收费统计图",
                  "收费项目",
                  "金额(元)",
                  dataset,
                  PlotOrientation.VERTICAL,
                  false,
                  false,
                  false);
 //CategoryPlot plot = chart.getCategoryPlot();
 CategoryPlot plot = (CategoryPlot) chart.getPlot();
//设置网格背景颜色
plot.setBackgroundPaint(Color.white);
//设置网格竖线颜色
plot.setDomainGridlinePaint(Color.pink);
//设置网格横线颜色
plot.setRangeGridlinePaint(Color.pink);
 
//显示每个柱的数值，并修改该数值的字体属性
BarRenderer3D renderer = new BarRenderer3D();
renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
renderer.setBaseItemLabelsVisible(true);
 String filename = ServletUtilities.saveChartAsPNG(chart, 420, 300, null, session);
String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;
%>
<img src="<%= graphURL %>" width=420 height=300 border=0> --%>
<%-- <% 
   List<Object[]>   fees = new ArrayList<Object[]>();
   fees=(List<Object[]>)request.getAttribute("fees");
   float[][]  data=null;
   String[] rowKeys=null ;
   String[] columnKeys = {""};
    for(int i=0;i<fees.size();i++){
    	Object[]  obj=fees.get(i);
    	data[i][0]=(Float)obj[1];
    	rowKeys[i]=(String)obj[2];
    	
    	
    }
  //float[][]  data=(float[][])request.getAttribute("fee");
//double[][] data = new double[][] {{1230},{1110},{1120},{1210}};
//String[] rowKeys =(String[]) request.getAttribute("feeitems");

CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data); 
 


/* //创建主题样式  
StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
//设置标题字体  
standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
//设置图例的字体  
standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
//设置轴向的字体  
standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
//应用主题样式  
ChartFactory.setChartTheme(standardChartTheme);  */
/* JFreeChart chart = ChartFactory.createBarChart3D("水果销量统计图", 
                  "水果",
                  "销量",
                  dataset,
                  PlotOrientation.VERTICAL,
                  true,
                  true,
                  false); */
/* CategoryPlot plot = chart.getCategoryPlot();  

//设置网格背景颜色
plot.setBackgroundPaint(Color.white);
//设置网格竖线颜色
plot.setDomainGridlinePaint(Color.pink);
//设置网格横线颜色
plot.setRangeGridlinePaint(Color.pink);
 
//显示每个柱的数值，并修改该数值的字体属性
BarRenderer3D renderer = new BarRenderer3D();
renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
renderer.setBaseItemLabelsVisible(true);
 
//默认的数字显示在柱子中，通过如下两句可调整数字的显示
//注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
renderer.setItemLabelAnchorOffset(10D);
 
//设置每个地区所包含的平行柱的之间距离
renderer.setItemMargin(0.4);
plot.setRenderer(renderer);
 
//设置地区、销量的显示位置
//将下方的“肉类”放到上方
//plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
//将默认放在左边的“销量”放到右方
//plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
 
 
String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, null, session);
String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename; */
JFreeChart chart = ChartFactory.createBarChart3D(
		// 标题
				"缴费金额柱状图",
				// 横轴标题
				"收费项目",
				// 纵轴标题
				"金额（元）", dataset,
				// 图表方向
				PlotOrientation.VERTICAL, true, false, false);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// 柱状图的x轴
		CategoryAxis domainAxis = plot.getDomainAxis();
		// 设置x轴坐标上的字体
		domainAxis.setTickLabelFont(new Font("新宋体", Font.PLAIN, 13));
		// x轴坐标上的字颜色
		domainAxis.setTickLabelPaint(new Color(59, 0, 237));
		// 设置x轴上的标题的字体
		domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 14));
		// x轴标题内容颜色
		domainAxis.setLabelPaint(Color.RED);

		// 柱状图的y轴
		ValueAxis valueAxis = plot.getRangeAxis();
		// 设置y轴坐标上的字体
		valueAxis.setTickLabelFont(new Font("新宋体", Font.PLAIN, 13));
		// y轴坐标上的字颜色
		valueAxis.setTickLabelPaint(new Color(59, 0, 237));
		// 设置y轴上的标题的字体
		valueAxis.setLabelFont(new Font("黑体", Font.PLAIN, 14));
		// y轴标题内容颜色
		valueAxis.setLabelPaint(Color.RED);
		valueAxis.setAutoRange(true);

		// 设置距离图片左端距离,此时为10%
		domainAxis.setLowerMargin(0.1);
		// 设置距离图片右端距离,此时为百分之10
		domainAxis.setUpperMargin(0.1);

		domainAxis.setCategoryLabelPositionOffset(10);// 图表横轴与标签的距离(10像素)
		domainAxis.setCategoryMargin(0.2);// 横轴标签之间的距离10%
		valueAxis.setLowerMargin(0.0);
		valueAxis.setUpperMargin(0.1);
		// 标题区域
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("新宋体", Font.BOLD, 18));
		// 图例区域
		LegendTitle legendTitle = chart.getLegend();
		// 设置图例字体
		legendTitle.setItemFont(new Font("新宋体", Font.PLAIN, 12));
		// 图例字体清晰
		chart.setTextAntiAlias(false);
		// 设置图片背景色
		plot.setBackgroundPaint(Color.WHITE);
		// 显示每个柱的数值
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setItemMargin(0.1);
		renderer.setBaseItemLabelsVisible(true);
		// 默认的数字显示在柱子中，通过如下两句可调整数字的显示
		// 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);
		// 设置每个平行柱之间距离(组内柱子间隔为组宽的5%)
		renderer.setItemMargin(0.05);
		// 将样式呈现到柱形图上
		plot.setRenderer(renderer);
		String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, null, session);
		String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;
%>
 <img src="<%= graphURL %>" width=530 height=320 border=0> --%>
<%
DefaultCategoryDataset dataset = new DefaultCategoryDataset();
List<Object[]>   fees = new ArrayList<Object[]>();
fees=(List<Object[]>)request.getAttribute("fees");
for(int i=0;i<fees.size();i++){
	
	 float     data=(Float)fees.get(i)[1];
	// out.print(data);
	 String      name=(String)fees.get(i)[2];
	 //out.print(name);
	dataset.addValue(data, name, name);
	
}
/* //创建主题样式  
StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
//设置标题字体  
standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
//设置图例的字体  
standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
//设置轴向的字体  
standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
//应用主题样式  
ChartFactory.setChartTheme(standardChartTheme);  */

JFreeChart chart = ChartFactory.createBarChart3D(
		"缴费金额柱状图",
		// 横轴标题
		"收费项目",
		// 纵轴标题
		"金额（元）",
                  dataset,
                  PlotOrientation.VERTICAL,
                  true,
                  false,
                  false);
CategoryPlot plot = (CategoryPlot) chart.getPlot();
// 柱状图的x轴
CategoryAxis domainAxis = plot.getDomainAxis();
// 设置x轴坐标上的字体
domainAxis.setTickLabelFont(new Font("新宋体", Font.PLAIN, 13));
// x轴坐标上的字颜色
domainAxis.setTickLabelPaint(new Color(59, 0, 237));
// 设置x轴上的标题的字体
domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 14));
// x轴标题内容颜色
domainAxis.setLabelPaint(Color.RED);

//柱状图的y轴
		ValueAxis valueAxis = plot.getRangeAxis();
		// 设置y轴坐标上的字体
		valueAxis.setTickLabelFont(new Font("新宋体", Font.PLAIN, 13));
		// y轴坐标上的字颜色
		valueAxis.setTickLabelPaint(new Color(59, 0, 237));
		// 设置y轴上的标题的字体
		valueAxis.setLabelFont(new Font("黑体", Font.PLAIN, 14));
		// y轴标题内容颜色
		valueAxis.setLabelPaint(Color.RED);
		valueAxis.setAutoRange(true);

		
		
		// 标题区域
				TextTitle textTitle = chart.getTitle();
				textTitle.setFont(new Font("新宋体", Font.BOLD, 18));
				// 图例区域
				LegendTitle legendTitle = chart.getLegend();
				// 设置图例字体
				legendTitle.setItemFont(new Font("新宋体", Font.PLAIN, 12));
				// 图例字体清晰
				chart.setTextAntiAlias(false);
				// 设置图片背景色
				plot.setBackgroundPaint(Color.WHITE);
				// 显示每个柱的数值
				BarRenderer3D renderer = new BarRenderer3D();
				renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
				renderer.setItemMargin(0.1);
				renderer.setBaseItemLabelsVisible(true);
				// 默认的数字显示在柱子中，通过如下两句可调整数字的显示
				// 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
				renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
						ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
				renderer.setItemLabelAnchorOffset(10D);
				// 将样式呈现到柱形图上
				plot.setRenderer(renderer);
				
String filename = ServletUtilities.saveChartAsPNG(chart, 420, 300, null, session);
String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;
%>
<img src="<%= graphURL %>" width=420 height=300 border=0> 
<% 
DefaultPieDataset dataset2 = new DefaultPieDataset();
//List<Object[]>   fees = new ArrayList<Object[]>();
//fees=(List<Object[]>)request.getAttribute("fees");
for(int i=0;i<fees.size();i++){
	
	 float     data=(Float)fees.get(i)[1];
	// out.print(data);
	 String      name=(String)fees.get(i)[2];
	 //out.print(name);
	 dataset2.setValue(name,data);
	
}
    
   
    
    JFreeChart chart2 = ChartFactory.createPieChart3D("资金所占比例图",dataset2,true,true,false);
    
    
    //设置饼状图的 主标题字体
    chart2.setTitle(new TextTitle("资金所占比例图",new Font("微软雅黑",Font.BOLD,22)));
    
    PiePlot plot2 = (PiePlot) chart2.getPlot();
    //设置饼状图体里的的各个标签字体
    plot2.setLabelFont(new Font("微软雅黑",Font.BOLD,25));
    
    
 // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
 		plot2.setLabelGenerator(new StandardPieSectionLabelGenerator(
 				"{0}={1}({2})", NumberFormat.getNumberInstance(),
 				new DecimalFormat("0.00%")));
    
    //设置图表下方的图例说明字体
    chart2.getLegend().setItemFont(new Font("微软雅黑",Font.BOLD,25));
 // 设置饼形图是否是完整规则的圆
 		plot2.setCircular(true);
    String fileName = ServletUtilities.saveChartAsPNG(chart2,800,600,session);
    String url = request.getContextPath()+"/DisplayChart?filename="+fileName;
   %>



      <img src="<%=url %>" width="500" height="400">

  
</div>
  </td>
  </tr>
  </table>
</body>
</html>
