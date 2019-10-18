package linhVu.controller;

import linhVu.model.Note;
import linhVu.model.NoteType;
import linhVu.service.NoteService;
import linhVu.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteTypeController {

    @Autowired
    NoteTypeService noteTypeService;
    @Autowired
    NoteService noteService;

    @GetMapping("/noteTypes")
    public ModelAndView listNoteType(Pageable pageable){
        Iterable<NoteType> noteTypes = noteTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("noteType/list");
        modelAndView.addObject("noteTypes", noteTypes);
        return modelAndView;
    }

    @GetMapping("/create-noteType")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/noteType/create");
        modelAndView.addObject("noteType", new NoteType());
        return modelAndView;
    }
    @PostMapping("/create-noteType")
    public ModelAndView saveNoteType(@ModelAttribute("noteType") NoteType noteType){
        noteTypeService.save(noteType);
        ModelAndView modelAndView = new ModelAndView("/noteType/create");
        modelAndView.addObject("noteType", new NoteType());
        modelAndView.addObject("message", "New noteType created successfully!");
        return modelAndView;
    }
    @GetMapping("/edit-noteType/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        NoteType noteType = noteTypeService.findById(id);
        if (noteType != null) {
            ModelAndView modelAndView = new ModelAndView("/noteType/edit");
            modelAndView.addObject("noteType", noteType);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-noteType")
    public ModelAndView updateNoteType(@ModelAttribute("noteType") NoteType noteType){
        noteTypeService.save(noteType);
        ModelAndView modelAndView = new ModelAndView("/noteType/edit");
        modelAndView.addObject("noteType", noteType);
        modelAndView.addObject("message", "noteType updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-noteType/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        NoteType noteType = noteTypeService.findById(id);
        if(noteType != null) {
            ModelAndView modelAndView = new ModelAndView("/noteType/delete");
            modelAndView.addObject("noteType", noteType);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-noteType/{id}")
    public String deleteNoteType(@PathVariable("id") long id){
        noteTypeService.remove(id);
        return "redirect:/noteTypes";
    }

    @GetMapping("view-noteType/{id}")
    public ModelAndView viewNoteType(@PathVariable("id") Long id){
        NoteType noteType = noteTypeService.findById(id);
        if(noteType == null){
            return new ModelAndView("/error-404");
        }

        Iterable<Note> notes = noteService.findAllByNoteType(noteType);
        ModelAndView modelAndView = new ModelAndView("noteType/view");
        modelAndView.addObject("noteType", noteType);
        modelAndView.addObject("notes", notes);
        return modelAndView;

    }

}
