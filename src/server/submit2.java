package server;

import java.io.*;
import java.net.*;

public class submit2 { // ���̵� �ߺ��Ǵ��� Ȯ���ϴ� Ŭ����

	boolean check = false; // ���̵� �ߺ�Ȯ���� ���� boolean�� ����
	String text;

	public submit2(String id) {

		try {
			// ����
			URL url = new URL("http://a123456789.dothome.co.kr/answer.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			// ������
			String param = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

			// ����
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
			osw.write(param);
			osw.flush();

			// ����
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
