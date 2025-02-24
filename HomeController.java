package kz.bitlab.techorda._5.firstProject.contollers;

import kz.bitlab.techorda._5.firstProject.db.DBManager;
import kz.bitlab.techorda._5.firstProject.models.Items;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HomeController {
    @GetMapping
    public String getIndex(Model model) {
        List<Items> itemList = DBManager.getItemList();
        model.addAttribute("tovary", itemList);


        model.addAttribute("name", "Dinara");
        return "index";
    }

    @GetMapping(value = "/addItem")
    public String getAddItemPage(Model model) {
        List<Items> itemList = DBManager.getItemList();
        return "addItem";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute("itemName") String name,
                          @ModelAttribute("itemDescription") String description,
                          @ModelAttribute("itemPrice") Double price) {

/*        Items item = new Items();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);*/


        Items item = Items.builder()
                .name(name)
                .description(description)
                .price(price)
                .build();

        DBManager.addItem(item);
        return "redirect:/";

    }


    @GetMapping (value="/details")
    public String getDetails(@RequestParam(name="id") Long id, Model model) {
       Items newItem =  DBManager.getItem(id);
       if(newItem != null) {
           model.addAttribute("tovar", newItem);
       }
       return "details";
    }

    @GetMapping (value="/details/{number}")
    public String getDetails2 (@PathVariable(name="number") Long number, Model model) {
        Items newItem =  DBManager.getItem(number);
        if(newItem != null) {
            model.addAttribute("tovar", newItem);
        }
        return "details";
    }

    @PostMapping(value = "/saveItem")
    public String saveItem(@RequestParam("idishka") Long id,
                           @RequestParam("itemName") String name,
                           @RequestParam("itemDescription") String description,
                           @RequestParam("itemPrice") Double price) {
        Items item = Items.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .build();

        DBManager.saveItem(item);
        return "redirect:/";
    }

    @PostMapping(value = "/deleteItem")
    public String deleteItem(@RequestParam("idshka") Long id) {
        DBManager.deleteItem(id);
        return "redirect:/";
    }
}
