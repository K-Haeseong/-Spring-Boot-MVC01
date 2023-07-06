package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    //상품목록
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    //상품상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    //상품상세 - 삭제
    @PostMapping("/{itemId}")
    public String item(@PathVariable Long itemId) {
        itemRepository.delete(itemId);
        return "redirect:/basic/items";
    }



//--------------------------------- 상품 등록----------------------------

    //상품등록 입력 전
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //상품등록 입력 후
//    @PostMapping("/add")
    public String addItemV1(
            @RequestParam("itemName") String itemName,
            @RequestParam Integer price,
            @RequestParam Integer quantity,
            Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item")Item item, Model model) {

        itemRepository.save(item);

//      model.addAttribute("item", item); 이거 생략해도 작동 잘 하네?
//      @ModelAttribute 추가 기능 : 모델(Model)에 @ModelAttribute로 지정한객체를 자동으로넣어준다.
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {

        itemRepository.save(item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item) {

        itemRepository.save(item);

        return "basic/item";
    }


//    @PostMapping("/add")
    public String addItemV5(Item item) {

        itemRepository.save(item);

        return "redirect:/basic/item/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }
//--------------------------------- 상품 등록----------------------------

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 1000, 100));
        itemRepository.save(new Item("itemB", 2000, 200));
    }

}
