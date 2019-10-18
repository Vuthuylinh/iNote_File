package linhVu.service;

import linhVu.model.Note;
import linhVu.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface NoteService extends GeneralService<Note> {
    Page<Note> findAllByTitleContaining(String name, Pageable pageable);
    List<Integer> getNumberPage(Page<Note> notes);
    Iterable<Note> findAllByNoteType(NoteType noteType);
    Page<Note> findAllByNoteType(NoteType noteType, Pageable pageable);

    //write file
    void writeJSON();
    void importJSON();

    void importExcel() throws IOException;
File exportExcel() throws IOException;

}
