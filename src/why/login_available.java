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


public class login_available {

	static boolean suc;
	
	login_available (String id, String pw) {
		try {
			// ����
			URL url = new URL("http://a123456789.dothome.co.kr/login_test.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			// ������
			String param = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
			param += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pw, "UTF-8");
			

			// ����
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
			osw.write(param);
			osw.flush();

			// ����
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;
			String able = null;
			while ((line = br.readLine()) != null) {
				able = line;
			}
			if(able.contains("OK")) { 
				new ChatClientApp(id);  //*************** ���Ͽ���
				suc = false;
			}
			else {
				new Print("���̵�� ��й�ȣ�� Ȯ���ϼ���.");
				suc = true;
			}
				
			// �ݱ�
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

