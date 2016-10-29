package com.roden.java.util.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * http://www.cnblogs.com/ITtangtang/archive/2012/05/01/2477563.html
 */
public class Test {

    public static void main(String[] args) {
        //matches()判断字符串是否匹配某个表达式，"."表示任何一个字符
        p("abc".matches("..."));//true
        //将字符串"a2389a"中的数字用*替换，\d 表示“0--9”数字
        p("a2389a".replaceAll("\\d", "*"));//a****a
        //将任何是a--z的字符串长度为3的字符串进行编译，这样可以加快匹配速度
        Pattern p = Pattern.compile("[a-z]{3}");
        //进行匹配，并将匹配结果放在Matcher对象中
        Matcher m = p.matcher("abc");
        p(m.matches());//true
        //上面的三行代码可以用下面一行代码代替
        p("abc".matches("[a-z]{3}"));//true
        
    /*  
        .              任何字符
        a?             a一次或一次也没有
        a*             a零次或多次
        a+            a一次或多次
        a{n}?      a恰好 n 次
        a{n,}?       a至少n次 
        a{n,m}?   a至少n次，但是不超过m次
        */
      //初步认识. * + ?
        p("a".matches("."));//true
        p("aa".matches("aa"));//true
        p("aaaa".matches("a*"));//true
        p("aaaa".matches("a+"));//true
        p("".matches("a*"));//true
        p("aaaa".matches("a?"));//false
        p("".matches("a?"));//true
        p("a".matches("a?"));//true
        p("1232435463685899".matches("\\d{3,100}"));//true
        p("192.168.0.aaa".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));//false
        p("192".matches("[0-2][0-9][0-9]"));//true
        /*
         * [abc]                        a、b 或 c（简单类）
			[^abc]                      任何字符，除了 a、b 或 c（否定）			
			[a-zA-Z]                   a 到 z 或 A 到 Z，两头的字母包括在内（范围）			
			[a-d[m-p]]                a 到 d 或 m 到 p：[a-dm-p]（并集）			
			[a-z&&[def]]             d、e 或 f（交集）			
			[a-z&&[^bc]]             a 到 z，除了 b 和 c：[ad-z]（减去）			
			[a-z&&[^m-p]]            a 到 z，而非 m 到 p：[a-lq-z]（减去）
         */
      //范围
        p("a".matches("[abc]"));//true
        p("a".matches("[^abc]"));//false
        p("A".matches("[a-zA-Z]"));//true
        p("A".matches("[a-z]|[A-Z]"));//true
        p("A".matches("[a-z[A-Z]]"));//true
        p("R".matches("[A-Z&&[RFG]]"));//true
        
        /*
			\d                          数字：[0-9]
			
			\D                         非数字： [^0-9]
			
			\s                          空白字符：[ \t\n\x0B\f\r]
			
			\S                         非空白字符：[^\s]
			
			\w                         单词字符：[a-zA-Z_0-9]
			
			\W                        非单词字符：[^\w]
         */
        //认识\s \w \d \
        p("\n\r\t".matches("\\s(4)"));//false
        p(" ".matches("\\S"));//false
        p("a_8 ".matches("\\w(3)"));//false
        p("abc888&^%".matches("[a-z]{1,3}\\d+[&^#%]+"));//true
        p("\\".matches("\\\\"));//true
        
        /*
         * 边界匹配器

      ^                                          行的开头

      $                                          行的结尾

      \b                                        单词边界

      \B                                        非单词边界

      \A                                        输入的开头

      \G                                       上一个匹配的结尾

      \Z                                       输入的结尾，仅用于最后的结束符（如果有的话）

      \z                                       输入的结尾
         */
      //边界匹配
        p("hello sir".matches("^h.*"));//true
        p("hello sir".matches(".*ir$"));//true
        p("hello sir".matches("^h[a-z]{1,3}o\\b.*"));//true
        p("hellosir".matches("^h[a-z]{1,3}o\\b.*"));//false
        //空白行:一个或多个(空白并且非换行符)开头，并以换行符结尾
        p(" \n".matches("^[\\s&&[^\\n]]*\\n$"));//true      
        
    }
    
    public static void method(){
    	  /*
    	方法解析

    matches():匹配整个字符串

    find():匹配子字符串

    lookingAt():永远从整个字符串的开头开始匹配
    */
  //email
    p("asdsfdfagf@adsdsfd.com".matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+"));//true
    
    //matches() find() lookingAt()
    Pattern p = Pattern.compile("\\d{3,5}");
    Matcher m = p.matcher("123-34345-234-00");
    
    //将整个"123-34345-234-00"用正则表达式引擎查找匹配，当到第一个"-"不匹配了，就停止，
    //但不会将不匹配的"-"吐出来
    p(m.matches());
    //将不匹配的"-"吐出来
    m.reset();
    
    //1:当前面有p(m.matches());查找子字符串从"...34345-234-00"开始
    //将会是第1,2两个查到"34345"和"234" 后面2个查不到为false
    //2:当前面有p(m.matches());和m.reset();查找子字符串从"123-34345-234-00"开始
    //将为true,true,true,false
    p(m.find());
    p(m.start()+"---"+m.end());
    p(m.find());
    p(m.start()+"---"+m.end());
    p(m.find());
    p(m.start()+"---"+m.end());
    p(m.find());
    //要是没找到就会报异常java.lang.IllegalStateException
    //p(m.start()+"---"+m.end());
    
    p(m.lookingAt());
    p(m.lookingAt());
    p(m.lookingAt());
    p(m.lookingAt());
   
 
    }
    public static void replace(){
    	 //字符串替换
        //Pattern.CASE_INSENSITIVE大小写不敏感
        Pattern p = Pattern.compile("java",Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("java Java jAva ILoveJavA youHateJAVA adsdsfd");
        //存放字符串
        StringBuffer  buf = new StringBuffer();
        //计数奇偶数
        int i  = 0;
        while(m.find()){
            i++;
            if(i%2 == 0){
                m.appendReplacement(buf, "java");
            }else{
                m.appendReplacement(buf, "JAVA");
            }
        }
        //不加这句话，字符串adsdsfd将会被遗弃
        m.appendTail(buf);
        p(buf);
    }
    
    public static void group(){
    	//group分组,用()分组
        Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");
        String s = "123aa-34345bb-234cc-00";
        Matcher m = p.matcher(s);
        p(m.groupCount());//2组
        while(m.find()){
            p(m.group());//数字字母都有
            //p(m.group(1));//只有数字
            //p(m.group(2));//只有字母
        }
    }
    
    public static void p(Object o){
        System.out.println(o);
    }
}