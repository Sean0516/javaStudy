package com.string;

/**
 * Created by Sean on 2018/4/13.
 */
public class StringMethod {
    public static void main(String[] args) {
/**
 * String
 */
        boolean result;
        String str1;
        String str = "Test";
//        concat(String str)将指定的字符串连接到该字符串的末尾
        str1 = str.concat("ssd");

//contains(CharSequence s)  当且仅当此字符串包含指定的char值序列时才返回true
        result = str.contains("test");

//        endsWith(String suffix)   测试此字符串是否以指定的后缀结尾
        result = str.endsWith("ss");

//        startsWith(String prefix)  测试此字符串是否以指定的前缀开头

        result = str.startsWith("");

//        isEmpty()   如果，且仅当 length()为 0 返回 true
        result = str.isEmpty();

//        matches(String regex) 告诉这个字符串是否匹配给定的正则
        result = str.matches("");

//        replace(char oldChar, char newChar)返回从替换所有出现的导致一个字符串 oldChar在此字符串 newChar

        str1 = str.replace("t", "ss");

//        replaceAll(String regex, String replacement)  用给定的替换替换与给定的 regular expression匹配的此字符串的每个子字符串。
        str1 = str.replaceAll("", "");

//        replaceFirst(String regex, String replacement)        用给定的替换替换与给定的 regular expression匹配的此字符串的第一个子字符串。
        str1 = str.replaceFirst("", "");

//        substring(int beginIndex)        返回一个字符串，该字符串是此字符串的子字符串。
        str1 = str.substring(2);
        str1 = str.substring(1, 2);

//        toCharArray()将此字符串转换为新的字符数组
        char[] chars = str.toCharArray();

//        toLowerCase()        将所有在此字符 String使用以小写规则
        str1 = str.toLowerCase();
//        toUpperCase()      将所有在此字符 String使用大写规则。
        str1 = str.toUpperCase();

//        trim()        返回一个字符串，其值为此字符串，并删除任何前导和尾随空格
        str1 = str.trim();

//        valueOf(char c)  类型转换  各种类型的数据
        str1 = String.valueOf(chars);


        /**
         * StringBuffer
         */

//        append(Object o)         将参数的字符串表示附加到序列中。
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("ss");

//        capacity() 返回当前容量
        stringBuffer.capacity();

//        insert(int offset, boolean b)        在此序列中插入参数
        stringBuffer.insert(0,"test");

//        replace(int start, int end, String str)
        stringBuffer.replace(1,2,"xs");

//        reverse()导致该字符序列被序列的相反代替
        stringBuffer.reverse();

//        substring(int start)        返回一个新的 String ，其中包含此字符序列中当前包含的字符的子序列。
       str1= stringBuffer.substring(1);

// string builder  和string buffer 方法一样


    }
}
