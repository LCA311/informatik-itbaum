package de.slg.it.task;

import de.slg.it.Start;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUploadTask implements Runnable {

    private String localPath;

    public ImageUploadTask(String localPath){
        this.localPath = localPath;
    }

    @Override
    public void run() {

        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        try {

            URL connectURL = new URL(Start.DOMAIN_DEV + "uploadImage.php");

            File file = new File(localPath);
            FileInputStream fileInputStream = new FileInputStream(file);

            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Connection", "Keep-Alive");

            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + localPath.substring(localPath.lastIndexOf("/")+1) +"\"" + lineEnd);
            dos.writeBytes(lineEnd);

            int bytesAvailable = fileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];

            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0)
            {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            fileInputStream.close();
            dos.flush();

            InputStream is = conn.getInputStream();
            int ch;

            StringBuilder b =new StringBuilder();
            while( ( ch = is.read() ) != -1 ){
                b.append((char)ch);
            }
            String s = b.toString();

            System.out.println(s);

            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
