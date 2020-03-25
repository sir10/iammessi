package lab2_ReadFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.io.IOException;

public class ReadFile {
	
	//通过BufferedReader读入文件
	public void readIn_bf(String path) throws IOException {
		
		File file = new File(path);
		BufferedReader br = null;
		Map<String,Integer> map = new TreeMap<String,Integer>();
		try 
		{
			br=new BufferedReader(new FileReader(file));
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line="";
		int cnt=0;
		List<String> l = new ArrayList<String>();
		try
	    {
            while ((line = br.readLine()) != null)
            {
            	//数组里面每个元素是一个单词
            	String str[]=line.split(" "); 
            	
            	//数据处理
            	
        		//将所有的大写转为小写并除去所有符号
        		for(int i=0;i<str.length;i++) {
        			str[i] = str[i].toLowerCase();
        			String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
        			String x = "";
        			str[i] = str[i].replaceAll(regEx,x);
        			l.add(str[i]);
        		}
            }
//            System.out.println(l.size());
            
          //统计词频
            ii:
        		for(int i=0; i<l.size(); i++) {
        			
        			//判断之前是否出现过这个字符串
        			for(int k=0;k<i;k++) {
        				if(l.get(i).equals(l.get(k)))
        					continue ii;
        			}
        			
        			//count此字符串出现过的次数
        			int temp = 1;
        			for(int j=i+1; j<l.size(); j++) {
        								
        				if(!l.get(i).equals(l.get(j))) {
        					
        				}
        				else {
        					temp++;
        				}	
        			}
        			//把字符串添加入Map
        			map.put((String) l.get(i), temp);	
        		}
            
    		
            Map<String, Integer> resultMap = sortMapByValue(map);    //按Key进行排序
            print(resultMap,"BufferedReader_output.txt");	
        
	    }
	    catch (IOException e)
        {
            e.printStackTrace();
        }
    }
	
	//通过InputStream读入文件
	public void readIn_is(String path) throws IOException {
		
		File file = new File(path);
		Map<String,Integer> map = new TreeMap<String,Integer>();
		List<String> l = new ArrayList<String>();
		InputStream is = new FileInputStream(file);
		StringBuffer sb = null;
		try {
			sb = new StringBuffer();
			int temp = 0;
		    do {
			    temp = is.read();
			    if(temp!=-1) {
				    sb.append((char)temp);
			    }
		    }while(temp!=-1);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			is.close();
		}
			
		String str[]=sb.toString().split(" ");
//		System.out.println(str.length);
		
		//数据处理
    	
		//将所有的大写转为小写并除去所有符号
		for(int i=0;i<str.length;i++) {
			str[i] = str[i].toLowerCase();
			String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
			String x = "";
			str[i] = str[i].replaceAll(regEx,x);
			l.add(str[i]);
		}
    
//    System.out.println(l.size());
    
    //统计词频
    ii:
		for(int i=0; i<l.size(); i++) {
			
			//判断之前是否出现过这个字符串
			for(int k=0;k<i;k++) {
				if(l.get(i).equals(l.get(k)))
					continue ii;
			}
			
			//count此字符串出现过的次数
			int temp = 1;
			for(int j=i+1; j<l.size(); j++) {
								
				if(!l.get(i).equals(l.get(j))) {
					
				}
				else {
					temp++;
				}	
			}
			//把字符串添加入Map
			map.put((String) l.get(i), temp);	
		}
    
	
    Map<String, Integer> resultMap = sortMapByValue(map);    //按Key进行排序
    print(resultMap,"InputStream_output.txt");
	    
		
	}
	
		
	//排序
	public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
	
	//输出结果
	private static void print(Map<String, Integer> m, String path) {
		Set<Map.Entry<String,Integer>> set = m.entrySet();
		Iterator<Map.Entry<String,Integer>> iter = set.iterator();
		File file = new File("E:/"+path);
        try {
   	     file.createNewFile();
   	     FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
		 BufferedWriter bw = new BufferedWriter(fileWriter);
         while(iter.hasNext()) {
			Map.Entry<String, Integer> ele = iter.next();
			String temp = ele.getKey()+" "+ele.getValue()+"\n";
			bw.write(temp);
		}
			bw.close();			
        }catch(IOException e){
   	       e.printStackTrace();
        }

	}
	
	
	public static void main(String[] args) throws IOException
	{
		ReadFile L=new ReadFile();
		String path="E:/了不起的盖茨比英文.txt";
		L.readIn_bf(path);
		L.readIn_is(path);
	}
	
}

class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Entry<String, Integer> me1, Entry<String, Integer> me2) {

        return -me1.getValue().compareTo(me2.getValue());
    }
}