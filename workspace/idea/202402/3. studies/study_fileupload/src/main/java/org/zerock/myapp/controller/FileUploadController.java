package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@Controller
public class FileUploadController {
    // org.springframework.web.multipart.support.StandardServletMultipartResolver
//    @Autowired private MultipartResolver resolver;


    @PostMapping("/fileUpload")
    String fileUpload(
//        @RequestParam("files")    // * No required. *
        List<MultipartFile> files
    ) throws IOException {
        log.trace("fileUpload({}) invoked.", files);

        for(MultipartFile mf : files) {
            // Request Parameter Name.
            log.info("\t1. name: {}", mf.getName());
            log.info("\t2. size: {}", mf.getSize());
            log.info("\t3. isEmpty: {}", mf.isEmpty());
            log.info("\t4. contentType: {}", mf.getContentType());
            log.info("\t5. originalFilename: {}", mf.getOriginalFilename());

            Path path = Path.of("C:/temp/upload/" + mf.getOriginalFilename());
            log.info("\t+ path: {}", path);

            if(!mf.isEmpty()) mf.transferTo(path);

            log.info("\t=====================================");
        } // enhanced for

        files.clear();

        return "Done";
    } // fileUpload

} // end class
