
package com.future.controller;

import com.future.model.HRefModel;
import com.future.service.FileSystemStorageService;
import com.mongodb.gridfs.GridFSDBFile;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class FileController {
    @Controller
    public class UploadController {

        @Autowired
        private FileSystemStorageService storageService;

        @RequestMapping(value = "/", method = RequestMethod.GET)
        public ModelAndView upload() {
            return new ModelAndView("upload");
        }


        @RequestMapping(value = "/files/list", method = RequestMethod.GET)
        public String listFiles(Model model) {
            List<Path> lodf = new ArrayList<>();
            List<HRefModel> uris = new ArrayList<>();

            try {
                lodf = storageService.listSourceFiles(storageService.getUploadLocation());
                for (Path pt : lodf) {
                    HRefModel href = new HRefModel();
                    href.setHref(MvcUriComponentsBuilder
                            .fromMethodName(UploadController.class, "serveFile", pt.getFileName().toString())
                            .build()
                            .toString());

                    href.setHrefText(pt.getFileName().toString());
                    uris.add(href);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            model.addAtribut("listOfEntries", uris);
            return "file_list :: urlFileList";
        }


        @GetMapping("/files/{filename:.+}")
        @ResponseBody
        public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
            Resource file = storageService.loadAsResource(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        }

        @RequestMapping(value = "/files/upload", method = RequestMethod.POST)
        public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
            storageService.store(file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
            return "redirect:/";
        }
    }
}