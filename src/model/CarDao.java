package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
public class CarDao {
	public  Car  getCarDetailById(int carid){
		  Car car=null;
		  try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=UTF8","root","root");
			QueryRunner run = new QueryRunner();
			ResultSetHandler<Car> h = new BeanHandler<Car>(Car.class);
			car= run.query(connection, "select * from car  where carid=?",carid, h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return car;
	}
	public  List<Car>  listAllCars(){
		  List<Car>  cars=new ArrayList<>();
		  try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=UTF8","root","root");
			
			QueryRunner run = new QueryRunner();
			ResultSetHandler<List<Car>> h = new BeanListHandler<Car>(Car.class);
			cars= run.query(connection, "select * from car order by carid desc", h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  return cars;
	}
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
	public boolean  addCar(Car c) {
		boolean result=false;
		  try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=UTF-8","root","root");
				
				QueryRunner run = new QueryRunner();
//				int count=run.update(connection,"insert into  car(pinpaiming,xilie,shoujia,pailiang,gonglishu,yanse)  values(?,?,?,?,?,?)",c.getPinpaiming(),c.getXilie(),c.getShoujia(),c.getPailiang(),c.getGonglishu(),c.getYanse());
				int count=run.update(connection,"insert into  car(pinpaiming,xilie,shoujia,pailiang,gonglishu,yanse,zhaopian)  values(?,?,?,?,?,?,?)",c.getPinpaiming(),c.getXilie(),c.getShoujia(),c.getPailiang(),c.getGonglishu(),c.getYanse(),c.getZhaopian());
				result=count>0?true:false;
			} catch (Exception e) {
				e.printStackTrace();
			}	  
		return result;
	}

	public boolean  updateCar(Car c) {
		boolean result=false;
		  try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?useUnicode=true&characterEncoding=UTF-8","root","root");
				
				QueryRunner run = new QueryRunner();
				int count=run.update(connection,"update   car  set  pinpaiming=?,xilie=?,shoujia=?,pailiang=?,gonglishu=?,yanse=?  where carid=?",c.getPinpaiming(),c.getXilie(),c.getShoujia(),c.getPailiang(),c.getGonglishu(),c.getYanse(),c.getCarid());
				result=count>0?true:false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			  
		return result;
	}

}
