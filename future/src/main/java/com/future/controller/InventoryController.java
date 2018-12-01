package com.future.controller;

import javax.validation.Valid;
import com.future.model.Inventory;
import com.future.repository.InventoryRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
/*@CrossOrigin("*")*/
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventory")
    public List<Inventory> getAllInventory() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

    @PostMapping("/inventory/create")
    public Inventory createInventory(@RequestBody Inventory inventory) {
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
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") String id) {
        inventoryRepository.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

}


/*




@Controller
public class InventoryController {

    @Autowired
    InventoryRepository inventoryRepository;


    @Autowired
    SearchInventoryRepository searchInventoryRepository;


    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("inventory", inventoryRepository.findAll());
        return "about";
    }

    @RequestMapping(value = "/addInventory", method = RequestMethod.POST)
    public String addInventory(@ModelAttribute Inventory inventory) {
        inventoryRepository.save(inventory);
        return "redirect:about";
    }


    @RequestMapping(value = "/search")
    public String search(Model model, @RequestParam String search) {
        model.addAttribute("inventoryList", searchInventoryRepository.searchInventory(search));
        model.addAttribute("search", search);
        return "about";
    }


    @RequestMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String inventoryId, @RequestParam String detail, @RequestParam Integer stock, @RequestParam Integer price) {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryId);
        inventory.setDetail(detail);
        inventory.setStock(stock);
        inventory.setPrice(price);
        inventoryRepository.save(inventory);

        return "redirect:/show/" + inventory.get_id();
    }

    @RequestMapping("/show/{_id}")
    public String show(@PathVariable String _id, Model model) {
        model.addAttribute("inventory", inventoryRepository.findOne(_id));
        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String _id) {
        Inventory inventory = inventoryRepository.findOne(_id);
        inventoryRepository.delete(inventory);

        return "redirect:/about";
    }

    @RequestMapping("/edit/{_id}")
    public String edit(@PathVariable String _id, Model model) {
        model.addAttribute("inventory", inventoryRepository.findOne(_id));
        return "edit";
    }

    @RequestMapping("/update")
    public String update(@RequestParam String _id,@RequestParam String inventoryId, @RequestParam String detail, @RequestParam Integer stock, @RequestParam Integer price) {
        Inventory inventory = inventoryRepository.findOne(_id);
        inventory.setInventoryId(inventoryId);
        inventory.setDetail(detail);
        inventory.setStock(stock);
        inventory.setPrice(price);
        inventoryRepository.save(inventory);

        return "redirect:/show/" + inventory.get_id();
    }




*/
