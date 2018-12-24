package com.hh.gridview_recyclerview.utils;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
	/**
	 * 正则表达式。
	 * 
	 * 正则表达式用于操作字符串数据
	 * 通过一些特定的符号来体现的
	 * 所以我们为了掌握正则表达式，必须学习一些符号
	 * 
	 * 代码虽然简化了，但是阅读性差
	 * -----------------------正则表达式对字符串的常见操作--------------------------------------------------------
	 * 
	 * 1，匹配。
	 * 			其实使用的就是String类中的matches方法
	 * 2，切割。
	 * 			其实使用的就是String类中的split方法
	 * 3，替换。
	 * 			其实使用的就是String类中的replaceAll方法
	 * 4，获取。
	 * @param args
	 * @throws  
	 */
	public static void main(String[] args)   {

		
//		checkQQ("0654534756");
//		functionDemo1("13170882739");
//		functionDemo2();
//		functionDemo3();
//		functionDemo4();
		try {
			ArrayList<String> getemal = getemalweb();
			for (String string : getemal) {
				
				System.out.println(string);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			
		}
	}
	/**
	 * 爬网页中的邮箱
	 * @return
	 * @throws IOException
	 */
	private static ArrayList<String> getemalweb() throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		//1,读取源文件。
//		BufferedReader buff = new BufferedReader(new FileReader("c:\\aa.html"));
		URL url = new URL("https://cloud.tencent.com/developer/news/228330");
		BufferedReader buff = new BufferedReader(new InputStreamReader(url.openStream()));
		
		//2，对读取的源文件数据进行规则匹配。从中获取符合规则的数据。
		String mail_regex = "\\w+@\\w+(\\.\\w+)+";
		Pattern P = Pattern.compile(mail_regex);
		String line = null;
		while((line = buff.readLine()) != null){
			Matcher m = P.matcher(line);
			while(m.find()){
				//3，将符合规则的数据储存到集合中
				list.add(m.group());
			}
		}
		return list;
	}
	/**
	 * 爬本地文件中的邮箱地址
	 * @return
	 * @throws IOException
	 */
	private static ArrayList<String> getemal() throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		//1,读取源文件。
		BufferedReader buff = new BufferedReader(new FileReader("c:\\aa.html"));
		//2，对读取的源文件数据进行规则匹配。从中获取符合规则的数据。
		String mail_regex = "\\w+@\\w+(\\.\\w+)+";
		Pattern P = Pattern.compile(mail_regex);
		String line = null;
		while((line = buff.readLine()) != null){
			Matcher m = P.matcher(line);
			while(m.find()){
				//3，将符合规则的数据储存到集合中
				list.add(m.group());
			}
		}
		return list;
	}
	/**
	 * 获取演示
	 * 将正则表规则进行对象的封装
	 * pattern p = Pattern.compile("a*b");
	 * 通过正则对象的matcher方法字符串相关联。获取要对字符串操作的对象matcher.
	 * Matcher m = m.matches("aaaaaaa");
	 * 通过Matcher 匹配器对象的方法对字符串进行操作。
	 * boolean b = m.matches();
	 */
	private static void functionDemo4() {
		String str = "da jia hao,ming tian fang jia!";
		String regex = "\\b[a-z]{3}\\b";
		//1将正则封装成对象。
		Pattern p = Pattern.compile(regex);
		//2通过正则对象获取匹配器对象。
		Matcher m = p.matcher(str);
		
		//使用Matcher 对象的方法对字符串进行操作
		//要获取三个字母组成的
		//查找。find（）；
		System.out.println(str);
		while(m.find()){
			System.out.println(m.group());
		}
		
	}

	/**
	 * 替换演示
	 * 
	 */
	private static void functionDemo3() {
		//叠词变一个
//		String str = "你哈哈哈是呵呵呵我嘻嘻的哇哇哇小噢噢噢噢苹啦啦啦果";
//		str = str.replaceAll("(.)\\1+", "$1");
//		System.out.println(str);
		
		
		String tel = "15180174308"; //151****4308
		tel = tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		System.out.println(tel);
		
	}

	/**
	 * 切割演示
	 * 注意“.”这个字符在正则表达式中代表任意字符，这时候要用“\\.”
	 */
	private static void functionDemo2() {
		//指定连词分割
		String str = "你A是AAAA我AA的AAA小AAA苹AAAAAAA果";
		String regex = "A+";
		//任意连词分割
//	String str = "你哈哈哈是呵呵呵我嘻嘻的哇哇哇小噢噢噢噢苹啦啦啦果";
//	String regex = "(.)\\1+";//组的概念
		//指定.分割
//	String str = "你.是.我.的.小.苹..果";
//	String regex = "\\.+";
	
		String[] hanzi = str.split(regex);
		for (String string : hanzi) {
			System.out.println(string);
		}
	}
	/**
	 * 演示匹配
	 */
	private static void functionDemo1(String tel) {
		//匹配手机号码是否正确
//		String regex = "1[3589][0-9]{9}";
		String regex = "1[3589]\\d{9}";
		boolean matches = tel.matches(regex);
		if(matches){
			System.out.println(tel +  "：号码正确");
		}else {
			System.out.println(tel +  "：号码错误");
		}
	}  

	/**
	 * 需求：定义一个功能对QQ号进行校验
	 * 要求：长度5~15位。只能是数字，0不能开头
	 * @param string
	 */
	private static void checkQQ(String qq) {
		String regex = "[1-9][0-9]{4,14}";
		boolean matches = qq.matches(regex);
		if(matches){
			System.out.println("恭喜你QQ号正确");
		}else{
			System.out.println("QQ号不合法");
		}
	}
}
