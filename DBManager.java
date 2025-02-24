package kz.bitlab.techorda._5.firstProject.db;

import kz.bitlab.techorda._5.firstProject.models.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBManager {
    static List<Items> itemList = new ArrayList<>();
    static Long id = 7L;

    static {
        itemList.add(new Items(1L, "iPhone 15", "This is the most powerful phone", 788.9));
        itemList.add(new Items(2L, "iPhone 11", "This is the most powerful phone", 588.9));
        itemList.add(new Items(3L, "iPhone 12", "This is the most powerful phone", 235.5));
        itemList.add(new Items(4L, "Samsung", "This is the most powerful phone", 350.3));
        itemList.add(new Items(5L, "Oppo", "This is the most powerful phone", 758.6));
        itemList.add(new Items(6L, "Nokia", "This is the most powerful phone", 348.7));
    }

    public static List<Items> getItemList() {
        return itemList;
    }

    public static void addItem(Items item) {
        item.setId(id);
        itemList.add(item);
        id++;
    }

    public static Items getItem(Long id){
        Items newIt = null;
        for(Items it : itemList){
            if(Objects.equals(it.getId(), id)){
                newIt = it;
            }
        }
        return newIt;
    }

    public static void saveItem(Items item) {
        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).getId() == item.getId()){
                itemList.set(i, item);
            }
        }
    }

    public static void deleteItem(Long id){
        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).getId() == id){
                itemList.remove(i);
            }
        }
    }
}
