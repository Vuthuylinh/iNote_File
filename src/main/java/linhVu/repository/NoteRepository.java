package linhVu.repository;

import linhVu.model.Note;
import linhVu.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NoteRepository extends PagingAndSortingRepository<Note,Long> {
    Iterable<Note> findAllByNoteType(NoteType noteType);
    Page<Note> findAllByNoteType(NoteType noteType, Pageable pageable);
    Page<Note> findAllByTitleContaining(String name, Pageable pageable);





}
