package lab3_Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;



public class ReturnSql implements SqlUtil{

	@Override
	public String query(User user) {
		String str = null;
		
		//获取Class
		Class c = user.getClass();
		if(!c.isAnnotationPresent(Table.class))
			return null;
		
		//获取表名
		Table table = (Table) c.getAnnotation(Table.class);
		String tableName = table.value();
		
		//获取表中所有字段
		Field[] fieldArray = c.getDeclaredFields(); 
		
		//遍历表中字段
		for (Field f: fieldArray) {
			//判断这个字段是否带有Column注解
			if(!f.isAnnotationPresent(Column.class))
				continue;
			//获取字段名
			String fieldName = f.getName();
			//获取字段值
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Object value = null;
			try {
				Method getMethod = c.getMethod(getMethodName);
				value = getMethod.invoke(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//拼接Sql语句
			if(value==null || (value instanceof Integer && (Integer)value==0)){  
                continue;  
            }
			if (value instanceof Integer) {
				str = "SELECT * FROM '" + tableName + "' WHERE '" + fieldName + "' = " + value;
			}
			else if (value instanceof Object) {
				str = "SELECT * FROM '" + tableName + "' WHERE '" + fieldName + "' LIKE '" + value + "'";
			}
			
		}
		return str + ";";
	}

	
	@Override
	public String insert(User user) {
        String str1 = "INSERT INTO ";
        String str2 = "VALUES (";
        
		//获取Class
		Class c = user.getClass();
		if(!c.isAnnotationPresent(Table.class))
			return null;
		
		//获取表名
		Table table = (Table) c.getAnnotation(Table.class);
		String tableName = table.value();
		str1 += "'" + tableName + "' (";
		
		//获取表中所有字段
		Field[] fieldArray = c.getDeclaredFields(); 
		
		//遍历表中字段
		for (int i = 0; i<fieldArray.length; i++) {
			Field f = fieldArray[i];
			//判断这个字段是否带有Column注解
			if(!f.isAnnotationPresent(Column.class))
				continue;
			//获取字段名
			String fieldName = f.getName();
			//获取字段值
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Object value = null;
			try {
				Method getMethod = c.getMethod(getMethodName);
				value = getMethod.invoke(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//拼接Sql语句
			if(value==null || (value instanceof Integer && (Integer)value==0)){  
                continue;  
            }
			if (i==fieldArray.length-1) {
				str1 += "'" + fieldName + "') ";
				if (value instanceof Integer) {
					str2 += value + ") ";
				}
				else if (value instanceof Object) {
					str2 += "'" + value + "') ";
				}
			}
			else{
				str1 += "'" + fieldName + "', ";
				if (value instanceof Integer) {
					str2 += value + ", ";
				}
				else if (value instanceof Object) {
					str2 += "'" + value + "', ";
				}
			}

		}
		return str1 + str2 + ";";
	}

	
    String tableName;
	@Override
	public String insert(List<User> users) {
		tableName = User.class.getAnnotation(Table.class).value();
		if (!User.class.isAnnotationPresent(Table.class)) 
        	return null;

	    Field[] fields = User.class.getDeclaredFields();
	    for (Field field : fields)
        {
        	field.setAccessible(true);
        }
		StringBuffer sql = new StringBuffer("INSERT INTO ");
        sql.append("'").append(tableName).append("'").append(" (");

        for (Field field : fields) 
        {
            Column column = field.getAnnotation(Column.class);
            sql.append("'").append(column.value()).append("', ");
        }
        sql.delete(sql.length() - 2, sql.length()).append(") ");
        sql.append(" VALUES (");
        for (User user : users) 
        {
            if (!User.class.isAnnotationPresent(Table.class))
            {
            	return null;
            }
            for (Field field : fields) 
            {
                Object value = null;
                try
                {
                    value = field.get(user);
                } 
                catch (IllegalAccessException e) 
                {
                    e.printStackTrace();
                }
                if (value == null)
                {
                	return null;
                }
                if (field.getType().equals(String.class))
                {
                	sql.append("'").append(value.toString()).append("'");
                }
                else
                {
                	sql.append(value.toString());	
                }
                sql.append(", ");
            }
            sql.delete(sql.length() - 2, sql.length()).append("), (");
        }
        sql.delete(sql.length() - 3, sql.length()).append(";");
        return sql.toString();
    	
	}

	
	@Override
	public String delete(User user) {
        String str = null;
		
		//获取Class
		Class c = user.getClass();
		if(!c.isAnnotationPresent(Table.class))
			return null;
		
		//获取表名
		Table table = (Table) c.getAnnotation(Table.class);
		String tableName = table.value();
		
		//获取表中所有字段
		Field[] fieldArray = c.getDeclaredFields(); 
		
		//遍历表中字段
		for (Field f: fieldArray) {
			//判断这个字段是否带有Column注解
			if(!f.isAnnotationPresent(Column.class))
				continue;
			//获取字段名
			String fieldName = f.getName();
			//获取字段值
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Object value = null;
			try {
				Method getMethod = c.getMethod(getMethodName);
				value = getMethod.invoke(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//拼接Sql语句
			if(value==null || (value instanceof Integer && (Integer)value==0)){  
                continue;  
            }
			if (value instanceof Integer) {
				str = "DELETE FROM '" + tableName + "' WHERE '" + fieldName + "' = " + value;
			}
			else if (value instanceof Object) {
				str = "DELETE FROM '" + tableName + "' WHERE '" + fieldName + "' = '" + value + "'";
			}
		}
		return str + ";";
	}

	@Override
	public String update(User user) {
		String str1 = "UPDATE ";
        String str2 = "WHERE ";
		//获取Class
		Class c = user.getClass();
		if(!c.isAnnotationPresent(Table.class))
			return null;
		
		//获取表名
		Table table = (Table) c.getAnnotation(Table.class);
		String tableName = table.value();
		str1 += "'" + tableName + "' SET ";
		
		//获取表中所有字段
		Field[] fieldArray = c.getDeclaredFields(); 
		
		//遍历表中字段
		for (int i = 0; i<fieldArray.length; i++) {
			Field f = fieldArray[i];
			//判断这个字段是否带有Column注解
			if(!f.isAnnotationPresent(Column.class))
				continue;
			//获取字段名
			String fieldName = f.getName();
			//获取字段值
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Object value = null;
			try {
				Method getMethod = c.getMethod(getMethodName);
				value = getMethod.invoke(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//拼接Sql语句
			if(value==null || (value instanceof Integer && (Integer)value==0)){  
                continue;  
            }
			if(fieldName.equals("id")) {
				str2 += "'" + fieldName + "' = " + value;
			}
			else{
				str1 += "'" + fieldName + "' = ";
				if (value instanceof Integer) {
					str1 += value + " ";
				}
				else if (value instanceof Object) {
					str1 += "'" + value + "' ";
				}
			}
			
		}
		return str1 + str2 + ";";
	}

}
