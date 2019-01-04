package com.future.controller;

import com.future.model.Image;
import com.future.model.Inventory;
import com.future.repository.EmployeeRepository;
import com.future.repository.InventoryRepository;
import com.future.service.ImageService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InventoryController {
    @Autowired
    ImageService imageService;
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
/*    @PostMapping("/inventory/create")
    public Inventory createInventory(@RequestBody Inventory inventory) {
        inventory.setAvailable(inventory.getStock());
        return inventoryRepository.save(inventory);
    }*/

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse create(
            @RequestPart(name = "data", required = true) CreateProductRequest createProductRequest,
            @RequestPart(name = "images", required = true) List<MultipartFile> images) throws Exception {
        ProductResponse data = productService.create(createProductRequest, images);
        return data;
    }

    @PostMapping(value="/items", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<?> image(
            @ModelAttribute("createNewItem") Image image){
        return imageService.storeItem(image);
    }



   /* @PostMapping("/inventory/create")
    public ResponseEntity<?> createInventory(@ModelAttribute Image inventory) {
        Inventory inventoryData = inventoryRepository.findByInventoryId(inventory.getInventoryId());
        String imageName = inventory.getImages().getOriginalFilename();
        System.out.println(inventory.getImages().getBytes());
        if(inventoryData!=null){
            return new ResponseEntity("ID SUDAH ADA", HttpStatus.BAD_REQUEST);
        }
        try{
            System.out.println(inventory.getImages().getBytes());
            Inventory i = new Inventory();
            System.out.println(inventory.getImages().getBytes());
            i.setInventoryId(inventory.getInventoryId());
            i.setDetail(inventory.getDetail());
            i.setStock(inventory.getStock());
            i.setPrice(inventory.getPrice());
            i.setImagePath(imageName);
            saveUploadedFile(inventory.getImages());
            saveInventory(i);
            }
        catch (IOException e) {
            return new ResponseEntity<>("Some error occured. Failed to add item", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Successed to add Item", HttpStatus.OK);
    }
    private void saveUploadedFile(MultipartFile images) throws IOException {
        if (!images.isEmpty()) {
            byte[] bytes = images.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + images.getOriginalFilename());
            Files.write(path, bytes);
        }
    }
    public Inventory saveInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }
    @PutMapping("/inventory/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable("id") String id, @RequestBody Inventory inventory) {
        Inventory inventoryData = inventoryRepository.findOne(id);
        if (inventory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        inventoryData.setInventoryId(inventory.getInventoryId());
        inventoryData.setDetail(inventory.getDetail());
        inventoryData.setStock(inventory.getStock());
        inventoryData.setPrice(inventory.getPrice());
        Inventory updatedinventory = inventoryRepository.save(inventoryData);
        return new ResponseEntity<>(updatedinventory, HttpStatus.OK);
    }*/

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") String id) {
        inventoryRepository.cr(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

}
