package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

    @InitBinder /** ValidationItemControllerV2가 호출 될 때 마다 새로 만들어짐. 컨트롤러 내에 어떤 메서드가 호출되든지 itemValidator가 호출되어서
        검증기를 넣어둬서 언제든 검증기 사용이 가능하다*/

    public void init(WebDataBinder dataBinder){
        dataBinder.addValidators(itemValidator);
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //BindingResult bindingResult 파라미터의 위치는 @ModelAttribute Item item 다음에 와야 한다

        // 검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));
        }
        if(item.getPrice() == null || item.getPrice()<1000 || item.getPrice()>1000000){
            bindingResult.addError(new FieldError("price", "price", "가격은 1,000 ~1,000,000까지 허용합니다."));
        }
        if(item.getQuantity() == null || item.getQuantity()>=9999){
            bindingResult.addError(new FieldError("quantity", "quantity", "수량은 최대 9,999까지 허용합니다."));
        }

        // 특정 필드가 아닌 복합 롤 검증에서는 ObjectError를 쓴다.
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice  = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재값 = "+resultPrice));
            }
        }

        //검증에 실패하면 다시 입력폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            //model.addAttribute("errors", bindingResult); <- bindingResult를 Model에 담지 않아도 됨.  BindingResult는 자동으로 Mpdel에 넘어감
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //BindingResult bindingResult 파라미터의 위치는 @ModelAttribute Item item 다음에 와야 한다

        // 검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(),false,null, null,"상품 이름은 필수입니다."));
        }
        if(item.getPrice() == null || item.getPrice()<1000 || item.getPrice()>1000000){
            bindingResult.addError(new FieldError("price", "price",item.getPrice(), false, null, null, "가격은 1,000 ~1,000,000까지 허용합니다."));
        }
        if(item.getQuantity() == null || item.getQuantity()>=9999){
            bindingResult.addError(new FieldError("quantity", "quantity", item.getQuantity(), false, null, null, "수량은 최대 9,999까지 허용합니다."));
        }

        // 특정 필드가 아닌 복합 롤 검증에서는 ObjectError를 쓴다.
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice  = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                bindingResult.addError(new ObjectError("item", null, null, "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재값 = "+resultPrice));
            }
        }

        //검증에 실패하면 다시 입력폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            //model.addAttribute("errors", bindingResult); <- bindingResult를 Model에 담지 않아도 됨.  BindingResult는 자동으로 Mpdel에 넘어감
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //@PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //BindingResult bindingResult 파라미터의 위치는 @ModelAttribute Item item 다음에 와야 한다

        // 검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.rejectValue("itemName", "required");
        }
        if(item.getPrice() == null || item.getPrice()<1000 || item.getPrice()>1000000){
            bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity()>=9999){
            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

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
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }
    //@PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //BindingResult bindingResult 파라미터의 위치는 @ModelAttribute Item item 다음에 와야 한다

        itemValidator.validate(item, bindingResult);

        //검증에 실패하면 다시 입력폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            //model.addAttribute("errors", bindingResult); <- bindingResult를 Model에 담지 않아도 됨.  BindingResult는 자동으로 Mpdel에 넘어감
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }
    @PostMapping("/add")
    public String addItemV6(@Validated @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //@Validated 를 넣어주면 @InitBinder로 넣어준 검증기가 항상 검증해주고 결과는 bindingResult에 담겨있음
        // @Validated는 검증기를 실행하라는 어노테이션임

        //검증에 실패하면 다시 입력폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            //model.addAttribute("errors", bindingResult); <- bindingResult를 Model에 담지 않아도 됨.  BindingResult는 자동으로 Mpdel에 넘어감
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

