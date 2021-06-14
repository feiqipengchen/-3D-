package org.jeecg.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/upload")
public class upLoadController {
    @RequestMapping("/wenjian")
    public Result<File> upLoadWenJian(MultipartHttpServletRequest multipartHttpServletRequest) {
        Result<File> result = new Result<File>();
        Map<String, MultipartFile> fileMap=multipartHttpServletRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取单个文件
            MultipartFile file = entity.getValue();
            // 获取文件名
            String fileName = file.getOriginalFilename();
            String []fileNameFen=fileName.split("-");
            System.out.println("上传的文件名为：" + fileNameFen[0]+"  "+fileNameFen[1]);
           // 文件上传后的路径
            String filePath = "F:\\ideal\\JeecgBoot\\jeecg-boot1\\ant-design-vue-jeecg\\public\\static\\model\\";
            File dest = new File(filePath +fileNameFen[0]+"\\"+fileNameFen[1]);
            try {
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    System.out.println("123");
                    dest.getParentFile().mkdirs();
                }
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result.setSuccess(true);
        return result;
    }
}
