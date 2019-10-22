package com.yh.fastdfs;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FastDFSUtils {

    public static void main( String[] args ) throws IOException {
        try {
            // 获取文件后缀名
        /*String ext = "jpg";
        FileInputStream fileReader = new FileInputStream("E:/photo/2531170_082435310724_2.jpg");
        byte[] bytes = new byte[10000000];//10M
        int length = fileReader.read(bytes);
        FastDFSFile file = new FastDFSFile(bytes,ext);
        NameValuePair[] meta_list = new NameValuePair[4];
        meta_list[0] = new NameValuePair("fileName", "abc");
        meta_list[1] = new NameValuePair("fileLength", String.valueOf(length));
        meta_list[2] = new NameValuePair("fileExt", ext);
        meta_list[3] = new NameValuePair("fileAuthor", "WangLiang");
        String filePath = FileManager.upload(file,meta_list);
        System.out.println(filePath);*/
//        int c=FileManager.deleteFile("group1","M00/00/00/wKgBXlzZW9CAWfIHAJiWgOQJpJY140.jpg");
//        System.out.println("C："+c);
            //上传
            String fileName="2531170_082435310724_2.jpg";
            File file = new File("E:\\1.jpg");
            Map<String,String> metaList = new HashMap<String, String>();
            metaList.put("fileName",fileName);
            metaList.put("fileType","sz");
            metaList.put("author","BLUE");
            metaList.put("date","2019-05-13");
            String fid = FastDFSClient.uploadFile(file,file.getName(),metaList);//group1/M00/00/00/CgqyYl0CLD2AHkiHABaE6B6DRK0737.jpg
            System.out.println("upload local file " + file.getPath() + " ok, fileid=" + fid);
            //下载
            //int r = FastDFSClient.downloadFile("group1/M00/00/00/CgqyYl0CLcOAPqzzABaE6B6DRK0713.jpg", new File("DownloadFile_fid.jpg"));
            //System.out.println(r == 0 ? "下载成功" : "下载失败");
            //删除
           /* int rr = FastDFSClient.deleteFile("group1/M00/00/00/wKgBXlzaMXiAUKkoAAGUVuLxeAk668.jpg");
            System.out.println(rr == 0 ? "删除成功" : "删除失败");*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
