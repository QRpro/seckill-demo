package info.neilqin;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import info.neilqin.entity.po.UserPO;
import info.neilqin.utils.EncryptUtils;
import info.neilqin.utils.SnowFlake;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestCase {

	private static Properties props;

	static {
		try {
			InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("application.yml");
			props = new Properties();
			props.load(in);
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)throws Exception {
		log.info("start at :{}", System.currentTimeMillis());
		List<UserPO> user = createUser(2000);
		insertDB(user);
		signInAndsaveTokenToFile(user);
		log.info("end  :{}", System.currentTimeMillis());
	}

	private static List<UserPO> createUser(int count) {
		List<UserPO> users = new ArrayList<UserPO>(count);
		//生成用户
		for (int i = 0; i < count; i++) {
			UserPO user = new UserPO();
			long id = SnowFlake.instance.nextId();
			user.setId(id);
			user.setPhone((13000000000L + i) + "");
			user.setLoginCount(0);
			user.setNickname("user" + i);
			user.setRegisterDate(new Date());
			user.setSalt(EncryptUtils.getRandomSalt(6));
			user.setPassword(EncryptUtils.Md5Encrypt(EncryptUtils.saltEncrypt(user.getSalt(), EncryptUtils.Md5Encrypt("123456"))));
			System.out.println(JSONObject.toJSONString(user));
			users.add(user);
		}
		log.info("create user");
		return users;
	}
	private static void insertDB(List<UserPO> users) throws Exception {
//		//插入数据库
		Connection conn = getConn();
		String sql = "insert into sk_user(login_count, nickname, register_date, salt, password, id,phone)values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < users.size(); i++) {
			UserPO user = users.get(i);
			pstmt.setInt(1, user.getLoginCount());
			pstmt.setString(2, user.getNickname());
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(4, user.getSalt());
			pstmt.setString(5, user.getPassword());
			pstmt.setLong(6, user.getId());
			pstmt.setString(7, user.getPhone());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		pstmt.close();
		conn.close();
		System.out.println("insert to db");
	}
	private static void signInAndsaveTokenToFile(List<UserPO> users) throws Exception {
		//登录，生成token
		String urlString = "http://localhost:8080/user/to_login";
		File file = new File("D:/tokens.txt");
		if(file.exists()) {
			file.delete();
		}
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		file.createNewFile();
		raf.seek(0);
		for(int i=0;i<users.size();i++) {
			UserPO user = users.get(i);
			URL url = new URL(urlString);
			HttpURLConnection co = (HttpURLConnection)url.openConnection();
			co.setRequestProperty("Content-Type", "application/json");
			co.setRequestMethod("POST");
			co.setDoOutput(true);
			co.connect();
			DataOutputStream out = new DataOutputStream(co.getOutputStream());
			Map<String,Object> paramMap = new HashMap<>(2);
			paramMap.put("phone", user.getPhone());
			paramMap.put("pwd", EncryptUtils.Md5Encrypt("123456"));
			String params = JSONObject.toJSONString(paramMap);
			out.write(params.getBytes());
			out.flush();
			InputStream inputStream = co.getInputStream();
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte buff[] = new byte[1024];
			int len = 0;
			while((len = inputStream.read(buff)) >= 0) {
				bout.write(buff, 0 ,len);
			}
			inputStream.close();
			bout.close();
			String response = new String(bout.toByteArray());
			JSONObject jo = JSON.parseObject(response);
			String token = jo.getString("data");
			String row = user.getId()+","+token;
			raf.seek(raf.length());
			raf.write(row.getBytes());
			raf.write("\r\n".getBytes());
			log.info("write to file : {}", user.getId());
		}
		raf.close();
	}



	public static Connection getConn() throws Exception{
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		String driver = props.getProperty("driver-class-name");
		Class.forName(driver);
		return DriverManager.getConnection(url,username, password);
	}
}
