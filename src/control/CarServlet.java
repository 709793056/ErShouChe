package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import model.Car;
import model.CarDao;

@WebServlet("/CarServlet")
@MultipartConfig //閲囩敤娴佺殑鏂瑰紡鎻愪氦琛ㄥ崟鍐呭
public class CarServlet extends HttpServlet {
	private CarDao  dao=new CarDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//鍙璋冪敤CarServlet 閮戒細杩涘叆杩欎釜鏂规硶锛� 鐒跺悗鍐嶆牴鎹敤鎴蜂紶杩囨潵鐨刴ethod鍙傛暟鍋氬垎娴�
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		switch (method) {
			case "listAll":
			{
				System.out.println("鏌ョ湅鎵�鏈夌殑");
				//鏌ヨ鏁版嵁搴撹幏寰楁墍鏈変簩鎵嬭溅杈嗕俊鎭劧鍚庤繑鍥炲埌椤甸潰涓婃樉绀�
				List<Car> cars=dao.listAllCars();
				
				//璁叉煡璇㈢殑鏁版嵁瀛樺偍鍒皉equest鑼冨洿鍐�
				request.setAttribute("cars", cars);
				
				//鏁版嵁瀛樺偍瀹屾瘯锛岃烦杞埌椤甸潰涓婂噯澶囧湪椤甸潰涓婃樉绀烘墍鏈変簩鎵嬭溅淇℃伅
				request.getRequestDispatcher("carList.jsp").forward(request, response);
				
				
				break;
			}
			case "add":
			{
				System.out.println("娣诲姞");
				//1.鑾峰彇琛ㄥ崟椤甸潰涓婄敤鎴峰～鍐欑殑鏁版嵁
				String pinpaiming=request.getParameter("pinpaiming");//ctrl+alt+ 涓嬬澶�
				String xilie=request.getParameter("xilie");//ctrl+alt+ 涓嬬澶�
				String shoujia=request.getParameter("shoujia");//ctrl+alt+ 涓嬬澶�
				String gonglishu=request.getParameter("gonglishu");//ctrl+alt+ 涓嬬澶�
				String pailiang=request.getParameter("pailiang");//ctrl+alt+ 涓嬬澶�
				String yanse=request.getParameter("yanse");//ctrl+alt+ 涓嬬澶�
				
				
				//璇诲彇涓婁紶琛ㄥ崟椤甸潰涓婄殑鏁版嵁骞跺皢鍥惧儚瀛樺偍鍒版湇鍔″櫒纾佺洏涓婏紝鐒跺悗灏嗗浘鐗囪矾寰勬坊鍔犲埌鏁版嵁搴撻噷闈紝瀹炵幇鍥剧墖涓婁紶鍔熻兘
				String fileName=request.getPart("zhaopian").getSubmittedFileName();
				Part  part=request.getPart("zhaopian");
				InputStream  inputStream=part.getInputStream();
				String uuidName=UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("."),fileName.length());
				File newFile= new File(request.getRealPath("images"),"car/"+uuidName);
				FileOutputStream  outputStream=new  FileOutputStream(newFile);
				byte[] bs=new byte[1024];
				int length=-1;
				while((length=inputStream.read(bs))!=-1) {
					outputStream.write(bs,0,length);
					outputStream.flush();
				}
				outputStream.close();
				String zhaopian="images/car/"+uuidName;
				
				
				
				//2.灏嗚繖浜涘睘鎬у皝瑁呮垚涓�涓猨ava瀵硅薄
				Car  car=new Car();
				car.setGonglishu(Integer.parseInt(gonglishu));
				car.setPailiang(pailiang);
				car.setPinpaiming(pinpaiming);
				car.setShoujia(Integer.parseInt(shoujia));
				car.setXilie(xilie);
				car.setYanse(yanse);
				car.setZhaopian(zhaopian);
				
				//3.璋冪敤dao鏂规硶锛岃杩欎釜鏂版坊鍔犵殑姹借溅淇℃伅鎻掑叆鍒版暟鎹簱琛ㄤ腑
				boolean result=dao.addCar(car);
				
				//4.鏍规嵁娣诲姞缁撴灉璺宠浆椤甸潰
				if(result) {
					request.getRequestDispatcher("CarServlet?method=listAll").forward(request, response);
				}else {
					request.getRequestDispatcher("carAdd.jsp").forward(request, response);
				}
				break;
			}
			case "getCarInfo":
			{
				System.out.println("淇敼鍓嶇殑鏌ヨ");
				//1.鑾峰彇鐢ㄦ埛瓒呴摼鎺ヤ紶杩囨潵鐨勬苯杞︾紪鍙�
				String carid=request.getParameter("carid");
				//2.璋冪敤dao鏌ヨ鍑鸿繖涓溅杈嗕俊鎭�
				Car car=dao.getCarDetailById(Integer.parseInt(carid));
				//3.灏嗘煡璇㈠嚭鏉ョ殑姹借溅瀛樺偍鍒皉equest鑼冨洿鍐�
				request.setAttribute("car", car);
				//4.璺宠浆鍒颁慨鏀圭殑jsp涓婃樉绀鸿繖涓溅杈嗘暟鎹緵鐢ㄦ埛淇敼鎿嶄綔
				request.getRequestDispatcher("carEdit.jsp").forward(request, response);
				break;
			}
			case "update":
			{
				System.out.println("淇敼");
				//1.杩樻槸鍏堣幏鍙栭〉闈笂鐢ㄦ埛淇敼鍚庣殑杞﹁締淇℃伅
				String pinpaiming=request.getParameter("pinpaiming");//ctrl+alt+ 涓嬬澶�
				String xilie=request.getParameter("xilie");//ctrl+alt+ 涓嬬澶�
				String shoujia=request.getParameter("shoujia");//ctrl+alt+ 涓嬬澶�
				String gonglishu=request.getParameter("gonglishu");//ctrl+alt+ 涓嬬澶�
				String pailiang=request.getParameter("pailiang");//ctrl+alt+ 涓嬬澶�
				String yanse=request.getParameter("yanse");//ctrl+alt+ 涓嬬澶�
				String carid=request.getParameter("carid");
				
				//2.灏嗚繖浜涘睘鎬у皝瑁呮垚涓�涓猨ava瀵硅薄
				Car  car=new Car();
				car.setCarid(Integer.parseInt(carid));
				car.setGonglishu(Integer.parseInt(gonglishu));
				car.setPailiang(pailiang);
				car.setPinpaiming(pinpaiming);
				car.setShoujia(Integer.parseInt(shoujia));
				car.setXilie(xilie);
				car.setYanse(yanse);
				
				//3.璋冪敤dao鏂规硶锛岃杩欎釜鏂版坊鍔犵殑姹借溅淇℃伅鎻掑叆鍒版暟鎹簱琛ㄤ腑
				boolean result=dao.updateCar(car);
				
				//4.鏍规嵁淇敼缁撴灉璺宠浆椤甸潰
				if(result) {
					request.getRequestDispatcher("CarServlet?method=listAll").forward(request, response);
				}else {
					request.getRequestDispatcher("carEdit.jsp").forward(request, response);
				}
				break;
			}
			case "delete":
			{
				System.out.println("鍒犻櫎");
				//1.鑾峰彇鐢ㄦ埛瓒呴摼鎺ヤ紶杩囨潵鐨勬苯杞︾紪鍙�
				String carid=request.getParameter("carid");
				//2.璋冪敤dao鏂规硶灏嗚繖涓紪鍙风殑杞﹁締鍒犻櫎鎺�
				boolean result=dao.deleteCar(Integer.parseInt(carid));
				//3.灏嗗垹闄ょ殑缁撴灉瀛樺偍鍒皉equest鑼冨洿鍐咃紝鐒跺悗鍐峧sp涓婂垽鏂粨鏋滄彁绀虹敤鎴锋搷浣滅粨鏋�
				request.setAttribute("deleteResult", result);
				//4.璺宠浆鍒板垪琛ㄩ〉闈�
				request.getRequestDispatcher("CarServlet?method=listAll").forward(request, response);
				break;
			}
	
		}
	}
}
