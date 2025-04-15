package com.chainxi.system;

import com.chainxi.system.config.oss.OssTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class OssTest {

    @Autowired
    private OssTemplate template;

    @Test
    public void testTemplateUpload() {
        assert template.getOSSSignUploadUrl() != null;
    }
}
