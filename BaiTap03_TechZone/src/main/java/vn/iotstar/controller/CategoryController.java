package vn.iotstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.iotstar.entity.CategoryEntity;
import vn.iotstar.repository.CategoryRepository;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Danh sach + tim kiem + phan trang
    @GetMapping("/list")
    public String list(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                        Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryEntity> pageResult = categoryRepository.searchByName(keyword, pageable);

        model.addAttribute("listcate", pageResult.getContent());
        model.addAttribute("pageResult", pageResult);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);

        return "admin/category/list";
    }

    // Form them moi
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new CategoryEntity());
        model.addAttribute("pageTitle", "Thêm Category");
        return "admin/category/addOrEdit";
    }

    // Form sua
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Integer id, Model model) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Category có id = " + id));
        model.addAttribute("category", category);
        model.addAttribute("pageTitle", "Cập nhật Category");
        return "admin/category/addOrEdit";
    }

    // Luu (dung chung cho ca them moi va cap nhat)
    @PostMapping("/save")
    public String save(@ModelAttribute("category") CategoryEntity category,
                        RedirectAttributes redirectAttributes) {

        boolean isNew = (category.getCategoryid() == null);
        categoryRepository.save(category);

        redirectAttributes.addFlashAttribute("message",
                isNew ? "Thêm category thành công!" : "Cập nhật category thành công!");

        return "redirect:/admin/category/list";
    }

    // Xoa
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            categoryRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Xoá category thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Không thể xoá category này (có thể đang được sản phẩm khác tham chiếu)!");
        }
        return "redirect:/admin/category/list";
    }
}
