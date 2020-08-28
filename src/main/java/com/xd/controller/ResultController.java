package com.xd.controller;

import com.xd.pojo.*;
import com.xd.service.TextService;
import com.xd.service.UploadFileService;
import com.xd.service.UserService;
import com.xd.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Select")
@SuppressWarnings("all")
public class ResultController {
    @Autowired
    TextService textService;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/AllPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public ResponseMessage Picture(@RequestBody RequestMessage message) {


        ResponseMessage response = userService.selectAllPicture(message);



        return response;
    }

    //查询体检报告图片
    @RequestMapping(value = "/ReportPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMessage selectReportPicture(@RequestBody RequestMessage message) {

        List<TextInfo> info = userService.selectReportPicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setTextInfo(info);

        return response;
    }

    //病症图片
    @RequestMapping(value = "/DiseasePicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

        public ResponseMessage selectDiseasePicture(@RequestBody RequestMessage message) {

            List<DiseasePicture> diseasePictures = userService.selectDiseasePicture(message);

            ResponseMessage response = new ResponseMessage();
            response.setDiseasePictures(diseasePictures);

            return response;
        }

    //查找化验检查
    @RequestMapping(value = "/LaboratoryPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public ResponseMessage LaboratoryPicture(@RequestBody RequestMessage message) {

        List<LaboratoryPicture> laboratoryPictures = userService.selectLaboratoryPicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setLaboratoryPictures(laboratoryPictures);

        return response;
    }

    //查找影像检查
    @RequestMapping(value = "/ImagePicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public ResponseMessage ImagePicture(@RequestBody RequestMessage message) {

        List<ImagePicture> imagePictures = userService.selectImagePicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setImagePictures(imagePictures);

        return response;
    }

    //侵入型器械检查

    @RequestMapping(value = "/InstrumentPicture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

    public ResponseMessage InstrumentPicture(@RequestBody RequestMessage message) {

        List<InstrumentPicture> instrumentPictures = userService.selectInstrumentPicture(message);

        ResponseMessage response = new ResponseMessage();
        response.setInstrumentPictures(instrumentPictures);

        return response;
    }

}
