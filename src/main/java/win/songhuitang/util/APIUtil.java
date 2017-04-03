package win.songhuitang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by simon.song on 2017/3/27.
 * <b>function:</b> GET / POST data from RESTFul URL.
 */
public class APIUtil {
    public static String getApiData(String requestUrl){

        StringBuffer strBuf = new StringBuffer();
        BufferedReader reader = null;

        try {

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

            String line;
            while ((line = reader.readLine()) != null){
                strBuf.append(line + " ");
            }


        }catch(MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } finally{
            StreamUtil.closeQuietly(reader);
        }

        return strBuf.toString();
    }

    public static String postApiData(String requestUrl, String data){

        StringBuffer line = new StringBuffer();

        OutputStreamWriter out = null;
        BufferedReader reader = null;

        try{
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/json");
            connection.setAllowUserInteraction(true);

            connection.connect();

            out = new OutputStreamWriter(connection.getOutputStream());

            //Do the post with data.
            out.write(data);

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String buffLine;
            while ((buffLine = reader.readLine()) != null){
                line.append(buffLine);
            }

            connection.disconnect();

        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            StreamUtil.closeQuietly(out);
            StreamUtil.closeQuietly(reader);
        }

        return line.toString();
    }

}
