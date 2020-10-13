package ru.ikbo1018.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ikbo1018.app.data.ImageRepository;
import ru.ikbo1018.app.data.StatusRepository;
import ru.ikbo1018.app.data.TypeRepository;
import ru.ikbo1018.app.models.Appeal;
import ru.ikbo1018.app.models.Image;
import ru.ikbo1018.app.models.Status;
import ru.ikbo1018.app.models.Type;
import ru.ikbo1018.app.services.AppealService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @Autowired
    private StatusRepository statusRepository;

    @Value("${maxFileCount}")
    private int maxFileCount;

    @GetMapping
    public String appeal(HttpServletRequest request) {
        List<Type> types = typeRepository.getAll();
        request.setAttribute("types", types);
        return "appeal/appeal";
    }

    @GetMapping(value = "/get/{id}")
    public String showAppeal(@PathVariable("id") int appealId, HttpServletRequest request) {
        try {
            Appeal appeal = appealService.getAppeal(appealId);
            List<Image> images = appealService.getAppealImages(appealId);
            Type type = typeRepository.getById(appeal.getTypeId());
            request.setAttribute("appeal", appeal);
            request.setAttribute("images", images);
            request.setAttribute("type", type);
            return "appeal/view";
        } catch (IllegalArgumentException e) {
            return "redirect:/lk";
        }
    }


    @GetMapping(value = "/check/{id}")
    public String checkAppeal(@PathVariable("id") int appealId, HttpServletRequest request) {
        try {
            Appeal appeal = appealService.getAppeal(appealId);
            List<Image> images = appealService.getAppealImages(appealId);
            Type type = typeRepository.getById(appeal.getTypeId());
            List<Status> statuses = statusRepository.getAll();
            request.setAttribute("appeal", appeal);
            request.setAttribute("images", images);
            request.setAttribute("type", type);
            request.setAttribute("statuses", statuses);
            return "appeal/check";
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Unable to load the appeal");
            return "appeal/check";
        }
    }

    @PostMapping(value = "/save/{id}")
    public String saveAppeal(@PathVariable("id") int appealId,
                             @RequestParam("answer") String answer,
                             @RequestParam("status") int statusId,
                             @SessionAttribute("accountId") int accountId) {
        try {
            Appeal appeal = appealService.getAppeal(appealId);
            appeal.setAnswerText(answer);
            appeal.setStatusId(statusId);
            appeal.setCheckDate(new Date());
            appeal.setOperatorId(accountId);
            appealService.updateAppeal(appeal);
        } catch (IllegalArgumentException e) {
            return "redirect:/lk";
        }
        return "redirect:/lk";
    }

    @PostMapping
    public String createAppeal(@RequestParam("typeId") int typeId,
                               @RequestParam("address") String address,
                               @RequestParam("appeal_text") String appeal_text,
                               @RequestParam("files") MultipartFile[] files,
                               HttpServletRequest request) {
        try {
            if(files.length > maxFileCount)
                throw new IllegalArgumentException("Incorrect file number");
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
            return "redirect:/appeal/get/" + appeal.getId();
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @GetMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public List<Appeal> getAppeals(HttpSession session) {
        Integer accountId = (Integer)session.getAttribute("accountId");
        return appealService.getAccountAppeals(accountId);
    }

    @GetMapping(value = "/operator")
    public String operator() {
        try {
            Appeal appeal = appealService.getUncheckedAppeal();
            return "redirect:/appeal/check/" + appeal.getId();
        } catch (IllegalArgumentException e) {
            return "redirect:/lk";
        }
    }
}
