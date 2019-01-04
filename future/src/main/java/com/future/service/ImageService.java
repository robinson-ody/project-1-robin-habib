package com.future.service;

import com.future.model.Image;
import com.future.model.Inventory;
import com.future.repository.InventoryRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class ImageService {

    @Value("${static.path}")
    private static String UPLOADED_FOLDER = "D:/FUTURE PROGRAM/project-1-robin-habib/project-1-robin-habib/Image/";
    // sesuai dengan folder kita masing-masing.

    @Autowired
    InventoryRepository inventoryRepository;


    public ResponseEntity<?> storeItem(Image newInventory) {
        Inventory itemExist = inventoryRepository.findByInventoryId(newInventory.getInventoryId());
        if(itemExist!=null)  return new ResponseEntity("Item already Exists with the name provided", HttpStatus.BAD_REQUEST);
        String fileName = newInventory.getImages().getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            return new ResponseEntity("Please select a file!", HttpStatus.OK);
        }
        try {
            //            item.setImage(bytes); // untuk save file / gambar ke field picture di database
            Inventory inventoryData = new Inventory();
            inventoryData.setInventoryId(newInventory.getInventoryId());
            inventoryData.setStock(newInventory.getStock());
            inventoryData.setDetail(newInventory.getDetail());
            inventoryData.setPrice(newInventory.getPrice());
            inventoryData.setImagePath(fileName);
            saveUploadedFile(newInventory.getImages());
            saveItem(inventoryData);
        } catch (IOException e) {
            return new ResponseEntity<>("Some error occured. Failed to add item", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Successed to add Item", HttpStatus.OK);
    }

    private void saveUploadedFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    }
    public Inventory saveItem(Inventory inventory){
        return inventoryRepository.save(inventory);
    }
}
