package com.future.controller;

import com.future.model.Inventory;
import com.future.repository.InventoryRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InventoryController {

//    private static String UPLOADED_FOLDER = "D:/FUTURE PROGRAM/project-1-robin-habib/project-1-robin-habib/Image/";
    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventory")
    public List<Inventory> getAllInventory() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

    @GetMapping("/inventory/{id}")
    public Inventory getUserInventory(@PathVariable("id") String id) {
        return (Inventory) inventoryRepository.findOne(id);
    }

    @PostMapping("/inventory/create")
    public Inventory createEmployee(@RequestBody Inventory inventory) {
        inventoryRepository.save(inventory);
        inventory.setAvailable(inventory.getStock());
        return inventoryRepository.save(inventory);
    }

    @PostMapping(value = "/create/abcd", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(
            @RequestPart(name = "inventoryId", required = true) String inventoryId,
            @RequestPart(name = "image", required = true) MultipartFile image) throws Exception {
        try {
            Inventory invenData = inventoryRepository.findByInventoryId(inventoryId);
            invenData.setDocType("pictures");
            invenData.setFile(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
            Inventory updatedinventory = inventoryRepository.save(invenData);
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

    @PostMapping("/inventory/create/image")
    public String singleFileUpload(@RequestParam("file") MultipartFile multipart, @RequestParam("inventoryId") String inventoryId) {
        try {
            Inventory invenData = inventoryRepository.findByInventoryId(inventoryId);
            invenData.setDocType("pictures");
            invenData.setFile(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));
            Inventory updatedinventory = inventoryRepository.save(invenData);
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }
    @PostMapping("/retrieve")
    public String retrieveFile(@RequestParam("inventoryId") String inventoryId){
        Inventory inventory = inventoryRepository.findByInventoryId(inventoryId);
        System.out.println(inventory);
        Binary file = inventory.getFile();
        if(file != null) {
            FileOutputStream fileOuputStream = null;
            try {
                fileOuputStream = new FileOutputStream(inventory.getFile().getData() + "inven_pic.jpg");
                fileOuputStream.write(file.getData());
            } catch (Exception e) {
                e.printStackTrace();
                return "failure";
            } finally {
                if (fileOuputStream != null) {
                    try {
                        fileOuputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "failure";
                    }
                }
            }
        }
        return "success";
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") String id) {
        inventoryRepository.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
