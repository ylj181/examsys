package com.qfedu.examsys.beforecontroller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.examsys.pojo.Radio;
import com.qfedu.examsys.service.RadioService;
import com.qfedu.examsys.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
public class ImportController {

    @Autowired
    private RadioService radioService;


    @RequestMapping("/importRadio")
    public String importExcel(@RequestParam MultipartFile upfile) {
        // 获取上传文件的输入流对象
        try {
            InputStream inputStream = upfile.getInputStream();

            String filename = upfile.getOriginalFilename();
            List<Map<String, Object>> list = ExcelUtils.readExcel(filename, inputStream);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(list);

            List<Radio> ulist = objectMapper.readValue(jsonStr, new TypeReference<List<Radio>>() {
            });
            System.out.println(ulist);
            radioService.insertMany(ulist);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/index.html";
    }
}
