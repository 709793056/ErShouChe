package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import model.User;

public class UserDao {
	
	/**
	 * 杩欐槸鏌ヨ鑾峰緱鎵�鏈夎溅杈嗙殑dao鏂规硶
	 * @return
	 */
	public  List<User>  listAllUsers(){
		  List<User>  users=new ArrayList<>();
		  try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=UTF-8","root","root");
			
			QueryRunner run = new QueryRunner();
			ResultSetHandler<List<User>> h = new BeanListHandler<User>(User.class);
			users= run.query(connection, "select * from user", h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  return users;
	}
	public  List<User>  listAllCars(){
		  List<User>  cars=new ArrayList<>();
		  try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=UTF8","root","root");
			
			QueryRunner run = new QueryRunner();
			ResultSetHandler<List<User>> h = new BeanListHandler<User>(User.class);
			cars= run.query(connection, "select * from car order by carid desc", h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  return cars;
	}
	/**
	 * 杩欐槸鍒犻櫎杞﹁締淇℃伅鐨刣ao鏂规硶
	 * @param carid
	 * @return
	 */
	public boolean  deleteCar(int carid) {
		boolean result=false;
		  try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=UTF-8","root","root");
				
				QueryRunner run = new QueryRunner();
				int count=run.update(connection,"delete from car where carid=?",carid);
				result=count>0?true:false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			  
		return result;
	}
	
	/**
	 * 杩欐槸娣诲姞杞﹁締淇℃伅鐨刣ao鏂规硶
	 * @param carid
	 * @return
	 */
	public boolean  addUser(User c) {
		boolean result=false;
		  try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=UTF-8","root","root");
				
				QueryRunner run = new QueryRunner();
//				int count=run.update(connection,"insert into  car(pinpaiming,xilie,shoujia,pailiang,gonglishu,yanse)  values(?,?,?,?,?,?)",c.getPinpaiming(),c.getXilie(),c.getShoujia(),c.getPailiang(),c.getGonglishu(),c.getYanse());
				int count=run.update(connection,"insert into  car(xingming,xingbie,nianling,dianhuahaoma,dianziyoujian,zhaopian)  values(?,?,?,?,?,?,?)",c.getXingming(),c.getXingbie(),c.getNianling(),c.getDianhuahaoma(),c.getDianziyoujian(),c.getZhaopian());
				result=count>0?true:false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			  
		return result;
	}
	/**
	 * 杩欐槸淇敼杞﹁締淇℃伅鐨刣ao鏂规硶
	 * @param carid
	 * @return
	 */
	public boolean  updateCar(User c) {
		boolean result=false;
		  try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=UTF-8","root","root");
				
				QueryRunner run = new QueryRunner();
				int count=run.update(connection,"update   car  set  pinpaiming=?,xilie=?,shoujia=?,pailiang=?,gonglishu=?,yanse=?  where carid=?",c.getXingming(),c.getXingbie(),c.getNianling(),c.getDianhuahaoma(),c.getDianziyoujian(),c.getUserid());
				result=count>0?true:false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			  
		return result;
	}

}