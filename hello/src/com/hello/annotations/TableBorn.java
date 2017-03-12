package com.hello.annotations;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class TableBorn {
	
	private String packet;
	
	public TableBorn(String packet){
		this.packet=packet;
	}
	
	public void bornTable(){
		if(packet.indexOf("/")<=0) return;
		File f=new File(this.getClass().getClassLoader().getResource(packet).toString().replaceAll("file:/", "/"));
		if(f.isDirectory()){
			for(File file:f.listFiles()){
				String name=file.getName();
				int len=name.lastIndexOf("/");
				born(packet.replace("/", ".")+"."+name.substring(len+1).replaceAll(".class", ""));
			}
		}
	}
	
	public static void main(String[] args) {
		new TableBorn("com/hello/entity").bornTable();
	}
	
	public void born(String classpath){
		try {
			String tableName="";
			Class cla=Class.forName(classpath);
			Object obj=cla.newInstance();
			//获取类名注解==表名
			Annotation t=cla.getDeclaredAnnotation(Table.class);
			if(t!=null){
				String sql="create table %s(";
				Table table=(Table) t;
				if("".equals(table.name())){
					String claPath=cla.toString();
					tableName=claPath.substring(claPath.lastIndexOf(".")+1);//默认是类名
				}else{
					tableName=table.name();
				}
				sql=String.format(sql, tableName);
				//获取属性注解==字段
				Field[] fields=cla.getDeclaredFields();
				for (Field field : fields) {
					String columnName=field.getName();//默认是属性名
					String type=convertType(field.getType().getTypeName());//基本类型
					Annotation f=field.getDeclaredAnnotation(Column.class);
					if(f==null) continue;
					Column column = (Column) f;
					if (!"".equals(column.name())) {
						columnName = column.name();
					}
					//TODO 主键
					int length = column.length();
					if(length==0){
						if(type.equals("varchar")){
							length=255;
						}else if(type.equals("int")||type.equals("long")){
							length=11;
						}
					} 
					String ifnull = column.ifNull() ? "default NULL" : "NOT NULL";
					if ("short/int/long/double".indexOf(type) > -1)
						ifnull = "NOT NULL default 0";
					if(column.isKey()) ifnull="NOT NULL PRIMARY KEY AUTO_INCREMENT";
					sql +=columnName+" "+type+"("+length+") "+ifnull;
					String comment = column.comment();
					if(!comment.equals("")) sql +=" COMMENT '"+comment+"'";
					sql+=",";
				}
				sql=sql.substring(0, sql.length()-1).concat(" ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
				System.out.println(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 转化基本类型为数据库类型
	 * @param original
	 * @return
	 */
	public String convertType(String original){
		if("short".equals(original)){
			return "short";
		}else if("byte".equals(original)){
			return "";
		}else if("int".equals(original)){
			return "int";
		}else if("long".equals(original)){
			return "int";
		}else if("float".equals(original)){
			return "float";
		}else if("double".equals(original)){
			return "double";
		}else if("java.lang.String".equals(original)){
			return "varchar";
		}else if("时间".equals(original)){
			return "datetime";
		}else if("char".equals(original)){
			return "char";
		}else if("".equals(original)){
			return "";
		}else{
			return "";
		}
	}
}
