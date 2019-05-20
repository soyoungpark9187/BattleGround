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

public class id_check { // ���̵� �ߺ��Ǵ��� Ȯ���ϴ� Ŭ����
	boolean check = false; //���̵� �ߺ�Ȯ���� ���� boolean�� ����

	id_check(String id) { 
		try {
			// ����
			URL url = new URL("http://a123456789.dothome.co.kr/id_check.php");
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
			String able = null;
			while ((line = br.readLine()) != null) {
				able = line;
			}
			if (able.contains("OK")) { // ���̵� �ߺ��϶�
				new Print("�̹� ������� ���̵� �Դϴ�.");
				check = false;
			} else { //���̵� �ߺ��� �ƴҶ�
				new Print("��밡���� ���̵� �Դϴ�.");
				check = true;
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
