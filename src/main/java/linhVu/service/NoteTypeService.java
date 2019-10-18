package linhVu.service;

import linhVu.model.NoteType;

public interface NoteTypeService extends GeneralService<NoteType> {
    Iterable<NoteType> findAll();
}
