package com.moxian.ng.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.MediaResourceIdValue;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ApiConstants.URI_API)
public class MediaResourceController {

    private static final Logger log = LoggerFactory.getLogger(MediaResourceController.class);

    @Inject
    private GridFsTemplate gridFsTemplate;

    // @Inject
    // private ContentNegotiationManager contentNegotiationManager;
    @RequestMapping(value="medias", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MediaResourceIdValue handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (log.isDebugEnabled()) {
            log.debug("handling file upploading...");
        }

        if (!file.isEmpty()) {

            GridFSFile gfFile = null;
            try {
                gfFile = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
            } catch (IOException e) {
                log.error("error in uploading:" + e.getMessage());
            }

            if (gfFile == null) {
                return null;
            }

            Object idobj = gfFile.getId();
            if (idobj != null) {
                String id = idobj.toString();

                if (log.isDebugEnabled()) {
                    log.debug("updated sucessfully!@" + id);
                }

                return new MediaResourceIdValue(id);
            }
        }

        return null;
    }

    @RequestMapping(value="medias", method = RequestMethod.PUT)
    @ResponseBody
    public MediaResourceIdValue handleBinaryArrayUpload(@RequestBody byte[] bytes, HttpServletRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handle upload data from request body directly...");
        }

        if (bytes != null && bytes.length > 0) {

            String fileName = req.getHeader("FILE_NAME");
            String contentType = req.getContentType();

            if (log.isDebugEnabled()) {
                log.debug("fileName@" + fileName);
                log.debug("contentType@" + contentType);
            }

            GridFSFile gfFile = gridFsTemplate.store(new ByteArrayInputStream(bytes), fileName, contentType);

            String id = gfFile.getId().toString();

            if (log.isDebugEnabled()) {
                log.debug("updated sucessfully!@" + id);
            }

            return new MediaResourceIdValue(id);
        }

        return null;
    }

    @RequestMapping(value = "public/medias/{id}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<Void> getMediaResource(@PathVariable("id") String id, HttpServletResponse res) {
        if (log.isDebugEnabled()) {
            log.debug("delete resource by id@" + id);
        }

        GridFSDBFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));

        if (file != null) {
            res.setContentType(file.getContentType());
            res.setContentLength(Long.valueOf(file.getLength()).intValue()); // 测试使用

            try {
                IOUtils.copy(file.getInputStream(), res.getOutputStream());
            } catch (IOException e) {
                log.error("error in uploading progress:" + e.getMessage());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "mgt/medias/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpEntity<Void> deleteMediaResource(@PathVariable("id") String id) {
        if (log.isDebugEnabled()) {
            log.debug("delete resource by id@" + id);
        }

        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
