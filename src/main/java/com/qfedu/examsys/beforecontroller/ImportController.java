package com.qfedu.examsys.beforecontroller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.examsys.pojo.Judge;
import com.qfedu.examsys.pojo.Radio;
import com.qfedu.examsys.pojo.ShortAnswer;
import com.qfedu.examsys.service.JudgeService;
import com.qfedu.examsys.service.RadioService;
import com.qfedu.examsys.service.ShortAnswerService;
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

/**
 * @author lexo
 *  导入选择题，判断题，简答题
 */
@Controller
public class ImportController {

    @Autowired
    private RadioService radioService;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private ShortAnswerService shortAnswerService;


    @RequestMapping("/importRadio")
    public String importExcelRadio(@RequestParam MultipartFile upfile) {

        // 获取上传文件的输入流对象
        try {
            InputStream inputStream = upfile.getInputStream();
            String filename = upfile.getOriginalFilename();
            if (filename.equals("")){
                throw new RuntimeException("请选择文件");

            }
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
        return "importRadio.html";
    }

    @RequestMapping("/importJudge")
    public String importExcelJudge(@RequestParam MultipartFile upfile) {
        // 获取上传文件的输入流对象
        try {
            InputStream inputStream = upfile.getInputStream();
            String filename = upfile.getOriginalFilename();
            List<Map<String, Object>> list = ExcelUtils.readExcel(filename, inputStream);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(list);

            List<Judge> ulist = objectMapper.readValue(jsonStr, new TypeReference<List<Judge>>() {
            });
            System.out.println(ulist);
            judgeService.insertManyJudges(ulist);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/index.html";
    }


    @RequestMapping("/importShortAnswer")
    public String importExcelShortAnswer(@RequestParam MultipartFile upfile) {
        // 获取上传文件的输入流对象
        try {
            InputStream inputStream = upfile.getInputStream();
            String filename = upfile.getOriginalFilename();
            List<Map<String, Object>> list = ExcelUtils.readExcel(filename, inputStream);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(list);

            List<ShortAnswer> ulist = objectMapper.readValue(jsonStr, new TypeReference<List<ShortAnswer>>() {
            });
            System.out.println(ulist);
            shortAnswerService.insertManyShortAnswer(ulist);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/index.html";
    }



}
