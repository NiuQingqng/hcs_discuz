package com.niuniu.hcs_discuz.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author niuniu
 * @date 2022/3/24
 */
public interface IFileService {

    String saveUploadFile(MultipartFile file);

}
