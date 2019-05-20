package server;

import java.io.*;
import java.net.*;

public class submit2 { // 아이디가 중복되는지 확인하는 클래스

	boolean check = false; // 아이디 중복확인을 위한 boolean값 선언
	String text;

	public submit2(String id) {

		try {
			// 연결
			URL url = new URL("http://a123456789.dothome.co.kr/answer.php");
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
			String able = "";
			while ((line = br.readLine()) != null) {
				able += line;
			}

			text = able;

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
