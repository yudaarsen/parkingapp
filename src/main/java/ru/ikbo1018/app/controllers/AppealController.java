package ru.ikbo1018.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ikbo1018.app.data.ImageRepository;
import ru.ikbo1018.app.data.TypeRepository;
import ru.ikbo1018.app.models.Appeal;
import ru.ikbo1018.app.models.Image;
import ru.ikbo1018.app.models.Type;
import ru.ikbo1018.app.services.AppealService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.util.List;

@Controller
@RequestMapping("/appeal")
public class AppealController {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private AppealService appealService;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping
    public String appeal(HttpServletRequest request) {
        List<Type> types = typeRepository.getAll();
        request.setAttribute("types", types);
        return "appeal/appeal";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String showAppeal(@PathVariable("id") int appealId) {
        return "Id = " + appealId;
    }

    @PostMapping
    public String createAppeal(@RequestParam("typeId") int typeId,
                               @RequestParam("address") String address,
                               @RequestParam("appeal_text") String appeal_text,
                               @RequestParam("files") MultipartFile[] files,
                               HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Integer accountId = (Integer)session.getAttribute("accountId");
            Appeal appeal = new Appeal();
            appeal.setTypeId(typeId);
            appeal.setAddress(address);
            appeal.setAppealText(appeal_text);
            appeal.setAccountId(accountId);
            appeal = appealService.createAppeal(appeal);
            for(MultipartFile file : files) {
                Image image = new Image();
                image.setAppealId(appeal.getId());
                image.setData(file.getBytes());
                imageRepository.create(image);
            }
            return "redirect:/appeal/" + appeal.getId();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return "redirect:/";
        }
    }
}
