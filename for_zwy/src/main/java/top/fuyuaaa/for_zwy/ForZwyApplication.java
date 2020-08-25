package top.fuyuaaa.for_zwy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class ForZwyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForZwyApplication.class, args);
    }


    @RequestMapping("/fileUpload")
    @ResponseBody
    public void forZwyUpload(
            @RequestParam("fileName") MultipartFile file,
            HttpServletResponse response
    ) throws Exception {


        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024 * 10);
        byteBuffer.put(file.getBytes());

        byteBuffer.flip();

        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer charBuffer = decoder.decode(byteBuffer);
        String str = charBuffer.toString();

        JSONArray jsonArray = JSONArray.parseArray(str);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        JSONObject jsonObject1 = jsonObject.getJSONObject("tableVO");
        JSONArray jsonArray1 = jsonObject1.getJSONArray("values");


        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("表");

        for (int i = 0; i < jsonArray1.size(); i++) {
            JSONArray jsonArray2 = jsonArray1.getJSONArray(i);
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < jsonArray2.size(); j++) {
                JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
                if (null == jsonObject2) {
                    continue;
                }
                Object value = jsonObject2.get("value");
                if (value == null) {
                    continue;
                }
                row.createCell(j).setCellValue(String.valueOf(jsonObject2.get("value")));
            }
        }

        String fileName = "zwy.xls";



        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers",
                "x-requested-with,Content-Type,Access-Control-Allow-Credentials,Access-Control-Allow-Origin,Content-Disposition");

        ServletOutputStream outputStream = response.getOutputStream();
        hssfWorkbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
