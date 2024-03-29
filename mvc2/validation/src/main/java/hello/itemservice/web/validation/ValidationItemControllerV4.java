package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.SaveCheck;
import hello.itemservice.domain.item.UpdateCheck;
import hello.itemservice.web.validation.form.ItemSaveForm;
import hello.itemservice.web.validation.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v4/items")
@RequiredArgsConstructor
public class ValidationItemControllerV4 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v4/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v4/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //@Validated 를 넣어주면 @InitBinder로 넣어준 검증기가 항상 검증해주고 결과는 bindingResult에 담겨있음
        // @Validated는 검증기를 실행하라는 어노테이션임

        // 특정 필드가 아닌 복합 롤 검증에서는 ObjectError를 쓴다.
        if(form.getPrice() != null && form.getQuantity() != null){
            int resultPrice  = form.getPrice() * form.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin", new Object[]{9999, resultPrice}, null);
            }
        }

        //검증에 실패하면 다시 입력폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            //model.addAttribute("errors", bindingResult); <- bindingResult를 Model에 담지 않아도 됨.  BindingResult는 자동으로 Mpdel에 넘어감
            return "validation/v4/addForm";
        }

        //성공 로직

        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v4/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/editForm";
    }

    //@PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute Item item, BindingResult bindingResult) {
        // 특정 필드가 아닌 복합 롤 검증에서는 ObjectError를 쓴다.
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice  = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin", new Object[]{9999, resultPrice}, null);
            }
        }

        //검증에 실패하면 다시 입력폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            //model.addAttribute("errors", bindingResult); <- bindingResult를 Model에 담지 않아도 됨.  BindingResult는 자동으로 Mpdel에 넘어감
            return "validation/v4/addForm";
        }

        itemRepository.update(itemId, item);
        return "redirect:/validation/v4/items/{itemId}";
    }

    @PostMapping("/{itemId}/edit")
    public String editV2(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {
        // 특정 필드가 아닌 복합 롤 검증에서는 ObjectError를 쓴다.
        if(form.getPrice() != null && form.getQuantity() != null){
            int resultPrice  = form.getPrice() * form.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin", new Object[]{9999, resultPrice}, null);
            }
        }

        //검증에 실패하면 다시 입력폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            //model.addAttribute("errors", bindingResult); <- bindingResult를 Model에 담지 않아도 됨.  BindingResult는 자동으로 Mpdel에 넘어감
            return "validation/v4/addForm";
        }
        Item item = new Item();
        item.setId(form.getId());
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        itemRepository.update(itemId, item);
        return "redirect:/validation/v4/items/{itemId}";
    }

}

