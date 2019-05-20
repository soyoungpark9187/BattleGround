package why;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class id_check { // 아이디가 중복되는지 확인하는 클래스
	boolean check = false; //아이디 중복확인을 위한 boolean값 선언

	id_check(String id) { 
		try {
			// 연결
			URL url = new URL("http://a123456789.dothome.co.kr/id_check.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			// 데이터
			String param = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8"); 

			// 전송
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
			osw.write(param);
			osw.flush();

			// 응답
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;
			String able = null;
			while ((line = br.readLine()) != null) {
				able = line;
			}
			if (able.contains("OK")) { // 아이디가 중복일때
				new Print("이미 사용중인 아이디 입니다.");
				check = false;
			} else { //아이디가 중복이 아닐때
				new Print("사용가능한 아이디 입니다.");
				check = true;
			}

			// 닫기
			osw.close();
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
